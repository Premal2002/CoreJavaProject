package product_management_system;

public abstract class Feedback {
	private int fId;
	private String message;
	
	public Feedback(int fId, String message) {
		super();
		this.fId = fId;
		this.message = message;
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
