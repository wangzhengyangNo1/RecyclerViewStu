package com.wzhy.recyclerviewstu.headerandfooter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

/**
 * 具有Header、Footer的包装类
 * 来自鸿洋_：[Android 优雅的为RecyclerView添加HeaderView和FooterView](https://blog.csdn.net/lmj623565791/article/details/51854533)
 */
public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int BASE_TYPE_HEADER = 10000;
    private static final int BASE_TYPE_FOOTER = 20000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();

    @NonNull
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mInnerAdapter;


    public HeaderAndFooterWrapper(@NonNull RecyclerView.Adapter adapter) {
        this.mInnerAdapter = adapter;
    }

    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getRealItemCount() + getHeadersCount();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_TYPE_HEADER, view);
    }

    public void addFooterView(View view) {
        mFooterViews.put(mFooterViews.size() + BASE_TYPE_FOOTER, view);
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFooterViews.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mHeaderViews.get(viewType));
            return holder;
        } else if (mFooterViews.get(viewType) != null) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(), mFooterViews.get(viewType));
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFooterViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    public int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            return;
        }

        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());

    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getRealItemCount() + getFootersCount();
    }

    @Override
    public long getItemId(int position) {
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            return RecyclerView.NO_ID;
        } else {
            return mInnerAdapter.getItemId(position - getHeadersCount());
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLm = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup oldSpanLookup = gridLm.getSpanSizeLookup();
            gridLm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    if (mHeaderViews.get(itemViewType) != null) {
                        return gridLm.getSpanCount();
                    } else if (mFooterViews.get(itemViewType) != null) {
                        return gridLm.getSpanCount();
                    }
                    if (oldSpanLookup != null) {
                        oldSpanLookup.getSpanSize(position);
                    }
                    return 1;
                }
            });
            gridLm.setSpanCount(gridLm.getSpanCount());
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams staggeredLp = (StaggeredGridLayoutManager.LayoutParams) lp;
                staggeredLp.setFullSpan(true);
            }
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

//        private SparseArray<View> mViews;


        public ViewHolder(View itemView) {
            super(itemView);
//            mViews = new SparseArray<>();
        }

        public static ViewHolder createViewHolder(Context context, View itemView) {
            return new ViewHolder(itemView);
        }

//        public SparseArray<View> getAllView() {
//            return mViews;
//        }
//
//        /**
//         * 通过控件的id获取对应的控件，如果没有则加入mViews
//         * @param viewId view的id
//         * @param <V> 泛型，View的子类
//         * @return
//         */
//        public <V extends View> V getView(int viewId) {
//            View v = mViews.get(viewId);
//            if (v == null) {
//                v = itemView.findViewById(viewId);
//                mViews.append(viewId, v);
//            }
//            return (V) v;
//        }

    }
}
