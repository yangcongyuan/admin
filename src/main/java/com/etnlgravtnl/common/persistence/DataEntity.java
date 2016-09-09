package com.etnlgravtnl.common.persistence;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据Entity类
 * @author guozheng
 * @version 2016-05-07
 */
public abstract class DataEntity<T> extends  BaseEntity<T>{


	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	protected boolean isNewRecord = false;

	/**
	 * 实体编号（唯一标识）
	 */
	protected int id;

	protected int page = 1;
	protected int rows;
	protected String sortRule;
	protected String order;
	protected long startPage;
	protected long endPage;
	protected String defaultSort;
	protected String searchSql;
	protected String orderSqlText;

	public String getOrderSqlText() {
		return orderSqlText;
	}

	public void setOrderSqlText(String orderSqlText) {
		this.orderSqlText = orderSqlText;
	}

	public String getSearchSql() {
		return searchSql;
	}

	public void setSearchSql(String searchSql) {
		this.searchSql = searchSql;
	}

	protected Map<String, Object> paramMap = new HashMap();



	public boolean isNewRecord() {
		return isNewRecord;
	}

	public void setNewRecord(boolean newRecord) {
		isNewRecord = newRecord;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSortRule() {
		return sortRule;
	}

	public void setSortRule(String sortRule) {
		this.sortRule = sortRule;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public long getStartPage() {
		return startPage;
	}

	public void setStartPage(long startPage) {
		this.startPage = startPage;
	}

	public long getEndPage() {
		return endPage;
	}

	public void setEndPage(long endPage) {
		this.endPage = endPage;
	}

	public String getDefaultSort() {
		return defaultSort;
	}

	public void setDefaultSort(String defaultSort) {
		this.defaultSort = defaultSort;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public DataEntity() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public boolean getIsNewRecord() {
		return true;
	}
}
