package com.web.form;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm implements Serializable {


	@NotEmpty(message = "loginIdを入力してください")
	private String loginId;

	@NotEmpty(message = "passwordを入力してください")
	private String password;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
