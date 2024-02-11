package product_management_system;

import java.sql.Connection;

public interface AdminTasks extends UserTasks {
	
	//Outer function in which all basic functionality defined
	public void ProductFunctions(Connection connection);
	public void CustomerFunctions(Connection connection);
	public void ManagerFunctions(Connection connection);
	
	//Actual functions which contains all logic and called in outer functions
	public void addNewManager(Connection connection);
	public void deleteManager(Connection connection);
	public void deleteCustomer(Connection connection);
	public void addNewProduct(Connection connection);
	public void deleteProduct(Connection connection);
	public void displayProducts(Connection connection);
	public void displayCustomers(Connection connection);
	public void displayManagers(Connection connection);
	public void checkReport(Connection connection);
}
