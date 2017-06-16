package main.tdt.it.finalproject.jsondata.service;

import java.util.List;

import main.tdt.it.finalproject.jsondata.AssetPrice;

public interface IReadJson {
	public List<AssetPrice> getData();
	public void setPath(String path);
}
