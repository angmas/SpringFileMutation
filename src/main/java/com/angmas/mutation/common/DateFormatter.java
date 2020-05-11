package com.angmas.mutation.common;


import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateFormatter {

    public static LocalDate formatStringToDate(String string) {
        DateTimeFormatter dateFormatAllDigits =  ISODateTimeFormat.basicDate();
        LocalDate date;
        string = string.replaceAll("\\-", "");
        try {
            date = LocalDate.parse(string, dateFormatAllDigits);
        } catch (IllegalArgumentException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }

        return date;
    }
}
