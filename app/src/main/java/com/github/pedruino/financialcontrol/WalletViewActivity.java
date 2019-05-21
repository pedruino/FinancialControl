package com.github.pedruino.financialcontrol;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.pedruino.financialcontrol.adapter.EntryAdapter;
import com.github.pedruino.financialcontrol.data.FakeDatabase;
import com.github.pedruino.financialcontrol.model.Entry;
import com.github.pedruino.financialcontrol.model.Wallet;
import com.github.pedruino.financialcontrol.util.Helper;

import java.util.List;

public class WalletViewActivity extends AppCompatActivity implements View.OnClickListener, EntryAdapter.OnEntryClickListener {
    public static final String PARAM_WALLET_INDEX = "PARAM_WALLET_INDEX";
    public static final String PARAM_WALLET = "PARAM_WALLET";
    private static final int ADD_CODE = 0;
    private RecyclerView entriesRecyclerView;
    private Button addEntryButton;
    private TextView summaryViewText;
    private EntryAdapter entryAdapter;
    private List<Entry> entries;
    private Wallet wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_view);

        Intent intent = getIntent();

        this.wallet = (Wallet) intent.getSerializableExtra(PARAM_WALLET);
        final int walletIndex = intent.getIntExtra(PARAM_WALLET_INDEX, 0);
        this.wallet.getEntries().addAll(FakeDatabase.getInstance().getWallets().get(walletIndex).getEntries());
        this.entries = FakeDatabase.getInstance().getWallets().get(walletIndex).getEntries();

        this.addEntryButton = findViewById(R.id.activity_wallet_view_create_entry_button);
        this.addEntryButton.setOnClickListener(this);

        this.summaryViewText = findViewById(R.id.activity_wallet_view_summary_text_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        this.entryAdapter = new EntryAdapter(this.entries, this);

        this.entriesRecyclerView = findViewById(R.id.activity_wallet_view_entries_recycler_view);
        this.entriesRecyclerView.setAdapter(this.entryAdapter);
        this.entriesRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(View v) {
        if (v == addEntryButton) {
            addEntry();
        }
    }

    private void addEntry() {
        Intent intent = new Intent(this, EntryEditActivity.class);
        intent.putExtra(PARAM_WALLET, this.wallet);
        startActivityForResult(intent, ADD_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_CODE && resultCode == RESULT_OK) {
            Entry entry = (Entry) data.getSerializableExtra(EntryEditActivity.PARAM_ENTRY);
            this.entries.add(entry);
            this.entryAdapter.notifyDataSetChanged();

            String balance = new StringBuilder().append(this.getResources().getString(R.string.total)).append(Helper.currencyFormat(this.wallet.getBalance())).toString();
            this.summaryViewText.setText(balance);
        }
    }


    @Override
    public void onEntryClick(int position) {
        //TODO: Implement edition

//        Intent intent = new Intent(this, EntryEditActivity.class);
//        intent.putExtra(EntryEditActivity.PARAM_ENTRY, this.entries.get(position));
//        startActivity(intent);
    }
}
