package com.example.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.LinearLayout;

import com.example.myapplication.R;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity3 extends AppCompatActivity {

    private Timer mTimer = new Timer();
    //背景
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login3);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearLayout1);
        mLinearLayout.setBackgroundResource(R.drawable.login_bg1);

       /* AlphaAnimation animAlpha = new AlphaAnimation(0.6f, 1.0f);
        animAlpha.setDuration(3000);
        animAlpha.setRepeatCount(Animation.INFINITE);//无限播放
        animAlpha.setRepeatMode(Animation.REVERSE);//设置播放模式
        animAlpha.setFillAfter(true);//让动画停留在最后一帧*/
       /* Animation setIn = AnimationUtils.loadAnimation(this, R.anim.push_bottom_in);
        mLinearLayout.startAnimation(setIn);
        mLinearLayout.clearAnimation();
        Animation setOut = AnimationUtils.loadAnimation(this, R.anim.push_bottom_out);

        mLinearLayout.startAnimation(setOut);*/

/*
        AlphaAnimation animAlpha1 = new AlphaAnimation(1.0f, 0.1f);
        animAlpha1.setDuration(100);
        animAlpha1.setFillAfter(true);
        mLinearLayout.startAnimation(animAlpha1);

        AlphaAnimation animAlpha = new AlphaAnimation(0.1f, 1.0f);
        animAlpha.setDuration(2000);
        animAlpha.setFillAfter(true);
        mLinearLayout.startAnimation(animAlpha);*/



        }


    public void changAnimal() {
        mLinearLayout.setBackgroundResource(R.drawable.login_bg1);
    }
    /*
    * * 动态改变bg
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
                       /* AlphaAnimation animAlpha = new AlphaAnimation(0.2f, 0.8f);
                        animAlpha.setDuration(2000);
                        animAlpha.setFillAfter(true);

                        mLinearLayout.startAnimation(animAlpha);
                        mLinearLayout.clearAnimation();
                        AlphaAnimation animAlpha1 = new AlphaAnimation(0.8f, 0.2f);
                        animAlpha.setDuration(2000);
                        animAlpha.setFillAfter(true);

                        mLinearLayout.startAnimation(animAlpha1);*/

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
