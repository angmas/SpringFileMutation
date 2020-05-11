package com.angmas.mutation.common;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateCalculatorTest {

    @Test
    void dates_05052005_04012007_returns_1() {
        Integer actual = 1;
        LocalDate olderDate = new LocalDate("2005-05-05");
        LocalDate newerDate = new LocalDate("2007-04-01");
        assertEquals(actual, DateCalculator.getDifferenceInYears(olderDate, newerDate));
    }

    @Test
    void dates_05052005_05052007_returns_2() {
        Integer actual = 2;
        LocalDate olderDate = new LocalDate("2005-05-05");
        LocalDate newerDate = new LocalDate("2007-05-05");
        assertEquals(actual, DateCalculator.getDifferenceInYears(olderDate, newerDate));
    }

    @Test
    void dates_05052005_null_returns_null() {
        LocalDate olderDate = new LocalDate("2005-05-05");
        LocalDate newerDate = null;
        assertNull(DateCalculator.getDifferenceInYears(olderDate, newerDate));
    }

    @Test
    void dates_null_05052007_returns_null() {
        LocalDate olderDate = null;
        LocalDate newerDate = new LocalDate("2007-05-05");
        assertNull(DateCalculator.getDifferenceInYears(olderDate, newerDate));
    }

    @Test
    void dates_05052006_05052007_returns_minus1() {
        Integer actual = -1;
        LocalDate olderDate = new LocalDate("2007-05-05");
        LocalDate newerDate = new LocalDate("2006-05-05");
        assertEquals(actual, DateCalculator.getDifferenceInYears(olderDate, newerDate));
    }

    @Test
    void dates_05052005_05052005_returns_0() {
        Integer actual = 0;
        LocalDate olderDate = new LocalDate("2005-05-05");
        LocalDate newerDate = new LocalDate("2005-05-05");
        assertEquals(actual, DateCalculator.getDifferenceInYears(olderDate, newerDate));
    }
}