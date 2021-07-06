package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

    public void activity2(View view) {
        Intent intent = new Intent(this, Questionspage.class);
        startActivity(intent);

    }
    public void Kamar(View view) {
        Intent intent = new Intent(this, Kamar.class);
        startActivity(intent);

    }
    public void loginpage(View view) {
        Intent intent = new Intent(this, Signuppage.class);
        startActivity(intent);

    }


    @Override
    public void onBackPressed() {
        finish();
    }


}
