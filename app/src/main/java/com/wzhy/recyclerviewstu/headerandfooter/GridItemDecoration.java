package com.wzhy.recyclerviewstu.headerandfooter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable mDivider;
    private boolean hasHeader;

    public GridItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    public GridItemDecoration(Context context, boolean hasHeader) {
        this(context);
        this.hasHeader = hasHeader;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int spanCount = getSpanCount(parent);

        int childCount = parent.getAdapter().getItemCount();
        int pos = position;

        if (hasHeader) {
            HeaderAndFooterWrapper adapter = (HeaderAndFooterWrapper) parent.getAdapter();
            if (position < adapter.getHeadersCount() || position >= adapter.getHeadersCount() + adapter.getRealItemCount()) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
                return;
            } else {
                pos = position - adapter.getHeadersCount();
            }

            if (isLastColumn(parent, pos, spanCount, childCount)) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
            }
        }
    }

    private boolean isLastColumn(RecyclerView parent, int pos, int spanCount, int childCount) {


        if ((pos + 1) % spanCount == 0) {
            return true;
        }
        return false;
    }



    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            return ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return 1;
    }
}
