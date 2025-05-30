package front.Menus.reusableMenuTest;

import java.util.List;
import java.util.Scanner;

public class ReusableMenu {

        private final String title;
        private final List<MenuOption> options;
        private final Scanner scanner = new Scanner(System.in);

    public String getTitle() {
        return title;
    }

    public ReusableMenu(String title, List<MenuOption> options) {
            if (options == null || options.isEmpty())
                throw new IllegalArgumentException("Menu must have at least one option");
            this.title = title;
            this.options = options;
        }

        /** Show the menu until the user picks a valid item and its action completes. */
        public void show() {
            while (true) {
                printMenu();
                int choice = readChoice();
                if (choice >= 1 && choice <= options.size()) {
                    options.get(choice - 1).action().run();
                    break;                              // remove this line if you want a looping menu
                } else {
                    System.out.println("✖ Invalid choice, try again!");
                }
            }
        }

        private void printMenu() {
            System.out.println("\n=== " + title + " ===");
            for (int i = 0; i < options.size(); i++) {
                System.out.printf("%d) %s%n", i + 1, options.get(i).label());
            }
            System.out.print("Select an option: ");
        }

        private int readChoice() {
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                return -1;                              // force “invalid choice”
            }
        }
    }


