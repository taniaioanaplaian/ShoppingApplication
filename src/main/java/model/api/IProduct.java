package model.api;

public interface IProduct {
    /**
     * Get product's name
     * @return name of the product
     * */
    String getName();

    /**
     * Set products's name
     * @param name the name to be set
     * */
    void setName(String name);


    /**
     * Get product's price
     * @return price value of the product
     * */
    Double getPrice();


    /**
     * Set product's price
     * @param price the value to be set
     * */
    void setPrice(Double price);
}
