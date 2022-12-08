package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class contactsActivity extends AppCompatActivity {
    ImageView imageViewBackIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        imageViewBackIcon=findViewById(R.id.imageViewBackIcon);
        imageViewBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(contactsActivity.this,landingActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

    }
}