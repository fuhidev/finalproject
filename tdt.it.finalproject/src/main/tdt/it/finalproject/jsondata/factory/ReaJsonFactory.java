package main.tdt.it.finalproject.jsondata.factory;

import main.tdt.it.finalproject.jsondata.service.IReadJson;
import main.tdt.it.finalproject.jsondata.service.ReadDollarJson;
import main.tdt.it.finalproject.jsondata.service.ReadGoldJson;

public class ReaJsonFactory implements IReadJsonFactory{
	

	public ReaJsonFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public IReadJson readGold() {
		return new ReadGoldJson();
	}

	@Override
	public IReadJson readDollar() {
		// TODO Auto-generated method stub
		return new ReadDollarJson();
	}

}
