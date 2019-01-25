package com.wzhy.recyclerviewstu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    protected View.OnClickListener mOnClickListener;

    protected View.OnClickListener getOnClickListener() {
        if (mOnClickListener == null) {
            mOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BaseActivity.this.onClick(v);
                }
            };
        }
        return mOnClickListener;
    }

    protected View.OnClickListener getOnNoDoubleClickListener() {
        if (mOnClickListener == null) {
            mOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setEnabled(false);
                    BaseActivity.this.onClick(v);
                    v.setEnabled(true);
                }
            };
        }
        return mOnClickListener;
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListeners();

    public abstract void onClick(View v);


}
