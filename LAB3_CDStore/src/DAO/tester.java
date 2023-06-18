/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Input.Input;

/**
 *
 * @author Dell
 */
public class tester {

    public static void main(String[] args) {
        cdDAOimplement dao = new cdDAOimplement();
        String[] options = {"View all CD", "Add a CD", "Find CD by title",
            "Update CD", "Remove CD", "Save to file"};
        int choice = 0;
        do {
            choice = printMenu(options);
            System.out.println("========================================");
            switch (choice) {
                case 1:
                    dao.getAllCD();
                    break;
                case 2:
                    dao.addCD();
                    break;
                case 3:
                    dao.findCDbyTitle();
                    break;
                case 4:
                    dao.updateCD();
                    break;
                case 5:
                    dao.deleteCDbyTitle();
                    break;
                case 6:
                    dao.writeFile();
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
        } while (choice > 0 && choice <= options.length);
    }

    public static int printMenu(String[] options) {
        System.out.println("==================Menu==================");
        int i = 1;
        for (String option : options) {
            System.out.println(i + ". " + option);
            i++;
        }
        return Input.readInt("Enter 1.." + options.length + " - other to quit", "Please enter an integer!");
    }
}
