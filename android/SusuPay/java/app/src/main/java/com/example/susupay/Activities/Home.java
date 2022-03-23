package com.example.susupay.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.susupay.R;

public class Home extends AppCompatActivity {
    ImageButton deposit, withdraw, transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        deposit = findViewById(R.id.deposit_img_btn);
        withdraw = findViewById(R.id.withdraw_img_btn);
        transaction = findViewById(R.id.trx_img_btn);

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Deposit.class);
                startActivity(intent);

            }
        });

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Withdraw.class);
                startActivity(intent);
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,Transaction.class);
                startActivity(intent);
            }
        });


    }
}