package DAO;


import java.util.ArrayList;


public abstract class ProductDAO {
    protected ArrayList<Product> productList;
    protected ArrayList<Product> originalProductList;
    protected final String FILE_NAME = "ProductData/ProductData.txt";
    
    public abstract void getAllProduct();
    public abstract Product getProductByID(String ID);
    public abstract void findProductbyTitle();
    public abstract void addProduct();
    public abstract void updateProduct ();
    public abstract void deleteProductbyName();
}
