package main.tdt.it.finalproject.portfolio.modal;

public class DoLechChuan {
	private Period period;
	private double value;
	public DoLechChuan(Period period, double value) {
		super();
		this.period = period;
		this.value = value;
	}
	public Period getPeriod() {
		return period;
	}
	public void setPeriod(Period period) {
		this.period = period;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "DoLechChuan [period=" + period + ", value=" + value + "]";
	}
	
}
