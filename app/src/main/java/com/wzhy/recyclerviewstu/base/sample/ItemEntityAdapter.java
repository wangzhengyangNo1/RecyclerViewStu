package com.wzhy.recyclerviewstu.base.sample;

import android.support.v7.widget.RecyclerView;

import com.wzhy.recyclerviewstu.R;
import com.wzhy.recyclerviewstu.base.BaseCommAdapter;

import java.util.Collection;

/**
 * 示例：通用适配器使用
 */
public class ItemEntityAdapter extends BaseCommAdapter<ItemEntity> {

    public ItemEntityAdapter(RecyclerView rv, Collection<ItemEntity> datas) {

        super(rv, datas);
    }

    @Override
    public int getItemLayoutId(int itemViewType) {
        return R.layout.item_simple_use;
    }

    @Override
    public void convert(RecyclerVHolder holder, ItemEntity item, int position) {

    }
}
