package main.tdt.it.finalproject.jsondata.service;

import java.util.List;

import main.tdt.it.finalproject.modal.AbstractPrice;

public interface IReadJson {
	public List<AbstractPrice> getData();
	public void setPath(String path);
}