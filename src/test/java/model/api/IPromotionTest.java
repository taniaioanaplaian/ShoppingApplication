package model.api;

import model.impl.MultipleDayPromotion;
import model.impl.SingleDayPromotion;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class IPromotionTest {

    @Test
    public void compare() {

        MultipleDayPromotion promotion =  new MultipleDayPromotion();
        LocalDate dateBefore = LocalDate.of(2020, Month.MAY, 24);
        LocalDate dateAfter = LocalDate.of(2020, Month.JULY, 29);
        promotion.setPeriod(dateBefore, dateAfter);
        MultipleDayPromotion secondPromotion = new MultipleDayPromotion();
        LocalDate dateBefore2 = LocalDate.of(2020, Month.MAY, 27);
        LocalDate dateAfter2 = LocalDate.of(2020, Month.JULY, 29);
        secondPromotion.setPeriod(dateBefore2, dateAfter2);
        assertEquals(1, promotion.compareTo(secondPromotion));
        assertEquals(-1, secondPromotion.compareTo(promotion));
        ArrayList<IPromotion> promotions = new ArrayList<>();
        promotions.add(promotion);
        promotions.add(secondPromotion);
        SingleDayPromotion promotion1 =  new SingleDayPromotion();
        promotion1.setStartDate(LocalDate.now());
        promotions.add(promotion1);
        Collections.sort(promotions);
        System.out.println(promotions.toString());
    }
}