package DAO;

import java.util.ArrayList;
import Input.Input;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.List;

public class ProductDAOimpl extends ProductDAO {

    boolean change = false;

    public ProductDAOimpl() {
        productList = new ArrayList<>();
        originalProductList = new ArrayList<>();
        iniData();

    }

    @Override
    public void getAllProduct() {
        if (productList.isEmpty()) {
            System.out.println("There are no Product!");
        } else {
            System.out.println("\t\tProduct");
            for (Product cd : productList) {
                System.out.print(cd);
            }
        }
    }

    @Override
    public Product getProductByID(String ID) {
        for (Product p : productList) {
            if (p.getID().equals(ID)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void addProduct() {
        String ID = "";
        String productName = "";
        float unitPrice;
        int quantity;
        String status = "";

        boolean valid = true;
        do {
            valid = true;
            try {
                ID = Input.readNonBlank("Enter Product's ID(number)");
            } catch (InputMismatchException e) {
                valid = false;
                System.out.println("ID cannot be blank!");
            }
            try {
                int value = Integer.parseInt(ID);
            } catch (NumberFormatException e) {
                System.out.println("ID can only be number");
                valid = false;
            }

            for (Product p : productList) {
                if (ID.equals(p.getID())) {
                    valid = false;
                    System.out.println("ID " + ID + " already exist.");
                }
            }
        } while (!valid);

        do {
            valid = true;
            try {
                productName = Input.readNonBlank("Enter Product's name(atlest 5 character)");
            } catch (InputMismatchException e) {
                valid = false;
                System.out.println("Product name cannot be blank!");
            }
            if (productName.length() < 5 || productName.contains(" ")) {
                valid = false;
                System.out.println("Product name must be atleast 5 characters and contains no space!");
            }
            if (searchProductByTitle(productName) != null) {
                valid = false;
                System.out.println("Product named " + productName + " already exist.");
            }
        } while (!valid);

        do {
            valid = true;
            unitPrice = Input.readPositiveFloat("Enter Product's unit price", "unitPrice must be from 0 to 10000!");
            if (unitPrice > 10000) {
                System.out.println("unitPrice must be from 0 to 10000!");
                valid = false;
            }
        } while (!valid);
        do {
            valid = true;
            quantity = Input.readPositiveInt("Enter product quantity", "Product's quantity must be from 1 to 1000!");
            if (quantity > 1000) {
                System.out.println("Product's quantity must be from 1 to 1000!");
                valid = false;
            }
        } while (!valid);

        do {
            valid = true;
            try {
                status = Input.readNonBlank("Enter Product's status (available/not available | y/n | a/n)");
            } catch (InputMismatchException e) {
                valid = false;
                System.out.println("ID cannot be blank!");
            }
            if (status.equalsIgnoreCase("available") || status.equalsIgnoreCase("y") || status.equalsIgnoreCase("a")) {
                status = "available";
            } else if (status.equalsIgnoreCase("not available") || status.equalsIgnoreCase("n")) {
                status = "not available";
            } else {
                valid = false;
                System.out.println("Invalid input!");
            }
        } while (!valid);

        productList.add(new Product(ID, productName, unitPrice, quantity, status));
        System.out.println("Product added successfully");
        change = true;
    }

    @Override
    public void findProductbyTitle() {
        String name = Input.readNonBlank("Enter product's name");
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().contains(name)) {
                result.add(i);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Product named " + name + " was not found!");
        } else {
            System.out.println("Found " + result.size() + " Product with name containing: " + name);
            for (Integer i : result) {
                System.out.print(productList.get(i));
            }
        }
    }

    private Product searchProductByTitle(String name) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().toUpperCase().equalsIgnoreCase(name)) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public void updateProduct() {
        String name = Input.readNonBlank("Enter Product's name").toUpperCase();
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getProductName().contains(name)) {
                result.add(p);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Product named " + name + " was not found!");
        } else if (result.size() == 1) {
            Product p = productList.get(productList.indexOf(result.get(0)));
            String newName = "";
            String newUnitPriceStr = "";
            float newUnitPrice;
            int newQuantity;
            String status;
            boolean valid = true;
            do {
                valid = true;
                try {
                    newName = Input.readString("Enter Product's new name");
                } catch (InputMismatchException e) {
                    newName = p.getProductName().toUpperCase();
                }
                if (newName.length() < 5 || newName.contains(" ")) {
                    valid = false;
                    System.out.println("Product name must be atleast 5 characters and contains no space!");
                }
                if (searchProductByTitle(newName) != null) {
                    valid = false;
                    System.out.println("Product named " + newName + " already exist.");
                }
            } while (!valid);

            p.setProductName(newName);
            do {
                valid = true;
                newUnitPriceStr = Input.readString("Enter Product's unitPrice(1-10000) - blank to skip");
                if (Float.parseFloat(newUnitPriceStr) <= 0 && Float.parseFloat(newUnitPriceStr) > 10000) {
                    System.out.println("Product's price must be from 1 to 10000!");
                    valid = false;
          
                }
                else{
                 newUnitPrice = Float.parseFloat(newUnitPriceStr);
                    p.setUnitPrice(newUnitPrice);
                    change = true;}
            } while (!valid);

         
            do {
                valid = true;
                String newQuantityStr = "";
                newQuantityStr = Input.readString("Enter product quantity - blank to skip");
                if (!newQuantityStr.isEmpty()) {
                    try {
                        if (Integer.parseInt(newQuantityStr) > 0) {
                            newQuantity = Integer.parseInt(newQuantityStr);
                            p.setQuantity(newQuantity);
                            change = true;
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        System.out.println("Quantity must be an integer.");
                    }
                }
            } while (!valid);

            do {
                valid = true;
                status = Input.readString("Enter Product's status (available/not available | y/n | a/n)");
                if (status.isEmpty()) {
                    status = p.getStatus();
                } else if (status.equalsIgnoreCase("available") || status.equalsIgnoreCase("y") || status.equalsIgnoreCase("a")) {
                    status = "available";
                } else if (status.equalsIgnoreCase("not available") || status.equalsIgnoreCase("n")) {
                    status = "not available";
                } else {
                    valid = false;
                    System.out.println("Invalid input!");
                }
            } while (!valid);
            p.setStatus(status);
            change = true;
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.println((i + 1) + ". " + result.get(i));
            }
            int choice = Input.readPositiveInt("There are more than one Product matched with that title. Enter 1.." + result.size() + " to delete that Product", "Enter 1.." + result.size());
            Product p = productList.get(productList.indexOf(result.get(choice - 1)));
            String newName = "";
            String newUnitPriceStr = "";
            float newUnitPrice;
            int newQuantity;
            String status;
            boolean valid = true;
            do {
                valid = true;
                try {
                    newName = Input.readString("Enter Product's name");
                } catch (InputMismatchException e) {
                    newName = p.getProductName();
                }
                if (newName.length() < 5 || newName.contains(" ")) {
                    valid = false;
                    System.out.println("Product name must be atleast 5 characters and contains no space!");
                }
                if (searchProductByTitle(newName) != null) {
                    valid = false;
                    System.out.println("Product named " + newName + " already exist.");
                }
            } while (!valid);
            p.setProductName(newName);
            do {
                valid = true;
                newUnitPriceStr = Input.readString("Enter Product's unitPrice(1-10000) - blank to skip");
                if (Float.parseFloat(newUnitPriceStr) <= 0 && Float.parseFloat(newUnitPriceStr) > 10000) {
                    System.out.println("Product's price must be from 1 to 10000!");
                    valid = false;
          
                }
                else{
                 newUnitPrice = Float.parseFloat(newUnitPriceStr);
                    p.setUnitPrice(newUnitPrice);
                    change = true;}
            } while (!valid);

            do {
                valid = true;
                String newQuantityStr = "";
                newQuantityStr = Input.readString("Enter product quantity(1-1000) - blank to skip");
                if (!newQuantityStr.isEmpty()) {

                    try {
                        if (Integer.parseInt(newQuantityStr) > 0) {
                            newQuantity = Integer.parseInt(newQuantityStr);
                            p.setQuantity(newQuantity);
                            change = true;
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        System.out.println("Unit price must be an integer.");
                    }

                }
            } while (!valid);

            do {
                valid = true;
                status = Input.readString("Enter Product's status (available/not available | y/n | a/n)");
                if (status.isEmpty()) {
                    status = p.getStatus();
                } else if (status.equalsIgnoreCase("available") || status.equalsIgnoreCase("y") || status.equalsIgnoreCase("a")) {
                    status = "available";
                } else if (status.equalsIgnoreCase("not available") || status.equalsIgnoreCase("n")) {
                    status = "not available";
                } else {
                    valid = false;
                    System.out.println("Invalid input!");
                }
            } while (!valid);
            p.setStatus(status);
            change = true;
        }
    }

    @Override
    public void deleteProductbyName() {
        String name = Input.readNonBlank("Enter Product's name").toUpperCase();
        ArrayList<Product> result = new ArrayList<>();
        for (Product p : productList) {
            if (p.getProductName().contains(name)) {
                result.add(p);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Product named " + name + " was not found!");
        } else if (result.size() == 1) {
            productList.remove(result.get(0));
            System.out.println("Product deleted successfully.");
            change = true;
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.println((i + 1) + ". " + result.get(i));
            }
            int choice = Input.readPositiveInt("There are more than one Product matched with that title. Enter 1.." + result.size() + " to delete that Product", "Enter 1.." + result.size());
            productList.remove(result.get(choice - 1));
            System.out.println("Product deleted successfully.");
            change = true;
        }
    }

    public List<String> readLinesFromFile() {
        try {
            ArrayList<String> lines = new ArrayList<>();
            File f = new File(FILE_NAME);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String tempLine = line.trim();
                if (!tempLine.isEmpty()) {
                    lines.add(tempLine);
                }
            }
            br.close();
            fr.close();
            return lines;
        } catch (IOException e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    private void iniData() {
        List<String> lines = readLinesFromFile();
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length != 5) {
                System.out.println("\t!!! There is an error in data file !!!");
                return;
            }
            Product cd = new Product(parts[0], parts[1], Float.parseFloat(parts[2]), Integer.parseInt(parts[3]), parts[4]);
            productList.add(cd);
            originalProductList.add(cd);
        }
    }

    public void writeFile() {
        try {
            File f = new File(FILE_NAME);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Object item : productList) {
                pw.print(item.toString());
            }
            pw.close();
            fw.close();
            change = false;
            originalProductList.clear();
            for (Product p : productList) {
                originalProductList.add(p);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("File saved");
    }

    public boolean isChanged() {
        return change;
    }

    public void checkExistProduct() {
        String name = Input.readNonBlank("Enter product's name");
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < originalProductList.size(); i++) {
            if (productList.get(i).getProductName().equalsIgnoreCase(name)) {
                result.add(i);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No Product Found!");
        } else {
            System.out.println("Exist Product");
        }
    }

    public void printAllProductFromFile() {
        if (originalProductList.isEmpty()) {
            System.out.println("There are no Product!");
        } else {
            System.out.println("\t\tProduct");
            for (Product cd : originalProductList) {
                System.out.print(cd);
            }
        }
    }
}
