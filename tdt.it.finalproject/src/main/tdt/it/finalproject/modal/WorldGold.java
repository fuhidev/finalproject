package main.tdt.it.finalproject.modal;

import java.util.Date;

import main.tdt.it.finalproject.util.DateTimeUtil;
import main.tdt.it.finalproject.util.ScraperUtil;

public class WorldGold  extends AbstractPrice{
	private String name;
	private double usPrice;
	private double vnPrice;
	private Date dateTime;
	
	
	public WorldGold(long id,String name, double usPrice, double vnPrice, Date dateTime) {
		super(id);
		this.name = name;
		this.usPrice = usPrice;
		this.vnPrice = vnPrice;
		this.dateTime = dateTime;
	}

	public WorldGold(String name, double usPrice, double vnPrice, Date dateTime) {
		super();
		this.name = name;
		this.usPrice = usPrice;
		this.vnPrice = vnPrice;
		this.dateTime = dateTime;
	}

	public WorldGold(String name, double usPrice, double vnPrice, String dateTime) {
		super();
		this.name = name;
		this.usPrice = usPrice;
		this.vnPrice = vnPrice;
		this.dateTime = DateTimeUtil.formatStringToDate(dateTime);
	}
	
	public WorldGold(String name, String usPrice, String vnPrice, String dateTime) {
		super();
		this.name = name;
		this.setUsPrice(usPrice);
		this.setVnPrice(vnPrice);
		this.dateTime = DateTimeUtil.formatStringToDate(dateTime);
		System.out.println(this.dateTime);
	}
	
	public WorldGold(double usPrice, double vnPrice, Date dateTime2) {
		super();
		this.name = "us";
		this.usPrice = usPrice;
		this.vnPrice = vnPrice;
		this.dateTime = dateTime2;
	}

	public WorldGold() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getUsPrice() {
		return usPrice;
	}
	public void setUsPrice(double usPrice) {
		this.usPrice = usPrice;
	}
	public void setUsPrice(String usPrice) {
		this.usPrice = ScraperUtil.convertStringToDouble(usPrice);
	}
	public double getVnPrice() {
		return vnPrice;
	}
	public void setVnPrice(double vnPrice) {
		this.vnPrice = vnPrice;
	}
	public void setVnPrice(String vnPrice) {
		this.vnPrice = ScraperUtil.convertStringToDouble(vnPrice);
	}
//	public String getDateTime() {
//		return dateTime;
//	}
//	public void setDateTime(String dateTime) {
//		this.dateTime = dateTime;
//	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(usPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(vnPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		WorldGold other = (WorldGold) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(usPrice) != Double.doubleToLongBits(other.usPrice))
			return false;
		if (Double.doubleToLongBits(vnPrice) != Double.doubleToLongBits(other.vnPrice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WorldGold [name=" + name + ", usPrice=" + usPrice + ", vnPrice=" + vnPrice + ", dateTime=" + dateTime
				+ "]";
	}
	
	
}
