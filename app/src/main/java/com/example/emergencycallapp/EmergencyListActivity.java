package com.example.emergencycallapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class EmergencyListActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private CardView cardViewFire;
    private CardView cardViewFlood;
    private CardView cardViewCrime;
    private CardView cardViewAccident;
    private CardView cardViewEarthQuake;
    private CardView cardViewIllness;
    private ImageView imageViewBackIcon;
    private static String number ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_list);

        // selecting cards

        cardViewFire = findViewById(R.id.cardViewFire);
        cardViewFlood = findViewById(R.id.cardViewFlood);
        cardViewCrime = findViewById(R.id.cardViewCrime);
        cardViewAccident = findViewById(R.id.cardViewAccident);
        cardViewEarthQuake = findViewById(R.id.cardViewEarthQuake);
        cardViewIllness = findViewById(R.id.cardViewIllness);
        // listeners

        // back arrow
        imageViewBackIcon = findViewById(R.id.imageViewBackIcon);

        imageViewBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmergencyListActivity.this , LandingActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // 1.Fire
        cardViewFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(EmergencyListActivity.this, "you clicked fire", Toast.LENGTH_SHORT).show();
                number = "+212617241788";
                makePhoneCall(number); // the number should be brought from database
            }
        });
        // 2.Flood
        cardViewFlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(EmergencyListActivity.this, "you clicked fire", Toast.LENGTH_SHORT).show();
                number = "+212617241788";
                makePhoneCall(number); // the number should be brought from database
            }
        });
        // 3.Crime
        cardViewCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(EmergencyListActivity.this, "you clicked fire", Toast.LENGTH_SHORT).show();
                number = "+212617241788";
                makePhoneCall(number); // the number should be brought from database
            }
        });
        // 4.Accident
        cardViewAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(EmergencyListActivity.this, "you clicked fire", Toast.LENGTH_SHORT).show();
                number = "+212617241788";
                makePhoneCall(number); // the number should be brought from database
            }
        });

        // 5.Earthquake
        cardViewEarthQuake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(EmergencyListActivity.this, "you clicked fire", Toast.LENGTH_SHORT).show();
                number = "+212617241788";
                makePhoneCall(number); // the number should be brought from database
            }
        });
        // 6.Illness
        cardViewIllness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(EmergencyListActivity.this, "you clicked fire", Toast.LENGTH_SHORT).show();
                number = "+212617241788";
                makePhoneCall(number); // the number should be brought from database
            }
        });

    }

    private void makePhoneCall(String number) {
        if (ContextCompat.checkSelfPermission(EmergencyListActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(EmergencyListActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        } else {
            String dial = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall(number);
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
