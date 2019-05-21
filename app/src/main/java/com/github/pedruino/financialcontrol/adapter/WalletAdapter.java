package com.github.pedruino.financialcontrol.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.pedruino.financialcontrol.R;
import com.github.pedruino.financialcontrol.model.Wallet;

import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletItemViewHolder> {
    private List<Wallet> wallets;
    private OnWalletClickListener onWalletClickListener;

    public WalletAdapter(List<Wallet> wallets, OnWalletClickListener onWalletClickListener) {
        this.wallets = wallets;
        this.onWalletClickListener = onWalletClickListener;
    }

    @NonNull
    @Override
    public WalletItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.row_wallet, viewGroup, false);
        return new WalletItemViewHolder(view, this.onWalletClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletItemViewHolder walletItemViewHolder, int i) {
        walletItemViewHolder.fillWallet(this.wallets.get(i));
    }

    @Override
    public int getItemCount() {
        return this.wallets.size();
    }

    public class WalletItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameTextView;
        private OnWalletClickListener onWalletClickListener;

        public WalletItemViewHolder(@NonNull View itemView, OnWalletClickListener onWalletClickListener) {
            super(itemView);
            this.nameTextView = itemView.findViewById(R.id.row_wallet_name_text_view);
            this.onWalletClickListener = onWalletClickListener;
            this.itemView.setOnClickListener(this);
        }

        private void fillWallet(Wallet wallet) {
            this.nameTextView.setText(wallet.getName());
        }

        @Override
        public void onClick(View v) {
            onWalletClickListener.onWalletClick(getAdapterPosition());
        }
    }

    public interface OnWalletClickListener {
        void onWalletClick(int position);
    }
}
