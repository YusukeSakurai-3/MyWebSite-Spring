package com.web.form;

import java.io.Serializable;

public class SearchForm implements Serializable {
	private String searchWord;
	private String startPrice;
	private String endPrice;

	public SearchForm() {}

    public SearchForm(String searchWord, String startPrice, String endPrice) {
    		this.searchWord = searchWord;
    		this.startPrice = startPrice;
    		this.endPrice = endPrice;
	}




	public String getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}

	public String getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}




}
