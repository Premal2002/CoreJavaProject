package product_management_system;

import java.sql.Connection;

public interface CustomerTasks extends UserTasks {
	public void register(Connection connection);
	public void viewProducts(Connection connection);
	public void orderProduct(Connection connection);
	public void orderStatus(Connection connection);
	public void cancelOrder(Connection connection);
	public void giveFeedback(Connection connection);
}
