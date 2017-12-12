package com.web.util;

/**
 *ページング処理をするためのクラス
 */

public class Paging {
	private int pageStart;
	private int pageEnd;

	public Paging(int pageNum, int maxPage) {
		int start = pageNum - 1;
		if(start <= 0) {
			start = 1;
		}
		int end = start + 4;
		if (end >=  maxPage) {
			end = maxPage;
			start = Math.max(end - 4, 1) ;
		}

		this.pageStart = start;
		this.pageEnd = end;

	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}



}
