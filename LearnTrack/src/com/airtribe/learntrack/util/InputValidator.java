package com.airtribe.learntrack.util;

import java.util.Scanner;

public class InputValidator {

    public static int readInt(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next();
            System.out.print(prompt);
        }
        return scanner.nextInt();
    }

    public static int readPositiveInt(Scanner scanner, String prompt) {
        int value;
        do {
            value = readInt(scanner, prompt);
            if (value <= 0) {
                System.out.println("Value must be a positive integer.");
            }
        } while (value <= 0);
        return value;
    }

    public static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    public static String readLine(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }

    public static boolean isValidId(int id, int maxId) {
        return id > 0 && id <= maxId;
    }
}