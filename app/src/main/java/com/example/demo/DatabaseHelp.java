package com.example.demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelp extends SQLiteOpenHelper {
    static String name="user.db";
    static int dbVersion=1;
    public DatabaseHelp(Context context) {
        super(context, name, null, dbVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {//建表
        String sql="create table user(id integer primary key autoincrement,username varchar(20),password varchar(20),likes varchar(50))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
