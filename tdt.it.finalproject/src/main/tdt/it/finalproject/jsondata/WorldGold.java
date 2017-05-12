package main.tdt.it.finalproject.jsondata;

import java.util.Date;

public class WorldGold {
	private String name;
	private double usPrice;
	private double vnPrice;
	private Date dateTime;
	public WorldGold(String name, double usPrice, double vnPrice, String dateTime) {
		super();
		this.name = name;
		this.usPrice = usPrice;
		this.vnPrice = vnPrice;
//		this.dateTime = dateTime;
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
	public double getVnPrice() {
		return vnPrice;
	}
	public void setVnPrice(double vnPrice) {
		this.vnPrice = vnPrice;
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
	
	
}
