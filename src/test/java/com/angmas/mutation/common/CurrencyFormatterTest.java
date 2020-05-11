package com.angmas.mutation.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CurrencyFormatterTest {

    @Test
    void emptyString_ReturnsNull() {
        assertNull(CurrencyFormatter.formatStringToNumber(""));

    }

    @Test
    void nonNumeric_ReturnsNull() {
        assertNull(CurrencyFormatter.formatStringToNumber("abc"));
    }

    @Test
    void null_ReturnsNull() {
        assertNull(CurrencyFormatter.formatStringToNumber(null));
    }

    @Test
    void number_0_Returns_0() {
        assertEquals("0.00", CurrencyFormatter.formatStringToNumber("0").toString());
    }

    @Test
    void number_dot99_Returns_0dot99() {
        assertEquals("0.99", CurrencyFormatter.formatStringToNumber(".99").toString());
    }

    @Test
    void number_dot_Returns_null() {
        Assertions.assertNull(CurrencyFormatter.formatStringToNumber("."));
    }

    @Test
    void number_10_Returns_10() {
        assertEquals("10.00", CurrencyFormatter.formatStringToNumber("10").toString());
    }

    @Test
    void minus_10_Returns_minus10() {
        assertEquals("-10.00", CurrencyFormatter.formatStringToNumber("-10").toString());
    }

    @Test
    void number_300_Returns_300dot00() {
        assertEquals("300.00", CurrencyFormatter.formatStringToNumber("300").toString());
    }
}