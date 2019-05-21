package com.github.pedruino.financialcontrol.data;

import com.github.pedruino.financialcontrol.model.Entry;
import com.github.pedruino.financialcontrol.model.Wallet;

import java.util.ArrayList;
import java.util.List;

public class FakeDatabase {
    private static FakeDatabase instance;
    private final List<Wallet> wallets;

    private FakeDatabase() {
        this.wallets = new ArrayList<>();
    }

    public static FakeDatabase getInstance() {
        if (instance == null) {
            instance = new FakeDatabase();
        }
        return instance;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }
}
