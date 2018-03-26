package com.rkpc.model;

public class Rates {

	private String code;
	private double rate;

	public Rates() {
		code = "";
		rate = 0;
	}

	public Rates(String code, double rate) {
		super();
		this.code = code;
		this.rate = rate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

}
