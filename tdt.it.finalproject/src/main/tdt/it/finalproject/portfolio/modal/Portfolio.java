package main.tdt.it.finalproject.portfolio.modal;

import java.util.Arrays;

public class Portfolio {
	private String id;
	private final int RETURN_YEAR = 2;
	private final int RETURN_MONTH = 1;
	private final int RETURN_WEEK = 0;
	private Return[] returns;
	private double[] variance;

	public Portfolio() {
		super();
		this.returns = new Return[3];
		this.variance = new double[3];
	}

	public Return getReturnMonth() {
		return this.returns[RETURN_MONTH];
	}

	public Return getReturnWeek() {
		return this.returns[RETURN_WEEK];
	}

	public Return getReturnYear() {
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

	public void setReturnMonth(Return val) {
		this.returns[RETURN_MONTH] = val;
	}

	public void setReturnWeek(Return val) {
		this.returns[RETURN_WEEK] = val;
	}

	public void setReturnYear(Return val) {
		this.returns[RETURN_YEAR] = val;
	}

	public void setReturnMonth(float val) {
		this.returns[RETURN_MONTH] = new Return(Period.MONTH, val);
	}

	public void setReturnWeek(float val) {
		this.returns[RETURN_WEEK] = new Return(Period.WEEK, val);
	}

	public void setReturnYear(float val) {
		this.returns[RETURN_YEAR] = new Return(Period.YEAR, val);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Portfolio [returns=" + Arrays.toString(returns) + ", variance=" + Arrays.toString(variance) + "]";
	}
	

}
