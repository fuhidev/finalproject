package main.tdt.it.finalproject.jsondata.service;

import java.util.List;

import main.tdt.it.finalproject.jsondata.AssetPrice;

public abstract class SummaryAsset {
	protected List<AssetPrice> datas;

	public SummaryAsset() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SummaryAsset(List<AssetPrice> datas) {
		super();
		this.datas = datas;
	}

	public List<AssetPrice> getDatas() {
		return datas;
	}

	public void setDatas(List<AssetPrice> datas) {
		this.datas = datas;
	}
	/**
	 * Lay gia tri trung binh trong 1 nam
	 * @return gia tri
	 */
	public float getSummary() {
		//kiem tra du lieu truyen vao co hay chua, va neu hon 1 phan tu thi thuc hien
		if(datas != null && datas.size()>1){
			return getSum();
		}
		
		return -1f;
	}
	/**
	 * Tinh gia tri trung binh
	 * @return gia tri trung binh
	 */
	protected abstract float getSum() ;
}
