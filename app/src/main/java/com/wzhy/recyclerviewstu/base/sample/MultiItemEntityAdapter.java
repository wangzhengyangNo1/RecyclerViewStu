package com.wzhy.recyclerviewstu.base.sample;

import android.support.v7.widget.RecyclerView;

import com.wzhy.recyclerviewstu.R;
import com.wzhy.recyclerviewstu.base.BaseMultiAdapter;

import java.util.Collection;


/**
 * 示例：多布局适配器使用
 */
public class MultiItemEntityAdapter extends BaseMultiAdapter<ItemEntity> {

    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int TYPE_3 = 2;

    public MultiItemEntityAdapter(RecyclerView rv, Collection<ItemEntity> datas) {
        super(rv, datas);
    }

    @Override
    public void convert(RecyclerVHolder holder, ItemEntity item, int position) {

    }

    @Override
    public int getItemLayoutId(int itemViewType) {
        int layoutId = R.layout.item_simple_use;
        switch (itemViewType) {
            case TYPE_1:
                layoutId = R.layout.item_simple_use;
                break;
            case TYPE_2:
                layoutId = R.layout.item_simple_use;
                break;
            case TYPE_3:
                layoutId = R.layout.item_simple_use;
                break;
        }
        return layoutId;
    }

    @Override
    public int getItemViewType(int position, ItemEntity itemEntity) {
        if (itemEntity instanceof ItemEntity) {
            return TYPE_1;
        } else if (itemEntity instanceof ItemEntity) {
            return TYPE_2;
        } else if (itemEntity instanceof ItemEntity) {
            return TYPE_3;
        }
        return TYPE_1;
    }
}
