package main.tdt.it.finalproject.modal;

public class InterestRate extends AbstractPrice {
	private String kyHan;
	private String nameBank;
	private String percentInterestRate;
	
	public InterestRate(String kyHan, String nameBank, String percentInterestRate) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((kyHan == null) ? 0 : kyHan.hashCode());
		result = prime * result + ((percentInterestRate == null) ? 0 : percentInterestRate.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterestRate other = (InterestRate) obj;
		if (kyHan == null) {
			if (other.kyHan != null)
				return false;
		} else if (!kyHan.equals(other.kyHan))
			return false;
		if (percentInterestRate == null) {
			if (other.percentInterestRate != null)
				return false;
		} else if (!percentInterestRate.equals(other.percentInterestRate))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "InterestRate [kyHan=" + kyHan + ", percentInterestRate=" + percentInterestRate + "]";
	}

	
}
