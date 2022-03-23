package com.example.susupay.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.susupay.MainActivity;
import com.example.susupay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText email, password;
    private ProgressBar progressBar;
    private TextView registerBtn, forgetPassword;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.login_button);
        registerBtn = findViewById(R.id.login_register_btn);
        email = findViewById(R.id.login_email);
        forgetPassword = findViewById(R.id.login_forgetPass_btn);
        password = findViewById(R.id.Password);
        progressBar = findViewById(R.id.progressBar);

        //get database instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = firebaseAuth -> {
            FirebaseUser user1 = firebaseAuth.getCurrentUser();
            if (user1 == null) {
                startActivity(new Intent(LoginActivity.this, Home.class));
            }
        };


        loginBtn.setOnClickListener(v -> {
            String UsrEmail = email.getText().toString();
            String StrPassword = password.getText().toString();

            if (TextUtils.isEmpty(UsrEmail)) {
                Toast.makeText(getApplicationContext(), "Enter Phone number!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(StrPassword)) {
                Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                return;
            }

            progressBar.setVisibility(View.VISIBLE);

            //authenticate user
            auth.signInWithEmailAndPassword(UsrEmail, StrPassword)
                    .addOnCompleteListener(LoginActivity.this, task -> {
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {
                            if (StrPassword.length() < 6) {
                                password.setError(getString(R.string.mini_password));

                            } else {
                                Toast.makeText(LoginActivity.this, "Incorrect password or email", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);


                            startActivity(intent);
                        }
                    });
        });

    registerBtn.setOnClickListener(v -> {
        Intent intent = new Intent(LoginActivity.this, Registration.class);
        startActivity(intent);
    });
    }


}