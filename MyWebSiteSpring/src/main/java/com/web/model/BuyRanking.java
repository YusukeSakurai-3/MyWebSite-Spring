package com.web.model;

import java.io.Serializable;


public class BuyRanking implements Serializable {
	private int itemId;
	private long sum;

	public BuyRanking(int itemId, long sum) {
		this.itemId = itemId;
		this.sum = sum;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public long getSum() {
		return sum;
	}
	public void setSum(long sum) {
		this.sum = sum;
	}






}
