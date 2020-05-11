package com.angmas.mutation.common;

import java.math.BigDecimal;

public class CurrencyFormatter {

    public static BigDecimal formatStringToNumber(String string) {
        BigDecimal bigDecimalamt;
        try {
            bigDecimalamt = new BigDecimal(string).setScale(2);
        } catch (NumberFormatException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
        return bigDecimalamt;
    }


}
