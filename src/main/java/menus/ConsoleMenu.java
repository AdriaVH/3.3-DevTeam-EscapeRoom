package menus;

import java.util.List;
import java.util.Scanner;

/**
 * Classe genèrica que mostra un menú per consola,
 * llegeix l’entrada i crida l’acció corresponent.
 */
public class ConsoleMenu {
    private final String title;
    private final List<MenuOption> options;
    private final Scanner scanner;

    public ConsoleMenu(String title, List<MenuOption> options, Scanner scanner) {
        this.title = title;
        this.options = options;
        this.scanner = scanner;
    }

    public void display() {
        int choice;
        do {
            System.out.println("\n===== " + title + " =====");
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("%d. %s%n", i, options.get(i).getLabel());
            }
            System.out.print("Choose an option: ");
            choice = readInt();
            if (choice < 0 || choice >= options.size()) {
                System.out.println("❗ Invalid option");
            } else {
                options.get(choice).execute();
            }
        } while (choice != 0);
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.nextLine();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
