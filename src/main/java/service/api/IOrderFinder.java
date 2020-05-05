package service.api;
import model.api.IOrder;
import model.api.ISearchResult;


public interface IOrderFinder {

    /**
     * Search the products from order
     * @param order the order of the client
     * @return the result of the search made for the client
     */
    ISearchResult findProducts(IOrder order);
}
