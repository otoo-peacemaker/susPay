package com.example.susupay.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.susupay.Adapters.TransactionRecyclerAdapter;
import com.example.susupay.Model.TransactionDetails;
import com.example.susupay.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Transaction extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);




    }


}