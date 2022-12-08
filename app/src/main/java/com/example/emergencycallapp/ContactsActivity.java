package com.example.emergencycallapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactsActivity extends AppCompatActivity {
    EditText editName,editnum,editemail;
    Button create_btn;
    ImageView imageViewBackIcon;

    RecyclerView recyclerView;
    FloatingActionButton add_btn;

    MyDatabaseHelper myDB;
    ArrayList<String> contact_id, contact_name, contact_num, contact_email;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        imageViewBackIcon=findViewById(R.id.imageViewBackIcon);

        editName=findViewById(R.id.editName);
        editnum=findViewById(R.id.editnum);
        editemail=findViewById(R.id.editemail);
        create_btn=findViewById(R.id.createbtn);

        //this listener is to go back to landingActivity when clicking on back arrow "<-"
        imageViewBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(ContactsActivity.this,LandingActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

        //this listener is to create contact on database when clicking on create button
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(ContactsActivity.this);
                myDB.addContact(editName.getText().toString().trim(),editnum.getText().toString().trim(),editemail.getText().toString().trim());
            }
        });

        myDB= new MyDatabaseHelper(ContactsActivity.this);
        contact_id= new ArrayList<>();
        contact_name= new ArrayList<>();
        contact_num= new ArrayList<>();
        contact_email= new ArrayList<>();

        storeDataInArrays();

        customAdapter= new CustomAdapter(ContactsActivity.this, contact_id, contact_name, contact_num, contact_email);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ContactsActivity.this));
    }
    void storeDataInArrays(){
        Cursor cursor= myDB.readAllDate();
        if(cursor.getCount()==0)
        {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext())
            {
                contact_id.add(cursor.getString(0));
                contact_name.add(cursor.getString(0));
                contact_num.add(cursor.getString(0));
                contact_email.add(cursor.getString(0));
            }
        }
    }
}