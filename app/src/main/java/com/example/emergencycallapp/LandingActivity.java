package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LandingActivity extends AppCompatActivity {

    Button sosbtn;
    ImageView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        sosbtn=findViewById(R.id.sosbtn);
        add=findViewById(R.id.imageViewBackIcon);
        sosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(LandingActivity.this,EmergencyListActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(LandingActivity.this,contactsActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}