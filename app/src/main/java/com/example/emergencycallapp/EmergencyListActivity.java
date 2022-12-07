package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;

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

    }
}
