package com.example.demo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserServer {
    private DatabaseHelp dbHelper;
    public UserServer(Context context){
        dbHelper=new DatabaseHelp(context);
    }

    //是否存在该用户
    public boolean isUser(String username){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from user where username=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    //登录
    public boolean login(String username,String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String md5Psw = Md5Utils.md5(password);
        String sql="select * from user where username=? and password=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{username,md5Psw});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }
    //注册
    public boolean register(User user){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into user(username,password,likes) values(?,?,?)";
        Object obj[]={user.getUsername(),user.getPassword(),user.getLikes()};
        sdb.execSQL(sql, obj);
        return true;
    }
    //修改
    public boolean updata(String username, String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql1 = "update user set password=? where username=?";
        Object obj[] = {password,username};
        sdb.execSQL(sql1,obj);
        return  true;
    }
}
