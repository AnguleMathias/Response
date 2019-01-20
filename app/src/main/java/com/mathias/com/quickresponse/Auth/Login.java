package com.mathias.com.quickresponse.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mathias.com.quickresponse.Home;
import com.mathias.com.quickresponse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;
    EditText inputEmail,inputPassword;
    TextInputLayout inputLayoutEmail,inputLayoutPassword;

    LinearLayout linearLayout;
    ProgressDialog dialog;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new ProgressDialog(this);


        mAuth = FirebaseAuth.getInstance();
        linearLayout = findViewById(R.id.l1login);

        inputEmail = findViewById(R.id.loginEmail);
        inputPassword = findViewById(R.id.loginPassword);

        inputLayoutEmail = findViewById(R.id.tilEmailLogin);
        inputLayoutPassword = findViewById(R.id.tilPasswordLogin);

        btnLogin = findViewById(R.id.loginbtn);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLogin();
            }
        });


    }

    private void startLogin() {

        dialog.setMessage("Login in....please wait");
        dialog.show();
        validateEmail(inputEmail.getText().toString().trim());
        validatePassword(inputPassword.getText().toString().trim());
        isValidEmail(inputEmail.getText().toString().trim());

        mAuth.signInWithEmailAndPassword(inputEmail.getText().toString().trim(),inputPassword.getText().toString().trim())
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            dialog.dismiss();
                            startActivity(new Intent(Login.this, Home.class));

                            Toast.makeText(Login.this, "You have successfully Logged in", Toast.LENGTH_SHORT).show();
finish();
                        }else {
                            dialog.dismiss();
                            Toast.makeText(Login.this, "You could not Login", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(Login.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(Login.this, String.valueOf(e), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean validateEmail(String email) {
        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("invalude email");
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword(String password) {
        if (password.isEmpty()|| password ==null) {
            inputLayoutPassword.setError("invalude password");
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
