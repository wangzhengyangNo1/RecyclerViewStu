package com.wzhy.recyclerviewstu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wzhy.recyclerviewstu.simple.SimpleUseActivity;

public class MainActivity extends BaseActivity {

    private TextView mSimpleUseTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListeners();
    }

    @Override
    protected void initView() {
        mSimpleUseTv = (TextView) findViewById(R.id.simple_use_tv);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListeners() {
        mSimpleUseTv.setOnClickListener(getOnClickListener());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.simple_use_tv:
                intent.setClass(MainActivity.this, SimpleUseActivity.class);
                break;
        }
        startActivity(intent);
    }
}
