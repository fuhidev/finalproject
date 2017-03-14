package tdt.it.finalproject.jsondata;

import org.json.simple.JSONAware;

public class GoldPrice extends AssetPrice implements JSONAware {
	private String buyPrice;
	private String sellPrice;
	private String dateTime;

	public GoldPrice(int id, String name, String buyPrice, String sellPrice, String dateTime) {
		super(id, name);
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.dateTime = dateTime;
	}
	
	public GoldPrice(int id, String name, String buyPrice, String sellPrice) {
		super(id, name);
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String toJSONString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{"); // Bắt đầu một đối tượng JSON là dấu mở ngoặc nhọn

		sb.append("\"id\":\"" + getId() + "\""); // dòng này có nghĩa là
													// "id":"Giá_Trị"
		sb.append(","); // sau mỗi cặp key/value là một dấu phẩy

		sb.append("\"name\":\"" + getName() + "\"");
		sb.append(",");

		sb.append("\"buyPrice\":\"" + getBuyPrice() + "\"");
		sb.append(",");

		sb.append("\"sellPrice\":\"" + getSellPrice() + "\"");
		sb.append(",");

		sb.append("\"date\":\"" + getDateTime() + "\"");

		sb.append("}"); // Kết thúc một đối tượng JSON là dấu đóng ngoặc nhọn

		return sb.toString();
	}

}