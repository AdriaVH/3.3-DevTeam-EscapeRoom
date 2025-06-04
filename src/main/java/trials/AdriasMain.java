package trials;

import Back.models.*;
import Back.Service.*;
import front.Menus.reusableMenuTest.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static front.Menus.reusableMenuTest.ReusableMenu.getListModifyMenu;

public class AdriasMain implements functionTrial {

    @Override
    public void trial() {
        List<Item> decoItems = new ArrayList<>();
        DecorationItem tree = new DecorationItem(2, "Tree", "jhjg", Material.CRYSTAL, Theme.HAUNTED_MANSION, BigDecimal.valueOf(12.99));
        decoItems.add(tree);

        List<ReusableMenu> listOfDecorMenus = new ArrayList<>();
        ReusableMenu creationMenu = createMenu("Create an Item", MenuAction.CREATE);
        ReusableMenu modifyMenu = makeItemMenuList("Create an Item", decoItems, MenuAction.MODIFY);
        ReusableMenu viewMenu = makeItemMenuList("View Items", decoItems, MenuAction.VIEW);

        listOfDecorMenus.add(modifyMenu);
        listOfDecorMenus.add(viewMenu);
        listOfDecorMenus.add(creationMenu);
        ReusableMenu decorMenu = arrayOfMenusToMenu("Decoration Items", listOfDecorMenus);

        ReusableMenu fakeMainMenu = new ReusableMenu("Objects Menu", List.of(
                new MenuOption("Scape Room", () -> System.out.println("running menu scape room")),
                new MenuOption("Room", () -> System.out.println("running menu room")),
                new MenuOption("Enigma", () -> System.out.println("running menu scape enigma")),
                new MenuOption("Clue", () -> System.out.println("running menu scape clue")),
                new MenuOption("Decoration Item", () -> decorMenu.show()),
                new MenuOption("Exit", () -> {
                    System.out.println("Bye!");
                    System.exit(0);
                })
        ));

        while (true) {                     // application loop
            fakeMainMenu.show();
        }
    }

    private ReusableMenu createMenu(String createAnItem, MenuAction menuAction) {
        Item item = null;
        return new ReusableMenu(createAnItem, List.of(new MenuOption("", getAction(item, menuAction))));
    }

    private Runnable getAction(Item item, MenuAction action) {
        switch (action) {
            case CREATE:
                return () -> ServiceDecorationItem.insert((DecorationItem) item);
            case MODIFY:
                return () -> System.out.println();//menu.show();
            case VIEW:
                return () -> System.out.println(item.toString());
            //case DELETE: return () -> item.delete();
            default:
                return () -> System.out.println();//menu.show();
        }
    }

    public ReusableMenu makeItemMenuList(String label, List<Item> list, MenuAction action) {
        List<MenuOption> modifyList = new ArrayList();
        Runnable r;

        for (Item item : list) {
            ReusableMenu objectMenu = new ReusableMenu(item.getName(), getListModifyMenu(item));

            r = getAction(item, action);
            modifyList.add(new MenuOption(item.getName(), r));
        }
        modifyList.add(new MenuOption("Back", () -> {
        }));

        ReusableMenu modifyMenu = new ReusableMenu(label, modifyList);
        return modifyMenu;
    }

    public ReusableMenu arrayOfMenusToMenu(String name, List<ReusableMenu> list) {
        List<MenuOption> finalList = new ArrayList<>();
        list.stream().forEach(menu -> finalList.add(new MenuOption(menu.getTitle(), () -> menu.show())));

        return new ReusableMenu(name, finalList);
    }


        /*public List getListModifyMenu(ReusableMenu menu){
        List list = List.of(new MenuOption("Modify", () -> menu.show()),
                new MenuOption("Back", () -> {}));
        return list ;
    }*/
}

