package product_management_system;

public class Customer extends Users {
	private String email,full_name,address;

	public Customer(int id, String username, String password, String email, String full_name, String address) {
		super(id, username, password);
		this.email = email;
		this.full_name = full_name;
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [email=" + email + ", full_name=" + full_name + ", address=" + address + ", username="
				+ getUsername() + "]";
	}
	
}
