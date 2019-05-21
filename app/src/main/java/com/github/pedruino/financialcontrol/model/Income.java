package com.github.pedruino.financialcontrol.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Income extends Entry {
    public Income(UUID walletId, BigDecimal value, Date date) {
        super(walletId, value, date);
    }
}
