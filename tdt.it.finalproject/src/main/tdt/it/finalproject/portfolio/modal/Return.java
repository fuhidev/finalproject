package main.tdt.it.finalproject.portfolio.modal;

import java.util.Date;

public class Return {
	private String id;
	private Period period;
	private float percent;
	private Date time;

	public Return(Period period, float price) {
		super();
		this.period = period;
		this.percent = price;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float price) {
		this.percent = price;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "SuatSinhLoiKyVong [period=" + period + ", percent=" + percent + "]";
	}

}
