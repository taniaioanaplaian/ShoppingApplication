package model.api;
import java.time.LocalDate;
import java.util.List;

public interface IPromotion extends Comparable<IPromotion> {

     /**
      * Add a product to promotion
      * @param product to be added
      * */
     void addProduct(IProduct product);

     /**
      * Get products in promotion
      * @return list containing the products
      * */
     List<IProduct> getProducts();

     /**
      * Get number of days the promotion lasts
      * @return long value representing the number of days
      * */
     long getNumberOfDays();

     /**
      * Get the last day of the promotion
      * @return local date in format yyyy-MM-dd
      * */
     LocalDate getEndDay();

     /**
      * Get the first day of the promotion
      * @return first day
      * */
     LocalDate getStartDay();

     /**
      * Overriden method used to compare two promotions
      * @param o2 the promotion this compares to
      * */
     @Override
     default  int compareTo(IPromotion o2){
          return Long.compare(this.getNumberOfDays(), o2.getNumberOfDays());
     }
}
