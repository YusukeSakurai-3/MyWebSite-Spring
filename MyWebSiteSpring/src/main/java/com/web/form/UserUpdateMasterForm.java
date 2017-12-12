package com.web.form;

import org.hibernate.validator.constraints.NotEmpty;

public class UserUpdateMasterForm {

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


	private int pointId;
	private int point;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
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
	public String getPrePassword() {
		return prePassword;
	}
	public void setPrePassword(String prePassword) {
		this.prePassword = prePassword;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	public int getPointId() {
		return pointId;
	}
	public void setPointId(int pointId) {
		this.pointId = pointId;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}


}
