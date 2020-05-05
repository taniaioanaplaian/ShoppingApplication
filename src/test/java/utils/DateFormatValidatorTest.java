package utils;

import org.junit.Test;

import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Locale;

import static org.junit.Assert.*;

public class DateFormatValidatorTest {

    @Test
    public void isValid() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd").withResolverStyle(ResolverStyle.STRICT);
        DateValidator validator = new DateFormatValidator(dateFormatter);
        assertTrue(validator.isValid("2019-02-28"));
        assertFalse(validator.isValid("2019-02-30"));
    }
}