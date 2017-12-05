package com.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.web.form.ItemReviewForm;

@Entity
public class Review implements Serializable {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int userId;
	private int itemId;
	private String title;
	private String fileName;
	private String reviewText;
	private int evaluation;

	public Review() {}

	public Review(ItemReviewForm form,int userId) {
		this.userId = userId;
		this.itemId = Integer.parseInt(form.getItemId());
		this.title = form.getTitle();
		this.fileName = form.getUploadFile().getOriginalFilename();
		this.reviewText = form.getReviewText();
		this.evaluation = Integer.parseInt(form.getEvaluation());

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
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}






}
