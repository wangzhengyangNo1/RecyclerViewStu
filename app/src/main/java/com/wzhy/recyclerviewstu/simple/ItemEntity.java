package com.wzhy.recyclerviewstu.simple;

public class ItemEntity {

    public int id;

    public String title;

    public int width;

    public int height;

    public ItemEntity() {
    }

    public ItemEntity(int id, String title) {
        this.id = id;
        this.title = title;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
