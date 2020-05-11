package com.angmas.mutation.common;

import org.joda.time.LocalDate;

public class AgeCalculator {
    public static Integer getDifferenceInYears(LocalDate birthDate) {
        Integer age = null;

        if (birthDate != null ) {
            age = DateCalculator.getDifferenceInYears(birthDate, LocalDate.now());

            if (age != null && age <= 0) {
                age = null;
            }
        }

        return age;
    }
}
