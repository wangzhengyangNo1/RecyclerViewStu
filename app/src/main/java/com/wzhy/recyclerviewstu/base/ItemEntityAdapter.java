package com.wzhy.recyclerviewstu.base;

import android.support.v7.widget.RecyclerView;

import com.wzhy.recyclerviewstu.simple.ItemEntity;

import java.util.Collection;

public class ItemEntityAdapter extends BaseRecyclerAdapter<ItemEntity> {

    public ItemEntityAdapter(RecyclerView rv, Collection<ItemEntity> datas, int itemLayoutId) {
        super(rv, datas, itemLayoutId);
    }

    @Override
    public void convert(RecyclerVHolder holder, ItemEntity item, int position, boolean isScrolling) {

    }
}
