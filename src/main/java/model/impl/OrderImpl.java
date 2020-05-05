package model.impl;

import model.api.IClient;
import model.api.IOrder;
import model.api.IProduct;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderImpl implements IOrder {

    private  IClient client;
    private  LocalDate orderDate;
    private List<IProduct> products;


    public OrderImpl() {
        products = new ArrayList<>();
    }

    @Override
    public IClient getClient() {
        return client;
    }


    @Override
    public void setClient(IClient client) {
        this.client = client;
    }

    @Override
    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Override
    public void setOrderDate(LocalDate date) {
        this.orderDate = date;
    }
    @Override
    public void addProduct(IProduct product) {
        this.products.add(product);
    }

    @Override
    public void addAllProducts(List<IProduct> products) {
        this.products = products;
    }

    @Override
    public List<IProduct> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "IOrderImpl{" +
                "client=" + client.getName() +
                ", orderDate=" + orderDate +
                ", products=" + products.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderImpl order = (OrderImpl) o;
        return client.equals(order.client) &&
                orderDate.equals(order.orderDate) &&
                products.equals(order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, orderDate, products);
    }
}
