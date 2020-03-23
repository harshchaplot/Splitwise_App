package com.example.madtproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private Context context;
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;

    public DBManager(Context c) {
        this.context = c;
    }

    public DBManager open() throws SQLException {
        this.dbHelper = new SQLiteHelper(this.context);
        this.database = this.dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        this.dbHelper.close();
    }

    public void insert(String uname, String password) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(SQLiteHelper.UNAME, uname);
        contentValue.put(SQLiteHelper.PASSWORD, password);
        this.database.insert(SQLiteHelper.TABLE_NAME_LOGIN, null, contentValue);
    }


    public Cursor fetch() {
        Cursor cursor = this.database.query(SQLiteHelper.TABLE_NAME_LOGIN, new String[]{SQLiteHelper._ID, SQLiteHelper.UNAME, SQLiteHelper.PASSWORD}, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String uname, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteHelper.UNAME, uname);
        contentValues.put(SQLiteHelper.PASSWORD, password);
        return this.database.update(SQLiteHelper.TABLE_NAME_LOGIN, contentValues, "_id = " + _id, null);
    }

    public void delete(long _id) {
        this.database.delete(SQLiteHelper.TABLE_NAME_LOGIN, "_id=" + _id, null);
    }

    public boolean check(String query){
        Cursor c = this.database.rawQuery(query,null);
        if(c.getCount()<=0){
            c.close();
            return false;
        }
        c.close();
        return true;
    }
}
