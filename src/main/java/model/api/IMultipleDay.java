package model.api;

import java.time.LocalDate;

public interface IMultipleDay {
     /**
      * Set period of promotion
      * @param startDate the first day of the promotion
      * @param finalDate last day of the promotion
      * */
     void setPeriod(LocalDate startDate, LocalDate finalDate);
}
