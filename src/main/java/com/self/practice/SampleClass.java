package com.self.practice;

public class SampleClass {

	public String getTicker_symbol() {
		return ticker_symbol;
	}

	public void setTicker_symbol(String ticker_symbol) {
		this.ticker_symbol = ticker_symbol;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private String ticker_symbol;
	private String sector;
	private double change;
	private double price;

}
