package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signuppage extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText nameText, emailText, passwordText;
    Button signupBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        mAuth = FirebaseAuth.getInstance();
        nameText = findViewById(R.id.name);
        emailText = findViewById(R.id.email);
        passwordText = findViewById(R.id.password);
        signupBtn = findViewById(R.id.signupBtn);
        loginBtn = findViewById(R.id.loginBtn);
        //when pressed login button open loginpage
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(Signuppage.this, Loginpage.class);
                startActivity(loginIntent);
            }
        });

        //when pressed signup button
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                //if email is empty or name or password create a new error(toast)
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(name) || TextUtils.isEmpty(password)){
                    Toast.makeText(Signuppage.this, "Enter every detail", Toast.LENGTH_SHORT).show();
                }
                else if(password.length() < 6){
                    //if password isnt over 6 digits create new error
                    Toast.makeText(Signuppage.this, "Password must be 6 characters", Toast.LENGTH_SHORT).show();
                }
                else {
                    //otherwise  create user
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    //if task is successful create new toast and open homepage
                                    if(task.isSuccessful()){
                                        Toast.makeText(Signuppage.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Signuppage.this, Homepage.class));
                                        //close activity when done
                                        finish();
                                    }
                                    else {
                                        //otherwise create new error(toast)
                                        Toast.makeText(Signuppage.this, "Couldn't Create an account", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}