package com.etnlgravtnl.modules.model;

/**
 * Created by admin on 2016/8/4.
 */
public class Page {

    private int currentPage;
    private int size;
    private int type;
    private Integer startSize;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getStartSize() {
        return startSize;
    }

    public void setStartSize(Integer startSize) {
        this.startSize = startSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
