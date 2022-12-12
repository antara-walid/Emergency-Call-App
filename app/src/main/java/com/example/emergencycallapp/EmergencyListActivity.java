package com.example.emergencycallapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EmergencyListActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private CardView cardViewFire;
    private CardView cardViewFlood;
    private CardView cardViewCrime;
    private CardView cardViewAccident;
    private CardView cardViewEmergencyNumber;
    private CardView cardViewIllness;
    private ImageView imageViewBackIcon;
    private TextView textViewMessage;
    private static String number;
    private static String locationStr;
    private static boolean check;
    private LocationManager locationManager;
    private LocationListener locationListener;

    private String emergencyPhoneNumber="0617404";

    String[] appPermissions = {
            Manifest.permission.SEND_SMS,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    // ******
    ActivityResultLauncher<String[]> mPerssmissionResultLauncher;
    private boolean isLocationPermissionGranted = false;
    private boolean isCallPermissionGranted = false;
    private boolean isSend_sms_PermissionGranted = false;
    // ******

    private static final int PERMISSIONS_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        check = true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency_list);


        // selecting cards

        cardViewFire = findViewById(R.id.cardViewFire);
        cardViewFlood = findViewById(R.id.cardViewFlood);
        cardViewCrime = findViewById(R.id.cardViewCrime);
        cardViewAccident = findViewById(R.id.cardViewAccident);
        cardViewEmergencyNumber = findViewById(R.id.cardViewEmergencyNumber);
        cardViewIllness = findViewById(R.id.cardViewIllness);




        // database

        try {
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(EmergencyListActivity.this);
            emergencyPhoneNumber = myDatabaseHelper.getFirstRow().getPhoneNumber();
        }catch (Exception e)
        {

        }

        // permissions
        mPerssmissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @Override
            public void onActivityResult(Map<String, Boolean> result) {
                if (result.get(Manifest.permission.ACCESS_FINE_LOCATION) != null)
                    isLocationPermissionGranted = result.get(Manifest.permission.ACCESS_FINE_LOCATION);
                if (result.get(Manifest.permission.CALL_PHONE) != null)
                    isCallPermissionGranted = result.get(Manifest.permission.CALL_PHONE);
                if (result.get(Manifest.permission.SEND_SMS) != null)
                    isSend_sms_PermissionGranted = result.get(Manifest.permission.SEND_SMS);
            }

        });

        requestPermissions();

        // location manager
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.d("location", location.toString());
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addresseList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (addresseList != null && addresseList.size() > 0) {
                        if (addresseList.get(0).getAdminArea() != null) {
                            locationStr = addresseList.get(0).getAddressLine(0);
                            // displays the location once but location keeps changing
                            if (check) {
                                String text = "your location is : " + locationStr;
                                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                                toast.show();
                            }
                            check = false;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
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







    }

    @Override
    protected void onStart() {
        super.onStart();
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
                number = "+2126111111"; //150
                String message = "user is facing a fire emergency please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database

            }
        });
        // 2.Flood
        cardViewFlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "+212617241788"; // 150
                String message = "user is facing a flood emergency please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database
            }
        });
        // 3.Crime
        cardViewCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "+212617241788"; //190
                String message = "user is facing a crime emergency please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database
            }
        });
        // 4.Accident
        cardViewAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "+212617241788"; //177
                String message = "user is facing an accident emergency please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database
            }
        });

        // 5.Health issues
        cardViewIllness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = "+212708291452"; // 150
                String message = "user is in poor health please contact him/her to make sure he is all right"; // this message is hard coded for now but it should get it information form db
                sendSms(message);
                makePhoneCall(number); // the number should be brought from database


            }
        });

        // 6.emergency number
        cardViewEmergencyNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmergencyListActivity.this, EmergencyContactsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void makePhoneCall(String number) {
        String dial = "tel:" + number;
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
    }

    private void sendSms(String sms) {
        //  ActivityCompat.requestPermissions(EmergencyListActivity.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);

        if(locationStr != null)
        {
            sms += "\n" + "user location is " + locationStr;
            SmsManager smsManager = SmsManager.getDefault();
            String firstEmergencyNumber = "+212"+emergencyPhoneNumber;
            smsManager.sendTextMessage(firstEmergencyNumber, null, sms, null, null);
        }else{
            SmsManager smsManager = SmsManager.getDefault();
            String firstEmergencyNumber = "+212"+emergencyPhoneNumber;
            smsManager.sendTextMessage(firstEmergencyNumber, null, sms, null, null);
        }

    }

    private void requestPermissions() {
        // 1.location permission
        isLocationPermissionGranted = ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED;

        // 2.call permission
        isCallPermissionGranted = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CALL_PHONE
        ) == PackageManager.PERMISSION_GRANTED;

        // 3.sms permission
        isSend_sms_PermissionGranted = ContextCompat.checkSelfPermission(
                this, Manifest.permission.SEND_SMS
        ) == PackageManager.PERMISSION_GRANTED;

        List<String> permissionRequests = new ArrayList<>();
        if (!isLocationPermissionGranted)
            permissionRequests.add(Manifest.permission.ACCESS_FINE_LOCATION);
        if (!isCallPermissionGranted)
            permissionRequests.add(Manifest.permission.CALL_PHONE);
        if (!isSend_sms_PermissionGranted)
            permissionRequests.add(Manifest.permission.SEND_SMS);
        if (!permissionRequests.isEmpty()) {
            mPerssmissionResultLauncher.launch(permissionRequests.toArray(new String[0]));
        }

    }

    // feature
}
