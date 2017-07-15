package main.tdt.it.finalproject.jsondata.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.tdt.it.finalproject.modal.AbstractPrice;
import main.tdt.it.finalproject.modal.GoldPrice;

public class ReadGoldJson implements IReadJson{
	private final String PATH = "jsonFile/";
	private String fileName;

	
	public ReadGoldJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReadGoldJson(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public List<AbstractPrice> getData() {
		List<AbstractPrice> result = new ArrayList<>();
		try {
			
			JSONParser parser = new JSONParser();
			System.out.println("Dang doc du lieu");
			FileReader file = new FileReader(PATH + fileName+".json");
			JSONArray jsonArray = (JSONArray) parser.parse(file);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				int id = Integer.parseInt((String) jsonObject.get("id"));
				String name = (String) jsonObject.get("name");
				double buyPrice = (double) jsonObject.get("buyPrice");
				double sellPrice = (double) jsonObject.get("sellPrice");
				String date = (String) jsonObject.get("date");
				result.add(new GoldPrice(id, name, buyPrice, sellPrice, date));
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
		return result;
	}

	@Override
	public void setPath(String path) {
		this.setFileName(path);
	}

}