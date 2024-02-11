package product_management_system;

public class Manager extends Users {
	private String fullName,email;

	public Manager(int id, String username, String password, String email,String fullName) {
		super(id, username, password);
		this.fullName = fullName;
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Manager [fullName=" + fullName + ", email=" + email + ", username=" + getUsername() + "]";
	}
	
	
}
