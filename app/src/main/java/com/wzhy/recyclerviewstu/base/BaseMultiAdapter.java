package com.wzhy.recyclerviewstu.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collection;

public abstract class BaseMultiAdapter<T> extends BaseCommAdapter<T> implements IItemViewType<T> {

    public BaseMultiAdapter(RecyclerView rv, Collection<T> datas) {
        super(rv, datas);
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(position, mDataList.get(position));
    }

    @NonNull
    @Override
    public RecyclerVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int itemLayoutId = getItemLayoutId(viewType);
        RecyclerVHolder holder = RecyclerVHolder.getHolder(mContext, parent, itemLayoutId);
        return holder;
    }
}
