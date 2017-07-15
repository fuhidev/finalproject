package main.tdt.it.finalproject.modal;

import java.util.Date;

public class InterestRate extends AbstractPrice {
	private String kyHan;
	private String nameBank;
	private double percentInterestRate;
	private Date date;
	public InterestRate(String kyHan, String nameBank, Double percentInterestRate) {
		this.kyHan = kyHan;
		this.nameBank = nameBank;
		this.percentInterestRate = percentInterestRate;
	}
	
	public InterestRate(String kyHan, String nameBank, Double percentInterestRate, Date date) {
		this.kyHan = kyHan;
		this.nameBank = nameBank;
		this.percentInterestRate = percentInterestRate;
		this.date = date;
	}
	public String getKyHan() {
		return kyHan;
	}
	public void setKyHan(String kyHan) {
		this.kyHan = kyHan;
	}
	
	public String getNameBank() {
		return nameBank;
	}
	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
	}
	public double getPercentInterestRate() {
		return percentInterestRate;
	}
	public void setPercentInterestRate(double percentInterestRate) {
		this.percentInterestRate = percentInterestRate;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPercentInterestRate(Double percentInterestRate) {
		this.percentInterestRate = percentInterestRate;
	}

	@Override
	public String toString() {
		return "InterestRate [kyHan=" + kyHan + ", percentInterestRate=" + percentInterestRate + "]";
	}

	
}
