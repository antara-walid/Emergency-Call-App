package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.emergencycallapp.databinding.ActivityEmergencyContactsBinding;

import java.util.ArrayList;

public class EmergencyContactsActivity extends AppCompatActivity {

    ActivityEmergencyContactsBinding binding;

    private ImageView imageViewBackIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmergencyContactsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // back icon
        imageViewBackIcon = findViewById(R.id.imageViewBackIcon);

        imageViewBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmergencyContactsActivity.this, EmergencyListActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // static data for test

        String [] fullNames = {"walid 404" ,"hamza slow"};
        String [] phoneNumbers = {"06171238" ,"06178382"};

        ArrayList<EmergencyContact> contactsList = new ArrayList<>();

        for(int i = 0 ; i <fullNames.length ;i++)
        {
            EmergencyContact contact = new EmergencyContact(fullNames[i],phoneNumbers[i] );
            contactsList.add(contact);
        }

        ListAdapter listAdapter = new ListAdapter(EmergencyContactsActivity.this,contactsList);

        binding.listViewContacts.setAdapter(listAdapter);
        binding.listViewContacts.setClickable(true);
        binding.listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = fullNames[i];
                String phoneNumber = phoneNumbers[i];
                makePhoneCall(phoneNumber);
            }
        });
    }
    private void makePhoneCall(String number) {
        String dial = "tel:" + number;
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
    }
}