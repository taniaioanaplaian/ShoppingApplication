package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class DayChecker {
    /**
     * This method checks whether the given date is Sunday
     * @param date date to check, cannot be null
     * @return true if is Sunday, false otherwise
     */
    public static boolean isSunday(LocalDate date) {
        DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return dayOfWeek == DayOfWeek.SUNDAY;
    }
}
