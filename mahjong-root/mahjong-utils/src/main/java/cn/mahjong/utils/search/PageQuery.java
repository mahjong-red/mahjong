package cn.mahjong.utils.search;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import cn.mahjong.model.base.impl.BaseObjectImpl;

public class PageQuery {

	public PageQuery(Class<? extends BaseObjectImpl> targetClass) {
		this.targetClass = targetClass;
	}

	private Class<? extends BaseObjectImpl> targetClass;

	private Class<?> dtoTarget;

	private String targetClassStr;

	private int rows = 10;

	private int page = 1;

	private Date searchDate;

	private String sort;

	private String order;

	private long total;

	private Set<SearchItem> searchIteams = new HashSet<SearchItem>();

	public Class<? extends BaseObjectImpl> getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class<? extends BaseObjectImpl> targetClass) {
		this.targetClass = targetClass;
	}

	public Class<?> getDtoTarget() {
		return dtoTarget;
	}

	public void setDtoTarget(Class<?> dtoTarget) {
		this.dtoTarget = dtoTarget;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return StringUtils.isBlank(order)?"desc":order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Set<SearchItem> getSearchIteams() {
		return searchIteams;
	}

	public void setSearchIteams(Set<SearchItem> searchIteams) {
		this.searchIteams = searchIteams;
	}

	public String getTargetClassStr() {
		return targetClassStr;
	}

	public void setTargetClassStr(String targetClassStr) {
		this.targetClassStr = targetClassStr;
	}

	public void addSearchItem(String itemName, SearchType searchType, Object value1, Object value2) {
		SearchItem searchItem = new SearchItem();
		searchItem.setItemName(itemName);
		searchItem.setSearchType(searchType);
		searchItem.setValue1(value1);
		searchItem.setValue2(value2);
		searchIteams.add(searchItem);
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
