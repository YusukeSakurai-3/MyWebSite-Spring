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

//	@ManyToOne
//	@JoinColumn(name="itemId", insertable=false, updatable=false)
//	private Item item;


	public BuyDetail(){

	}


	public BuyDetail(int itemId, int buyId) {
		this.itemId = itemId;
		this.buyId = buyId;
		//this.item = null;
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

//
//	public Item getItem() {
//		return item;
//	}
//
//
//	public void setItem(Item item) {
//		this.item = item;
//	}



}
