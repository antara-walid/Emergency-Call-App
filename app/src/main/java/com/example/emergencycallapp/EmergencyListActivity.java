package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class EmergencyListActivity extends AppCompatActivity {

    private CardView cardViewFire;
    private CardView cardViewFlood;
    private CardView cardViewCrime;
    private CardView cardViewAccident;
    private CardView cardViewEarthQuake;
    private CardView cardViewIllness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_list);

        // selecting cards

        cardViewFire = findViewById(R.id.cardViewFire);
        cardViewFlood= findViewById(R.id.cardViewFlood);
        cardViewCrime = findViewById(R.id.cardViewCrime);
        cardViewAccident = findViewById(R.id.cardViewAccident);
        cardViewEarthQuake = findViewById(R.id.cardViewEarthQuake);
        cardViewIllness = findViewById(R.id.cardViewIllness);

        // listeners

        cardViewFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EmergencyListActivity.this,"you clicked fire" ,Toast.LENGTH_SHORT ).show();
            }
        });


    }
}
