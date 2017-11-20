package com.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Point implements Serializable {



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

	public Point() {}

	public Point(int userId){
		this.userId = userId;
		this.point = 0;
	}

	//ポイント情報を更新する時に使用するコンストラクタ
	public Point(Point userPoint, int userId, int usingPoint, int preTotalPrice) {
		this.id = userPoint.getId();
		this.userId = userId;

		//ポイントを使用したかどうかによって値を操作する
		if(usingPoint == 0) {
			this.point = userPoint.getPoint() + preTotalPrice/100;
		}else {
			this.point = userPoint.getPoint() - usingPoint;
		}
	}


}
