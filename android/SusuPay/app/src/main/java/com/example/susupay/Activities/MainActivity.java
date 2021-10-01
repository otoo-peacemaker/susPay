package com.example.susupay.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.susupay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private EditText email, password;
    private ProgressBar progressBar;
    private TextView registerBtn, forgetPassword;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.login_button);
        registerBtn = findViewById(R.id.login_register_btn);
        email = findViewById(R.id.phone_num);
        forgetPassword = findViewById(R.id.login_forgetPass_btn);
        password = findViewById(R.id.Password);
        progressBar = findViewById(R.id.progressBar);

        //get database instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    startActivity(new Intent(MainActivity.this, Home.class));
                }
            }
        };


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    if (StrPassword.length() < 6) {
                                        password.setError(getString(R.string.mini_password));

                                    } else {
                                        Toast.makeText(MainActivity.this, "Incorrect password or email", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                    startActivity(intent);
                                }
                            }
                        });
            }
        });


        BottomNavigationView navView = findViewById(R.id.bottomNav_view);
//        //pass the bottom navigation id
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_transaction, R.id.navigation_profile)
//                .build();
//        //Initialize NavController.
//        NavController navController = Navigation.findNavController(this, R.id.bottom_nav);
//
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);

    }

    public void RegistrationBtn(View view) {
        Intent intent = new Intent(this.getApplicationContext(), Registration.class);
        startActivity(intent);
    }
}