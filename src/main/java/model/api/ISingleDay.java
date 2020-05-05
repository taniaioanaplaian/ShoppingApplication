package model.api;

import java.time.LocalDate;

public interface ISingleDay {
    /**
     * Set the day of the promotion
     * @param startDate representing the first, and also the last the of the promotion
     * */
    void setStartDate(LocalDate startDate);
}
