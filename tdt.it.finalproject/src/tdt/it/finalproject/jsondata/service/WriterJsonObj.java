package tdt.it.finalproject.jsondata.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;

import tdt.it.finalproject.jsondata.GoldPrice;

public class WriterJsonObj {

	public void export(List<GoldPrice> goldPrices, String fileName) {
		System.out.println("Dang doc du lieu");
		String JSONResult = JSONArray.toJSONString(goldPrices);
		System.out.println("Tien hanh ghi du lieu");
		try (FileWriter file = new FileWriter(fileName + ".json")) {
			file.write(JSONResult);
			System.out.println("Successfully Copied JSON Object to File...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
