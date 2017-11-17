package com.web.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.web.form.UserCreateForm;
import com.web.form.UserUpdateForm;
import com.web.util.Util;

@Entity
public class User implements Serializable {


	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private Date birthDate;
	private String address;
	private String loginId;
	private Boolean is_open;
	private String password;
	private Date createDate;
	private Date updateDate;


	public User() {}

	//ユーザー登録時に使うコンストラクタ
	public User(UserCreateForm form) {
		this.loginId = form.getLoginId();
		this.name = form.getUserName();
		this.password = Util.toCode(form.getPassword());
		this.address = form.getAddress();
		this.loginId = form.getLoginId();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     // Date型変換
	     Date formatDate = null;
	     try {
			formatDate = sdf.parse(form.getBirthDate());
		 } catch (ParseException e) {
			e.printStackTrace();
		}
	     this.birthDate = formatDate;
	     this.is_open = false;
	     this.createDate = new Timestamp(System.currentTimeMillis());
	     this.updateDate = new Timestamp(System.currentTimeMillis());
	}

	//ユーザー更新時に使うコンストラクタ
	public User(UserUpdateForm form) {
		this.id = Integer.parseInt(form.getUserId());
		this.name = form.getUserName();
		this.loginId = form.getLoginId();
		if(!(form.getPassword()).equals("")) {
		    this.password = Util.toCode(form.getPassword());
		}else {
			this.password = form.getPrePassword();
		}
		this.address = form.getAddress();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdff = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// Date型変換
		Date formatDate = null;
		try {
			formatDate = sdf.parse(form.getBirthDate());
			this.createDate = sdff.parse(form.getCreateDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.birthDate = formatDate;
		this.is_open = (form.getIsOpen()).equals("open")?true:false;
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public Boolean getIs_open() {
		return is_open;
	}
	public void setIs_open(Boolean is_open) {
		this.is_open = is_open;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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

	public String getFormatBirthDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(birthDate);
	}

	public String getStringBirthDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(birthDate);
	}

	public String getStringCreateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(createDate);
	}



}
