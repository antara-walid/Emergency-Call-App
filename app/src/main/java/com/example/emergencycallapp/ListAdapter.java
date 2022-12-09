package com.example.emergencycallapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<EmergencyContact> {

    public ListAdapter(Context context , ArrayList<EmergencyContact> list)
    {
        super(context,R.layout.list_item,list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        EmergencyContact contact = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView fullName = convertView.findViewById(R.id.fullName);
        TextView phoneNumber = convertView.findViewById(R.id.phoneNumber);

        fullName.setText(contact.getFullName());
        phoneNumber.setText(contact.getPhoneNumber());

        return convertView;
    }
}
