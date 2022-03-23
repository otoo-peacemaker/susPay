package com.example.susupay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.susupay.Adapters.TransactionRecyclerAdapter;
import com.example.susupay.Model.TransactionDetails;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TransactionHistory extends AppCompatActivity {
    private List<TransactionDetails> detailsList;
    private TransactionRecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);


        recyclerView = findViewById(R.id.recycler_transaction_history);
        recyclerAdapter = new TransactionRecyclerAdapter(detailsList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);

        loadTransactionDetails();


    }

    private void loadTransactionDetails() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(new Date());

        TransactionDetails details = new TransactionDetails(R.drawable.app_logo,"Deposit",200.00,date);
        detailsList.add(details);
        details = new TransactionDetails(R.drawable.app_logo,"Deposit",200.00,date);
        detailsList.add(details);
        details = new TransactionDetails(R.drawable.app_logo,"Deposit",200.00,date);
        detailsList.add(details);
        details = new TransactionDetails(R.drawable.app_logo,"Deposit",200.00,date);
        detailsList.add(details);
        details = new TransactionDetails(R.drawable.app_logo,"Deposit",200.00,date);
        detailsList.add(details);
        details = new TransactionDetails(R.drawable.app_logo,"Deposit",200.00,date);
        detailsList.add(details);
        details = new TransactionDetails(R.drawable.app_logo,"Deposit",200.00,date);
        detailsList.add(details);

    }
}