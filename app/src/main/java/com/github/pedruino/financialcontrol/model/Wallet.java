package com.github.pedruino.financialcontrol.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Wallet implements Serializable {
    private final UUID id;
    private String name;
    private List<Entry> entries;

    public Wallet(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.entries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((Wallet) obj).getId());
    }

    public BigDecimal getBalance() {
        BigDecimal balance = new BigDecimal(0);
        for (Entry e : this.entries) {
            balance = balance.add(e.getValue());
        }
        return balance;
    }
}
