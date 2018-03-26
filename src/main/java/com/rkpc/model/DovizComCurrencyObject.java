package com.rkpc.model;

@SuppressWarnings("serial")
public class DovizComCurrencyObject implements java.io.Serializable {
	private double selling;
	private int update_date;
	private int currency;
	private double buying;
	private double change_rate;
	private String name;
	private String full_name;
	private String code;

	public DovizComCurrencyObject() {
		selling = 1;
		buying = 1;
		code = "TL";
	}

	public Double getSelling() {
		return selling;
	}

	public void setSelling(Double selling) {
		this.selling = selling;
	}

	public int getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(int update_date) {
		this.update_date = update_date;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public Double getBuying() {
		return buying;
	}

	public void setBuying(Double buying) {
		this.buying = buying;
	}

	public Double getChange_rate() {
		return change_rate;
	}

	public void setChange_rate(Double change_rate) {
		this.change_rate = change_rate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
