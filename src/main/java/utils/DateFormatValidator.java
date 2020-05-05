package utils;

import java.time.format.DateTimeFormatter;

public class DateFormatValidator implements DateValidator {

    private DateTimeFormatter dateFormatter;

    public DateFormatValidator(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean isValid(String dateStr) {
        try {
            this.dateFormatter.parse(dateStr);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
