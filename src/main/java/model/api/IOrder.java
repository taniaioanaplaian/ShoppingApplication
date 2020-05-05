package model.api;

import java.time.LocalDate;
import java.util.List;

public interface IOrder{

    /**
     * Returns the client who made the order
     * @return client
     * */
    IClient getClient();

    /**
     * Return date when the order was put
     * @return date
     */
    LocalDate getOrderDate();

    /**
     * Returns all products the client ordered
     * @return list containing the products
     * */
    List<IProduct> getProducts();

    /**
     * Set the client who made the order
     * @param client the client to be set
     * */
    void setClient(IClient client);

   /**
    * Set the date when the order was placed
    * @param date the date to bet set
    * */
    void setOrderDate(LocalDate date);

   /**
    * Add a new product to order
    * @param product the product to be added
    * */
    void addProduct(IProduct product);


    void addAllProducts(List<IProduct> products);
}
