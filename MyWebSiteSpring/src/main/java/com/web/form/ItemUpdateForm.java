package com.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class ItemUpdateForm implements Serializable {

	private Integer id;
	@NotEmpty(message="商品名を入力してください")
	private String name;
	@NotNull(message="価格を入力してください")
	private Integer price;
	@NotEmpty(message="詳細を入力してください")
	private String detail;
	private MultipartFile uploadFile;
	private String preFileName;
	private String createDate;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getPreFileName() {
		return preFileName;
	}
	public void setPreFileName(String preFileName) {
		this.preFileName = preFileName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}



}
