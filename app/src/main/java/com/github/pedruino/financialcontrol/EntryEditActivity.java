package com.github.pedruino.financialcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.github.pedruino.financialcontrol.model.Entry;
import com.github.pedruino.financialcontrol.model.Expense;
import com.github.pedruino.financialcontrol.model.Income;
import com.github.pedruino.financialcontrol.model.Wallet;

import java.math.BigDecimal;
import java.util.Calendar;

public class EntryEditActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String PARAM_ENTRY = "PARAM_ENTRY";
    private EditText valueEditText;
    private DatePicker dueDatePicker;
    private Button addButton;
    private Wallet wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_edit);

        this.valueEditText = findViewById(R.id.activity_entry_edit_value_edit_text);
        this.dueDatePicker = findViewById(R.id.activity_entry_edit_due_date_picker);

        Intent intent = getIntent();
        this.wallet = (Wallet) intent.getSerializableExtra(WalletViewActivity.PARAM_WALLET);

        this.addButton = findViewById(R.id.activity_entry_edit_add_button);
        this.addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == this.addButton) {
            add();
        }
    }

    private void add() {
        if (validate()) {
            Entry entry;
            double value = Double.parseDouble(valueEditText.getText().toString());
            if (value > 0) {
                entry = new Income(this.wallet.getId(), BigDecimal.valueOf(value), getDateFromDatePicker(dueDatePicker));
            } else {
                entry = new Expense(this.wallet.getId(), BigDecimal.valueOf(value), getDateFromDatePicker(dueDatePicker));
            }
            Intent intent = new Intent();
            intent.putExtra(PARAM_ENTRY, entry);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private boolean validate() {
        if (valueEditText.getText().toString() == null || valueEditText.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    private static java.util.Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }
}
