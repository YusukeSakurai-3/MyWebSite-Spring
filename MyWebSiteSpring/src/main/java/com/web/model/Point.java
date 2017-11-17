package com.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Point implements Serializable {

	public Point() {}

	public Point(int userId){
		this.userId = userId;
		this.point = 0;
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int userId;
	private int point;

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
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}



}
