package com.wzhy.recyclerviewstu.divides;

import android.support.annotation.NonNull;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wzhy.recyclerviewstu.MyApp;
import com.wzhy.recyclerviewstu.R;
import com.wzhy.recyclerviewstu.base.sample.ItemEntity;
import com.wzhy.recyclerviewstu.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DividerTestAdapter extends RecyclerView.Adapter<DividerTestAdapter.ItemViewHolder> {

    private List<ItemEntity> mDataList;

    //是否错列排列
    private boolean isStaggered = false;


    private int mOrientation = -1;

    public DividerTestAdapter() {
        mDataList = new ArrayList<>();
    }

    public List<ItemEntity> getDataList() {
        return mDataList;
    }


    public void setDataList(List<ItemEntity> dataList){
        if (dataList != null) {
            mDataList.clear();
            mDataList.addAll(dataList);
        }
    }

    public void addDataList(List<ItemEntity> dataList){
        if (dataList != null) {
            mDataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    public void setStaggered(int orientation, boolean isStaggered) {
        this.isStaggered = isStaggered;
        this.mOrientation = orientation;
        if (orientation == OrientationHelper.HORIZONTAL) {
            for (ItemEntity itemEntity : mDataList) {
                itemEntity.width = getRandomSize();
            }
        } else {
            for (ItemEntity itemEntity : mDataList) {
                itemEntity.height = getRandomSize();
            }
        }

    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_div_test, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemEntity item = getItem(position);
        if (isStaggered) {
            ViewGroup.LayoutParams lp = holder.titleTv.getLayoutParams();
            if (mOrientation == OrientationHelper.HORIZONTAL) {
                ViewGroup.LayoutParams itemViewLp = holder.itemView.getLayoutParams();
                itemViewLp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                lp.width = item.width;
                lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
            } else {
                lp.height = item.height;
            }
            holder.titleTv.setLayoutParams(lp);
        }
        holder.titleTv.setText(item.title);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public ItemEntity getItem(int position) {
        return mDataList.get(position);
    }

    public int getRandomSize(){
        Random random = new Random();
        int seed = DisplayUtil.dip2px(MyApp.getAppContext(), 240f);
        int result = seed/2 + random.nextInt(seed);
        return result;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
        }
    }
}
