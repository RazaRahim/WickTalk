package com.example.wicktalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.wicktalk.onboard.slider;

import static java.lang.Thread.sleep;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent i=new Intent(splash.this, slider.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}