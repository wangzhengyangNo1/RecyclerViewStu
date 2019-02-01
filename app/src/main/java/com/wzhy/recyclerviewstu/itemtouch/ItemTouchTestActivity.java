package com.wzhy.recyclerviewstu.itemtouch;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v13.view.DragStartHelper;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

import com.wzhy.recyclerviewstu.BaseActivity;
import com.wzhy.recyclerviewstu.R;
import com.wzhy.recyclerviewstu.base.sample.ItemEntity;
import com.wzhy.recyclerviewstu.divides.GridDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ItemTouchTestActivity extends BaseActivity {

    private RecyclerView mRv;
    private ItemTouchTestAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private ItemTouchHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_use);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("拖拽和滑动删除");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_horizontal, menu);
        menu.add(0, LINEAR_HORIZONTAL, 0, "Linear Horizontal");
        menu.add(0, LINEAR_VERTICAL, 1, "Linear Vertical");
        menu.add(0, GRID_HORIZONTAL, 2, "Grid Horizontal");
        menu.add(0, GRID_VERTICAL, 3, "Grid Vertical");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        final Intent intent = new Intent(this, ItemTouchTestActivity.class);
        intent.putExtra("layout_type", itemId);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initView() {
        mRv = (RecyclerView) findViewById(R.id.simple_use_rv);
        initRv(mLayoutType);

        mAdapter.setOnStartDragListener(new ItemTouchTestAdapter.OnStartDragListener() {
            @Override
            public void onStartDrag(RecyclerView.ViewHolder holder) {
                final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(70);
                mHelper.startDrag(holder);
            }
        });

//        mAdapter.setOnStartSwipeListener(new ItemTouchTestAdapter.OnStartSwipeListener() {
//            @Override
//            public void onStartSwipe(RecyclerView.ViewHolder holder) {
//                mHelper.startSwipe(holder);
//            }
//        });


    }

    private int mLayoutType = GRID_VERTICAL;


    private void initRv(int type) {
        mAdapter = new ItemTouchTestAdapter();
        mAdapter.setDataList(getDataList());
        if (mHelper != null) {
            mRv.removeItemDecoration(mHelper);
        }

        switch (type) {
            case LINEAR_HORIZONTAL:
                mLayoutManager = new LinearLayoutManager(ItemTouchTestActivity.this, OrientationHelper.HORIZONTAL, false);
                break;
            case LINEAR_VERTICAL:
                mLayoutManager = new LinearLayoutManager(ItemTouchTestActivity.this, OrientationHelper.VERTICAL, false);
                break;
            case GRID_HORIZONTAL:
                mLayoutManager = new GridLayoutManager(ItemTouchTestActivity.this, 3, OrientationHelper.HORIZONTAL, false);
                break;
            case GRID_VERTICAL:
                mLayoutManager = new GridLayoutManager(ItemTouchTestActivity.this, 3, OrientationHelper.VERTICAL, false);
                break;
        }

        mRv.setLayoutManager(mLayoutManager);
        mHelper = new ItemTouchHelper(new SimpleItemTouchCallback(mAdapter));
        mHelper.attachToRecyclerView(mRv);
        mRv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }


    @Override
    protected void initData() {
        final Intent intent = getIntent();
        if (intent != null) {
            mLayoutType = intent.getIntExtra("layout_type", GRID_VERTICAL);
        }
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
            int id = i;
            dataList.add(new ItemEntity(id, "Item " + id));
        }
        return dataList;
    }
}
