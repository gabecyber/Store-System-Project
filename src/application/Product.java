package application;


public class Product {
	int ID;
	String name;
	String manufacturer;
	double price;
	int quantity;
	String productType;
	
	
	public Product(int ID, String name, String manufacturer, double price,
			int quantity, String productType) {
		this.ID = ID;
		this.name = name;
		this.manufacturer = manufacturer;
		this.price = price;
		this.quantity = quantity;
		this.productType = productType;
	}


	protected int getID() {
		return ID;
	}


	protected void setID(int iD) {
		ID = iD;
	}


	protected String getName() {
		return name;
	}


	protected void setName(String name) {
		this.name = name;
	}


	protected String getManufacturer() {
		return manufacturer;
	}


	protected void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	protected double getPrice() {
		return price;
	}


	protected void setPrice(double price) {
		this.price = price;
	}


	protected int getQuantity() {
		return quantity;
	}


	protected void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	protected String getProductType() {
		return productType;
	}


	protected void setProductType(String productType) {
		this.productType = productType;
	}
	
	public String toString() {
		return String.format("%d %s %s %.2f %d %s", ID, name, manufacturer, price, quantity, productType);
	}
	
}