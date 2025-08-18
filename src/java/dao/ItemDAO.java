package dao;

import model.Item;
import java.util.List;

public interface ItemDAO {
    boolean addItem(Item item);
    Item getItemById(int itemId);
    List<Item> getAllItems();

    // ✅ Add these two methods
    boolean updateItem(Item item);
    boolean deleteItem(int itemId);
}
