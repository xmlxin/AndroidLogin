package com.example.myapplication.utils;

import android.os.Handler;

import com.dd.processbutton.ProcessButton;

import java.util.Random;

public class ProgressGenerator {

    /**
     * 加载进度的监听器
     */
    public interface OnCompleteListener {

        //加载完调用的方法
        public void onComplete();
    }

    private OnCompleteListener mListener;

    private Random random = new Random();
    private int mProgress;

    //在构造方法里面传入一个监听器
    public ProgressGenerator(OnCompleteListener listener) {
        mListener = listener;
    }

    public void start(final ProcessButton button) {
        final Handler handler = new Handler();
        //发送延迟消息
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //这里为什么可以直接使用mProgress，如果mProgress是String类型的呢？Integer？对象？成员变量和局部变量的区别
                mProgress += 10;
                button.setProgress(mProgress);
                if (mProgress < 100) {
                    //发送延迟消息 Runnable r, long delayMillis，这里this代表什么？底层调用sendMessageDelayed(getPostMessage(r), delayMillis);
                    handler.postDelayed(this, generateDelay());
                } else {
                    //mProgress=100的时，调用监听的方法
                    mListener.onComplete();
                }
            }
        }, generateDelay());
    }

    /**
     * 发送延迟消息时，时间值不是固定的毫秒值，而是一个0-1之间的随机数
     * @return
     */
    private int generateDelay() {
        return random.nextInt(1000);
    }
}
