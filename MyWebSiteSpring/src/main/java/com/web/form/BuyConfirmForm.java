package com.web.form;

import java.io.Serializable;

import javax.validation.constraints.Min;

public class BuyConfirmForm implements Serializable {

	private int deliveryMethodId;

	@Min(0)
	private Integer point;

	public int getDeliveryMethodId() {
		return deliveryMethodId;
	}

	public void setDeliveryMethodId(int deliveryMethodId) {
		this.deliveryMethodId = deliveryMethodId;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}







}
