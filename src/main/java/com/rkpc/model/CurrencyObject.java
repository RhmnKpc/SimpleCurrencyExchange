package com.rkpc.model;

import java.util.Date;
import java.util.List;

public class CurrencyObject {

	private String base;
	private Date date;
	private List<Rates> rates;

	public CurrencyObject() {
		base = "";
		date = new Date();
		rates = new java.util.ArrayList<>();
	}

	public CurrencyObject(String base, Date date, List<Rates> rates) {
		super();
		this.base = base;
		this.date = date;
		this.rates = rates;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Rates> getRates() {
		return rates;
	}

	public void setRates(List<Rates> rates) {
		this.rates = rates;
	}

}
