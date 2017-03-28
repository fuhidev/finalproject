package main.tdt.it.finalproject.jsondata.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.jsondata.GoldPrice;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {
	private final String PATH = "jsonFile/";
	private String fileName;

	public ReadJson(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPATH() {
		return PATH;
	}

	public List<DollarPrice> convertDollarOjbect(List<DollarPrice> datas) {
		try {
			JSONParser parser = new JSONParser();
			System.out.println("Dang doc du lieu");
			FileReader file = new FileReader(PATH + fileName + ".json");
			JSONArray jsonArray = (JSONArray) parser.parse(file);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				int id = Integer.parseInt((String) jsonObject.get("id"));
				String name = (String) jsonObject.get("name");
				String buyCash = (String) jsonObject.get("buyCash");
				String buyTransfer = (String) jsonObject.get("buyCash");
				String sellPrice = (String) jsonObject.get("buyCash");
				String date = (String) jsonObject.get("buyCash");
				datas.add(new DollarPrice(id, name, buyCash, buyTransfer,
						sellPrice, date));
			}
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println(e);
			e.printStackTrace();
		}
		return datas;
	}

	public List<GoldPrice> convertGoldObject(List<GoldPrice> datas) {
		try {
			JSONParser parser = new JSONParser();
			System.out.println("Dang doc du lieu");
			FileReader file = new FileReader(PATH + fileName + ".txt");
			JSONArray jsonArray = (JSONArray) parser.parse(file);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				int id = Integer.parseInt((String) jsonObject.get("id"));
				String name = (String) jsonObject.get("name");
				String buyPrice = (String) jsonObject.get("buyPrice");
				String sellPrice = (String) jsonObject.get("sellPrice");
				String date = (String) jsonObject.get("date");
				datas.add(new GoldPrice(id, name, buyPrice, sellPrice, date));
				GoldPrice gold = new GoldPrice(id, name, buyPrice, sellPrice, date);
				System.out.println(gold.toString());
				
			}
			file.close();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (ParseException e) {
			System.err.println(e);
			e.printStackTrace();
		}finally{
		
		}
		return datas;
	}
	public static void main(String[] args) {
		List<GoldPrice> datas = new ArrayList<GoldPrice>();
		ReadJson json = new ReadJson("gold_20160101-20170201");
		json.convertGoldObject(datas);
	}
}
