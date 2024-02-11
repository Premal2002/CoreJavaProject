package product_management_system;

public class CustomerFeedback extends Feedback {
	String customer_username;
	public CustomerFeedback(int fId, String message,String customer_username) {
		super(fId, message);
		this.customer_username = customer_username;
	}

	public String getCustomer() {
		return customer_username;
	}

	public void setCustomer(String customer_username) {
		this.customer_username = customer_username;
	}
	
}
