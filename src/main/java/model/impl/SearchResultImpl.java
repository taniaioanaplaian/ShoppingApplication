package model.impl;

import model.api.IProduct;
import model.api.ISearchResult;
import model.api.IStore;

import java.util.List;

public class SearchResultImpl implements ISearchResult {

    private  String clientName;
    private  List<IProduct> products;
    private  List<IProduct> notFound;
    private List<IStore> stores;


    public SearchResultImpl(String clientName, List<IProduct> products,List<IProduct> notFound,  List<IStore> store) {
        this.clientName = clientName;
        this.products = products;
        this.stores = store;
        this.notFound = notFound;
    }


    @Override
    public String getClientName() {
        return this.clientName;
    }

    @Override
    public List<IProduct> getOrderResult() {
        return this.products;
    }

    @Override
    public List<IStore> getStores() {
        return this.stores;
    }

    @Override
    public Double getFinalCost() {
        return products.stream()
                .map(IProduct::getPrice).mapToDouble(Double::doubleValue).sum();
    }

    @Override
    public List<IProduct> getNotFound() {
        return this.notFound;
    }

}
