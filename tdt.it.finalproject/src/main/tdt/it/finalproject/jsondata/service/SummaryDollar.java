package main.tdt.it.finalproject.jsondata.service;

import main.tdt.it.finalproject.jsondata.AssetPrice;
import main.tdt.it.finalproject.jsondata.DollarPrice;

public class SummaryDollar extends SummaryAsset {

	@Override
	protected float getSum() {
		float sum = 0f;
		for (AssetPrice f : datas) {
			//Cast Asset to Dollar
			DollarPrice data = (DollarPrice) f;
			sum+=data.getFloatBuyCash();
		}
		return sum;
	}

}
