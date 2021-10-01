package com.example.susupay.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.susupay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;
import java.util.regex.Pattern;

import model.RegistrarClass;

public class Registration extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText firstname, lastname, phoneNum, NationalID, email, password1, password2;
    private Button sendVerification;
    private RegistrarClass customers;
    private FirebaseAuth mAuth;
    private FirebaseDatabase Fdatabase;


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

        //You can set your progress bar here before the firebase authentication
        mAuth = FirebaseAuth.getInstance();


//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        toolbar.setNavigationIcon(R.drawable.arrow_back);
//        //setting the app title
//        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//


    }

    //handle user before login method
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            //handle the already login user
        }
    }

    public void registerUser() {
        String userFirstname, userLastname, userPassword1, userPassword2;
        String userPhoneNum, userNationalID, userRegistrationEmail;
        String userPassword = null;

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
            return;
        }
        if (TextUtils.isEmpty(userLastname)) {
            Toast.makeText(this, "field empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword1) && userPassword1.length() < 6) {
            if (TextUtils.isEmpty(userPassword2) && userPassword2.length() < 6) {
                if (!userPassword1.equals(userPassword2)) {
                    Toast.makeText(this, "incorrect password", Toast.LENGTH_SHORT).show();
                }
            }
            return;
        }
        if (TextUtils.isEmpty(userPhoneNum) || userPhoneNum.length() < 10) {
            Toast.makeText(this, "Enter 10 digit number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userNationalID)) {
            Toast.makeText(this, "field empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userRegistrationEmail) || !Patterns.EMAIL_ADDRESS
                .matcher(userRegistrationEmail)
                .matches()) {
            Toast.makeText(this, "incorrect email", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(userRegistrationEmail, userPassword2)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //if email and password is successfully passed add the following as well
                            customers = new RegistrarClass(
                                    userFirstname,
                                    userLastname,
                                    userNationalID,
                                    userPhoneNum
                            );//end of constructor class

                            FirebaseDatabase.getInstance().getReference("Customers")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(customers)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(Registration.this, "passed", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(Registration.this, "make sure all fields are filled", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });

                        }//end of successful task
                        else {
                            Toast.makeText(Registration.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void verificationButton(View view) {
        switch (view.getId()) {
            case R.id.send_verification_button:
                registerUser();
                break;
//            case R.id.verificationBtn:
//                Intent intent = new Intent(this, smsVerification.class);
//                startActivity(intent);
//                break;
        }

    }
}