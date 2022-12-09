package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.emergencycallapp.databinding.ActivityEmergencyContactsBinding;

public class EmergencyContactsActivity extends AppCompatActivity {

    ActivityEmergencyContactsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);
    }
}