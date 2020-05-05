package utils;

public interface DateValidator {
        /**
         * Check if given date follows format yyyy-MM-dd
         * @param dateStr the date to be checked
         * @return true if the date is valid, false otherwise
         * */
        boolean isValid(String dateStr);
}
