package com.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
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

import com.web.form.ItemCreateForm;
import com.web.form.ItemUpdateForm;

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

	/**
	 * 商品登録で使用
	 * @param form
	 */
    public Item(ItemCreateForm form) {
		this.name = form.getName();
		this.detail = form.getDetail();
		this.price = form.getPrice();
		if(form.getUploadFile().isEmpty()) {
			this.fileName = "noimage.png";
		}
		else
		{
			this.fileName = form.getUploadFile().getOriginalFilename();
		}

	     this.createDate = new Timestamp(System.currentTimeMillis());
	     this.updateDate = new Timestamp(System.currentTimeMillis());

	}


    /**
     * 商品情報を更新する時に使用
     * @param form
     * */
	public Item(ItemUpdateForm form) {
		this.id = form.getId();
		this.name = form.getName();
		this.detail = form.getDetail();
		this.price = form.getPrice();

		if(form.getUploadFile().isEmpty()) {
			this.fileName = form.getPreFileName();
		}
		else
		{
			this.fileName = form.getUploadFile().getOriginalFilename();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// Date型変換
		//Date formatDate = null;
		try {
			this.createDate = sdf.parse(form.getCreateDate());

		} catch (ParseException e) {
			e.printStackTrace();
		}

	     this.updateDate = new Timestamp(System.currentTimeMillis());

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

	public String getFormatUpdateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(updateDate);
	}

	public List<BuyDetail> getBuyDetailList() {
		return buyDetailList;
	}
	public void setBuyDetailList(List<BuyDetail> buyDetailList) {
		this.buyDetailList = buyDetailList;
	}

	public String getStringCreateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(createDate);
	}

	public int getPurchaseNumber() {
		return buyDetailList.size();
	}


}
