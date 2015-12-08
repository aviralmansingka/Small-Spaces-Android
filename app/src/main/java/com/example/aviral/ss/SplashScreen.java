package com.example.aviral.ss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Aviral on 12/7/2015.
 */
public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);
            }
        };
        t.start();
    }
}
