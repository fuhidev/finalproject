package main.tdt.it.finalproject.jsondata;

import org.json.simple.JSONAware;

public class DollarPrice extends AssetPrice implements JSONAware {

	public String buyCash;
	public String buyTransfer;
	public String sellPrice;
	public String date;

	public DollarPrice(int id, String name, String buyCash, String buyTransfer, String sellPrice, String date) {
		super(id, name);
		this.buyCash = buyCash;
		this.buyTransfer = buyTransfer;
		this.sellPrice = sellPrice;
		this.date = date;
	}

	public String getBuyCash() {
		return buyCash;
	}

	public void setBuyCash(String buyCash) {
		this.buyCash = buyCash;
	}

	public String getBuyTransfer() {
		return buyTransfer;
	}

	public void setBuyTransfer(String buyTransfer) {
		this.buyTransfer = buyTransfer;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toJSONString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{"); // Bắt đầu một đối tượng JSON là dấu mở ngoặc nhọn

		sb.append("\"id\":\"" + getId() + "\""); // dòng này có nghĩa là
													// "id":"Giá_Trị"
		sb.append(","); // sau mỗi cặp key/value là một dấu phẩy

		sb.append("\"name\":\"" + getName() + "\"");
		sb.append(",");

		sb.append("\"buyCash\":\"" + getBuyCash() + "\"");
		sb.append(",");

		sb.append("\"buyTransfer\":\"" + getBuyTransfer() + "\"");
		sb.append(",");

		sb.append("\"sellPrice\":\"" + getSellPrice() + "\"");
		sb.append(",");

		sb.append("\"date\":\"" + getDate() + "\"");

		sb.append("}"); // Kết thúc một đối tượng JSON là dấu đóng ngoặc nhọn

		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		DollarPrice price = (DollarPrice) obj;
		return super.equals(obj) && this.buyCash.equals(price.getBuyCash())
				&& this.sellPrice.equals(price.getSellPrice()) && this.buyTransfer.equals(price.getBuyTransfer())
				&& this.date.equals(price.getDate());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + this.toJSONString();
	}

}
