package testpack;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.*;

class MyClass implements Serializable {
    private int id;
    private String name;
	public MyClass(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

    // Constructor, getters, and setters
}

public class ObjectSerializationExample {
    public static void main(String[] args) {
        try {
            // Establishing connection to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");

            // Inserting object into database
//            MyClass objectToStore = new MyClass(4, "Example");
//            MyClass objectToStore1 = new MyClass(5, "Example");
//            
//            MyClass[] marray = {objectToStore,objectToStore1};
//            
//            byte[] serializedObject;
//            try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                 ObjectOutputStream oos = new ObjectOutputStream(baos)) {
//                oos.writeObject(marray);
//                serializedObject = baos.toByteArray();
//            }
//
//            String insertQuery = "INSERT INTO serialized_objects (id, data) VALUES (?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
//                preparedStatement.setInt(1, objectToStore.getId());
//                preparedStatement.setBytes(2, serializedObject);
//                preparedStatement.executeUpdate();
//            }

            // Retrieving object from database
            
            
            int objectId = 1; // ID of the object to retrieve
            String selectQuery = "SELECT * FROM serialized_objects";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
//                preparedStatement.setInt(1, objectId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        byte[] data = resultSet.getBytes("data");
                        System.out.println(data);
                        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
                             ObjectInputStream ois = new ObjectInputStream(bais)) {
                            MyClass[] retrievedObject = (MyClass[]) ois.readObject();
                            for(MyClass m : retrievedObject) {
                            System.out.println("Retrieved object: " + m.getName());
                            System.out.println("Retrieved object: " + m.getId());
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            // Closing connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
