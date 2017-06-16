package main.tdt.it.finalproject.jsondata.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;

import main.tdt.it.finalproject.jsondata.AssetPrice;

/**
 * Ghi dung lieu ra file json
 * 
 * @author fuhi
 *
 */
public class WriterJson {
	private final String PATH = "jsonFile/";
	private String fileName;

	public WriterJson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WriterJson(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void export(List<AssetPrice> datas) {
		System.out.println("Dang doc du lieu");
		File file = new File(PATH + fileName + ".json");
		String JSONResult = JSONArray.toJSONString(datas);
		System.out.println("Tien hanh ghi du lieu");
		// tien hanh ghi file
		try (FileWriter writer = new FileWriter(file, file.exists())) {
			writer.write(JSONResult);
			System.out.println("Successfully Copied JSON Object to File...");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
