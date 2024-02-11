package product_management_system;

public class Product {
	private int pId,pPrice,pQuantity;
	private String pName,pDescription,pCategory;
	
	public Product(int pId, String pName, String pDescription, String pCategory, int pPrice, int pQuantity) {
		super();
		this.pId = pId;
		this.pPrice = pPrice;
		this.pQuantity = pQuantity;
		this.pName = pName;
		this.pDescription = pDescription;
		this.pCategory = pCategory;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getpQuantity() {
		return pQuantity;
	}

	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpDescription() {
		return pDescription;
	}

	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	
	@Override
	public String toString() {
		return "Product [pId=" + pId + ", pPrice=" + pPrice + ", pQuantity=" + pQuantity + ", pName=" + pName
				+ ", pDescription=" + pDescription + ", pCategory=" + pCategory + "]";
	}
	
	
}
