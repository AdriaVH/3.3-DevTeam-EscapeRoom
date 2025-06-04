package menus;

/**
 * Un node d’opció de menú: text i acció.
 */
public class MenuOption {
    private final String label;
    private final MenuAction action;

    public MenuOption(String label, MenuAction action) {
        this.label = label;
        this.action = action;
    }

    public String getLabel() {
        return label;
    }

    public void execute() {
        action.execute();
    }
}
