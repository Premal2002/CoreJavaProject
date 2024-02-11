package product_management_system;

public class ManagerReports extends Feedback {
	String manager_username;
	public ManagerReports(int fId, String message,String manager_username) {
		super(fId, message);
		// TODO Auto-generated constructor stub
		this.manager_username = manager_username;
	}
	public String getManager_username() {
		return manager_username;
	}
	public void setManager_username(String manager_username) {
		this.manager_username = manager_username;
	}
	
	

}
