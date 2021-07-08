package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {
    Button exit;

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
    public void exit(View view) {
        exit();
    }

    private void exit() {
        exit = findViewById(R.id.Exit);
        System.exit(0);
    }


    @Override
    public void onBackPressed() {
        finish();
    }


}
