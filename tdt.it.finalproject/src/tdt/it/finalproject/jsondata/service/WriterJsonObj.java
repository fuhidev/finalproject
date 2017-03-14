package tdt.it.finalproject.jsondata.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;

import tdt.it.finalproject.jsondata.AssetPrice;

/**
 * Ghi dung lieu ra file json
 * @author fuhi
 *
 */
public class WriterJsonObj {
	public static final String PATH="jsonFile";

	public void export(List<AssetPrice> datas, String fileName) {
		System.out.println("Dang doc du lieu");
		String JSONResult = JSONArray.toJSONString(datas);
		System.out.println("Tien hanh ghi du lieu");
		try (FileWriter file = new FileWriter(PATH+"\\"+fileName + ".json")) {
			file.write(JSONResult);
			System.out.println("Successfully Copied JSON Object to File...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
