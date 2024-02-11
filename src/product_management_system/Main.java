package product_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	static int id;
	static String username;
	
	public static Connection getMyConnection() {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=	DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","root");
		return con;
		}catch(SQLException ex)
		{
			System.out.println("sqlexception"+ex.getMessage()+" "+ex.getClass());
			return null;
		}catch(ClassNotFoundException ex)
		{
			System.out.println("cnfe"+ex.getMessage()+" "+ex.getClass());
			return null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int choice;
		boolean flag=true;
		
		do {
		System.out.println("\nSelect User : ");
		System.out.println("1.Admin");
		System.out.println("2.Customer");
		System.out.println("3.Manager");
		System.out.println("4.Terminate program");
		
		choice = sc.nextInt();
		
		switch(choice) {
		case 1 : {
			AdminTasksImpl admin = new AdminTasksImpl();
			boolean isLoggedIn = admin.LogIn(getMyConnection());
			
			if(isLoggedIn) {
				//Admin Tasks
				System.out.println("\n********************Welcome to Admin Panel********************");
				int adminChoice;
				boolean adminFlag = true;
				do {
					System.out.println("\nChoose entity on Which you want to perform operations : ");
					System.out.println("1.Products");
					System.out.println("2.Customers");
					System.out.println("3.Managers");
					System.out.println("4.Log out as Admin");
					
					adminChoice = sc.nextInt();
					
					switch(adminChoice) {
					case 1 : {
						admin.ProductFunctions(getMyConnection());
						break;
					}
					case 2 : {
						admin.CustomerFunctions(getMyConnection());
						break;
					}
					case 3 : {
						admin.ManagerFunctions(getMyConnection());
						break;
					}
					case 4 : {
						System.out.println("Admin Logged-Out successfully");
						username=null;
						id=0;
						adminFlag=false;
						break;
					}
					default : {
						System.out.println("Enter right choice!!!");
						break;
					}
					}
					}while(adminFlag);
			}
			break;
		}
		case 2 : {
			CustomerTasksImpl customer = new CustomerTasksImpl();
			
			int c_choice1;
			boolean cFlag1 = true;
			do {
			System.out.println("Customer Section");
			System.out.println("1.Login");
			System.out.println("2.Register");
			System.out.println("3.exit");
			c_choice1 = sc.nextInt();
			switch(c_choice1) {
			
			case 1 : {
				boolean isLoggedIn = customer.LogIn(getMyConnection());
				if(isLoggedIn) {
					//Customer Tasks
					System.out.println("\n********************Welcome to Customer Panel********************");
					int customerChoice;
					boolean customerFlag = true;
					do {
						System.out.println("\nChoose function : ");
						System.out.println("1.View Products");
						System.out.println("2.Order Products");
						System.out.println("3.Cancel Order");
						System.out.println("4.Order Status");
						System.out.println("5.Give Feedback");
						System.out.println("6.Log Out");
						
						customerChoice = sc.nextInt();
						
						switch(customerChoice) {
						case 1 : {
							customer.viewProducts(getMyConnection());
							break;
						}
						case 2 : {
							customer.orderProduct(getMyConnection());
							break;
						}
						case 3 : {
							customer.cancelOrder(getMyConnection());
							break;
						}
						case 4 : {
							customer.orderStatus(getMyConnection());
							break;
						}
						case 5 : {
							customer.giveFeedback(getMyConnection());
							break;
						}
						case 6 : {
							System.out.println("Customer Logged-Out successfully");
							username=null;
							id=0;
							customerFlag=false;
							break;
						}
						default : {
							System.out.println("Enter right choice!!!");
							break;
						}
						}
						}while(customerFlag);
				}
				break;
			}
			case 2 : {
				customer.register(getMyConnection());
				break;
			}
			case 3 : {
				cFlag1 = false;
				break;
			}
			default : {
				System.out.println("Enter right choice");
				break;
			}
			}}while(cFlag1);
			break;
			
		}
		case 3:{
			ManagerTasksImpl manager = new ManagerTasksImpl();			
			boolean isLoggedIn = manager.LogIn(getMyConnection());
			if(isLoggedIn) {
				//Customer Tasks
				System.out.println("\n********************Welcome to Manager Panel********************");
				int managerChoice;
				boolean managerFlag = true;
				do {
					System.out.println("\nChoose function : ");
					System.out.println("1.View Sales");
					System.out.println("2.Process Orders");
					System.out.println("3.User Feedbacks");
					System.out.println("4.Generate Report");
					System.out.println("5.Log Out");
					
					managerChoice = sc.nextInt();
					
					switch(managerChoice) {
					case 1 : {
						manager.salesInfo(getMyConnection());
						break;
					}
					case 2 : {
						manager.processOrders(getMyConnection());
						break;
					}
					case 3 : {
						manager.userFeedbacks(getMyConnection());
						break;
					}
					case 4 : {
						manager.generateReports(getMyConnection());
						break;
					}
					case 5 : {
						System.out.println("Manager Logged-Out successfully");
						username=null;
						id=0;
						managerFlag=false;
						break;
					}
					default : {
						System.out.println("Enter right choice!!!");
						break;
					}
					}
					}while(managerFlag);
			}
			break;
		}
		case 4 : {
			System.out.println("Program Terminated Successfully");
			flag = false;
			break;
		}
		default : {
			System.out.println("Enter right choice!!!");
			break;
		}
		}
		
		}while(flag);
		
	}

}
