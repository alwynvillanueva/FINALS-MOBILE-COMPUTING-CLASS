package com.mlabs.bbm.firstandroidapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "account.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        //Latest edit
        String CREATE_TABLE_ACCOUNT = "CREATE TABLE " + Account.TABLE + "("
                + Account.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Account.KEY_firstname + " TEXT,"
                + Account.KEY_lastname + " TEXT,"
                + Account.KEY_username + " TEXT UNIQUE,"
                + Account.KEY_email + " TEXT UNIQUE, "
                + Account.KEY_password + " TEXT, "
                + Account.KEY_datecreated + " TEXT)";
        //
        db.execSQL(CREATE_TABLE_ACCOUNT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + Account.TABLE);

        onCreate(db);
    }
}

