package com.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BuyDetail implements Serializable {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int buyId;
	private int itemId;

	public BuyDetail(){}


	public BuyDetail(int itemId, int buyId) {
		this.itemId = itemId;
		this.buyId = buyId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBuyId() {
		return buyId;
	}
	public void setBuyId(int buyId) {
		this.buyId = buyId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}



}
