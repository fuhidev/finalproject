package main.tdt.it.finalproject.modal;

import java.util.Date;

public class InterestRate extends AbstractPrice {
	private String period;
	private String nameBank;
	private float percentInterestRate;
	private Date date;

	public InterestRate(String kyHan, String nameBank, float percentInterestRate, Date date) {
		super(date);
		this.period = kyHan;
		this.nameBank = nameBank;
		this.percentInterestRate = percentInterestRate;
		this.date = date;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String kyHan) {
		this.period = kyHan;
	}

	public String getNameBank() {
		return nameBank;
	}

	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
	}

	public float getPercentInterestRate() {
		return percentInterestRate;
	}

	public void setPercentInterestRate(float percentInterestRate) {
		this.percentInterestRate = percentInterestRate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "InterestRate [period=" + period + ", percentInterestRate=" + percentInterestRate + "]";
	}

}
