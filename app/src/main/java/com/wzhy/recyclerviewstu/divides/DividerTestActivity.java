package com.wzhy.recyclerviewstu.divides;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
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

public class DividerTestActivity extends BaseActivity {

    private RecyclerView mSimpleUseRv;
    private DividerTestAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private DividerItemDecoration mLinearDivider;
    private GridDividerItemDecoration mGridDivider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_use);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_horizontal, menu);
        menu.add(0, LINEAR_HORIZONTAL, 0, "Linear Horizontal");
        menu.add(0, LINEAR_VERTICAL, 1, "Linear Vertical");
        menu.add(0, GRID_HORIZONTAL, 2, "Grid Horizontal");
        menu.add(0, GRID_VERTICAL, 3, "Grid Vertical");
        menu.add(0, STAGGERED_HORIZONTAL, 4, "Staggered Horizontal");
        menu.add(0, STAGGERED_VERTICAL, 5, "Staggered Vertical");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        initRv(itemId);

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        mSimpleUseRv = (RecyclerView) findViewById(R.id.simple_use_rv);
        mLinearDivider = new DividerItemDecoration(this, RecyclerView.HORIZONTAL);
        mGridDivider = new GridDividerItemDecoration(this);
        initRv(LINEAR_VERTICAL);
    }


    private void initRv(int type) {
        mAdapter = new DividerTestAdapter();
        mAdapter.setDataList(getDataList());
        for (int i = 0; i < mSimpleUseRv.getItemDecorationCount(); i++) {
            mSimpleUseRv.removeItemDecorationAt(0);
        }
        switch (type) {
            case LINEAR_HORIZONTAL:
                mLinearDivider.setOrientation(RecyclerView.HORIZONTAL);
                mLayoutManager = new LinearLayoutManager(DividerTestActivity.this, OrientationHelper.HORIZONTAL, false);
                mSimpleUseRv.addItemDecoration(mLinearDivider);
                break;
            case LINEAR_VERTICAL:
                mLinearDivider.setOrientation(RecyclerView.VERTICAL);
                mLayoutManager = new LinearLayoutManager(DividerTestActivity.this, OrientationHelper.VERTICAL, false);
                mSimpleUseRv.addItemDecoration(mLinearDivider);
                break;
            case GRID_HORIZONTAL:
                mLayoutManager = new GridLayoutManager(DividerTestActivity.this, 3, OrientationHelper.HORIZONTAL, false);
                mSimpleUseRv.addItemDecoration(mGridDivider);
                break;
            case GRID_VERTICAL:
                mLayoutManager = new GridLayoutManager(DividerTestActivity.this, 3, OrientationHelper.VERTICAL, false);
                mSimpleUseRv.addItemDecoration(mGridDivider);
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
