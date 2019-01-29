package com.wzhy.recyclerviewstu.headerandfooter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public class ViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;


    public ViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public static ViewHolder createViewHolder(Context context, View itemView) {
        return new ViewHolder(itemView);
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
