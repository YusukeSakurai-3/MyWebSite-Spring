package com.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Buy implements Serializable {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int userId;
	private int totalPrice;
	private int deliveryMethodId;
	private Date createDate;

	public Buy() {

	}

	//登録したい情報を持つコンストラクタ
	public Buy(int userId, int totalPrice, int deliveryMethodId) {
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.deliveryMethodId = deliveryMethodId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getDeliveryMethodId() {
		return deliveryMethodId;
	}
	public void setDelivertMethodId(int deliveryMethodId) {
		this.deliveryMethodId = deliveryMethodId;
	}


	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setDeliveryMethodId(int deliveryMethodId) {
		this.deliveryMethodId = deliveryMethodId;
	}

	//現在時刻をセットする
	public void setCurrentDate() {
		this.createDate = new Timestamp(System.currentTimeMillis());

	}



}
