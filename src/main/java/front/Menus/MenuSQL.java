package front.Menus;

import ConnectionDB.SQLExecutor;
import java.util.Scanner;

public class MenuSQL {
    private final Scanner scanner = new Scanner(System.in);
    private final SQLExecutor executor = new SQLExecutor();

    public void start() {
        int option;
        do {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Manage ScapeRooms");
            System.out.println("2. Manage Rooms");
            System.out.println("3. Manage Items");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> scapeRoomMenu();
                case 2 -> roomMenu();
                case 3 -> itemMenu();
                case 0 -> System.out.println("👋 Exiting...");
                default -> System.out.println("❗ Invalid option");
            }
        } while (option != 0);
    }

    private void scapeRoomMenu() {
        menuTemplate("scaperoom", new String[]{"name"});
    }

    private void roomMenu() {
        menuTemplate("room", new String[]{"scape_room_id", "difficult_level", "theme"});
    }

    private void itemMenu() {
        int option;
        do {
            System.out.println("\n===== Item Menu =====");
            System.out.println("1. Manage DecorationItems");
            System.out.println("2. Manage Enigmas");
            System.out.println("3. Manage Clues");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> menuTemplate("decorationitem", new String[]{"item_id", "material", "theme", "price"});
                case 2 -> menuTemplate("enigma", new String[]{"item_id", "theme"});
                case 3 -> menuTemplate("clue", new String[]{"enigma_id", "item_id", "theme", "price"});
            }
        } while (option != 0);
    }

    private void menuTemplate(String table, String[] fields) {
        int option;
        do {
            System.out.println("\n===== " + table + " Menu =====");
            System.out.println("1. Show all");
            System.out.println("2. Create new");
            System.out.println("3. Update existing");
            System.out.println("4. Delete");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> executor.executeQuery("SELECT * FROM " + table);
                case 2 -> {
                    String sql = buildInsertSQL(table, fields);
                    String[] values = getUserInputs(fields);
                    executor.executeUpdate(sql, values);
                }
                case 3 -> {
                    String sql = buildUpdateSQL(table, fields);
                    String[] values = getUserInputs(fields);
                    System.out.print("Enter ID to update: ");
                    String id = scanner.nextLine();
                    String[] params = new String[values.length + 1];
                    System.arraycopy(values, 0, params, 0, values.length);
                    params[values.length] = id;
                    executor.executeUpdate(sql, params);
                }
                case 4 -> {
                    System.out.print("Enter ID to delete: ");
                    String id = scanner.nextLine();
                    executor.executeUpdate("DELETE FROM " + table + " WHERE id = ?", id);
                }
            }
        } while (option != 0);
    }

    private String[] getUserInputs(String[] fields) {
        String[] values = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            System.out.print("Enter " + fields[i] + ": ");
            values[i] = scanner.nextLine();
            values[i] = values[i].isEmpty() ? null : values[i] ;
        }
        return values;
    }

    private String buildInsertSQL(String table, String[] fields) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + table + " (");
        sql.append(String.join(", ", fields)).append(") VALUES (");
        sql.append("?, ".repeat(fields.length));
        sql.setLength(sql.length() - 2);
        sql.append(")");
        System.out.println(sql);
        return sql.toString();
    }

    private String buildUpdateSQL(String table, String[] fields) {
        StringBuilder sql = new StringBuilder("UPDATE " + table + " SET ");
        for (String field : fields) sql.append(field).append(" = ?, ");
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        return sql.toString();
    }
}

