package product_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.Scanner;

public class ManagerTasksImpl implements ManagerTasks {

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
			PreparedStatement st=connection.prepareStatement("select *from manager where manager_username=?");
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
	public void salesInfo(Connection connection) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement st=connection.prepareStatement("select product_name, count(product_name)from orders group by product_name");
			ResultSet rs=st.executeQuery();
			try (Formatter fmt = new Formatter()) {
				fmt.format("%20s %20s\n","Product_Name","Order_Count");  
				while(rs.next()) {
					fmt.format("%20s %20s\n", rs.getString(1), rs.getInt(2));  
				}
				System.out.println(fmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void processOrders(Connection connection) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement st=connection.prepareStatement("select *from orders");
			ResultSet rs=st.executeQuery();
			try (Formatter fmt = new Formatter()) {
				fmt.format("%20s %20s %20s %20s %20s %20s %20s\n", "Order_Id","Product_ID", "Product_Name", "Order_Date","Customer_Username","Order_Status","isDelivered");  
				while(rs.next()) {
					fmt.format("%20s %20s %20s %20s %20s %20s %20s\n", rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5),rs.getString(6),rs.getBoolean(7));  
				}
				System.out.println(fmt);
			}
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter order id and updated status to process order: ");
				PreparedStatement st1=connection.prepareStatement("update orders set o_status = ? where o_id = ?");
				st1.setInt(2, sc.nextInt());
				st1.setString(1, sc.next());
				int rs1=st1.executeUpdate();
				
				if(rs1==1) {
					System.out.println("Order processed");
				}
				else {
					System.out.println("Order not found with entered id");
				}		
				
			}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void userFeedbacks(Connection connection) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement st=connection.prepareStatement("select *from customer_feedback");
			ResultSet rs=st.executeQuery();
			try (Formatter fmt = new Formatter()) {
				fmt.format("%20s %20s %20s\n", "FeedBack_Id","Feedback_Message","Customer_UserName");  
				while(rs.next()) {
					fmt.format("%20s %20s %20s\n", rs.getInt(1), rs.getString(2), rs.getString(3));  
				}
				System.out.println(fmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void generateReports(Connection connection) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Report Message : ");
		ManagerReports mr = new ManagerReports(0, sc.next(),Main.username);
		
		try {
			PreparedStatement st=connection.prepareStatement("insert into manager_reports(r_message,manager_username) values(?,?)");
			
			st.setString(1, mr.getMessage());
			st.setString(2, mr.getManager_username());
			int rs=st.executeUpdate();
			
			if(rs==1) {
				System.out.println("Report Added");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
