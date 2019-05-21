package com.github.pedruino.financialcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.pedruino.financialcontrol.model.Wallet;

public class WalletEditActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PARAM_WALLET = "PARAM_ENTRY";
    private EditText nameEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_edit);

        this.nameEditText = findViewById(R.id.activity_wallet_edit_name_edit_text);
        this.addButton = findViewById(R.id.activity_wallet_edit_add_button);
        this.addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == this.addButton) {
            add();
        }
    }

    private void add() {
        if (this.nameEditText.getText().toString() == null || this.nameEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, R.string.error_field_required_wallet_name, Toast.LENGTH_SHORT).show();
        } else {
            Wallet wallet = new Wallet(nameEditText.getText().toString());
            Intent intent = new Intent();
            intent.putExtra(PARAM_WALLET, wallet);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
