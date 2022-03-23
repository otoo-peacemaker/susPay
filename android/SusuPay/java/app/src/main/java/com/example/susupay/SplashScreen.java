package com.example.susupay;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.susupay.Activities.LoginActivity;

public class SplashScreen extends AppCompatActivity {
    int SPLASH_TIME = 5000; //This is 3 seconds
    ProgressBar splashProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //This is additional feature, used to run a progress bar
        splashProgress = findViewById(R.id.progressBar);
        ImageView imageView = findViewById(R.id.imageViewBack);

        Animation slideAnimation = AnimationUtils.loadAnimation(this, R.anim.anime);
        imageView.startAnimation(slideAnimation);

        playProgress();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next pageL
                Intent mySuperIntent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(mySuperIntent);

                //This 'finish()' is for exiting the app when back button pressed from Home page which is ActivityHome
                finish();
            }
        }, SPLASH_TIME);

    }

    //Method to run progress bar for 5 seconds
    private void playProgress() {
        //splashProgress.incrementProgressBy(20);
        //splashProgress.getMax();
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
                .setDuration(5000)
                .start();
    }

}