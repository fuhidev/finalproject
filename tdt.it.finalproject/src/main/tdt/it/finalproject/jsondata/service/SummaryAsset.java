package main.tdt.it.finalproject.jsondata.service;

import java.util.List;

import main.tdt.it.finalproject.modal.AbstractPrice;

public abstract class SummaryAsset {
	protected List<AbstractPrice> datas;

	public SummaryAsset() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SummaryAsset(List<AbstractPrice> datas) {
		super();
		this.datas = datas;
	}

	public List<AbstractPrice> getDatas() {
		return datas;
	}

	public void setDatas(List<AbstractPrice> datas) {
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
