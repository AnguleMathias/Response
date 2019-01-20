package com.mathias.com.quickresponse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.mathias.com.quickresponse.Welcome.WelcomeActivity;

public class intro extends AppCompatActivity {
    protected int _splashTime = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_intro);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(intro.this,
                        WelcomeActivity.class));
                finish();
            }
        }, secondsDelayed * 1500);
    }
}
