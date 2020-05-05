package model.impl;
import model.api.IProduct;
import model.api.IPromotion;
import model.api.ISingleDay;
import java.util.*;
import java.time.LocalDate;


public class SingleDayPromotion implements IPromotion, ISingleDay{

    private LocalDate startDay;
    private List<IProduct> products;

    public SingleDayPromotion(){
        this.products = new ArrayList<>();
    }

    @Override
    public void addProduct(IProduct product) {
        this.products.add(product);
    }

    @Override
    public List<IProduct> getProducts() {
        return products;
    }


    @Override
    public long getNumberOfDays() {
        return 1;
    }

    @Override
    public LocalDate getEndDay() {
        return startDay;
    }


    @Override
    public LocalDate getStartDay() {
        return this.startDay;
    }
    @Override
    public void setStartDate(LocalDate startDate) {
        this.startDay = startDate;
    }

    @Override
    public String toString() {
        return "SingleDayPromotion{" +
                "promotionDate=" + this.startDay +
                ", products=" + products.toString() +
                '}';
    }


}
