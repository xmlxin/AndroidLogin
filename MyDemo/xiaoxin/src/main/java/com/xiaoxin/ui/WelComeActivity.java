package com.xiaoxin.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiaoxin.feng.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/3/23.
 */
public class WelComeActivity extends AppCompatActivity {
    protected static final int LOAD_IMAGE = 1;
    protected static final int LOAD_ERROR = 2;
    // 服务器所有图片的路径
    private List<String> paths;
    private ImageView iv;
    /**
     * 当前的位置
     */
    private int currentPosition = 0;

    //1.创建一个消息处理器
    private Handler handler = new Handler(){
        //3.处理消息
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_IMAGE:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    iv.setImageBitmap(bitmap);
                    break;

                case LOAD_ERROR:
                    Toast.makeText(getApplicationContext(), (String)msg.obj, 0).show();
                    break;
            }

        };
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        //链接服务器获取所有图片的目录信息
        loadAllImagePath();
    }


    /**
     * 开始加载图标,在从服务器获取完毕资源路径之后执行
     */

    /**
     * 通过路径获取图片资源
     *
     * @param path
     *            图片的路径
     * @return
     */

    /**
     * 获取全部图片资源的路径
     */
    public void loadAllImagePath(){
        //浏览器发送一个get清秋就可以把服务器的资源读取出来
        //用代码模拟一个http的get请求
       try {
           // 1得到服务器资源的路径
           URL uil = new URL("http://192.168.15.60:8989/info.txt");
           //2通过这个路径，打开浏览器把资源获取出来
           HttpURLConnection conn  = (HttpURLConnection) uil.openConnection();
           //3 设置请求方式为get请求
           conn.setRequestMethod("GET");
           //为了有一个更好的用户ui提醒，号区服务器返回的状态码
          int code =  conn.getResponseCode();  //200 OK;  404 资源没有找到  ；503 服务器内部错误 302重定向
           if(code == 200) {
           //4获取服务器返回的流
           InputStream is = conn.getInputStream();

           File file = new File(getCacheDir(),"info.html");
           FileOutputStream fos = new FileOutputStream(file);
           byte[] buff = new byte[1024];
           int len = 0;
           while((len = is.read(buff))!= -1) {
               fos.write(buff,0,len);
           }
           is.close();
           fos.close();
           }else if(code == 404) {
               Toast.makeText(this,"获取失败，没有找到资源",Toast.LENGTH_SHORT).show();
           }else if(code == 503) {
               Toast.makeText(this,"获取失败，服务器内部错误",Toast.LENGTH_SHORT).show();
           }else if(code == 302) {
               Toast.makeText(this,"获取失败，重定向",Toast.LENGTH_SHORT).show();
           }else {
               Toast.makeText(this,"服务器异常",Toast.LENGTH_SHORT).show();
           }
       }catch(Exception e) {
           Toast.makeText(this,"获取失败",Toast.LENGTH_SHORT).show();
       }
    }
}
