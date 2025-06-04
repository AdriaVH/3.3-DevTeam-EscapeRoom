package Back.Service;

import Back.models.DecorationItem;
import Back.models.Item;
import Back.models.Material;
import Back.models.Theme;
import front.dao.DecorationItemDAO;

import java.math.BigDecimal;

public class ServiceDecorationItem {
    public static void insert(DecorationItem item) {
        DecorationItemDAO decoItemDAO = new DecorationItemDAO();
        //function
        decoItemDAO.create(item);
        DecorationItem decoItem = new DecorationItem(item.getRoomId(), item.getName(), item.getDescription(),
                item.getMaterial(), item.getTheme(), item.getPrice());
    }
}
