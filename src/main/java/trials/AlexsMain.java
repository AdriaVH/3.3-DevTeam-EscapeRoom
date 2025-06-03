package trials;

import front.Menus.MenuSQL;


public class AlexsMain implements functionTrial {


    @Override
    public void trial() {
        if (!ConnectionDB.DatabaseConnection.testConnection()) {
            System.out.println("❌ Cannot continue, exiting...");
            return;
        }

        front.Menus.MenuSQL menu = new front.Menus.MenuSQL();
        menu.start();
    }
}
