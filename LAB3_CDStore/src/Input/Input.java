/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Input;

import java.util.Scanner;

/**
 *
 * @author Dell
 */
public final class Input {

    private Input() {
    }
    public static Scanner SC;

    public static final String ID_PATTERN = "CD\\d{3}";

    public static boolean validString(String str, String regEx) {
        return str.matches(regEx);
    }

    public static String readString(String message) {
        String input;
        System.out.print(message + ": ");
        input = SC.nextLine().trim();
        return input;
    }

    public static String readNonBlank(String message) {
        String input = "";
        SC = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            input = SC.nextLine().trim();
        } while (input.isEmpty());

        return input;
    }

    public static String readPattern(String message, String pattern) {
        String input = "";
        boolean valid;
        SC = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            input = SC.nextLine().trim();
            valid = validString(input, pattern);
        } while (!valid);
        return input;
    }

    public static int readPositiveInt(String message, String errorMessage) {
        int result;
        do {
            try {
                result = Integer.parseInt(readNonBlank(message));
            } catch (NumberFormatException e) {
                result = -1;
            }
            if (result <= 0) {
                System.out.println(errorMessage);
            }
        } while (result <= 0);
        return result;
    }

    public static boolean readBool(String message) {
        SC = new Scanner(System.in);
        String input;
        System.out.print(message + "[1/0|Y/N|T/F]");
        input = SC.next().trim();
        if (input.isEmpty()) {
            return false;
        }

        char c = Character.toUpperCase(input.charAt(0));
        return (c == '1' || c == 'Y' || c == 'T');
    }

    public static int readInt(String message, String errorMessage) {
        int result;
        do {
            try {
                result = Integer.parseInt(readNonBlank(message));
            } catch (NumberFormatException e) {
                result = -1;
            }
        } while (result < 0);
        return result;
    }

    public static String inputString(String msg, String pattern) {
        String s;
        do {
            System.out.println(msg);
            s = SC.nextLine();
            if (!s.matches(pattern)) {
                System.out.println("Please follow the format!!!");
            }
        } while (!s.matches(pattern));
        return s;
    }
}
