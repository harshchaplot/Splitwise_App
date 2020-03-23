package com.example.madtproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String PASSWORD = "password";
    private static final String CREATE_TABLE_LOGIN = " create table LOGIN ( _id INTEGER PRIMARY KEY AUTOINCREMENT, uname TEXT NOT NULL , password TEXT );";
    private static final String DB_NAME = "LOGIN.DB";
    private static final int DB_VERSION = 1;
    public static final String UNAME = "uname";
    public static final String TABLE_NAME_LOGIN = "LOGIN";
    public static final String _ID = "_id";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LOGIN);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS CANDIDATE");
        onCreate(db);
    }
}