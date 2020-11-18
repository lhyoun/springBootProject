package com.project.matchingapp3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.project.matchingapp3.MainActivity;
import com.project.matchingapp3.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Glide 라이브러리로 gif파일 띄우기
        ImageView intro = (ImageView) findViewById(R.id.img_intro);
        Glide.with(this).load(R.drawable.intro).into(intro);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3500);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}