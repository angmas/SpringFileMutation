package com.angmas.mutation.common;

import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgeCalculatorTest {



    @Test
    void date_01012005_returns_15() {
        LocalDate birthDate = new LocalDate("2005-01-01");
        Integer actual = 15;
        assertEquals(actual, AgeCalculator.getDifferenceInYears(birthDate));
    }

    @Test
    void null_returns_null() {
        LocalDate birthDate = null;

        assertNull(AgeCalculator.getDifferenceInYears(birthDate));
    }

    @Test
    void futureDate_returns_null() {
        LocalDate birthDate = new LocalDate("3005-01-01");

        assertNull(AgeCalculator.getDifferenceInYears(birthDate));
    }

    @Test
    void todaysDate_returns_null() {
        LocalDate birthDate = LocalDate.now();

        assertNull(AgeCalculator.getDifferenceInYears(birthDate));
    }
}