package main.tdt.it.finalproject.jsondata.factory;

import main.tdt.it.finalproject.jsondata.service.IReadJson;

public interface IReadJsonFactory {
	public IReadJson readGold();
	public IReadJson readDollar();
}
