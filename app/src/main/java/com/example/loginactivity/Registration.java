package com.example.loginactivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        TextView btn=findViewById(R.id.alreadyhaveanaccount);
        btn.setOnClickListener(v -> startActivity(new Intent(Registration.this,Login.class)));
    }
}