package com.zk.test.domain;

import java.math.BigDecimal;

public class Rate {
	private String from;
	private String to;
	private BigDecimal amount;
	private BigDecimal rate;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public Rate(String from, String to, BigDecimal amount, BigDecimal rate) {
		super();
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.rate = rate;
	}
	
	
}
