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

public abstract class BaseCommAdapter<T> extends RecyclerView.Adapter<BaseCommAdapter.RecyclerVHolder> {

    protected List<T> mDataList;

    protected Context mContext;

    private OnItemClickListener mItemClickListener;

    public interface OnItemClickListener<T> {
        void onItemClick(View view, T data, int position);
    }


    public BaseCommAdapter(RecyclerView rv, Collection<T> datas) {
        if (datas == null) {
            mDataList = new ArrayList<>();
        } else if (datas instanceof List) {
            mDataList = (List<T>) datas;
        } else {
            mDataList = new ArrayList<>(datas);
        }
        mContext = rv.getContext();
    }

    public abstract void convert(RecyclerVHolder holder, T item, int position);

    public abstract int getItemLayoutId(int itemViewType);
    ;

    @NonNull
    @Override
    public RecyclerVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return RecyclerVHolder.getHolder(mContext, parent, getItemLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVHolder holder, int position) {
        convert(holder, mDataList.get(position), position);
        holder.itemView.setOnClickListener(getOnClickListener(position));

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
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

    public BaseCommAdapter<T> refresh(Collection<T> datas) {
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


        @NonNull
        protected static RecyclerVHolder getHolder(Context context, @NonNull ViewGroup parent, int itemLayoutId) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View root = inflater.inflate(itemLayoutId, parent, false);
            return new RecyclerVHolder(root);
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
