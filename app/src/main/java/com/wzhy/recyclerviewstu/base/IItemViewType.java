package com.wzhy.recyclerviewstu.base;

public interface IItemViewType<T> {

    int getItemViewType(int position, T t);
}
