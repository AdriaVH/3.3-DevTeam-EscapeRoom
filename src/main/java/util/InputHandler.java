package util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static String readStringNotNull(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();

            if (input == null || input.trim().isEmpty()) {
                System.out.println("❌ Input cannot be empty. Try again.");
            } else {
                return input.trim();
            }
        }
    }


    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                if (value < 0) {
                    System.out.println("❌ Number cannot be negative. Try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Try again.");
            }
        }
    }

    public static Integer readOptionalInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (input.isBlank()) {
                return null; // Optional: user left it empty
            }
            try {
                int value = Integer.parseInt(input);
                if (value < 0) {
                    System.out.println("❌ Number cannot be negative. Try again.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Try again.");
            }
        }
    }

    public static String readOptionalString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isBlank()) {
            return null;
        }
        return input;
    }

    public static <T extends Enum<T>> T readOptionalEnum(Class<T> enumClass, String prompt) {
        System.out.println(prompt + " " + Arrays.toString(enumClass.getEnumConstants()));
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.isBlank()) {
                return null; // Optional: user left it empty
            }
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid value. Try again.");
            }
        }
    }

    public static BigDecimal readOptionalBigDecimal(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()) {
            return null;
        }
        try {
            BigDecimal value = new BigDecimal(String.valueOf(input));
            if (value.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("❌ Value cannot be negative. Please try again.");
                return readOptionalBigDecimal(prompt);
            }
            return value;
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid decimal number. Please try again.");
            return readOptionalBigDecimal(prompt);
        }
    }

    public static BigDecimal readBigDecimal(String prompt) {
        System.out.print(prompt);
        try {
            BigDecimal value = new BigDecimal(String.valueOf(scanner.nextLine()));
            if (value.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("❌ Value cannot be negative. Please try again.");
                return readBigDecimal(prompt);
            }
            return value;
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid decimal number. Please try again.");
            return readBigDecimal(prompt);
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

    public static Integer readValidOrSkipId(Function<Integer, Object> findByIdFunction, String prompt) {
        while (true) {
            Integer scapeRoomId = InputHandler.readOptionalInt(prompt);
            String name = prompt.split("\\s+")[1];

            if (scapeRoomId == null || findByIdFunction.apply(scapeRoomId) != null || scapeRoomId == 0) {
                return scapeRoomId;
            }
            System.out.println("❌ No " + name + " exists with ID: " + scapeRoomId + ". Please try again.");
        }
    }

    public static Integer readValidId(Function<Integer, Object> findByIdFunction, String prompt) {
        while (true) {
            Integer id = InputHandler.readInt(prompt);
            String name = prompt.split("\\s+")[1];

            if (findByIdFunction.apply(id) != null) {
                return id;
            }
            System.out.println("❌ No " + name + " exists with ID: " + id + ". Please try again.");
        }
    }
}