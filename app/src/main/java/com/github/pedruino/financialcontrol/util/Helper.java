package com.github.pedruino.financialcontrol.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Helper {
    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }
}
