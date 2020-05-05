package model.impl;

import model.api.IProduct;
import model.api.ISearchResult;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class SearchResultImplTest {

    @Test
    public void getFinalCost() {

        String clientName = "Ana";
        List<IProduct> productList = new ArrayList<>();
        ProductImpl firstProduct = new ProductImpl();
        ProductImpl secondProduct = new ProductImpl();
        firstProduct.setName("Branzica");
        firstProduct.setPrice(5.0);
        productList.add(firstProduct);
        secondProduct.setPrice(10.0);
        secondProduct.setName("Lapte");
        productList.add(secondProduct);
        ISearchResult searchResult =  new SearchResultImpl(clientName, productList, new ArrayList<>(), new ArrayList<>());
        assertEquals((Double)15.0, searchResult.getFinalCost());
    }
}