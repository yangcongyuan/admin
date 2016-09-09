package com.etnlgravtnl.system.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/6/20.
 */
public class SearchResult<T> implements Serializable{

    private long totalRows;
    private List<T> rows;

    public long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
