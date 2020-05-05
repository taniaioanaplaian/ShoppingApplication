package model.impl;

import model.api.IProduct;
import model.api.IPromotion;
import model.api.IStore;

import java.util.ArrayList;
import java.util.List;

public class StoreImpl implements IStore {

    private String name;
    private boolean isOpen;
    private List<IProduct> products;
    private List<IPromotion> promotions;

    public StoreImpl(){
        isOpen = false;
        this.products = new ArrayList<>();
        this.promotions = new ArrayList<>();
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public void setOpen(boolean open) {
        this.isOpen = open;
    }

    @Override
    public void setName(String storeName) {
        this.name  =storeName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<IProduct> getProducts() {
        return products;
    }

    @Override
    public void addProduct(IProduct product) {
        products.add(product);
    }

    @Override
    public List<IPromotion> getPromotions() {
        return promotions;
    }

    @Override
    public void addPromotion(IPromotion promotion) {
        promotions.add(promotion);
    }

}
