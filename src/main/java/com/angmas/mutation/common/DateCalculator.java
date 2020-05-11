package com.angmas.mutation.common;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class DateCalculator {
    public static Integer getDifferenceInYears(LocalDate olderDate, LocalDate newerDate) {
        Integer yearDifference = null;

        if (olderDate != null && newerDate != null)
            try {
                Years years = Years.yearsBetween(olderDate, newerDate);
                yearDifference = years.getYears();
            } catch (IllegalArgumentException e){
                return null;
            }
        return yearDifference;
    }
}
