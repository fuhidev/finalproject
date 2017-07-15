package main.tdt.it.finalproject.jsondata.service;

import main.tdt.it.finalproject.jsondata.DollarPrice;
import main.tdt.it.finalproject.modal.AbstractPrice;

public class SummaryDollar extends SummaryAsset {

	@Override
	protected float getSum() {
		float sum = 0f;
		for (AbstractPrice f : datas) {
			//Cast Asset to Dollar
			DollarPrice data = (DollarPrice) f;
			sum+=data.getPrice();
		}
		return sum;
	}

}
