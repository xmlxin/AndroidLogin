package com.xiaoxin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/3/25.
 */
public class MyDBOpenHelper extends SQLiteOpenHelper {

    public MyDBOpenHelper(Context context) {
        // public MyDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        //第一个参数是上下文
        //第二个参数是数据库的名字
        //第三个参数null代表默认的游标工厂
        //第四个参数是数据库的版本号,版本号只能变大不能变小
        super(context, "xiaoxin.db", null, 1);
    }
    @Override
    //当数据库第一次被调用的时候调用的方法，适合表结构的初始化
    public void onCreate(SQLiteDatabase db) {
        //执行sql语句
        db.execSQL("create table stu (id integer primary key autoincrement,phone varchar(20),pwd varchar(20))");
    }

    @Override
    //当数据库更新的时候调用的方法，如果旧的表结构定义的不合理，，进行修改数据
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table stu add count varchar(20)");//给表增加一列
    }


}
