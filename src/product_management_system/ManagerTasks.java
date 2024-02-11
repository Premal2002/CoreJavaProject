package product_management_system;

import java.sql.Connection;

public interface ManagerTasks extends UserTasks {

	public void salesInfo(Connection connection);
	public void processOrders(Connection connection);
	public void userFeedbacks(Connection connection);
	public void generateReports(Connection connection);
	
}
