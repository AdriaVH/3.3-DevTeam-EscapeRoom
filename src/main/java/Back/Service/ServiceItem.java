package Back.Service;

import Back.models.DecorationItem;
import Back.models.Material;
import Back.models.Theme;
import front.dao.DecorationItemDAO;

import java.math.BigDecimal;

import static APP.Main.scanner;

public class ServiceItem {
    public void create(DecorationItem item) {
        DecorationItemDAO decoItemDAO = new DecorationItemDAO();

        item = fillItem();
        decoItemDAO.insert(item);

    }

    public void update(DecorationItem item) {
        DecorationItemDAO decoItemDAO = new DecorationItemDAO();
        item = fillItem();

        decoItemDAO.update(item);
    }

    public DecorationItem fillItem() {
        try {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Room Id: ");
            int roomId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Description: ");
            String description = scanner.nextLine();
            System.out.print("Enter Material: ");
            Material material = Material.valueOf(scanner.nextLine());
            System.out.print("Enter theme: ");
            Theme theme = Theme.valueOf(scanner.nextLine());
            System.out.print("Enter price: ");
            BigDecimal price = BigDecimal.valueOf(Long.parseLong(scanner.nextLine()));

            return new DecorationItem(name, roomId, description, material, theme, price);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
}
