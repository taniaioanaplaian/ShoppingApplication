package utils;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DayCheckerTest {

    @Test
    public void isSunday() {
        assertFalse(DayChecker.isSunday(LocalDate.of(2020, 4, 30)));
        assertFalse(DayChecker.isSunday(LocalDate.of(2020, 4, 15)));
        assertTrue(DayChecker.isSunday(LocalDate.of(2020, 4, 26)));
    }
}