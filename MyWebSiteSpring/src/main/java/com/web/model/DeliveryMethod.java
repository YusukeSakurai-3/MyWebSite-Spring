package com.web.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DeliveryMethod implements Serializable {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String Name;
	private int price;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Idにひもづいた配送方法名のMapを返す
	 * @param 配送方法のリスト
	 * @HashMap<Integer, String> Idと配送方法の組
	 */
	public static HashMap<Integer, String> getDeliveryMethodName(List<DeliveryMethod> deliveryMethodList) {
		HashMap<Integer,String> deliveryMethodMap = new HashMap<Integer,String>();

		for(DeliveryMethod method: deliveryMethodList) {
			deliveryMethodMap.put(method.getId(), method.getName());
		}

		return deliveryMethodMap;
	}
	public static HashMap<Integer, Integer> getDeliveryMethodPrice(List<DeliveryMethod> deliveryMethodList) {
		HashMap<Integer,Integer> deliveryMethodPrice = new HashMap<Integer,Integer>();

		for(DeliveryMethod method: deliveryMethodList) {
			deliveryMethodPrice.put(method.getId(), method.getPrice());
		}
		return deliveryMethodPrice;
	}



}
