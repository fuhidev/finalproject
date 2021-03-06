package main.tdt.it.finalproject.portfolio.modal;

import java.util.Arrays;

public class Portfolio {
	private String id;
	private final int RETURN_YEAR = 2;
	private final int RETURN_MONTH = 1;
	private final int RETURN_WEEK = 0;
	private float[] returns;
	private float[] standardDeviation;

	public Portfolio() {
		super();
		this.returns = new float[3];
		this.standardDeviation = new float[3];
	}

	public float getReturnMonth() {
		return this.returns[RETURN_MONTH];
	}

	public float getReturnWeek() {
		return this.returns[RETURN_WEEK];
	}

	public float getReturn() {
		return this.returns[RETURN_YEAR];
	}

	public float getStandardDeviationMonth() {
		return this.standardDeviation[RETURN_MONTH];
	}

	public float getStandardDeviationWeek() {
		return this.standardDeviation[RETURN_WEEK];
	}

	public float getStandardDeviation() {
		return this.standardDeviation[RETURN_YEAR];
	}

	public void setReturnMonth(float val) {
		this.returns[RETURN_MONTH] = val;
	}

	public void setReturnWeek(float val) {
		this.returns[RETURN_WEEK] = val;
	}

	public void setReturnYear(float val) {
		this.returns[RETURN_YEAR] = val;
	}


	public void setStandardDeviationMonth(float val) {
		this.standardDeviation[RETURN_MONTH] = val;
	}

	public void setStandardDeviationWeek(float val) {
		this.standardDeviation[RETURN_WEEK] = val;
	}

	public void setStandardDeviationYear(float val) {
		this.standardDeviation[RETURN_YEAR] = val;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Portfolio [returns=" + Arrays.toString(returns) + ", standardDeviation="
				+ Arrays.toString(standardDeviation) + "]";
	}
}
