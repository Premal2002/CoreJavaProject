package testpack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class Student implements Serializable {
    String name;
    int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    
}

public class Test2 {
	
	public static void main(String[] args) {
	
		 Student s = new Student("Premal", 21);
	        Student s1 = new Student("Sanket", 22);

	        Student[] sarray = { s, s1 };

	        byte[] sObj;

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");

	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");

	            // Serialize the array of Student objects
	            try (ByteArrayOutputStream b = new ByteArrayOutputStream();
	                    ObjectOutputStream o = new ObjectOutputStream(b)) {
	                o.writeObject(sarray);
	                sObj = b.toByteArray();

	                // Store the serialized data in the database
	                String insertQuery = "INSERT INTO test (Student) VALUES (?)";
	                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
	                    preparedStatement.setBytes(1, sObj);
	                    preparedStatement.executeUpdate();
	                }
	            }

	            // Retrieve the serialized data from the database
	            String selectQuery = "SELECT * FROM test";
	            try (Statement statement = connection.createStatement();
	                    ResultSet rs = statement.executeQuery(selectQuery)) {
	                while (rs.next()) {
	                    byte[] data = rs.getBytes("Student");

	                    // Deserialize the byte array back into an array of Student objects
	                    try (ByteArrayInputStream b = new ByteArrayInputStream(data);
	                            ObjectInputStream o = new ObjectInputStream(b)) {
	                        Student[] stest = (Student[]) o.readObject();

	                        // Process the deserialized Student objects
	                        for (Student sr : stest) {
	                            System.out.println("Name: " + sr.name);
	                            System.out.println("Age: " + sr.age);
	                            System.out.println();
	                        }
	                    }
	                }
	            }

	        } catch (  SQLException | IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		
       

	}

}
