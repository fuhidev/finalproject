package main.tdt.it.finalproject.jsondata;

public class InterestRate {
	private String kyHan;
	private String nameBank;
	private String percentInterestRate;
	public InterestRate(String kyHan, String nameBank, String percentInterestRate) {
		super();
		this.kyHan = kyHan;
		this.nameBank = nameBank;
		this.percentInterestRate = percentInterestRate;
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
	public String getPercentInterestRate() {
		return percentInterestRate;
	}
	public void setPercentInterestRate(String percentInterestRate) {
		this.percentInterestRate = percentInterestRate;
	}

	
}
