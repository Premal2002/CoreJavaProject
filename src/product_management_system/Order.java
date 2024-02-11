package product_management_system;

import java.util.Arrays;
import java.util.Date;

public class Order {

	private int oId,totalAmount;
	private int product_id;
	private String product_name;
	private Date orderedDate;
	private String customer_username,order_status;
	private boolean isDelivered;
	
	public Order(int oId, int totalAmount, int product_id, String product_name, Date orderedDate,
			String customer_username, String order_status, boolean isDelivered) {
		super();
		this.oId = oId;
		this.totalAmount = totalAmount;
		this.product_id = product_id;
		this.product_name = product_name;
		this.orderedDate = orderedDate;
		this.customer_username = customer_username;
		this.order_status = order_status;
		this.isDelivered = isDelivered;
	}
	
	
}
