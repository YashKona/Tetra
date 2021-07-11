package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loginpage extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText emailText, passwordText;
    Button loginBtn, signupBtn;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        // find ids
        mAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        signupBtn = findViewById(R.id.signupBtn);
        forgotPassword = findViewById(R.id.forgotPassword);
        //when pressed forgot password
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open reset password page
                Intent resetpasswordpageintent = new Intent(Loginpage.this, Resetpasswordpage.class);
                startActivity(resetpasswordpageintent);
            }
        });

        //when pressed singup button
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open singuppage
                Intent signupintent = new Intent(Loginpage.this, Signuppage.class);
                startActivity(signupintent);
            }
        });

        //when pressed login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new strings
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                //if email or password is empty, make new error(toast)
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(Loginpage.this, "Enter every details", Toast.LENGTH_SHORT).show();
                }
                //if filled in
                else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //if task is complete new message(toast)
                                    if (task.isSuccessful()){
                                        Toast.makeText(Loginpage.this, "Log in Successful", Toast.LENGTH_SHORT).show();
                                        //open homepage
                                        Intent signoutintent = new Intent(Loginpage.this, Homepage.class);
                                        startActivity(signoutintent);
                                    }
                                    else {
                                        //if task didnt go properly make new toast
                                        Toast.makeText(Loginpage.this, "Please enter correct details", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

    }
}