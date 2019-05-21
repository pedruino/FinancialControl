package com.github.pedruino.financialcontrol.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.pedruino.financialcontrol.R;
import com.github.pedruino.financialcontrol.model.Entry;
import com.github.pedruino.financialcontrol.util.Helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryItemViewHolder> {
    private List<Entry> entries;
    private OnEntryClickListener onEntryClickListener;

    public EntryAdapter(List<Entry> entries, OnEntryClickListener onEntryClickListener) {
        this.entries = entries;
        this.onEntryClickListener = onEntryClickListener;
    }

    @NonNull
    @Override
    public EntryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_entry, viewGroup, false);
        return new EntryItemViewHolder(view, this.onEntryClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EntryItemViewHolder walletItemViewHolder, int i) {
        walletItemViewHolder.fillEntry(this.entries.get(i));
    }

    @Override
    public int getItemCount() {
        return this.entries.size();
    }

    public class EntryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView valueTextView;
        private TextView dateTextView;
        private OnEntryClickListener onEntryClickListener;

        public EntryItemViewHolder(@NonNull View itemView, OnEntryClickListener onEntryClickListener) {
            super(itemView);
            this.valueTextView = itemView.findViewById(R.id.row_entry_value_text_view);
            this.dateTextView = itemView.findViewById(R.id.row_entry_date_text_view);

            this.onEntryClickListener = onEntryClickListener;
            this.itemView.setOnClickListener(this);
        }

        private void fillEntry(Entry entry) {
            this.valueTextView.setText(Helper.currencyFormat(entry.getValue()));
            this.dateTextView.setText(formatDate(entry.getDueDate()));
        }

        private String formatDate(Date date) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormatter.format(date);
        }

        @Override
        public void onClick(View v) {
            onEntryClickListener.onEntryClick(getAdapterPosition());
        }
    }

    public interface OnEntryClickListener {
        void onEntryClick(int position);
    }
}
