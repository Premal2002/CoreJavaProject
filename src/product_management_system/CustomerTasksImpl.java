package product_management_system;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Scanner;

public class CustomerTasksImpl implements CustomerTasks {

	@Override
	public boolean LogIn(Connection connection) {
		// TODO Auto-generated method stub
		String username,Password;
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Username : ");
		username = sc.next();
		System.out.print("\nEnter Password : ");
		Password = sc.next();
		

		try {
			PreparedStatement st=connection.prepareStatement("select *from customer where customer_username=?");
			st.setString(1, username);
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(3).equals(Password)) {
					Main.username = username;
					Main.id = rs.getInt(1);
					System.out.println("Logged In successfully");
					return true;
				}else {
					throw new WrongCredetialsException("Wrong Log-in Credentials Exception");
				}
			}else {
				throw new WrongCredetialsException("Wrong Log-in Credentials Exception");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (WrongCredetialsException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return false;
	}

	@Override
	public void register(Connection connection) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("\nEnter Customer details in sequence(c_id,c_username,c_password,c_email,c_name,c_address) : ");
		Customer c = new Customer(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next());
		
		try {
			PreparedStatement st=connection.prepareStatement("insert into customer values(?,?,?,?,?,?)");
			st.setInt(1, c.getId());
			st.setString(2, c.getUsername());
			st.setString(3, c.getPassword());
			st.setString(4, c.getEmail());
			st.setString(5, c.getFull_name());
			st.setString(6, c.getAddress());
			int rs=st.executeUpdate();
			
			if(rs==1) {
				System.out.println("Customer registered");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void viewProducts(Connection connection) {
		// TODO Auto-generated method stub
		String category;
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Category(Mobile/Laptop/Cable) : ");
		category  = sc.next();
		
		try {
			PreparedStatement st=connection.prepareStatement("select *from products where product_category = ?");
			st.setString(1, category);
			ResultSet rs=st.executeQuery();
			try (Formatter fmt = new Formatter()) {
				fmt.format("%20s %20s %20s %20s %20s\n", "Product_Id", "Product_Name", "Product_Description","Product_Category","Product_Price");  
				while(rs.next()) {
					fmt.format("%20s %20s %20s %20s %20s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));  
				}
				System.out.println(fmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void orderProduct(Connection connection) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Product id that you want to order : ");
		int p_id = sc.nextInt();
		try {
			PreparedStatement st=connection.prepareStatement("select * from products where product_id = ?");
			st.setInt(1, p_id);
	
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {
				String p_name = rs.getString(2);
				
				PreparedStatement st1=connection.prepareStatement("insert into orders(product_id,product_name,o_date,customer_username,o_status,isDelivered) values(?,?,?,?,?,?)");
				st1.setInt(1, p_id);
				st1.setString(2, p_name);
				LocalDate dateObj = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String date = dateObj.format(formatter);

				st1.setString(3, date);
				st1.setString(4, Main.username);
				st1.setString(5, "Under Reiew");
				st1.setBoolean(6, false);
				
				int rs1 = st1.executeUpdate();
				
				if(rs1==1) {
					System.out.println("Order Placed");
				}
			}else {
				System.out.println("Product not present with id "+p_id);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void orderStatus(Connection connection) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement st=connection.prepareStatement("select *from orders where customer_username = ?");
			st.setString(1, Main.username);
			ResultSet rs=st.executeQuery();
			try (Formatter fmt = new Formatter()) {
				fmt.format("%20s %20s %20s %20s %20s\n", "Order_Id","Product_ID", "Product_Name", "Order_Date","Order_Status");  
				while(rs.next()) {
					fmt.format("%20s %20s %20s %20s %20s\n", rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(6));  
				}
				System.out.println(fmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void cancelOrder(Connection connection) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Order id which you want to cancel : ");
		int o_id=sc.nextInt();
		try {
			PreparedStatement st=connection.prepareStatement("select * from orders where o_id = ?");
			st.setInt(1, o_id);
	
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {
			boolean isDelivered = rs.getBoolean(7);
			if(!isDelivered) {
			PreparedStatement st1=connection.prepareStatement("delete from orders where o_id = ?");
			st1.setInt(1, o_id);
			int rs1=st1.executeUpdate();
			
			if(rs1==1) {
				System.out.println("Order cancelled");
			}
			}}else {
				throw new IdNotFoundException("Id Not Found Exception");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void giveFeedback(Connection connection) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Feedback Message : ");
		CustomerFeedback f = new CustomerFeedback(0, sc.next(), Main.username);
		
		try {
			PreparedStatement st=connection.prepareStatement("insert into customer_feedback(f_message,customer_username) values(?,?)");
			
			st.setString(1, f.getMessage());
			st.setString(2, f.getCustomer());
			int rs=st.executeUpdate();
			
			if(rs==1) {
				System.out.println("Feedback done");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}
