package application;



public class Customer extends CentralSystem {

	
	
	//***Variables***
	String firstName;
	String lastName;
	int ID;
	
	//Constructor with ID initialized
	public Customer(String lastName, String firstName, int ID) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.ID = ID;
	}
	
	//Constructor with no predetermined ID
	public Customer(String lastName, String firstName) {
		this(lastName, firstName, 0);
	}
	
	//Gets customer ID
	public int getID() {
		return ID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String toString() {
		return String.format("%s %s %d", firstName, lastName, ID);
	}
}