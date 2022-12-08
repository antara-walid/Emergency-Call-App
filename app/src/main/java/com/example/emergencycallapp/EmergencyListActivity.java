package com.example.emergencycallapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EmergencyListActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private CardView cardViewFire;
    private CardView cardViewFlood;
    private CardView cardViewCrime;
    private CardView cardViewAccident;
    private CardView cardViewEarthQuake;
    private CardView cardViewIllness;
    private ImageView imageViewBackIcon;
    private TextView textViewMessage;
    private static String number;

    private LocationManager locationManager;
    private LocationListener locationListener;

    String[] appPermissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.SEND_SMS
    };

    private static final int PERMISSIONS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_list);

        textViewMessage = findViewById(R.id.textViewEmergencyMessage); // delete it after
        // asking for permissions
        if (checkRequestPermissions()) {

        }

        // location manager
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("location", location.toString());
                //textViewMessage.setText(location.getLatitude() + " - "+ location.getLongitude());
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


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
                Intent intent = new Intent(EmergencyListActivity.this, LandingActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // 1.Fire
        cardViewFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "+212617241788";
                String message = "user is facing a fire emergency please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database

            }
        });
        // 2.Flood
        cardViewFlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "+212617241788";
                String message = "user is facing a flood emergency please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database
            }
        });
        // 3.Crime
        cardViewCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "+212617241788";
                String message = "user is facing a crime emergency please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database
            }
        });
        // 4.Accident
        cardViewAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "+212617241788";
                String message = "user is facing an accident emergency please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database
            }
        });

        // 5.Earthquake
        cardViewEarthQuake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "+212617241788";
                String message = "user is facing an earthquake emergency please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database
            }
        });
        // 6.Health issues
        cardViewIllness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "+212617241788";
                String message = "user is in poor health please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database


            }
        });

    }

    private boolean checkRequestPermissions() {
        List<String> listOfPermissionsNeeded = new ArrayList<>();
        for (String permission : appPermissions) {
            if (ContextCompat.checkSelfPermission(EmergencyListActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
                listOfPermissionsNeeded.add(permission); // so that we can ask for them later
            }
        }

        if(!listOfPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(EmergencyListActivity.this,listOfPermissionsNeeded.toArray(
                    new String[listOfPermissionsNeeded.size()]) ,PERMISSIONS_REQUEST_CODE);
            return false;
        }
        return true;
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

    private void sendSms(String sms) {
      //  ActivityCompat.requestPermissions(EmergencyListActivity.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(number, null, sms, null, null);
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
