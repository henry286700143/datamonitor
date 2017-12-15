package com.viewstar.jsdatamonitor.util;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;
import org.displaytag.tags.TableTagParameters;

public class PaginatedListHelper implements PaginatedList {

	public static final int perpage = 15;

	public static final int C3perpage = 30;

	public static final int OrderLogperpage = 25;

	private List list = new ArrayList();

	private int pageNumber = 1;

	private int objectsPerPage = 20;

	private int fullListSize = 0;

	private String sortCriterion;

	private SortOrderEnum sortDirection;

	private String searchId;

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getObjectsPerPage() {
		return objectsPerPage;
	}

	public void setObjectsPerPage(int objectsPerPage) {
		this.objectsPerPage = objectsPerPage;
	}

	public int getFullListSize() {
		return fullListSize;
	}

	public void setFullListSize(int fullListSize) {
		this.fullListSize = fullListSize;
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public static Integer getStartPage(HttpServletRequest request, int perpage) {
		int page;
		if (request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE) != null
				&& !"".equals(request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE))) {
			page = Integer.parseInt(request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE));
		} else {
			page = 1;
		}
		return new Integer((page - 1) * perpage + 1);
	}

	public static Integer getEndPage(HttpServletRequest request, int perpage) {
		int page;
		if (request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE) != null
				&& !"".equals(request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE))) {
			page = Integer.parseInt(request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE));
		} else {
			page = 1;
		}
		return new Integer((page - 1) * perpage + 1 + perpage - 1);
	}

	public static int getPage(HttpServletRequest request) {

		int page;
		if (request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE) != null
				&& !"".equals(request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE))) {
			page = Integer.parseInt(request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE));
		} else {
			page = 1;
		}

		return page;

	}

	public static PaginatedListHelper getPaginatedListHelper(
			HttpServletRequest request, int count, int perpage, List list) {
		PaginatedListHelper paginaredList = new PaginatedListHelper();
		int page;
		if (request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE) != null
				&& !"".equals(request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE))) {
			page = Integer.parseInt(request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE));
		} else {
			page = 1;
		}
		paginaredList.setFullListSize(count);
		paginaredList.setPageNumber(page);
		paginaredList.setObjectsPerPage(perpage);
		paginaredList.setList(list);

		return paginaredList;

	}

	public static PaginatedListHelper getPaginatedListHelper(
			HttpServletRequest request, int count, int perpage, List list,
			String attrName) {
		PaginatedListHelper paginaredList = new PaginatedListHelper();
		int page;
		if (request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE) != null
				&& !"".equals(request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE))) {
			page = Integer.parseInt(request.getParameter(TableTagParameters.SORT_AMOUNT_PAGE));
		} else {
			page = 1;
		}
		paginaredList.setFullListSize(count);
		paginaredList.setPageNumber(page);
		paginaredList.setObjectsPerPage(perpage);
		paginaredList.setList(list);

		return paginaredList;

	}

	public static Integer getFullListSize(HttpServletRequest request,
			String attrName) {
		if (request.getSession().getAttribute(attrName) != null) {
			Integer count = Integer.valueOf(request.getSession().getAttribute(
					attrName).toString());
			return count;
		}
		return null;
	}

}
