package com.web.util;

import com.web.form.UserSearchForm;

/**
 *
 * UserSearchFormの未入力チェックをする
 *
 */
public class UserCheck {

	private Boolean idCheck;
	private Boolean nameCheck;
	private Boolean startDateCheck;
	private Boolean endDateCheck;

	public UserCheck (UserSearchForm form) {
		this.idCheck = form.getLoginId().length() != 0;
		this.nameCheck = form.getSearchName().length() != 0;
		this.startDateCheck = form.getStartDate().length() != 0;
		this.endDateCheck = form.getEndDate().length() != 0;
	}


	public Boolean getIdCheck() {
		return idCheck;
	}
	public void setIdCheck(Boolean idCheck) {
		this.idCheck = idCheck;
	}
	public Boolean getNameCheck() {
		return nameCheck;
	}
	public void setNameCheck(Boolean nameCheck) {
		this.nameCheck = nameCheck;
	}
	public Boolean getStartDateCheck() {
		return startDateCheck;
	}
	public void setStartDateCheck(Boolean startDateCheck) {
		this.startDateCheck = startDateCheck;
	}
	public Boolean getEndDateCheck() {
		return endDateCheck;
	}
	public void setEndDateCheck(Boolean endDateCheck) {
		this.endDateCheck = endDateCheck;
	}




}
