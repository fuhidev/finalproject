package main.tdt.it.finalproject.portfolio.modal;

public class Point {
	private float standardDeviation;
	private float expectedReturn;
	public Point(float standardDeviation, float expectedReturn) {
		super();
		this.standardDeviation = standardDeviation;
		this.expectedReturn = expectedReturn;
	}
	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}
	public float getStandardDeviation() {
		return standardDeviation;
	}
	public void setStandardDeviation(float standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	public float getExpectedReturn() {
		return expectedReturn;
	}
	public void setExpectedReturn(float expectedReturn) {
		this.expectedReturn = expectedReturn;
	}
	@Override
	public String toString() {
		return "Point [standardDeviation=" + standardDeviation + ", expectedReturn=" + expectedReturn + "]";
	}
	
	
}
