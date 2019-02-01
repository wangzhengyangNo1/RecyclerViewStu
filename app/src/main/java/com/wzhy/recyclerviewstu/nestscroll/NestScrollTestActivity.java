package com.wzhy.recyclerviewstu.nestscroll;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wzhy.recyclerviewstu.BaseActivity;
import com.wzhy.recyclerviewstu.R;
import com.wzhy.recyclerviewstu.base.sample.ItemEntity;
import com.wzhy.recyclerviewstu.simple.SimpleUseAdapter;

import java.util.ArrayList;
import java.util.List;

public class NestScrollTestActivity extends BaseActivity {

    private RecyclerView mSimpleUseRv;
    private NestScrollTestAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_scroll_test);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("RecyclerView的简单使用");
            actionBar.setIcon(R.mipmap.ic_launcher);
        }


        initData();
        initView();
        setListeners();

    }


    private static final int LINEAR_HORIZONTAL = 0;
    private static final int LINEAR_VERTICAL = 1;
    private static final int GRID_HORIZONTAL = 2;
    private static final int GRID_VERTICAL = 3;
    private static final int STAGGERED_HORIZONTAL = 4;
    private static final int STAGGERED_VERTICAL = 5;


    @Override
    protected void initView() {
        mSimpleUseRv = (RecyclerView) findViewById(R.id.rv);
        initRv(STAGGERED_VERTICAL);
    }


    private void initRv(int type) {
        mAdapter = new NestScrollTestAdapter();
        mAdapter.setDataList(getDataList());
        switch (type) {
            case LINEAR_HORIZONTAL:
                mLayoutManager = new LinearLayoutManager(NestScrollTestActivity.this, OrientationHelper.HORIZONTAL, false);
                break;
            case LINEAR_VERTICAL:
                mLayoutManager = new LinearLayoutManager(NestScrollTestActivity.this, OrientationHelper.VERTICAL, false);
                break;
            case GRID_HORIZONTAL:
                mLayoutManager = new GridLayoutManager(NestScrollTestActivity.this, 3, OrientationHelper.HORIZONTAL, false);
                break;
            case GRID_VERTICAL:
                mLayoutManager = new GridLayoutManager(NestScrollTestActivity.this, 3, OrientationHelper.VERTICAL, false);
                break;
            case STAGGERED_HORIZONTAL:
                mLayoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.HORIZONTAL);
                mAdapter.setStaggered(OrientationHelper.HORIZONTAL, true);
                break;
            case STAGGERED_VERTICAL:
                mLayoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
                mAdapter.setStaggered(OrientationHelper.VERTICAL, true);
                break;
        }
        mSimpleUseRv.setLayoutManager(mLayoutManager);
        mSimpleUseRv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }


    @Override
    protected void initData() {

    }


    @Override
    protected void setListeners() {

    }

    @Override
    public void onClick(View v) {

    }

    private static final int PAGE_SIZE = 20;

    private List<ItemEntity> getDataList() {
        ArrayList<ItemEntity> dataList = new ArrayList<>();
        for (int i = 0; i < PAGE_SIZE; i++) {
            int id =  i;
            dataList.add(new ItemEntity(id, "Item " + id));
        }
        return dataList;
    }
}
