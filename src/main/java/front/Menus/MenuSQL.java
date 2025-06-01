package front.Menus;

import ConnectionDB.SQLExecutor;

import java.util.Scanner;


public class MenuSQL {
    private final Scanner scanner = new Scanner(System.in);
    private final SQLExecutor executor = new SQLExecutor();

    public void start() {
        int option;
        do {
            System.out.println("Menu:");
            System.out.println("1. Show all clients");
            System.out.println("2. Show employers");
            System.out.println("3. Show concrete order sold by employee");
            //System.out.println("4. ADD");
            System.out.println("5. UPDATE last name");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();


            switch (option) {
                case 1 -> executor.executeQuery("SELECT * FROM client");
                case 2 -> executor.executeQuery("SELECT * FROM employee");
                case 3 ->
                        executor.executeQuery("SELECT CONCAT(e.first_name, ' ', e.last_name) AS employee_name, COUNT(*) AS total_orders FROM orders o JOIN employee e ON o.delivery_employee_id = e.employee_id WHERE e.nif = '87654321B' GROUP BY e.employee_id");
               // case 4 ->
                 //       executor.executeUpdate("INSERT INTO client (first_name, last_name, address, postal_code, locality_id, phone_number) VALUES ('" + name + "','" + last_name + "' , '125 Main Street', 08210, 2, '660000000')");
                case 5 -> {
                    executor.executeQuery("SELECT first_name FROM client");
                    System.out.println("Insert name:");
                    String name = scanner.nextLine();
                    executor.executeUpdate("UPDATE client SET last_name = 'Doe' WHERE first_name = '" + name + "'");
                }
                case 0 -> System.out.println("Disconecting...");
                default -> System.out.println("No valid option");
            }
        } while (option != 0);
    }
}