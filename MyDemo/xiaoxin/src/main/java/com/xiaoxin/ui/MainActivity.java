package com.xiaoxin.ui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.xiaoxin.dao.ContactInfoDao;
import com.xiaoxin.feng.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/3/22.
 */

public class MainActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_ischeck;
    private Drawable drawable1;
    private Drawable drawable2;
    private Drawable drawable3;
    private Drawable drawable4;
    private Drawable[] drawables = new Drawable[4];
    private View view ;
    private ImageView imageView;

    private ContactInfoDao contactInfoDao;
    private Random random = new Random();
    private LinearLayout linearLayout;
    WelComeActivity welComeActivity = new WelComeActivity();


    /**
     * 修改登录动画
     */
    private Timer mTimer = new Timer();
    private Random mRandom = new Random();
    int[] icons = new int[]{R.drawable.ui_001,R.drawable.ui_002,R.drawable.ui_003};
    private LinearLayout mLinearLayout;

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //声明数据库的文件类
        contactInfoDao = new ContactInfoDao(this);

      /*  drawable1 = getResources().getDrawable(R.mipmap.ui_001);
        drawable2 = getResources().getDrawable(R.mipmap.ui_002);
        drawable3 = getResources().getDrawable(R.mipmap.ui_003);
        drawable4 = getResources().getDrawable(R.mipmap.ui_004);
        drawables[0] = drawable1;
        drawables[1] = drawable2;
        drawables[2] = drawable3;
        drawables[3] = drawable4;
*/
        mLinearLayout = (LinearLayout)findViewById(R.id.line1);

        changeBackground();
        //linearLayout.setBackgroundDrawable(drawables[random.nextInt(4)]);

      /*  drawables[random.nextInt(4)];
         imageView.setBackgroundResource(R.drawable.ui_001);
          linearLayout.getBackground();
         view.setBackground(drawables[random.nextInt(5)]);
         linearLayout = view.setBackgroundDrawable(drawables[random.nextInt(5)]);
          Drawable d=Drawable.createFromPath(drawable1);
        temp.setBackgroundDrawable(d);*/

        //1.找到我们关心的控件
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
         cb_ischeck = (CheckBox) findViewById(R.id.cb_ischeck);
      /*  Map<String,String> map = UserInfoUtils.readInfo(MainActivity.this);
        if (map !=null) {
            String name = map.get("name");
            String pwd = map.get("pwd");
            et_username.setText(name);
            et_password.setText(pwd);
        }
        */
    }

    public void add_name(View view) {
        //1.声明一个意图对象
        Intent intent = new Intent();
        //2.指定清单文件中声明的动作
        intent.setAction("com.xiaoxin.dao.addUserAndPwd");
        //3.指定清单文件中声明的类别
        intent.addCategory("android.intent.category.DEFAULT");
        //开启界面
        startActivity(intent);
    }

    /**
     * 登录校验
     * @param view
     */
    public void login(View view) {
        //2.1获取用户名和密码
        String phone =  et_username.getText().toString().trim();
        String pwd =  et_password.getText().toString().trim();
        //判断name和pwd是否为空
        if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(MainActivity.this,"用户名或密码为空",Toast.LENGTH_SHORT).show();
        }else {

            boolean a = contactInfoDao.getPhoneNumber(phone, pwd);
            //  boolean b = contactInfoDao.getPwd(pwd);
            if(a) {
                Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                //1.声明一个意图对象
                Intent intent = new Intent();
                //2.指定清单文件中声明的动作
                intent.setAction("com.xiaoxin.ui.openWelCome");
                //3.指定清单文件中声明的类别
                intent.addCategory("android.intent.category.DEFAULT");
                //开启界面
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
            }
            // Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();

            //把用户名和密码存储起来
            // boolean result = UserInfoUtils.saveInfo(MainActivity.this,name,pwd);



        }
    }

    public void lookForPwd(View view) {
        Intent intent = new Intent();
        //2.指定清单文件中声明的动作
        intent.setAction("com.xiaoxin.ui.lookForPwd");
        //3.指定清单文件中声明的类别
        intent.addCategory("android.intent.category.DEFAULT");
        //开启界面
        startActivity(intent);

    }

    /**
     * 动态改变bg
     */
    public void changeBackground() {

        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mLinearLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        //mLinearLayout.setBackgroundDrawable();
                        //渐变动画
                       /* AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
                        animAlpha.setDuration(2000);
                        animAlpha.setFillAfter(true);

                        mLinearLayout.startAnimation(animAlpha);*/
                        mLinearLayout.setBackgroundResource(icons[mRandom.nextInt(3)]);
                    }
                });
            }
        }, 2000, 2000);
    }

    /**
     * 销毁的时候移除mTimer
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }
}
