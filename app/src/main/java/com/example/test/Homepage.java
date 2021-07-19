package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Homepage extends AppCompatActivity {
    Button exit;
    Button btn_signout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        btn_signout = findViewById(R.id.signoutBtn);
        mAuth = FirebaseAuth.getInstance();
        //when clicked signout startup the following code
        btn_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //signingout
                mAuth.signOut();
                //open loginpage
                startActivity(new Intent(Homepage.this,Loginpage.class));
                finish();
            }
        });
    }

    public void Question(View view) {
        //open questionpage
        Intent intent = new Intent(this, Questionspage.class);
        startActivity(intent);
    }
    public void Kamar(View view) {
        //open kamar file
        Intent intent = new Intent(this, Kamar.class);
        startActivity(intent);
    }
    public void Nzqa(View view) {
        //open nzqa file
        Intent intent = new Intent(this, Nzqa.class);
        startActivity(intent);
    }
    public void loginpage(View view) {
        //open signuppage
        Intent intent = new Intent(this, Signuppage.class);
        startActivity(intent);
    }
    public void exit(View view) {
        //launch exit
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        //finish activity
        finish();
    }
}
