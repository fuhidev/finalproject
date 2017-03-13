package tdt.it.finalproject.jsondata;

import org.json.simple.JSONAware;

public class GoldPrice implements JSONAware {
	private int id;
	private String name;
	// private double buyPrice;
	// private double sellPrice;
	private String buyPrice;
	private String sellPrice;
	private String dateTime;
	public GoldPrice() {

	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public GoldPrice(int id, String name, String buyPrice, String sellPrice) {
		super();
		this.id = id;
		this.name = name;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
	}

	public GoldPrice(int id, String name, String buyPrice, String sellPrice,
			String dateTime) {
		super();
		this.id = id;
		this.name = name;
		this.buyPrice = buyPrice;
		this.sellPrice = sellPrice;
		this.dateTime = dateTime;
	}

	// public GoldPrice(int id, String name, double buyPrice, double sellPrice)
	// {
	// super();
	// this.id = id;
	// this.name = name;
	// this.buyPrice = buyPrice;
	// this.sellPrice = sellPrice;
	// }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// public double getBuyPrice() {
	// return buyPrice;
	// }
	//
	// public void setBuyPrice(float buyPrice) {
	// this.buyPrice = buyPrice;
	// }
	//
	// public double getSellPrice() {
	// return sellPrice;
	// }
	//
	// public void setSellPrice(float sellPrice) {
	// this.sellPrice = sellPrice;
	// }

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