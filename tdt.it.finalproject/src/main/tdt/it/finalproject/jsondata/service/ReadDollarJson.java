package main.tdt.it.finalproject.jsondata.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.tdt.it.finalproject.jsondata.DollarPrice1;
import main.tdt.it.finalproject.modal.AbstractPrice;

public class ReadDollarJson implements IReadJson {
	private final String PATH = "jsonFile/";
	private String fileName;

	public ReadDollarJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReadDollarJson(String fileName) {
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
		FileReader file=null;
		try {
			JSONParser parser = new JSONParser();
			System.out.println("Dang doc du lieu");
			file = new FileReader(PATH + fileName + ".json");
			JSONArray jsonArray = (JSONArray) parser.parse(file);
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				int id = Integer.parseInt((String) jsonObject.get("id"));
				String name = (String) jsonObject.get("name");
				double buyCash = (double) jsonObject.get("buyCash");
				double buyTransfer = (double) jsonObject.get("buyTransfer");
				double sellPrice = (double) jsonObject.get("sellPrice");
				String date = (String) jsonObject.get("date");
				result.add(new DollarPrice1(id, name, buyCash, buyTransfer, sellPrice, date));
			}
			
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			if(file!=null)
				try {
					file.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}

	@Override
	public void setPath(String path) {
		this.setFileName(path);
	}

}
