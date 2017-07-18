package main.tdt.it.finalproject.portfolio.modal;

public class SuatSinhLoiKyVong {
	private Period period;
	private double price;

	public SuatSinhLoiKyVong(Period period, double price) {
		super();
		this.period = period;
		this.price = price;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "SuatSinhLoiKyVong [period=" + period + ", price=" + price + "]";
	}

}
