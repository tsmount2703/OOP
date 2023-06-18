/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Input.Input;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author Dell
 */
public class cdDAOimplement extends cdDAO {

    boolean change = false;

    public cdDAOimplement() {
        catalog = new ArrayList<>();
        iniData();
    }

    @Override
    public void getAllCD() {
        if (catalog.isEmpty()) {
            System.out.println("There are no CD!");
        } else {
            System.out.println("\t\tCD");
            for (CD cd : catalog) {
                System.out.print(cd);
            }
        }
    }

    @Override
    public CD getCD(String ID) {
        for (CD cd : catalog) {
            if (cd.getID().equals(ID)) {
                return cd;
            }
        }
        return null;
    }

    @Override
    public void addCD() {
        if (catalog.size() >= CATALOG_SIZE) {
            System.out.println("Unable to add CD. Catalog is full, remove a CD to add a new one.");
        } else {
            CD cd = inputCD();
            catalog.add(cd);
            System.out.println("CD added successfully!");
            change = true;
        }
    }

    @Override
    public void updateCD() {
        String title = Input.readNonBlank("Enter CD's title");
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).getTitle().contains(title)) {
                result.add(i);
            }
        }
        if (result.isEmpty()) {
            System.out.println("CD with title: " + title + " was not found!");
        } else if (result.size() == 1) {
            change = true;
            CD item = catalog.get(result.get(0));
            boolean valid;
            String collectionName = "";
            String type = "";
            int unitPrice = -1;
            int publishingYear = -1;
            do {
                valid = true;
                collectionName = Input.readString("Enter CD's collection name (game/movie/music) - blank to skip");
                if (collectionName.isEmpty()) {
                    collectionName = item.getCollectionName();
                }
                if (!collectionName.equalsIgnoreCase("game") && !collectionName.equalsIgnoreCase("movie") && !collectionName.equalsIgnoreCase("music")) {
                    valid = false;
                    System.out.println("Collection name can only be game or movie or music");
                }
            } while (!valid);
            item.setCollectionName(collectionName);

            do {
                valid = true;
                type = Input.readString("Enter CD's type (audio/video) - blank to skip");
                if (type.isEmpty()) {
                    type = item.getType();
                }
                if (!type.equalsIgnoreCase("audio") && !type.equalsIgnoreCase("video")) {
                    valid = false;
                    System.out.println("CD type can only be audio or video");
                }
            } while (!valid);
            item.setType(type);
            do {
                valid = true;
                String unitPriceStr = "";
                unitPriceStr = Input.readString("Enter CD's unitPrice - blank to skip");
                if (!unitPriceStr.isEmpty()) {
                    try {
                        if (Integer.parseInt(unitPriceStr) > 0) {
                            unitPrice = Integer.parseInt(unitPriceStr);
                            item.setUnitPrice(unitPrice);
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        System.out.println("Unit price must be an integer.");
                    }

                }
            } while (!valid);

            do {
                valid = true;
                String publishingYearStr = "";
                publishingYearStr = Input.readString("Enter CD's publishing year - blank to skip");
                if (!publishingYearStr.isEmpty()) {
                    try {
                        if (Integer.parseInt(publishingYearStr) > 0) {
                            publishingYear = Integer.parseInt(publishingYearStr);
                            item.setPublishingYear(publishingYear);
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        System.out.println("Publishing year must be an integer.");
                    }

                }
            } while (!valid);

        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.print((i + 1) + ". " + catalog.get(result.get(i)));
            }
            int choice;
            do {
                choice = Input.readPositiveInt("There are more than one CD matched with that title. Enter 1.." + result.size() + " to update that CD", "Enter 1.." + result.size());
            } while (choice < 1 && choice > result.size());
            change = true;
            CD item = catalog.get(result.get(choice - 1));
            boolean valid;
            String collectionName = item.getCollectionName();
            String type = item.getTitle();
            int unitPrice = item.getUnitPrice();
            int publishingYear = item.getPublishingYear();
            do {
                valid = true;
                collectionName = Input.readString("Enter CD's collection name (game/movie/music) - blank to skip");
                if (collectionName.isEmpty()) {
                    collectionName = item.getCollectionName();
                }
                if (!collectionName.equalsIgnoreCase("game") && !collectionName.equalsIgnoreCase("movie") && !collectionName.equalsIgnoreCase("music")) {
                    valid = false;
                    System.out.println("Collection name can only be game or movie or music");
                }
            } while (!valid);
            item.setCollectionName(collectionName);

            do {
                valid = true;
                type = Input.readString("Enter CD's type (audio/video) - blank to skip");
                if (!type.isEmpty()) {
                    if (!type.equalsIgnoreCase("audio") && !type.equalsIgnoreCase("video")) {
                        valid = false;
                        System.out.println("CD type can only be audio or video");
                    }
                }
            } while (!valid);

            do {
                valid = true;
                String unitPriceStr = "";
                unitPriceStr = Input.readString("Enter CD's unitPrice - blank to skip");
                if (!unitPriceStr.isEmpty()) {
                    try {
                        if (Integer.parseInt(unitPriceStr) > 0) {
                            unitPrice = Integer.parseInt(unitPriceStr);
                            item.setUnitPrice(unitPrice);
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        System.out.println("Unit price must be an integer.");
                    }
                }
            } while (!valid);

            do {
                valid = true;
                String publishingYearStr = "";
                publishingYearStr = Input.readString("Enter CD's publishing year - blank to skip");
                if (!publishingYearStr.isEmpty()) {
                    try {
                        if (Integer.parseInt(publishingYearStr) > 0) {
                            publishingYear = Integer.parseInt(publishingYearStr);
                            item.setPublishingYear(publishingYear);
                        }
                    } catch (NumberFormatException e) {
                        valid = false;
                        System.out.println("Publishing year must be an integer.");
                    }
                }
            } while (!valid);
        }
        System.out.println("CD updated successfully!");
    }

    @Override
    public void deleteCDbyTitle() {
        String title = Input.readNonBlank("Enter CD's title");
        ArrayList<CD> result = new ArrayList<>();
        for (CD cd : catalog) {
            if (cd.getTitle().contains(title)) {
                result.add(cd);
            }
        }
        if (result.isEmpty()) {
            System.out.println("CD with title: " + title + " was not found!");
        } else if (result.size() == 1) {
            catalog.remove(result.get(0));
            System.out.println("CD deleted successfully.");
            change = true;
        } else {
            for (int i = 0; i < result.size(); i++) {
                System.out.println((i + 1) + ". " + result.get(i));
            }
            int choice = Input.readPositiveInt("There are more than one CD matched with that title. Enter 1.." + result.size() + " to delete that CD", "Enter 1.." + result.size());
            catalog.remove(result.get(choice - 1));
            System.out.println("CD deleted successfully.");
            change = true;
        }

    }

    public CD inputCD() {
        String ID = "";
        String collectionName = "";
        String type = "";
        String title = "";
        int unitPrice = -1;
        int publishingYear = -1;

        boolean valid = true;
        do {
            valid = true;
            try {
                ID = Input.inputString("Enter CD's ID", Input.ID_PATTERN);
            } catch (InputMismatchException e) {
                valid = false;
                System.out.println("ID cannot be blank!");
            }
            for (CD cd : catalog) {
                if (ID.equals(cd.getID())) {
                    valid = false;
                    System.out.println("ID " + ID + " already exist.");
                }
            }
        } while (!valid);

        do {
            valid = true;
            try {
                collectionName = Input.readNonBlank("Enter CD's collection name (game/movie/music)");
            } catch (InputMismatchException e) {
                valid = false;
                System.out.println("collection name cannot be blank!");
            }
            if (!collectionName.equalsIgnoreCase("game") && !collectionName.equalsIgnoreCase("movie") && !collectionName.equalsIgnoreCase("music")) {
                valid = false;
                System.out.println("Collection name can only be game or movie or music");
            }
        } while (!valid);

        do {
            valid = true;
            try {
                type = Input.readNonBlank("Enter CD's type (audio/video)");
            } catch (InputMismatchException e) {
                valid = false;
                System.out.println("collection name cannot be blank!");
            }
            if (!type.equalsIgnoreCase("audio") && !type.equalsIgnoreCase("video")) {
                valid = false;
                System.out.println("CD type can only be audio or video.");
            }
        } while (!valid);

        title = Input.readNonBlank("Enter CD's title");
        unitPrice = Input.readPositiveInt("Enter CD's unit price", "unitPrice must be a positive number!");
        publishingYear = Input.readPositiveInt("Enter CD's publishing year", "publishing year must be a positive number!");

        return new CD(ID, collectionName, type, title, unitPrice, publishingYear);
    }

    @Override
    public void findCDbyTitle() {
        String title = Input.readNonBlank("Enter CD's title");
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).getTitle().contains(title)) {
                result.add(i);
            }
        }
        if (result.isEmpty()) {
            System.out.println("CD with title: " + title + " was not found!");
        } else {
            System.out.println("Found " + result.size() + " CD with title: " + title);
            for (Integer i : result) {
                System.out.print(catalog.get(i));
            }
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
            if (parts.length < 6) {
                System.out.println("\t!!! There is an erron in account file !!!");
                return;
            }
            CD cd = new CD(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
            catalog.add(cd);
        }
    }

    public void writeFile() {
        try {
            File f = new File(FILE_NAME);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Object item : catalog) {
                pw.print(item.toString());
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("File saved");
        change = false;
    }

    public boolean isChanged() {
        return change;
    }
}
