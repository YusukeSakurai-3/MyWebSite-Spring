package com.web.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class UserUpdateForm implements Serializable {

	private String userId;
	private String loginId;
	@NotEmpty(message="ユーザ名を入力してください")
	private String userName;

	private String password;
	private String passwordConfirm;
	private String prePassword;
	private String createDate;
	@NotEmpty(message="生年月日を入力してください")
	private String birthDate;
	@NotEmpty(message="住所を入力してください")
	private String address;
	private String isOpen;



	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrePassword() {
		return prePassword;
	}
	public void setPrePassword(String prePassword) {
		this.prePassword = prePassword;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}



}
