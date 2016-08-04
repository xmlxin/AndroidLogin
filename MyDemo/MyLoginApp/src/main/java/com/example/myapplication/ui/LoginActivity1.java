package com.example.myapplication.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.myapplication.R;
import com.example.myapplication.hert.MyHert;
import com.example.myapplication.utils.ProgressGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity1 extends Activity implements ProgressGenerator.OnCompleteListener{

    public static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";

    private EditText mEditEmail;
    private EditText mEditPassword;
    private ProgressGenerator mProgressGenerator;
    private ActionProcessButton mBtnSignIn;

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    private Timer mTimer = new Timer();
    private Random mRandom = new Random();
    int[] icons = new int[]{R.drawable.ui_001,R.drawable.ui_002,R.drawable.ui_003,R.drawable.ui_004,R.drawable.ui_005};
    //根布局
    private LinearLayout mLinearLayout;
    //头像
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        //头像
        mImageView = (ImageView) findViewById(R.id.iv_head);
       // mImageView.setColorFilter(R.color.colorTransparent);
        //使用ImageLoader加载图片
        Resources resources = getResources();
        resources.getDrawable(R.drawable.head_img);
        ImageLoader.getInstance().init( ImageLoaderConfiguration.createDefault(this));
        //ImageLoader.getInstance().displayImage( "assets://head_img", R.drawable.head_img,ImageLoaderOptions.round_options);
        //ImageLoader.getInstance().displayImage("drawable://"+R.drawable.head_img,  mImageView, ImageLoaderOptions.round_options);
        changeBackground();

        mEditEmail = (EditText) findViewById(R.id.username);
        mEditPassword = (EditText) findViewById(R.id.password);

        //工具类
        mProgressGenerator = new ProgressGenerator(this);
        mBtnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);

        final TextInputLayout usernameWrapper =  (TextInputLayout) findViewById(R.id.usernameWrapper);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.passwordWrapper);

        Bundle extras = getIntent().getExtras();
        if(extras != null && extras.getBoolean(EXTRAS_ENDLESS_MODE)) { //true进来
            mBtnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        } else {
            mBtnSignIn.setMode(ActionProcessButton.Mode.PROGRESS);     //false进来
        }
         /* Intent intent = getIntent();
        if(intent != null && intent.getBooleanExtra("EXTRAS_ENDLESS_MODE",false)) {
            btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        } else {
            btnSignIn.setMode(ActionProcessButton.Mode.PROGRESS);
        }*/
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
                String username = usernameWrapper.getEditText().getText().toString();
                String password = usernameWrapper.getEditText().getText().toString();
                if (!validateEmail(username)) {
                    usernameWrapper.setError("邮箱格式不正确");

                } else if (!validatePassword(password)) {
                    passwordWrapper.setError("密码错误");
                } else {
                    usernameWrapper.setErrorEnabled(false);
                    passwordWrapper.setErrorEnabled(false);
                    doLogin();
                }
                /*//调用ProgressGenerator的start()，加载进度
                mProgressGenerator.start(mBtnSignIn);
                //设置false使btnSignIn，editEmail，editPassword失去焦点，不能点击，
                mBtnSignIn.setEnabled(false);
                mEditEmail.setEnabled(false);
                mEditPassword.setEnabled(false);*/
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void doLogin() {
        //调用ProgressGenerator的start()，加载进度
        mProgressGenerator.start(mBtnSignIn);
        //设置false使btnSignIn，editEmail，editPassword失去焦点，不能点击，
        mBtnSignIn.setEnabled(false);
        mEditEmail.setEnabled(false);
        mEditPassword.setEnabled(false);
        //登录的时候把mTimer移除
        mTimer.cancel();
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /**
     * 验证密码
     * @param password
     * @return
     */
    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

    /**
     * 隐藏虚拟键盘
     */
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    /**
     * 登录成功的回调
     */
    @Override
    public void onComplete() {

        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
        //handler发送延时任务
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(LoginActivity1.this, MyHert.class));
                finish();//把mainActivity关闭
            }
        }, 1000);

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
                        //渐变
                       /* AlphaAnimation animAlpha = new AlphaAnimation(0, 1);
                        animAlpha.setDuration(2000);
                        animAlpha.setFillAfter(true);

                        mLinearLayout.startAnimation(animAlpha);*/
                        mLinearLayout.setBackgroundResource(icons[mRandom.nextInt(5)]);
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
