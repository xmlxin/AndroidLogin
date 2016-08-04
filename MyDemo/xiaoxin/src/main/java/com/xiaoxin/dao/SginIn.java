package com.xiaoxin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.xiaoxin.db.MyDBOpenHelper;
import com.xiaoxin.feng.R;
import com.xiaoxin.util.UserInfoUtils;

/**
 * Created by Administrator on 2016/3/26.
 */
public class SginIn extends AppCompatActivity  {

    EditText addName;
    EditText addPwd;
    private ContactInfoDao contactInfoDao;

    public SginIn() {}
    //数据库打开的帮助类
    private MyDBOpenHelper helper;
    //在构造方法里面完成类的初始化
    public SginIn(Context context) {
        helper = new MyDBOpenHelper(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_name_activity);
        contactInfoDao = new ContactInfoDao(this);

        //声明数据库的文件类
        MyDBOpenHelper helper = new MyDBOpenHelper(this);
        //关键代码，必须通过帮助类。获取一个数据库文件
        helper.getWritableDatabase();
        addName = (EditText) findViewById(R.id.et_add_user_name);
        addPwd = (EditText)findViewById(R.id.et_add_user_pwd);
    }

   public void addUserAndPwd(View view) {
       String phone =  addName.getText().toString().trim();
       String pwd =  addPwd.getText().toString().trim();

       //判断name和pwd是否为空
       if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
           Toast.makeText(SginIn.this, "用户名或密码为空", Toast.LENGTH_SHORT).show();
       }else {
         boolean result =  contactInfoDao.add(phone,pwd);
           if(result) {
               Toast.makeText(SginIn.this, "注册成功", Toast.LENGTH_SHORT).show();
               //1.声明一个意图对象
               Intent intent = new Intent();
               //2.指定清单文件中声明的动作
               intent.setAction("com.xiaoxin.ui.openWelCome");
               //3.指定清单文件中声明的类别
               intent.addCategory("android.intent.category.DEFAULT");
               //开启界面
               startActivity(intent);
           }else {
               Toast.makeText(SginIn.this, "注册失败", Toast.LENGTH_SHORT).show();
           }

       }

   }


}
