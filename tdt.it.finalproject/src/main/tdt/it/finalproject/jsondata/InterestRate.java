package main.tdt.it.finalproject.jsondata;

public class InterestRate extends AssetPrice {
	private String kyHan;
	private String percentInterestRate;
	public InterestRate(String kyHan, String nameBank, String percentInterestRate) {
		this.kyHan = kyHan;
		this.name = nameBank;
		this.percentInterestRate = percentInterestRate;
	}
	public String getKyHan() {
		return kyHan;
	}
	public void setKyHan(String kyHan) {
		this.kyHan = kyHan;
	}
	public String getNameBank() {
		return this.getName();
	}
	public void setNameBank(String nameBank) {
		this.setName(nameBank);
	}
	public String getPercentInterestRate() {
		return percentInterestRate;
	}
	public void setPercentInterestRate(String percentInterestRate) {
		this.percentInterestRate = percentInterestRate;
	}

	
}
