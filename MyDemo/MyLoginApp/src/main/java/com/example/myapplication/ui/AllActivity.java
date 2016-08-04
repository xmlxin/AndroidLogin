package com.example.myapplication.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;

/**
 * 登录界面特效集合
 * Created by xiaoxin on 2016/7/25.
 */
public class AllActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] items = getResources().getStringArray(R.array.sample_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);父类什么也没有做，注释掉
        switch (position) {
            case 0:
                startSignInActivity(true);
                break;
            case 1:
                startSignInActivity(false);
                break;
            case 2:
                startLogin2Activity();
                break;
            case 3:
                startLogin3Activity();
                break;
            case 4:
                startLogin4Activity();
                break;
        }
    }

    private void startSignInActivity(boolean isEndlessMode) {
        Intent intent = new Intent(this, LoginActivity1.class);
        intent.putExtra(LoginActivity1.EXTRAS_ENDLESS_MODE, isEndlessMode);
        startActivity(intent);
    }

    private void startLogin2Activity() {
        startActivity(new Intent(this,LoginActivity2.class));
    }

    private void startLogin3Activity() {
        startActivity(new Intent(this,LoginActivity3.class));
    }

    private void startLogin4Activity() {
        startActivity(new Intent(this,LoginActivity4.class));
    }
}
