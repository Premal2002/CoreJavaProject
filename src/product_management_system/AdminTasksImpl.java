package product_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminTasksImpl implements AdminTasks {

	@Override
	public boolean LogIn(Connection connection)  {
		// TODO Auto-generated method stub
		String username,Password;
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter Username : ");
		username = sc.next();
		System.out.print("\nEnter Password : ");
		Password = sc.next();
		

		try {
			PreparedStatement st=connection.prepareStatement("select *from admin where admin_username=?");
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

	

	//Manager Functions
	@Override
	public void displayManagers(Connection connection) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement st=connection.prepareStatement("select *from manager");
			ResultSet rs=st.executeQuery();
			try (Formatter fmt = new Formatter()) {
				fmt.format("%15s %15s %15s\n", "Manager_Id", "Manager_Name", "Manager_Email");  
				while(rs.next()) {
					fmt.format("%14s %14s %17s\n", rs.getInt(1), rs.getString(5), rs.getString(4));  
				}
				System.out.println(fmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public void addNewManager(Connection connection) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Manager details in sequence(Id,Username,Password,Email,Name) : ");
		Manager m = new Manager(sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.next());
		try {
			PreparedStatement st=connection.prepareStatement("insert into manager values(?,?,?,?,?)");
			st.setInt(1, m.getId());
			st.setString(2, m.getUsername());
			st.setString(3, m.getPassword());
			st.setString(4, m.getEmail());
			st.setString(5, m.getFullName());
			int rs=st.executeUpdate();
			
			if(rs==1) {
				System.out.println("Manager added");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	@Override
	public void deleteManager(Connection connection) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Manager id which you want to delete : ");
		try {
			PreparedStatement st=connection.prepareStatement("delete from manager where manager_id = ?");
			st.setInt(1, sc.nextInt());
			int rs=st.executeUpdate();
			
			if(rs==1) {
				System.out.println("Manager deleted");
			}else {
				throw new IdNotFoundException("Id Not Found Exception");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
	}

	
	@Override
	public void checkReport(Connection connection) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement st=connection.prepareStatement("select *from manager_reports");
			ResultSet rs=st.executeQuery();
			try (Formatter fmt = new Formatter()) {
				fmt.format("%15s %15s\n", "Report_Id", "Report_Message");  
				while(rs.next()) {
					fmt.format("%14s %17s\n", rs.getInt(1), rs.getString(2));  
				}
				System.out.println(fmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}


	//Product functions
	@Override
	public void addNewProduct(Connection connection) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Product details in sequence(Name,Description,Category,Price,Quantity) : ");
		Product p = new Product(0, sc.next(),sc.next(), sc.next(), sc.nextInt(), sc.nextInt());
		try {
			PreparedStatement st=connection.prepareStatement("insert into products(product_name,product_desc,product_category,product_price,product_quantity) values(?,?,?,?,?)");
			st.setString(1, p.getpName());
			st.setString(2, p.getpDescription());
			st.setString(3, p.getpCategory());
			st.setInt(4, p.getpPrice());
			st.setInt(5, p.getpQuantity());
			int rs=st.executeUpdate();
			
			if(rs==1) {
				System.out.println("Product added");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteProduct(Connection connection) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Product id which you want to delete : ");
		try {
			PreparedStatement st=connection.prepareStatement("delete from products where product_id = ?");
			st.setInt(1, sc.nextInt());
			int rs=st.executeUpdate();
			
			if(rs==1) {
				System.out.println("Product deleted");
			}else {
				throw new IdNotFoundException("Id Not Found Exception");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println("Input Mismatch Exception");
		} catch (IdNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void displayProducts(Connection connection) {
		// TODO Auto-generated method stub
		
		try {
			PreparedStatement st=connection.prepareStatement("select *from products");
			ResultSet rs=st.executeQuery();
			try (Formatter fmt = new Formatter()) {
				fmt.format("%20s %20s %20s %20s %20s %20s\n", "Product_Id", "Product_Name", "Product_Description","Product_Category","Product_Price","Product_Quantity");  
				while(rs.next()) {
					fmt.format("%20s %20s %20s %20s %20s %20s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));  
				}
				System.out.println(fmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}



	//Customer Functions
	
	@Override
	public void deleteCustomer(Connection connection) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Customer id which you want to delete : ");
		try {
			PreparedStatement st=connection.prepareStatement("delete from customer where customer_id = ?");
			st.setInt(1, sc.nextInt());
			int rs=st.executeUpdate();
			
			if(rs==1) {
				System.out.println("Customer deleted");
			}else {
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
	public void displayCustomers(Connection connection) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement st=connection.prepareStatement("select *from customer");
			ResultSet rs=st.executeQuery();
			try (Formatter fmt = new Formatter()) {
				fmt.format("%20s %20s %20s %20s %20s %20s\n", "Customer_Id", "Customer_Username", "Customer_Password","Customer_Email","Customer_Name","Customer_Address");  
				while(rs.next()) {
					fmt.format("%20s %20s %20s %20s %20s %20s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));  
				}
				System.out.println(fmt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}


	@Override
	public void ProductFunctions(Connection connection) {
		// TODO Auto-generated method stub
		displayProducts(connection);
		int choice;
		boolean flag = true;
		
		Scanner sc  = new Scanner(System.in);
		do {
		System.out.println("1.Add New Product");
		System.out.println("2.Delete Product");
		System.out.println("3.Exit");
		
		choice = sc.nextInt();
		
		
		switch(choice) {
		case 1 : {
			addNewProduct(connection);
			break;
		}
		case 2 : {
			deleteProduct(connection);
			break;
		}
		case 3 : {
			flag = false;
			break;
		}
		default : {
			System.out.println("Enter right choice!!!");
			break;
		}
		}}while(flag);
		
	}


	@Override
	public void CustomerFunctions(Connection connection) {
		// TODO Auto-generated method stub
		displayCustomers(connection);
		int choice;
		boolean flag = true;
		
		Scanner sc  = new Scanner(System.in);
		do {
		System.out.println("1.Delete Customer");
		System.out.println("2.Exit");
		
		choice = sc.nextInt();
		
		switch(choice) {
		case 1 : {
			deleteCustomer(connection);
			break;
		}
		case 2 : {
			flag = false;
			break;
		}
		default : {
			System.out.println("Enter right choice!!!");
			break;
		}
		}}while(flag);
		
	}




	@Override
	public void ManagerFunctions(Connection connection) {
		// TODO Auto-generated method stub
		displayManagers(connection);
		int choice;
		boolean flag = true;
		
		Scanner sc  = new Scanner(System.in);
		do {
		System.out.println("1.Add New Manager");
		System.out.println("2.Delete Manager");
		System.out.println("3.Display Manager Reports");
		System.out.println("4.Exit");
		
		choice = sc.nextInt();
		
		switch(choice) {
		case 1 : {
			addNewManager(connection);
			break;
		}
		case 2 : {
			deleteManager(connection);
			break;
		}
		case 3 : {
			checkReport(connection);
			break;
		}
		case 4 : {
			flag = false;
			break;
		}
		default : {
			System.out.println("Enter right choice!!!");
			break;
		}
		}}while(flag);
		
	}
	

}
