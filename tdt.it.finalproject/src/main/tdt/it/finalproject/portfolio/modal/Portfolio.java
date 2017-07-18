package main.tdt.it.finalproject.portfolio.modal;

import java.util.Arrays;

public class Portfolio {
	private final int RETURN_YEAR = 1;
	private final int RETURN_MONTH = 1;
	private final int RETURN_WEEK = 1;
	private double[] returns;
	private double[] variance;

	public Portfolio() {
		super();
		this.returns = new double[3];
		this.variance = new double[3];
	}

	public double getReturnMonth() {
		return this.returns[RETURN_MONTH];
	}

	public double getReturnWeek() {
		return this.returns[RETURN_WEEK];
	}

	public double getReturnYear() {
		return this.returns[RETURN_YEAR];
	}

	public double getVarianceMonth() {
		return this.variance[RETURN_MONTH];
	}

	public double getVarianceWeek() {
		return this.variance[RETURN_WEEK];
	}

	public double getVarianceYear() {
		return this.variance[RETURN_YEAR];
	}

	public void setReturnMonth(double val) {
		this.returns[RETURN_MONTH] = val;
	}

	public void setReturnWeek(double val) {
		this.returns[RETURN_WEEK] = val;
	}

	public void setReturnYear(double val) {
		this.returns[RETURN_YEAR] = val;
	}

	public void setVarianceMonth(double val) {
		this.variance[RETURN_MONTH] = val;
	}

	public void setVarianceWeek(double val) {
		this.variance[RETURN_WEEK] = val;
	}

	public void setVarianceYear(double val) {
		this.variance[RETURN_YEAR] = val;
	}

	@Override
	public String toString() {
		return "Portfolio [ returns=" + Arrays.toString(returns) + ", variance=" + Arrays.toString(variance)
				+ "]";
	}
	
}
