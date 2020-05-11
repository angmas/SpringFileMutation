package com.angmas.mutation.common;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


class DateFormatterTest {

    @Test
    void string_20190228_dash_returns_date() {
        assertEquals("2019-02-28", DateFormatter.formatStringToDate("2019-02-28").toString());
    }

    @Test
    void string_20190228_nodash_returns_date() {
        assertEquals("2019-02-28", DateFormatter.formatStringToDate("20190228").toString());
    }

    @Test
    void string_20190230_nodash_returns_null() {
        assertNull(DateFormatter.formatStringToDate("20190230"));
    }



    @Test
    void string_99999999_returns_null() {
        assertNull(DateFormatter.formatStringToDate("99999999"));
    }

    @Test
    void null_returns_null() {
        assertNull(DateFormatter.formatStringToDate("99999999"));
    }

    @Test
    void string_0_returns_null() {
        assertNull(DateFormatter.formatStringToDate("0"));
    }

    @Test
    void string_00000000_returns_null() {
        assertNull(DateFormatter.formatStringToDate("00000000"));
    }

    @Test
    void timestamp_returns_null() {
        assertNull(DateFormatter.formatStringToDate("2019-10-24T18:16:44"));
    }
}