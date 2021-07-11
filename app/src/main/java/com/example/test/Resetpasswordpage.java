package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Resetpasswordpage extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText emailText;
    Button forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpasswordpage);
        forgotPassword = findViewById(R.id.forgotPassword);
        mAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.email);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Resetpasswordpage.this, "Enter every details", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.sendPasswordResetEmail(emailText.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Resetpasswordpage.this, "Email Sent", Toast.LENGTH_SHORT).show();
                                        Intent resetpasswordintent = new Intent(Resetpasswordpage.this, Homepage.class);
                                        startActivity(resetpasswordintent);
                                        finish();
                                    } else {
                                        Toast.makeText(Resetpasswordpage.this, "Please enter correct details", Toast.LENGTH_SHORT).show();


                                    }
                                }
                            });
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
