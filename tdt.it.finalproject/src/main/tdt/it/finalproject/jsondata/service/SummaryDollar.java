package main.tdt.it.finalproject.jsondata.service;

import main.tdt.it.finalproject.jsondata.DollarPrice1;
import main.tdt.it.finalproject.modal.AbstractPrice;

public class SummaryDollar extends SummaryAsset {

	@Override
	protected float getSum() {
		float sum = 0f;
		for (AbstractPrice f : datas) {
			//Cast Asset to Dollar
			DollarPrice1 data = (DollarPrice1) f;
			sum+=data.getBuyCash();
		}
		return sum;
	}

}
