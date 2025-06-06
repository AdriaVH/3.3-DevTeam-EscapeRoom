package util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Try again.");
            }
        }
    }

    /*public static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid decimal. Try again.");
            }
        }
    }*/

    public static Integer readOptionalInt(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();              //Revisar si falta el try-catch
        return input.isBlank() ? null : Integer.parseInt(input);
    }

    public static BigDecimal readBigDecimal(String prompt) {
        System.out.print(prompt);
        try {
            return new BigDecimal(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid decimal number. Please try again.");
            return readBigDecimal(prompt); // Reintentar
        }
    }

    public static <T extends Enum<T>> T readEnum(Class<T> enumClass, String prompt) {
        System.out.println(prompt + " " + Arrays.toString(enumClass.getEnumConstants()));
        while (true) {
            String input = scanner.nextLine().toUpperCase();
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid value. Try again.");
            }
        }
    }
}