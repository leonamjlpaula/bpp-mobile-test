package br.com.leonam.bppmbiletest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import br.com.leonam.bppmbiletest.objects.Transaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<Transaction> transactions;

    TransactionAdapter(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Transaction transaction = transactions.get(position);

        holder.tvMerchant.setText(transaction.getMerchantName());
        holder.tvValue.setText("$" + transaction.getBillingAmount() + " " + transaction.getBillingCurrency());
        holder.tvDate.setText(changeDateFormat(transaction.getTransactionDate()));
        holder.tvDescription.setText(transaction.getMccDescription());

    }

    private String changeDateFormat(String oldDateString){

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy hh:mm:ss", Locale.getDefault());
        Date d;
        try {
            d = sdf.parse(oldDateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return oldDateString;
        }
        sdf.applyPattern("dd/MM/yyyy hh:mm");
        return sdf.format(d);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_adapter_merchant)
        TextView tvMerchant;

        @BindView(R.id.tv_adapter_value)
        TextView tvValue;

        @BindView(R.id.tv_adapter_date)
        TextView tvDate;

        @BindView(R.id.tv_adapter_description)
        TextView tvDescription;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
