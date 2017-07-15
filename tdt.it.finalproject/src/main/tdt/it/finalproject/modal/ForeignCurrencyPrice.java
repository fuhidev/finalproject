package main.tdt.it.finalproject.modal;

public class ForeignCurrencyPrice extends AbstractPrice{
	protected double price;
	protected String name;


	public ForeignCurrencyPrice(String name, double price) {
		this.name = name;
		this.price=price;
	}

	public ForeignCurrencyPrice(long id, String name,double price) {
		super(id);
		this.name = name;
		this.price=price;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ForeignCurrencyPrice [price=" + price + ", name=" + name + "]";
	}

	
}
