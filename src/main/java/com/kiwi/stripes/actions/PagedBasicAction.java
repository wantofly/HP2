package com.kiwi.stripes.actions;

import com.kiwi.stripes.actions.BasicAction;

public class PagedBasicAction extends BasicAction
{
	private int page = 0;
	private int pageSize = 10;
	private int curendNum = 0;
	private int curbeginNum = 0;
	private int totalRecNum = 0;
	private int totalPageNum = 0;
	private int nextPageNum = 0;
	private boolean isMultipage = false; 
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		curbeginNum = (page-1) * pageSize;
		curendNum = page * pageSize - 1;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
		if (this.totalPageNum>0) setMultipage(true); 
	}
	public boolean isMultipage() {
		return isMultipage;
	}
	public void setMultipage(boolean isMultipage) {
		this.isMultipage = isMultipage;
	}
	public int getNextPageNum() {
		return nextPageNum;
	}
	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalRecNum() {
		return totalRecNum;
	}
	public void setTotalRecNum(int totalRecNum) {
		this.totalRecNum = totalRecNum;
		setTotalPageNum(this.totalRecNum/pageSize);
		if (totalPageNum*pageSize<totalRecNum){
			setTotalPageNum(totalPageNum+1);
		}
		if (this.page == 0) setPage(1);
	}
	public int getCurbeginNum() {
		return curbeginNum;
	}
	public void setCurbeginNum(int curbeginNum) {
		this.curbeginNum = curbeginNum;
	}
	public int getCurendNum() {
		return curendNum;
	}
	public void setCurendNum(int curendNum) {
		this.curendNum = curendNum;
	}
	
}
