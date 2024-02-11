package testpack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//Serialization - process of converting objects to byte stream (then only we can write object in file)
class Product implements Serializable {//we use serializable to convert object into bytestream then only we can store it into file//empty interface - no abstract methods in it
	int pid;
	String pname;
	int pquantity;
	
	public Product(int pid, String pname, int pquantity) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pquantity = pquantity;
	}
	
	
}

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Object read and write example
		try(
				FileOutputStream stream=new FileOutputStream("object.txt",true);
				ObjectOutputStream objwrite=new ObjectOutputStream(stream);
				)
		{
			
			objwrite.writeObject(new Product(101,"mobile",3));
			
			
		}catch(IOException ex)
		{
			System.out.println(ex.getMessage()+" "+ex.getClass());
		}
		
		
		//Reding an Object
//		try(
//				FileInputStream stream=new FileInputStream("object.txt");
//				ObjectInputStream objwrite=new ObjectInputStream(stream);
//				)
//		{
//			
//			Product product=(Product)objwrite.readObject();
//			System.out.println(product.pid+" "+product.pname+" "+product.pquantity);
//			
//			
//		}catch(IOException ex)
//		{
//			System.out.println(ex.getMessage()+" "+ex.getClass());
//		}
//		catch(ClassNotFoundException ex) {
//			System.out.println(ex.getMessage());
//		}
	}

}
