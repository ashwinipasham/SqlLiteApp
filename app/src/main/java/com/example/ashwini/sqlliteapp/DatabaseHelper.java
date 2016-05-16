package com.example.ashwini.sqlliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RajuReddy on 4/11/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Database_Name = "Contact.db";
    public static final String Table_Name = "Contact_table";
    public static final String Id = "ID";
    public static final String Name = "NAME";
    public static final String Email = "EMAIL";
    public static final String Mobile = "MOBILE";


    public DatabaseHelper(Context context) {
        super(context, Database_Name, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + Table_Name + "(ID INTEGER PRIMARY KEY  AUTOINCREMENT,NAME TEXT, EMAIL TEXT, MOBILE INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists " + Table_Name);
        onCreate(db);
    }

    public boolean insertData(String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(Name, name);
        contentValue.put(Email, email);
        contentValue.put(Mobile, phone);
        long result = db.insert(Table_Name, null, contentValue);
        if (result == -1) {
            return false;

        } else {
            return true;
        }
    }

    public boolean updateData( String id, String name, String email, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(Id,id );
        contentValue.put(Name, name );
        contentValue.put(Email, email );
        contentValue.put(Mobile, phone);
        db.update(Table_Name, contentValue,"ID = ?", new String[] {id} );
        return true;


    }

    public boolean deleteData(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(Id, id);
        db.delete(Table_Name, "ID = ?", new String [] {id} );
        return true;
    }

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + Table_Name, null);
        return res;

    }
}
