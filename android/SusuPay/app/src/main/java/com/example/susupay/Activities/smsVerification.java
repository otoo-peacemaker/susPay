package com.example.susupay.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.susupay.R;

public class smsVerification extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_verification);


    }


    public void onSubmitVerification(View view) {


        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
        finish();
    }
}