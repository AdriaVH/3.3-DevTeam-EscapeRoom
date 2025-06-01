package trials;

import ConnectionDB.ReadDatabase;
import front.Menus.MenuSQL;


public class AlexsMain implements functionTrial {


    @Override
    public void trial() {
        ReadDatabase app = new ReadDatabase();
        new MenuSQL().start();
    }
}
