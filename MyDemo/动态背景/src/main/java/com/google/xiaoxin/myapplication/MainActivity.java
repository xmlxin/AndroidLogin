package com.google.xiaoxin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    private GifImageView test_gif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test_gif = (GifImageView) findViewById(R.id.test_gif);
        //设置图片数据
        test_gif.setImageResource(R.drawable.ui_bg_03);
        final android.widget.MediaController mediaController = new android.widget.MediaController(this);
        mediaController.setMediaPlayer((GifDrawable) test_gif.getDrawable());
        test_gif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaController.show();
                //Snackbar.make(test_gif, "可以点击哦", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
