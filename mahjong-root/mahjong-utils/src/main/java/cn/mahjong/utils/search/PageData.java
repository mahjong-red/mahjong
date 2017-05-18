package cn.mahjong.utils.search;

import java.util.List;

public class PageData {

	private int total;
	
	private List<?> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public PageData(int total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	
	public PageData() {
	}
}
