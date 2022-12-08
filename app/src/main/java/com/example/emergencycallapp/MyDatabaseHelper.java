package com.example.emergencycallapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "EmergencyApp.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="contact";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_NUM = "number_phone";
    private static final String COLUMN_EMAIL = "email";
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String query = "CREATE TABLE "+ TABLE_NAME+" (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_NAME + " TEXT, " + COLUMN_NUM + " TEXT, " + COLUMN_EMAIL + " TEXT);";
    db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
    }
    void addContact(String name,String num,String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_NUM,num);
        cv.put(COLUMN_EMAIL,email);

        long result = db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "you successfully added your emergency call", Toast.LENGTH_SHORT).show();
        }
    }
}
