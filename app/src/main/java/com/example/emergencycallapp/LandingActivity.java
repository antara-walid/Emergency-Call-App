package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

<<<<<<< HEAD:app/src/main/java/com/example/emergencycallapp/landingActivity.java
public class landingActivity extends AppCompatActivity {
    Button sosbtn;
    ImageView add;
=======
public class LandingActivity extends AppCompatActivity {

>>>>>>> 089d3e959c206d3cc7a278679e60aba53296a239:app/src/main/java/com/example/emergencycallapp/LandingActivity.java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        sosbtn=findViewById(R.id.sosbtn);
        add=findViewById(R.id.imageViewBackIcon);
        sosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(landingActivity.this,EmergencyListActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(landingActivity.this,contactsActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
    }
}