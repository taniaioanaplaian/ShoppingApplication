package model.api;

import java.util.List;

public interface IStore {

    /**
     * Check if the store is open on Sunday
     * @return true if store is open, false otherwise
     *
     * */
    boolean isOpen();

    /**
     * Make store open/closed on Sunday
     * @param open true if store is open, false otherwise
     * */
    void setOpen(boolean open);


    /**
     * Set store's name
     * @param storeName string value representing store's name
     * */
    void setName(String storeName);

    /**
     * Get store's name
     * @return stringValue representing store's name
     * */
    String getName();


    /**
     * Get all products which are not in any promotion
     * @return list of products
     * */
    List<IProduct> getProducts();

    /**
     * Add a new product in store
     * @param product  to be added
     * */
    void addProduct(IProduct product);

    /**
     * Get all promotions in the stores
     * @return list containing the promotions
     * */
    List<IPromotion> getPromotions();

    /**
     * Add a new promotion to the store
     * @param promotion the promotion to be added
     * */
    void addPromotion(IPromotion promotion);

}
