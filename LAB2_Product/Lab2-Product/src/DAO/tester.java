package DAO;

import Input.Input;


public class tester {
    public static void main(String[] args) {
        ProductDAOimpl dao = new ProductDAOimpl();
            int choice = 0;
            do {
                choice = printMenu();
                System.out.println("========================================");
                switch (choice) {
                    case 1: 
                        dao.getAllProduct();
                        break;
                    case 2: 
                        dao.addProduct();
                        break;
                    case 3:
                        dao.checkExistProduct();
                        break;
                    case 4:
                        dao.findProductbyTitle();
                        break;
                    case 5:
                        int c;
                        do {
                            c = Input.readPositiveInt("1. Update Product\n2. Delete Product\nEnter 1 or 2", "Enter 1 or 2");
                            switch (c) {
                                case 1:
                                    dao.updateProduct();
                                    break;
                                case 2:
                                    dao.deleteProductbyName();
                                    break;
                                default:
                                    System.out.println("Enter 1 or 2");
                            }
                        } while (c<1 || c> 2);
                        break;
                    case 6: 
                        dao.writeFile();
                        break;
                    case 7:
                        dao.printAllProductFromFile();
                        break;
                    default:
                        if (dao.isChanged()) {
                            boolean result = Input.readBool("Data chagned. Write to file?");
                            if (result == true) {
                                dao.writeFile();
                            }
                        }
                        break;
                }
            }
            while (choice > 0 && choice <= 7);
    }

    public static int printMenu(){
        System.out.println("==================Menu==================");
        System.out.println("1. Print\n2. Create a Product\n3. Check exist Product\n4. Search Product's information by Name\n5. Update Product:\n\t5.1 Update Product\n\t5.2Delete Product\n6. Save Product to file\n7. Print list Products from the file");
        return Input.readPositiveInt("Others - Quit.", "Please enter an integer!");
    }   
}


