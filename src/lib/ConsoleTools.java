package lib;


import cz.gyarab.util.Utils;

import java.util.Scanner;

public class ConsoleTools {
    private static int retryInt(String prompt, int min, int max) { // Retries the int prompt
        System.out.print("Invalid input, try again: ");
        Utils.sleep(650);
        return intPrompt(prompt, min, max);
    }

    private static int retryArray(String prompt, String[] array) { // Retries the array prompt
        System.out.print("Invalid input, try again: ");
        Utils.sleep(650);
        return arrayPrompt(prompt, array);
    }
    public static void clearConsole() { // Clears the console (does NOT work in built-in terminal in IntelliJ IDEA, use the external terminal)
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }
    public static String stringPrompt(String prompt) { // Prompts the user for a string
        clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int intPrompt(String prompt, int min, int max) { // Prompts the user for an int
        clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);

        try {
            int input = scanner.nextInt();

            if (input < min || input > max) {
                return retryInt(prompt, min, max);
            }

            return input;
        }
        catch (Exception e) {
            return retryInt(prompt, min, max);
        }
    }

    public static boolean boolPrompt(String prompt) { // Prompts the user for a boolean
        clearConsole();
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine().equalsIgnoreCase("y");
    }

    public static int arrayPrompt(String prompt, String[] array) { // Prompts the user for an element from an array
        clearConsole();
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            System.out.println((i + 1) + ": " + array[i]);
        }
        System.out.print("Enter a number: ");

        try {
            int input = scanner.nextInt();

            if (input < 1 || input > array.length) {
                return retryArray(prompt, array);
            }

            return input - 1;
        }
        catch (Exception e) {
            return retryArray(prompt, array);
        }
    }
}
