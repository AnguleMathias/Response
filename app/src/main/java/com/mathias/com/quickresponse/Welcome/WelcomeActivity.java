package com.mathias.com.quickresponse.Welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.mathias.com.quickresponse.Auth.Login;
import com.mathias.com.quickresponse.Auth.Signup;
import com.mathias.com.quickresponse.R;


import mehdi.sakout.fancybuttons.FancyButton;

public class WelcomeActivity extends AppCompatActivity {

    FancyButton btnSignup, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnLogin = findViewById(R.id.btnlogin);
        btnSignup = findViewById(R.id.btnsignup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, Login.class));
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(WelcomeActivity.this, Signup.class));

            }
        });

    }
}
