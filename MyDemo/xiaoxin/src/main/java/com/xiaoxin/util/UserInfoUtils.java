package com.xiaoxin.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/22.
 */
public class UserInfoUtils {
    /*
    //保存用户名和密码的业务
    public static boolean saveInfo(Context context,String name,String pwd) {
        try {
            String result = name + "##" + pwd;
            //创建file类指定要存储的位置
            String path = context.getFilesDir().getPath();
           // File file = new File("/data/data/com.xiaoxin.feng/info.txt");
            File file = new File(path,"info.txt");
            //创建一个文件输出流
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(result.getBytes());
            fos.close();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //读取data洗info.txt信息
    public static Map<String,String> readInfo(Context context) {
        //定义map
        try {
            Map<String, String> maps = new HashMap<String, String>();
            String path = context.getFilesDir().getPath();
           // File file = new File("/data/data/com.xiaoxin.feng/info.txt");
            File file = new File(path,"info.txt");
            FileInputStream fis = new FileInputStream(file);
            BufferedReader bufr = new BufferedReader(new InputStreamReader(fis));
            String cont = bufr.readLine(); //读取一行数据
            //切割字符串
            String[] splits = cont.split("##");
            String name = splits[0];
            String pwd = splits[1];
            maps.put("name",name);
            maps.put("pwd", pwd);
            fis.close();
            return maps;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    */
}
