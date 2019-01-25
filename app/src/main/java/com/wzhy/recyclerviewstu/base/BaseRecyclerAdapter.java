package com.wzhy.recyclerviewstu.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.RecyclerVHolder> {

    protected List<T> mDataList;

    protected final int mItemLayoutId;

    protected boolean isScrolling;

    protected Context cxt;

    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T data, int position);
    }


    public BaseRecyclerAdapter(RecyclerView rv, Collection<T> datas, int itemLayoutId) {
        if (datas == null) {
            mDataList = new ArrayList<>();
        } else if (datas instanceof List) {
            mDataList = (List<T>) datas;
        } else {
            mDataList = new ArrayList<>(datas);
        }

        this.mItemLayoutId = itemLayoutId;
        cxt = rv.getContext();

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                isScrolling = !(newState == RecyclerView.SCROLL_STATE_IDLE);
                if (!isScrolling) {
                    notifyDataSetChanged();
                }
            }
        });
    }

    public abstract void convert(RecyclerVHolder holder, T item, int position, boolean isScrolling);

    @NonNull
    @Override
    public RecyclerVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cxt);
        View root = inflater.inflate(mItemLayoutId, parent, false);
        return new RecyclerVHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVHolder holder, int position) {
        convert(holder, mDataList.get(position), position, isScrolling);
        holder.itemView.setOnClickListener(getOnClickListener(position));

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mItemClickListener = listener;
    }

    public View.OnClickListener getOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null && v != null) {
                    mItemClickListener.onItemClick(v, mDataList.get(position), position);
                }
            }
        };
    }

    public BaseRecyclerAdapter<T> refresh(Collection<T> datas) {
        if (datas == null) {
            mDataList = new ArrayList<>();
        } else if (datas instanceof List) {
            mDataList = (List<T>) datas;
        } else {
            mDataList = new ArrayList<>(datas);
        }
        return this;
    }


    public static class RecyclerVHolder extends RecyclerView.ViewHolder {

        private SparseArray<View> mViews;

        public RecyclerVHolder(View itemView) {
            super(itemView);
            this.mViews = new SparseArray<View>(((ViewGroup) itemView).getChildCount());
        }

        public SparseArray<View> getAllView() {
            return mViews;
        }

        /**
         * 通过控件的id获取对应的控件，如果没有则加入mViews
         * @param viewId view的id
         * @param <V> 泛型，View的子类
         * @return
         */
        public <V extends View> V getView(int viewId) {
            View v = mViews.get(viewId);
            if (v == null) {
                v = itemView.findViewById(viewId);
                mViews.append(viewId, v);
            }
            return (V) v;
        }

    }


}
