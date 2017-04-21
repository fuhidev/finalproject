package main.tdt.it.finalproject.jsondata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class WorldGold {
	private String name;
	private double usPrice;
	private double vnPrice;
	private String dateTime;
	public WorldGold(String name, double usPrice, double vnPrice, String dateTime) {
		super();
		this.name = name;
		this.usPrice = usPrice;
		this.vnPrice = vnPrice;
		this.dateTime = dateTime;
	}
	
	public WorldGold(double usPrice, double vnPrice, String dateTime) {
		super();
		this.name = "us";
		this.usPrice = usPrice;
		this.vnPrice = vnPrice;
		this.dateTime = dateTime;
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
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
}
