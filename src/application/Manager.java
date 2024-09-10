
package application;


import java.util.List;
import java.io.File;

public class Manager extends CentralSystem  {
	Store currStore = null;
	
	//Manager constructor
	public Manager() {}
	
	/*Method which determines which store the manager would like to manage*/
	public void manageStore(int ID) {
		if(stores.containsKey(ID)) {
			currStore = stores.get(ID);
		}
		throw new IllegalArgumentException("Invalid ID inputted");
	}
	
	//Adds product to file
	public boolean addProduct(int productID, String name, String manufacturer, double price,
			int quantity, String productType) {
		 if(currStore.addProduct(productID, name, manufacturer, price, quantity, productType)) {
			 return true;
		 }
		 return false; 
		 
	}
	
	//Removes product from file
	public boolean removeProduct(int ID) {
		if(currStore.removeProduct(ID)) {
			return true;
		}
		return false;
	}
	
	public List<Product> getProductList(int ID) {
		return stores.get(ID).displayProductList();
	}
	
	/*Creates new store and saves it into system*/
	public void createStore(int ID, String name) {
		if(ID > 99 || ID < 10) {
			throw new IllegalArgumentException("Invalid ID inputted");
		}
		
		Store newStore = new Store(name, ID); 
		
		if(stores.containsKey(newStore.getID())) {
			throw new IllegalArgumentException("Store already exists");
		}
		
		try {
			rw.ListAdder(newStore.toString(), storeList);
			stores.put(newStore.getID(), newStore);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/*Removes store from inventory*/
	public boolean removeStore(int ID) {
		if(stores.containsKey(ID)) {
			String lineToRemove = stores.get(ID).toString();
			try {
				rw.ListRemover(lineToRemove, storeList); //File from CentralSystem class
				stores.remove(ID);
				return true;
			}
			catch (Exception e) {
				return false;
			}
		}
		return false;
	}
	
	/*public Store getStore(int ID) {
		return stores.get(ID);
	}*/
	
	
	
}