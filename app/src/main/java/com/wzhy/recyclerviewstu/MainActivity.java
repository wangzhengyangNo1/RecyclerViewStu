package com.wzhy.recyclerviewstu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wzhy.recyclerviewstu.divides.DividerTestActivity;
import com.wzhy.recyclerviewstu.headerandfooter.HeaderFooterActivity;
import com.wzhy.recyclerviewstu.itemtouch.ItemTouchTestActivity;
import com.wzhy.recyclerviewstu.nestscroll.NestScrollTestActivity;
import com.wzhy.recyclerviewstu.simple.SimpleUseActivity;

public class MainActivity extends BaseActivity {

    private TextView mSimpleUseTv;
    private TextView mHeaderFooterTv;
    private TextView mDividerTv;
    private TextView mItemTouchTv;
    private TextView mClickListenerTv;
    private TextView mNestScrollTv;
    private TextView mSwipeRefreshTv;
    private TextView mRefreshLoadMoreTv;
    private TextView mComplexLayoutTv;

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
        mHeaderFooterTv = (TextView) findViewById(R.id.header_footer_tv);
        mDividerTv = (TextView) findViewById(R.id.divider_tv);
        mItemTouchTv = (TextView) findViewById(R.id.item_touch_tv);
        mClickListenerTv = (TextView) findViewById(R.id.click_listener_tv);
        mNestScrollTv = (TextView) findViewById(R.id.nest_scroll_tv);
        mSwipeRefreshTv = (TextView) findViewById(R.id.swipe_refresh_tv);
        mRefreshLoadMoreTv = (TextView) findViewById(R.id.refresh_load_more_tv);
        mComplexLayoutTv = (TextView) findViewById(R.id.complex_layout_tv);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListeners() {
        mSimpleUseTv.setOnClickListener(getOnNoDoubleClickListener());
        mHeaderFooterTv.setOnClickListener(getOnNoDoubleClickListener());
        mDividerTv.setOnClickListener(getOnNoDoubleClickListener());
        mItemTouchTv.setOnClickListener(getOnNoDoubleClickListener());
//        mClickListenerTv.setOnClickListener(getOnNoDoubleClickListener());
        mNestScrollTv.setOnClickListener(getOnNoDoubleClickListener());
//        mSwipeRefreshTv.setOnClickListener(getOnNoDoubleClickListener());
//        mRefreshLoadMoreTv.setOnClickListener(getOnNoDoubleClickListener());
//        mComplexLayoutTv.setOnClickListener(getOnNoDoubleClickListener());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.simple_use_tv://基本使用
                intent.setClass(MainActivity.this, SimpleUseActivity.class);
                break;
            case R.id.header_footer_tv://添加HeaderView和FooterView
                intent.setClass(MainActivity.this, HeaderFooterActivity.class);
                break;
                case R.id.divider_tv://分割线
                intent.setClass(MainActivity.this, DividerTestActivity.class);
                break;
            case R.id.item_touch_tv://拖拽和滑动删除
                intent.setClass(MainActivity.this, ItemTouchTestActivity.class);
                break;
//            case R.id.click_listener_tv://点击事件
////                intent.setClass(MainActivity.this, DDDD.class);
//                break;
            case R.id.nest_scroll_tv://嵌套滑动
                intent.setClass(MainActivity.this, NestScrollTestActivity.class);
                break;
//            case R.id.swipe_refresh_tv://与SwipeRefreshLayout结合加载刷新
////                intent.setClass(MainActivity.this, DDDD.class);
//                break;
//            case R.id.refresh_load_more_tv:
////                intent.setClass(MainActivity.this, DDDD.class);
//                break;
//            case R.id.complex_layout_tv:
////                intent.setClass(MainActivity.this, DDDD.class);
//                break;
        }
        startActivity(intent);
    }
}
