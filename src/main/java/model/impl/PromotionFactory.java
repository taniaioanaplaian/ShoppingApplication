package model.impl;

import model.api.IPromotion;
import model.enumeration.PromotionType;

public class PromotionFactory {

    public static IPromotion createPromotion(PromotionType type){
        switch (type){
            case SINGLE_DAY:
                return new SingleDayPromotion();
            case MULTIPLE_DAY:
                return new MultipleDayPromotion();
            default: return null;
        }

    }

}
