package com.github.pedruino.financialcontrol.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public abstract class Entry implements Serializable {
    private final UUID id;
    private final UUID walletId;
    private BigDecimal value;
    private Date dueDate;

    protected Entry(UUID walletId, BigDecimal value, Date dueDate) {
        this.id = UUID.randomUUID();
        this.walletId = walletId;
        this.value = value;
        this.dueDate = dueDate;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Date getDueDate() {
        return dueDate;
    }

    @Override
    public boolean equals(Object obj) {
        Entry other = (Entry) obj;
        return this.id.equals(other.id) && this.walletId.equals(other.walletId);
    }
}
