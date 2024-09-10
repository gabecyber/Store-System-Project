package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.event.ChangeListener;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CentralSystem {
	
	/************************************************************************************************/
	
	/***Variables & Constructor***/
	
	 @FXML
	 private TextField IDEnter;
	 @FXML
	 private Label WrongLogin;
	 @FXML
	 private TextField FirstName;
	 @FXML
	 private TextField LastName;
	 @FXML
	 private Label WrongSignup;
	 @FXML
	 private Label IDDisplay;
	 @FXML
	 private ListView<String> CustomerListM;
	 @FXML
	 private ListView<String> StoreListC;
	 @FXML
	 private TextField StoreName;
	 @FXML
	 private ListView<String> StoreListM;
	 @FXML
	 private ListView<String> ProductListM;
	 @FXML
	 private TextField Pname;
	 @FXML
	 private TextField Manufacturer;
	 @FXML
	 private TextField PID;
	 @FXML
	 private TextField pQuantity;
	 @FXML
	 private TextField PType;
	 @FXML
	 private TextField PPrice;
	 
	//***HashMaps that store all stores and customers in system**//
	protected HashMap<Integer, Store> stores = new HashMap<Integer, Store>();
	protected HashMap<Integer, Customer> customers = new HashMap<Integer, Customer>();
	protected HashMap<Integer, Product> products = new HashMap <Integer, Product>();
	//***File Variables**//
	ReaderWriter rw = new ReaderWriter();
	File customerList = new File("src/application/customers_total.txt");
	File storeList = new File("src/application/stores_total.txt");
	File storeProductList = new File("src/application/store_product_list.txt");
	File OrderList = new File("src/ application/ OrderList.txt");
	File InventoryList = new File("src / application/ InventoryList.txt");
	//**CentralSystem constructor**//
	public CentralSystem() {}
	
	/*************************************************************************************************/
	
	/***Methods***/
	
	/*Iterates through a customer list text file, creates customers based on read information, and
	 * adds them to the customers HashMap
	 */
	public boolean uploadCustomers() {
		try {
			Set<String> readCustomers = rw.ListReader(customerList);
			
			for(String currLine : readCustomers) {
				Customer currCustomer = formatCustomer(currLine);
				customers.put(currCustomer.getID(), currCustomer);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*Iterates through a store list text file, creates customers based on read information, and
	 * adds them to the stores HashMap (Could be implemented later as a directory search, not 
	 * a text file search)
	 */
	public boolean uploadStores() {
		try {
			Set<String> readStores = rw.ListReader(storeList);
			
			for(String currLine : readStores) {
				Store currStore = formatStore(currLine);
				stores.put(currStore.getID(), currStore);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	protected boolean uploadProducts() {
		try {
			Set<String> readProducts = rw.ListReader(storeProductList);
			for(String currLine : readProducts) {
				Product currProduct = formatProduct(currLine);
				products.put(currProduct.getID(), currProduct);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/*Prints a list of current customers*/
	@FXML
	public void getCustomers() {
			uploadCustomers();
		
		for(int currKey : customers.keySet()) {
			String currCustomer = customers.get(currKey).toString();
			CustomerListM.getItems().add(currCustomer);
		}
		
	}
	/*Prints a list of current stores*/
	public void getStores() {
		uploadStores();
		for(int currKey : stores.keySet()) {
			String currStore = stores.get(currKey).toString();
			StoreListM.getItems().add(currStore);
			
			
		}
	}
	
public void getProducts() {
		uploadProducts();
	
		for(int currKey : products.keySet()) {
			String currProduct = products.get(currKey).toString();
			ProductListM.getItems().add(currProduct);
	}
}
	
		

	
	/*Creates a LinkedList of store products*/
	public List<Product> displayProductList() {
		List<Product> listProducts = new LinkedList<Product>();
		for(Product p: products.values()) {
			listProducts.add(p);
		}
		return listProducts;
	}
	public void storeCreation() throws IOException {
		String name;
		
		
		if(StoreName.getText().isEmpty()) {
			//
		}
		
		else { 
			name = StoreName.getText();
			Random random = new Random();
			int maxNum = 99;
			int minNum = 10;
			int newID = random.nextInt((maxNum - minNum) - 1) + minNum;
			if( doesStoreExist(newID)) {
				while( doesStoreExist(newID)) {
					newID = random.nextInt((maxNum - minNum) + 1) + minNum;
				}
			}
			
			Store newStore = new Store (name, newID);
			
			stores.put(newID, newStore);
			String inputToFile = String.format("%s %d\n", name, newStore.getID());
			rw.ListAdder(inputToFile, storeList);
			
			
			SceneChanger("Manager_StoreDisplay.fxml");
		}
			}	
	public void addProduct() throws IOException {
		int ID;
		String manufacturer;
		String Type;
		int quantity;
		double Price;
		String name;
		
//		if(name.getText().isEmpty()) {
//			
//		}
		
//		else { 
			 name = Pname.getText();
			 ID = Integer.parseInt(PID.getText());
			 manufacturer = Manufacturer.getText();
			 quantity = Integer.parseInt(pQuantity.getText());
			 Price = Double.parseDouble(PPrice.getText());
			 Type = PType.getText();
			 Product newProduct = new Product(ID, name, manufacturer,Price, quantity, Type);
				products.put(ID, newProduct);
				
				//Prints information to file
				String inputToFile = String.format("%d %s %s %f %d %s\n", ID, name, manufacturer,Price, quantity, Type);
				rw.ListAdder(inputToFile, storeProductList);
				
				
				SceneChanger("Customer_ProductManager.fxml");
		}
	
//	 @FXML
//	 private TextField Pname;
//	 @FXML
//	 private TextField Manufacturer;
//	 @FXML
//	 private TextField PID;
//	 @FXML
//	 private TextField pQuantity;
//	 @FXML
//	 private TextField PType;
//	 @FXML
//	 private TextField PPrice;

	public void customerSignUp () throws IOException {
		String firstName;
		String lastName;
		
		if(FirstName.getText().isEmpty()|| LastName.getText().isEmpty()) {
			WrongSignup.setText("Please Enter both first name and last name");
		}
		
		else{
			firstName = FirstName.getText();
			lastName = LastName.getText();
		//ID setup
		Random random = new Random();
		int maxNum = 9999;
		int minNum = 1000;
		
		
		//Creates new customer
		int newID = random.nextInt((maxNum - minNum) - 1) + minNum;
		if(doesCustomerExist(newID)) {
			while(doesCustomerExist(newID)) {
				newID = random.nextInt((maxNum - minNum) + 1) + minNum;
			}
		}
		Customer newCustomer = new Customer(firstName, lastName, newID);
		customers.put(newID, newCustomer);
		
		//Prints information to file
		String inputToFile = String.format("%s %s %d\n", lastName, firstName,
				newCustomer.getID());
		rw.ListAdder(inputToFile, customerList);
		
		
		SceneChanger("Customer_AccountCreated.fxml");
		
		}
	}
	/*Logs customer into the system if they input an existing ID Numbers. Prints
	 * message otherwise.
	 */
	public boolean doesCustomerExist(int ID) {
			
			if(customers.containsKey(ID)) {
				return true;
			}
			return false;
		}

		public boolean doesStoreExist(int ID) {
			if(stores.containsKey(ID)) {
				return true;
			}
			return false;
		}
	public void customerLogin() throws IOException {
		uploadCustomers();	
		 int ID = Integer.parseInt(IDEnter.getText());
		if(!customers.containsKey(ID)) {
			WrongLogin.setText("ID is not registered");
			
		}
		if(IDEnter.getText().isEmpty()) {
            WrongLogin.setText("Please enter your ID");
        }
		if(customers.containsKey(ID)){ 
			SceneChanger("Customer_MainMenu.fxml");
		}
		
	}
	

//	allows for changing of scenes
public void SceneChanger(String Scene)throws IOException{
	Main m = new Main();
	
	m.changeScene(Scene);
}

public void switchToCustomerSignup(ActionEvent event) throws IOException {

	
	SceneChanger("Customer_Signup.fxml");
}
public void switchToCustomerLogin(ActionEvent event) throws IOException {
	
	SceneChanger("Customer_Login.fxml");
}
public void switchToAccountCreated(ActionEvent event) throws IOException {
customerSignUp();
}
public void switchToManagerCustomerPicker(ActionEvent event) throws IOException {
	SceneChanger("Manager_CustomerPicker.fxml");
	
}
public void switchToManagerStorePicker(ActionEvent event) throws IOException {
	SceneChanger("Manager_StorePicker.fxml");
}
public void switchToManagerMenu(ActionEvent event) throws IOException {
	SceneChanger("Manager_Menu.fxml");
}
public void switchToOrdersPage(ActionEvent event) throws IOException {
	SceneChanger("Customer_OrderList.fxml");
}
public void switchToStoreDisplay(ActionEvent event) throws IOException{
	SceneChanger("Manager_StoreDisplay.fxml");
}
public void switchToStoreAdder(ActionEvent event) throws IOException{
	SceneChanger("Manager_AddingStore.fxml");
}
public void switchToCustomerStorePicker(ActionEvent event) throws IOException{
	SceneChanger("Costumer_StorePicker.fxml");
}
public void switchToCustomerMainMenu(ActionEvent event) throws IOException{
	SceneChanger("Customer_MainMenu.fxml");
}
public void switchToCustomerCartPage(ActionEvent event) throws IOException{
	SceneChanger("Costumer_CartPage.fxml");
}
public void switchToCustomerReceiptPage(ActionEvent event) throws IOException{
	SceneChanger("Costumer_ReceiptPage.fxml");
}
public void switchToManagerProductManager(ActionEvent event) throws IOException{
	SceneChanger("Manager_ProductManager.fxml");
}
public void switchToScene1(ActionEvent event) throws IOException {
	
	SceneChanger("FirstScene.fxml");
	 }
public void removeStore(ActionEvent event) throws IOException{
	int selectedID = StoreListM.getSelectionModel().getSelectedIndex();
	StoreListM.getItems().remove(selectedID);
	
	}
public void removeProduct(ActionEvent event) throws IOException{
	int selectedID = ProductListM.getSelectionModel().getSelectedIndex();
	StoreListM.getItems().remove(selectedID);
	
	}

public void switchToProductAdder(ActionEvent event) throws IOException {
	
	SceneChanger("Manager_Adding Product.fxml");
	 }

	/************************************************************************************************/
	
	/***HELPER METHODS***/
	public Customer formatCustomer(String currLine) {
		String[] parameters = currLine.split(" ");
		String lastName = parameters[0];
		String firstName = parameters[1];
		int ID = Integer.parseInt(parameters[2]);
		Customer newCustomer = new Customer(lastName, firstName, ID);
		return newCustomer;
	}
	
	public Store formatStore(String currLine) {
		String[] parameters = currLine.split(" ");
		String name = parameters[0];
		int ID = Integer.parseInt(parameters[1]);
		Store newStore = new Store(name, ID);
		return newStore;
	}
	public Product formatProduct(String currLine) {
		String[] parameters = currLine.split(" ");
		int ID = Integer.parseInt(parameters[0]);
		String name = parameters[1];
		String manufacturer = parameters[2];
		double price = Double.parseDouble(parameters[3]);
		int quantity = Integer.parseInt(parameters[4]);
		String productType = parameters[5];
		
		Product product = new Product(ID, name, manufacturer, price, quantity, productType);
		return product;
	}
	
}