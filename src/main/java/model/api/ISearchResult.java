package model.api;

import java.util.List;

public interface ISearchResult  {
    /**
     * The client name for which the search was made
     */
    String getClientName();

    /**
     * The descriptions of the products found for the client
     * @return list of products representing all products found at stores
     */
    List<IProduct> getOrderResult();

    /**
     * The stores for each product found
     * @return list of stores from where the products are bought
     * */
    List<IStore> getStores();

    /**
     * Final cost of all products found
     * @return double value representing the cost of all products from order
     * */
    Double getFinalCost();


    /**
     * Get all the products not found from the order
     * @return list rof products
     * */
    List<IProduct> getNotFound();
}
