package model.impl;

import model.api.IProduct;
import java.text.DecimalFormat;

public class ProductImpl implements IProduct {

    private String name;
    private Double price;
    private DecimalFormat df2 = new DecimalFormat("#.##");

    public ProductImpl(){
        this.price = 0.0;
    }

    public ProductImpl(String name, Double price){
        this.price =price;
        this.name=name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + df2.format(price) +
                '}';
    }
}
