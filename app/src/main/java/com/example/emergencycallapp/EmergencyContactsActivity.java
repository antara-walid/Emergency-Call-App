package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.emergencycallapp.databinding.ActivityEmergencyContactsBinding;

import java.util.ArrayList;
import java.util.List;

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


        // database
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(EmergencyContactsActivity.this);
        //List<EmergencyContact> emergencyContactList = myDatabaseHelper.getAll();
        List<EmergencyContact> emergencyContactList = new ArrayList<>();
        Cursor cursor = myDatabaseHelper.readAllData();
        if(cursor.getCount() != 0)
        {
            while (cursor.moveToNext())
            {
                EmergencyContact contact = new EmergencyContact(cursor.getString(1),cursor.getString(2));
                emergencyContactList.add(contact);
            }
        }

       // emergencyContactList.add(new EmergencyContact("test" ,"0000000"));
        System.out.println( "list eme : " +emergencyContactList);

        List<String> fullNames  = new ArrayList<>();
        List<String> phoneNumbers = new ArrayList<>();
        for(EmergencyContact contact : emergencyContactList)
        {
            fullNames.add(contact.getFullName());
            phoneNumbers.add(contact.getPhoneNumber());
        }

        ArrayList<EmergencyContact> contactsList = new ArrayList<>();

        for(int i = 0 ; i <fullNames.size() ;i++)
        {
            EmergencyContact contact = new EmergencyContact(fullNames.get(i),phoneNumbers.get(i) );
            contactsList.add(contact);
        }

        ListAdapter listAdapter = new ListAdapter(EmergencyContactsActivity.this,contactsList);

        binding.listViewContacts.setAdapter(listAdapter);
        binding.listViewContacts.setClickable(true);
        binding.listViewContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = fullNames.get(i);
                String phoneNumber = phoneNumbers.get(i);
                makePhoneCall(phoneNumber);
            }
        });
    }
    private void makePhoneCall(String number) {
        String dial = "tel:" + number;
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
    }
}