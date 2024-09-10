
package application;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Store {

	//***Instance Variables***//
	ReaderWriter rw = new ReaderWriter();
	
	private String name;
	private int ID;
	File storeProductList = new File("sms_project/src/" + name + "_products.txt");
	HashMap<Integer, Product> products = new HashMap<Integer, Product>();
	
	//Store constructor
	protected Store (String name, int ID) {
		this.name = name;
		this.ID = ID;
	}
	
	protected String getName() {
		return name;
	}
	
	protected int getID() {
		return ID;
	}
	
	/*Uploads saved products from store into current store*/
	
	
	

	/*Adds inputed product to product storage if not already existing*/
	protected boolean addProduct(int ID, String name, String manufacturer, double price, 
			int quantity, String productType) {
		Product newProduct = new Product(ID, name, manufacturer, price, quantity, productType);
		if(products.containsKey(newProduct.getID())) {
			return false;
		}
		
		String inputToFile = newProduct.toString();
		try {
			rw.ListAdder(inputToFile, storeProductList);
			products.put(newProduct.getID(), newProduct);
			return true;
			
		}
		catch(Exception e) {
			return false;
		}
	}
	
	/*Removes product from system and storage*/
	protected boolean removeProduct(int ID) {
		if(products.containsKey(ID)) {
			String lineToRemove = products.get(ID).toString();
			try {
				rw.ListRemover(lineToRemove, storeProductList);
				products.remove(ID);
				return true;
			}
			catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	
	/*Creates a LinkedList of store products*/
	public List<Product> displayProductList() {
		List<Product> listProducts = new LinkedList<Product>();
		for(Product p: products.values()) {
			listProducts.add(p);
		}
		return listProducts;
	}
	
	
}