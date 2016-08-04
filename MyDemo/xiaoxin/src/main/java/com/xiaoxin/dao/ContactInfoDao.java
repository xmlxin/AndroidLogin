package com.xiaoxin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.xiaoxin.db.MyDBOpenHelper;

/** 联系人数据库的访问类
 * Created by Administrator on 2016/3/25.
 */
public class ContactInfoDao {

    //数据库打开的帮助类
    private MyDBOpenHelper helper;
    //在构造方法里面完成类的初始化
    public ContactInfoDao(Context context) {
        helper = new MyDBOpenHelper(context);
    }

    public boolean add(String phone,String pwd) {
        SQLiteDatabase db = helper.getWritableDatabase();
       // db.execSQL("insert into stu (name,phone) values (?,?)",new Object[]{name,phone});
        ContentValues values = new ContentValues();
        values.put("phone",phone);
        values.put("pwd",pwd);
        //内部是组品sql语句实现的
       long rowid = db.insert("stu",null,values);//返回的是一个数据库的行号，-1代表添加失败
        //操作完咬记得释放数据库资源
        db.close();
        if(rowid == -1) {
            return false;
        }else{
            return true;
        }

    }
    //删除一条记录
    public int  delete(String name) {
        SQLiteDatabase db = helper.getWritableDatabase();
       //db.execSQL("delete from stu where name = ?",new Object[]{name});
       int rowcount = db.delete("stu", "name=?", new String[]{name});//返回0代表没有删除，返回int值代表删除了几行数据
        //操作完咬记得释放数据库资源
        db.close();
        return rowcount;
    }
    //修改记录
    public int update(String newphone,String name) {
        SQLiteDatabase db = helper.getWritableDatabase();
       // db.execSQL("update stu set phone = ? where name = ?",new Object[]{newphone,name});
        ContentValues values = new ContentValues();
        values.put("phone", newphone);
       int rowcount =  db.update("stu", values, "name=?", new String[]{name});
        //操作完咬记得释放数据库资源
        db.close();
        return rowcount;
    }
    //查询记录
    public boolean getPhoneNumber(String phone,String pwd) {
       // String phone = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        //查询用的方法，有返回值
       Cursor cursor = db.rawQuery("select * from stu where phone = ? and pwd = ?", new String[]{phone,pwd});
        //Cursor cursor = db.query("stu", new String[]{phone,pwd}, "phone=? and pwd=?", new String[]{phone,pwd}, null, null, null);
        if(cursor.moveToNext()) { //如果光标可以移动到下一位，代表可以查询到数据
            phone =  cursor.getString(0); //cursor.getString(0);第0列
            pwd =  cursor.getString(0);
            //关闭掉光标，释放资源
            cursor.close();
            //操作完咬记得释放数据库资源
            db.close();
            return true;
        }else {
            return false;
        }

    }
    //查询记录
    public boolean getPwd(String pwd) {
      //  String phone = null;
        SQLiteDatabase db = helper.getReadableDatabase();
        //查询用的方法，有返回值
        //Cursor cursor = db.rawQuery("select phone from stu where name = ?", new String[]{name});
        Cursor cursor = db.query("stu",new String[]{pwd},"pwd=?",new String[]{pwd},null,null,null);
        if(cursor.moveToNext()) { //如果光标可以移动到下一位，代表可以查询到数据
            pwd =  cursor.getString(0); //cursor.getString(0);第0列
            cursor.close();
            return true;
        }else {
            return false;
        }
        //关闭掉光标，释放资源

        //操作完咬记得释放数据库资源


    }
}
