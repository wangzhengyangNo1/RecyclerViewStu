package com.wzhy.recyclerviewstu.itemtouch;

import android.support.annotation.NonNull;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzhy.recyclerviewstu.MyApp;
import com.wzhy.recyclerviewstu.R;
import com.wzhy.recyclerviewstu.base.sample.ItemEntity;
import com.wzhy.recyclerviewstu.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemTouchTestAdapter extends RecyclerView.Adapter<ItemTouchTestAdapter.ItemViewHolder> {

    private List<ItemEntity> mDataList;

    private int mOrientation = -1;

    public ItemTouchTestAdapter() {
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

    public List<ItemEntity> getDataLIst() {
        return mDataList;
    }

    public void addDataList(List<ItemEntity> dataList){
        if (dataList != null) {
            mDataList.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mDataList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_use, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
        ItemEntity item = getItem(position);
        holder.titleTv.setText(item.title);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnStartDragListener != null) {
                    mOnStartDragListener.onStartDrag(holder);
                }
                return false;
            }
        });
//        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    if (mOnStartSwipeListener != null) {
//                        mOnStartSwipeListener.onStartSwipe(holder);
//                    }
//                }
//                return false;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public ItemEntity getItem(int position) {
        return mDataList.get(position);
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.title_tv);
        }
    }

    private OnStartDragListener mOnStartDragListener;

    public void setOnStartDragListener(OnStartDragListener onStartDragListener) {
        this.mOnStartDragListener = onStartDragListener;
    }

    public interface OnStartDragListener {

        void onStartDrag(RecyclerView.ViewHolder holder);
    }

    private OnStartSwipeListener mOnStartSwipeListener;

    public void setOnStartSwipeListener(OnStartSwipeListener onStartSwipeListener) {
        this.mOnStartSwipeListener = onStartSwipeListener;
    }

    public interface OnStartSwipeListener {
        void onStartSwipe(RecyclerView.ViewHolder holder);
    }

}
