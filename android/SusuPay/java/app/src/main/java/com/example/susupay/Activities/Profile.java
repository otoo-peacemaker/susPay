package com.example.susupay.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.susupay.R;
import com.google.android.material.tabs.TabLayout;
import com.example.susupay.Adapters.PagerAdapter;
public class Profile extends AppCompatActivity {
    ImageView profileBkBtn;
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileBkBtn = findViewById(R.id.profile_bk_btn);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        viewPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        //join the tab_layout with the viewpager
        tabLayout.setupWithViewPager(viewPager);
        profileBkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Home.class);
                startActivity(intent);
            }
        });
    }
}