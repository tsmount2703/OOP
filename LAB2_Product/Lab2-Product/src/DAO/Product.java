package DAO;


public class Product {
    private String ID;
    private String productName;
    private float unitPrice;
    private int quantity;
    private String status;

    public Product() {
    }

    public Product(String ID, String productName, float unitPrice, int quantity, String status) {
        this.ID = ID;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ID + "," + productName + "," + unitPrice + "," + quantity + "," + status + "\n";
    }

    
    
    
}
