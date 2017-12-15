package com.viewstar.jsdatamonitor.util.param;

import java.util.ArrayList;
import java.util.List;

public class DataTableReciveParam {

	private int startRow;

	private int endRow;

	private List<String> orderList = new ArrayList<String>();

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public List<String> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<String> orderList) {
		this.orderList = orderList;
	}

}
