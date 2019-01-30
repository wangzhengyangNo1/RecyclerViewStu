package com.wzhy.recyclerviewstu.divides;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridDividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;


    public GridDividerItemDecoration() {
        super();
    }

    public GridDividerItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int childCount = parent.getChildCount();
        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int spanCount = layoutManager.getSpanCount();
        final int orientation = layoutManager.getOrientation();
        boolean isDrawHorizontalDivider = true;
        boolean isDrawVerticalDivider = true;
        int extra = childCount % spanCount;
        extra = extra == 0 ? spanCount : extra;
        for (int i = 0; i < childCount; i++) {
            isDrawHorizontalDivider = true;
            isDrawVerticalDivider = true;
            //如果是竖直方向，最右边一列不绘制竖直方向间隔
            if (orientation == RecyclerView.VERTICAL && (i + 1) % spanCount == 0) {
                isDrawVerticalDivider = false;
            }

//            //如果是竖直方向，最后一行不绘制水平方向间隔
//            if (orientation == RecyclerView.VERTICAL && i >= childCount - extra) {
//                isDrawHorizontalDivider = false;
//            }

            //如果是水平方向，最下面一行不绘制水平方向间隔
            if (orientation == RecyclerView.HORIZONTAL && (i + 1) % spanCount == 0) {
                isDrawHorizontalDivider = false;
            }

//            //如果是水平方向，最右边一列不绘制竖直方向间隔
//            if (orientation == RecyclerView.HORIZONTAL && i >= childCount - extra) {
//                isDrawVerticalDivider = false;
//            }

            if (isDrawHorizontalDivider) {
                drawHorizontalDivider(c, parent, i);
            }

            if (isDrawVerticalDivider) {
                drawVerticalDivider(c, parent, i);
            }

        }

    }

    private void drawVerticalDivider(Canvas c, RecyclerView parent, int i) {
        final View child = parent.getChildAt(i);
        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
        final int top = child.getTop() - layoutParams.topMargin;
        final int bottom = child.getBottom() + layoutParams.bottomMargin;
        final int left = child.getRight() + layoutParams.rightMargin;
        final int right = left + mDivider.getIntrinsicWidth();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    private void drawHorizontalDivider(Canvas c, RecyclerView parent, int i) {
        final View child = parent.getChildAt(i);
        final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
        final int top = child.getBottom() + lp.bottomMargin;
        final int bottom = top + mDivider.getIntrinsicHeight();
        final int left = child.getLeft() - lp.leftMargin;
        final int right = child.getRight() + lp.rightMargin + mDivider.getIntrinsicWidth();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mDivider == null) {
            outRect.set(0, 0, 0, 0);
            return;
        }

        final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
        final int spanCount = layoutManager.getSpanCount();
        final int orientation = layoutManager.getOrientation();
        final int position = parent.getChildAdapterPosition(view);
        if (orientation == RecyclerView.VERTICAL && (position + 1) % spanCount == 0) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else if (orientation == RecyclerView.HORIZONTAL && (position + 1) % spanCount == 0) {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
        }

    }
}
