package com.wzhy.recyclerviewstu.headerandfooter;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wzhy.recyclerviewstu.BaseActivity;
import com.wzhy.recyclerviewstu.R;
import com.wzhy.recyclerviewstu.base.sample.ItemEntity;
import com.wzhy.recyclerviewstu.simple.SimpleUseAdapter;

import java.util.ArrayList;
import java.util.List;

public class HeaderFooterActivity extends BaseActivity {

    private RecyclerView mHeaderFooterRv;
    private SimpleUseAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_footer);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("HeaderView和FooterView");
            actionBar.setIcon(R.mipmap.ic_launcher);
        }


        initData();
        initView();
        setListeners();

    }


    private static final int LINEAR = 1;
    private static final int GRID = 2;
    private static final int STAGGERED = 3;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_horizontal, menu);
        menu.add(0, LINEAR, 1, "Linear");
        menu.add(0, GRID, 2, "Grid");
        menu.add(0, STAGGERED, 3, "Staggered");
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
        mHeaderFooterRv = (RecyclerView) findViewById(R.id.header_footer_rv);
        initRv(LINEAR);
    }


    private void initRv(int type) {
        mAdapter = new SimpleUseAdapter();
        mAdapter.setDataList(getDataList());
        switch (type) {
            case LINEAR:
                mLayoutManager = new LinearLayoutManager(HeaderFooterActivity.this, OrientationHelper.VERTICAL, false);
                break;
            case GRID:
                mLayoutManager = new GridLayoutManager(HeaderFooterActivity.this, 3, OrientationHelper.VERTICAL, false);
                break;
            case STAGGERED:
                mLayoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
                mAdapter.setStaggered(OrientationHelper.VERTICAL, true);
                break;
        }
        mHeaderFooterRv.setLayoutManager(mLayoutManager);
        HeaderAndFooterWrapper<ItemEntity> adapter = new HeaderAndFooterWrapper<ItemEntity>(mAdapter);
        View headerView1 = LayoutInflater.from(HeaderFooterActivity.this).inflate(R.layout.item_header_view_1, mHeaderFooterRv, false);
        View headerView2 = LayoutInflater.from(HeaderFooterActivity.this).inflate(R.layout.item_header_view_1, mHeaderFooterRv, false);
        View footerView1 = LayoutInflater.from(HeaderFooterActivity.this).inflate(R.layout.item_footer_view_1, mHeaderFooterRv, false);
        View footerView2 = LayoutInflater.from(HeaderFooterActivity.this).inflate(R.layout.item_footer_view_1, mHeaderFooterRv, false);
        adapter.addHeaderView(headerView1);
        adapter.addHeaderView(headerView2);
        adapter.addFooterView(footerView1);
        adapter.addFooterView(footerView2);
        mHeaderFooterRv.setAdapter(adapter);
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
