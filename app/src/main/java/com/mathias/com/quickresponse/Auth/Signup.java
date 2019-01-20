package com.mathias.com.quickresponse.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.mathias.com.quickresponse.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Signup extends AppCompatActivity {


    FirebaseAuth mAuth;

    private EditText  username, pin2, phone_number;

    LinearLayout linearLayout;
    EditText inputEmail, inputPassword;
    TextInputLayout inputLayoutEmail, inputLayoutPassword;
    SharedPreferences pref;
Button btnSign;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        dialog = new ProgressDialog(this);
    pin2 = findViewById(R.id.pin2);
        mAuth = FirebaseAuth.getInstance();
        linearLayout = findViewById(R.id.l1sign);
        inputEmail = findViewById(R.id.signupEmail);
        inputPassword = findViewById(R.id.signupPassword);
username =findViewById(R.id.username);
phone_number=findViewById(R.id.phone_number);
        inputLayoutEmail = findViewById(R.id.tilEmailSign);
        inputLayoutPassword = findViewById(R.id.tilPasswordSign);
  pref = getApplicationContext().getSharedPreferences("mypref",MODE_PRIVATE);
  final SharedPreferences.Editor editor = pref.edit();

        btnSign = findViewById(R.id.signupbtn);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             editor.putString("email",inputEmail.getText().toString());
                editor.putString("pin",inputPassword.getText().toString());
                editor.putString("username",username.getText().toString());

                editor.putString("phone",phone_number.getText().toString());
             editor.commit();
                startSignup();
            }
        });

    }

    private void startSignup() {

        dialog.setMessage("Registering you....please wait");
        dialog.show();
        validateEmail(inputEmail.getText().toString().trim());
        validatePassword(inputPassword.getText().toString().trim());
        isValidEmail(inputEmail.getText().toString().trim());


        mAuth.createUserWithEmailAndPassword(inputEmail.getText().toString().trim(), inputPassword.getText().toString().trim())
                .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            dialog.dismiss();
                            Toast.makeText(Signup.this, "You have successfully Signed up", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Signup.this, register.class);
                            startActivity(intent);

                        } else {
                            dialog.dismiss();
                            Toast.makeText(Signup.this, "You could not Signup", Toast.LENGTH_SHORT).show();

                        }
                    }
                }).addOnFailureListener(Signup.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signup.this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });


    }

    //validating email
    private boolean validateEmail(String email) {
        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("invalide email");
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    //validating password
    private boolean validatePassword(String password) {
        if (password.isEmpty() || password == null || password != pin2.getText().toString().trim()) {
            inputLayoutPassword.setError("invaled email");
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



