package com.example.susupay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.susupay.Navbottom.FragmentHome;
import com.example.susupay.Navbottom.FragmentProfile;
import com.example.susupay.Navbottom.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading the default fragment
        loadFragment(new FragmentHome());
        //instantiate the BottomNav
        BottomNavigationView navView = findViewById(R.id.bottomNav_view);
        navView.setOnItemSelectedListener(this);
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new FragmentHome();
                break;

            case R.id.navigation_transaction:
                fragment = new FragmentTransaction();
                break;

            case R.id.navigation_profile:
                fragment = new FragmentProfile();
                break;
        }
        return loadFragment(fragment);
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.navHostFragment, fragment)
                    .commit();
            return true;
        }
        return false;

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}