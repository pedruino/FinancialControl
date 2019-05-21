package com.github.pedruino.financialcontrol.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Expense extends Entry {
    public Expense(UUID walletId, BigDecimal value, Date date) {
        super(walletId, value, date);
    }
}
