package com.web.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Item implements Serializable {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)

	private int id;
	private String name;
	private String detail;
	private int price;
	private String fileName;
	private Date createDate;
	private Date updateDate;

	@OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name = "itemId")
	private List<BuyDetail> buyDetailList;


	public Item() {
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getFormatCreateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(createDate);
	}


	public List<BuyDetail> getBuyDetailList() {
		return buyDetailList;
	}


	public void setBuyDetailList(List<BuyDetail> buyDetailList) {
		this.buyDetailList = buyDetailList;
	}


}
