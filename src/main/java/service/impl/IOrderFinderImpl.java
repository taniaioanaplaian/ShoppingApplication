package service.impl;
import model.api.*;
import model.impl.SearchResultImpl;
import service.api.IOrderFinder;
import utils.DayChecker;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class IOrderFinderImpl implements IOrderFinder {

    private  final List<IStore> stores;
    public IOrderFinderImpl(List<IStore> stores) {
        this.stores = stores;
    }

    @Override
    public ISearchResult findProducts(IOrder order) {

            //get client's choices for shopping sorted by priority
            Map<Integer, String> sorted = getClientStores(order);

            //sort all the stores acording to client's choice
            Map<Integer, IStore> availableStores = getOrderedStores(sorted, order.getOrderDate());

            List<IStore> currStore =  new ArrayList<>();
            List<IProduct> foundProducts = new ArrayList<>();

            //find all products in promotions
            getPromotionProducts(order, availableStores, foundProducts, currStore);
            //find all products with full price
            getFullyPricedProducts(order, availableStores, foundProducts, currStore);
            //find all products which are not found in any store
            List<IProduct> notFound  = notFound(order, foundProducts);

            return new SearchResultImpl(order.getClient().getName(), foundProducts, notFound, currStore);
    }

    /*
    *Search all products from order which are not in any promotion
    * */

    private void getFullyPricedProducts(IOrder order, Map<Integer, IStore> availableStores, List<IProduct> foundProducts, List<IStore> currStore) {

        for(IProduct product : order.getProducts()){
            //if not found in promotion
            if(findProductByName(foundProducts, product.getName()) == null){

                for(IStore currentStore : availableStores.values()){

                    //search only the products from order
                    List<IProduct> storeProducts = currentStore.getProducts()
                            .stream()
                            .filter(e -> findProductByName(foundProducts, e.getName()) == null)
                            .collect(Collectors.toList());

                    IProduct currentProduct = findProductByName(storeProducts, product.getName());
                    //if not found already
                    if(currentProduct != null){
                        foundProducts.add(currentProduct);
                        currStore.add(currentStore);
                    }
                }
            }
        }
    }

    /*
    * Search each product from the order in all promotions of every store
    * */
    private void getPromotionProducts(IOrder order, Map<Integer, IStore> availableStores, List<IProduct> foundProducts, List<IStore> currStore) {

        //for each product
        for(IProduct iProduct: order.getProducts()){
            //in each store
            for(IStore currentStore :  availableStores.values()){

                List<IPromotion> promotions = getAvailablePromotions(currentStore.getPromotions(), order);
                    //search in each promotion
                    for (IPromotion promotion : promotions) {

                        //product searched
                        IProduct currentPromotionProduct = findProductByName(promotion.getProducts(), iProduct.getName());
                        //found the product in promotion
                        if (currentPromotionProduct != null) {

                            IProduct prevPromotionProduct = findProductByName(foundProducts, iProduct.getName());
                            //if the product was not found yet, shop it
                            if (prevPromotionProduct == null) {
                                foundProducts.add(currentPromotionProduct);
                                //save the store where the product was found
                                currStore.add(currentStore);
                            }

                        }
                }
            }
        }
    }


    /*
    * Find all products from order which are not found at any store
    * */
    public List<IProduct> notFound(IOrder order, java.util.List<IProduct> found)
    {
        List<IProduct> notFound = new ArrayList<>();
        for(IProduct product : order.getProducts()){
            if(this.findProductByName(found,product.getName()) == null){
               notFound.add(product);
            }
        }
        return notFound;
    }


    /*
    * Select all promotions available for date the order was placed and sort them  by number of days
    *
    * */

    private List<IPromotion> getAvailablePromotions(List<IPromotion> promotions, IOrder order){
        LocalDate orderDate = order.getOrderDate();
        List<IPromotion> available = new ArrayList<>();
        for(IPromotion promotion : promotions){
            if((promotion.getStartDay().isBefore(orderDate) || promotion.getStartDay().equals(orderDate) )
                    && promotion.getEndDay().isAfter(orderDate) || promotion.getEndDay().equals(orderDate))
                available.add(promotion);
        }
        Collections.sort(available);
        return available;
    }

    /*
    * Get client's choices for shopping and sort them according to their priority
    * */
    private Map<Integer, String> getClientStores(IOrder order) {
        Map<Integer, String> clientStores = order.getClient().getStorePriority() ;
        Map<Integer, String> sorted = new HashMap<>();
        clientStores.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        return sorted;
    }

    /*
    * Order the stores in the system according to the order's client priority and the date of order
    * If store is closed when the order was placed, the store will not be taken into consideration
    * */
    private Map<Integer, IStore> getOrderedStores(Map<Integer, String> sorted, LocalDate orderDate) {

        Map<Integer, IStore> availableStores = new HashMap<>();
        for(IStore currentStore : this.stores){
            if(sorted.containsValue(currentStore.getName())){
                //check if open
                if((DayChecker.isSunday(orderDate) && currentStore.isOpen()) || !DayChecker.isSunday(orderDate)) {
                    int priority = findPriority(sorted, currentStore.getName());
                    availableStores.put(priority, currentStore);
                }
            }
        }
        return availableStores;
    }


    /*
    *Get priority of store, according to current order
    */

    private int findPriority(Map<Integer, String> sorted, String name){
        for (Map.Entry<Integer, String> entry : sorted.entrySet()) {
            if (entry.getValue().equals(name)) {
                return entry.getKey();
            }
        }
        return 0;
    }


    /**
     * Check if a product is found in the list
     * @param products the list with products
     * @param productName name of the searched product
     * @return product if the product was found
     * */

    private IProduct findProductByName(List<IProduct> products, String productName) {
        return products.stream().filter(product -> productName.equals(product.getName())).findFirst().orElse(null);
    }

}
