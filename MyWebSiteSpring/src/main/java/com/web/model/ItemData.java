package com.web.model;

import java.io.Serializable;
import java.util.List;

public class ItemData implements Serializable {
	private Item item;
	private long sum;
//
	public ItemData(Item item, long sum) {
		this.item = item;
		this.sum = sum;
	}

	public ItemData(Item item, int sum) {
		this.item = item;
		this.sum = sum;
	}




	public ItemData(Item item, List<BuyDetail> buyDetailList) {
		this.item = item;
		this.sum =  0L;
	}

	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public long getSum() {
		return sum;
	}
	public void setSum(long sum) {
		this.sum = sum;
	}



}
