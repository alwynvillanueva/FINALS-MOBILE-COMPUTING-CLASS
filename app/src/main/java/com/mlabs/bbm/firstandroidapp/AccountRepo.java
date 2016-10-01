package com.mlabs.bbm.firstandroidapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;


public class AccountRepo {

    private DBHelper dbHelper;

    public AccountRepo(Context context){
        dbHelper = new DBHelper(context);
    }

    public int createAccount(Account acct){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Account.KEY_firstname, acct.acct_firstname);
        values.put(Account.KEY_lastname, acct.acct_lastname);
        values.put(Account.KEY_username, acct.acct_username);
        values.put(Account.KEY_email, acct.acct_email);
        values.put(Account.KEY_password, acct.acct_password);
        values.put(Account.KEY_datecreated, acct.acct_datecreated);

        long acct_Id = db.insert(Account.TABLE, null, values);
        db.close();
        return (int) acct_Id;

    }

    public void deleteAccount(int acct_Id){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(Account.TABLE, Account.KEY_ID + "+ ?", new String[] {String.valueOf(acct_Id)});
        db.close();

    }

    public void updateAccount(Account acct){

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Account.KEY_email, acct.acct_email);
        values.put(Account.KEY_password, acct.acct_password);
        values.put(Account.KEY_datecreated, acct.acct_datecreated);

        db.update(Account.TABLE, values, Account.KEY_ID + "= ?", new String[] {String.valueOf(acct.acct_ID)});
        db.close();

    }

    public boolean validateLogin(String email, String password){

        boolean res = false;
        HashMap<String, String> user = new HashMap<String, String>();

        String selectQuery = "SELECT " + Account.KEY_email + ", " + Account.KEY_password  + ", " + Account.KEY_username + " FROM " + Account.TABLE + " WHERE " + Account.KEY_email + " = \"" + email.toString() +  "\"" + " OR " + Account.KEY_username + " = \"" + email.toString() + "\"" + " AND " + Account.KEY_password + " = \"" + password.toString() +"\"";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            user.put(Account.KEY_email, cursor.getString(0));
            user.put(Account.KEY_password, cursor.getString(1));
            res = true;
        }
        else
        {
            res = false;
        }

        cursor.close();
        db.close();


        return res;


    }
    public boolean isExisting (Account acct){

        //Latest edit
        boolean res = false;
        HashMap<String, String> user = new HashMap<String, String>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery = "SELECT " + Account.KEY_email + ", " + Account.KEY_username + " FROM " + Account.TABLE + " WHERE " + Account.KEY_email + " = \"" + acct.acct_email + "\"" + " OR " + Account.KEY_username + " = \"" + acct.acct_username + "\"";

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            user.put(Account.KEY_email, cursor.getString(0));
            user.put(Account.KEY_username, cursor.getString(1));
            res = true;
        }
        else
        {
            res = false;
        }
        cursor.close();
        db.close();
        //
        return res;

    }
}
