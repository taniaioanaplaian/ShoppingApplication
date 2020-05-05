package model.impl;

import model.api.IMultipleDay;
import model.api.IProduct;
import model.api.IPromotion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MultipleDayPromotion  implements IPromotion,  IMultipleDay  {

    private LocalDate startDay;
    private List<IProduct> products;
    private LocalDate endDay;

    public MultipleDayPromotion(){
        products = new ArrayList<>();
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    @Override
    public LocalDate getStartDay() {
        return this.startDay;
    }

    @Override
    public void setPeriod(LocalDate startDate, LocalDate finalDate) {
        this.endDay = finalDate;
        this.startDay = startDate;
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
        return  ChronoUnit.DAYS.between(startDay, endDay);
    }


    @Override
    public String toString() {
        return "MultipleDayPromotion{" +
                "startDate=" + startDay +
                "endDate=" + endDay +
                ", products=" + products.toString() +
                '}';
    }

}
