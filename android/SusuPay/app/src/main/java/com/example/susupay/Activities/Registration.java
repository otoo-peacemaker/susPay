package com.example.susupay.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.susupay.R;
import com.example.susupay.model.RegistrarClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Registration extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText firstname, lastname, phoneNum, NationalID, email, password1, password2;
    private Button sendVerification;
    private FirebaseAuth mAuth;
    private FirebaseDatabase Fdatabase;
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://susupay-1afb7-default-rtdb.firebaseio.com/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //registering the UI component
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.lastname);
        phoneNum = findViewById(R.id.reg_phone_num);
        NationalID = findViewById(R.id.nationalID);
        password1 = findViewById(R.id.password_1);
        password2 = findViewById(R.id.password_2);
        email = findViewById(R.id.editTextTextEmailAddress);

        sendVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    public void registerUser() {
        String userFirstname, userLastname, userPassword1, userPassword2;
        String userPhoneNum, userNationalID, userRegistrationEmail;
        String userPassword;

        userFirstname = firstname.getText().toString().trim();
        userLastname = lastname.getText().toString().trim();
        userPassword1 = password1.getText().toString().trim();
        userPassword2 = password2.getText().toString().trim();
        userPhoneNum = phoneNum.getText().toString().trim();
        userNationalID = NationalID.getText().toString().trim();
        userRegistrationEmail = email.getText().toString().trim();

        //checking for validation
        if (TextUtils.isEmpty(userFirstname)) {
//            editTextName.setError(getString(R.string.input_error_name));
//            userFirstname.requestFocus();
            Toast.makeText(this, "field empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userLastname)) {
            Toast.makeText(this, "field empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userPassword1) && userPassword1.length() < 6) {
            if (TextUtils.isEmpty(userPassword2) && userPassword2.length() < 6) {
                if (!userPassword1.equals(userPassword2)) {
                    Toast.makeText(this, "incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (TextUtils.isEmpty(userPhoneNum) || userPhoneNum.length() < 10) {
            Toast.makeText(this, "Enter 10 digit number", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userNationalID)) {
            Toast.makeText(this, "field empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userRegistrationEmail) || !Patterns.EMAIL_ADDRESS
                .matcher(userRegistrationEmail)
                .matches()) {
            Toast.makeText(this, "incorrect email", Toast.LENGTH_SHORT).show();
        } else {
            //sending data to the firebase realtime Database and I am using the phone number as unique id
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //checking if phone number is not already registered
                    if (snapshot.hasChild(userPhoneNum)) {
                        Toast.makeText(Registration.this, "Phone already registered", Toast.LENGTH_SHORT).show();
                    } else {
                        databaseReference.child("users").child(userPhoneNum).child("firstname").setValue(userFirstname);
                        databaseReference.child("users").child(userPhoneNum).child("lastname").setValue(userLastname);
                        databaseReference.child("users").child(userPhoneNum).child("userID").setValue(userNationalID);
                        databaseReference.child("users").child(userPhoneNum).child("email").setValue(userRegistrationEmail);
                        databaseReference.child("users").child(userPhoneNum).child("password").setValue(userPassword1);

                        Toast.makeText(Registration.this, "User registration successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }
    }
}