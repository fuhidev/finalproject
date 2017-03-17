package main.tdt.it.finalproject.jsondata.service;

import java.io.BufferedWriter;
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
	private final String PATH = "jsonFile\\";
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void export(List<AssetPrice> datas) {
		System.out.println("Dang doc du lieu");
		File file = new File(PATH + fileName+".json");
		String JSONResult = JSONArray.toJSONString(datas);
		System.out.println("Tien hanh ghi du lieu");
		// xem thu phai la noi file hay khong
		boolean isAppend = false;
		// neu nhu da ton tai file nay trong thu muc thi tuc la phai ghi them
		// chu khong tao moi
		if (file.exists()) {
			isAppend = true;
		}
		// tien hanh ghi file
		try (FileWriter writer = new FileWriter(PATH + fileName + ".json", isAppend)) {
			writer.write(JSONResult);
			System.out.println("Successfully Copied JSON Object to File...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
