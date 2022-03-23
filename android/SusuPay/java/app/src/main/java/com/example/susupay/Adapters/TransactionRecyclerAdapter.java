package com.example.susupay.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.susupay.Model.TransactionDetails;
import com.example.susupay.R;

import java.util.List;

public class TransactionRecyclerAdapter extends RecyclerView.Adapter<TransactionRecyclerAdapter.MyTransactionViewHolder> {

    List<TransactionDetails>transactionList;
   public TransactionRecyclerAdapter(List<TransactionDetails>transactionList){
        this.transactionList = transactionList;
   }

    @NonNull
    @Override
    public MyTransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_history_list,
                        parent,false);
        return new MyTransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTransactionViewHolder holder, int position) {
        TransactionDetails transactionClass = transactionList.get(position);
        holder.amount.setText((int) transactionClass.getTransaction_amount());
        holder.imageView.setImageResource(transactionClass.getImageView());
        holder.transaction_mode.setText(transactionClass.getTransaction_mode());
        holder.date.setText(transactionClass.getTransaction_date());

        // in this method we are calling an intent and passing data to that
        // intent for adding a new transaction.


    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class MyTransactionViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView transaction_mode, amount, date;

        public MyTransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.transaction_imageView);
            transaction_mode = itemView.findViewById(R.id.transaction_mode);
            amount = itemView.findViewById(R.id.transaction_amount);
            date = itemView.findViewById(R.id.transaction_date);

        }
    }
}
