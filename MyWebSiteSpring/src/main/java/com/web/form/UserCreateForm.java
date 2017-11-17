package com.web.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class UserCreateForm implements Serializable {


	@NotEmpty(message="loginIdを入力してください")
	private String loginId;
	@NotEmpty(message="ユーザ名を入力してください")
	private String userName;
	@NotEmpty(message="パスワードを入力してください")
	private String password;
	@NotEmpty(message="パスワード(確認)を入力してください")
	private String passwordConfirm;
	@NotEmpty(message="生年月日を入力してください")
	private String birthDate;
	@NotEmpty(message="住所を入力してください")
	private String address;

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


}
