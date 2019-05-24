package com.github.pedruino.financialcontrol;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.github.pedruino.financialcontrol.adapter.WalletAdapter;
import com.github.pedruino.financialcontrol.data.FakeDatabase;
import com.github.pedruino.financialcontrol.model.Wallet;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, WalletAdapter.OnWalletClickListener {
    private static final int ADD_CODE = 0;
    private RecyclerView walletsRecyclerView;
    private Button addWalletButton;
    private List<Wallet> wallets;
    private WalletAdapter walletAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.wallets = FakeDatabase.getInstance().getWallets();
        this.addWalletButton = findViewById(R.id.activity_main_add_wallet_button);
        this.addWalletButton.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.walletAdapter = new WalletAdapter(this.wallets, this);

        this.walletsRecyclerView = findViewById(R.id.activity_main_wallets_recycler_view);
        this.walletsRecyclerView.setAdapter(this.walletAdapter);
        this.walletsRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(View v) {
        if (v == this.addWalletButton) {
            addWallet();
        }
    }

    private void addWallet() {
        Intent intent = new Intent(this, WalletEditActivity.class);
        startActivityForResult(intent, ADD_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_CODE && resultCode == RESULT_OK) {
            Wallet wallet = (Wallet) data.getSerializableExtra(WalletEditActivity.PARAM_WALLET);
            this.wallets.add(wallet);
            this.walletAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onWalletClick(int position) {
        Intent intent = new Intent(this, WalletViewActivity.class);
        intent.putExtra(WalletViewActivity.PARAM_WALLET_INDEX, position);
        startActivity(intent);
    }
}
