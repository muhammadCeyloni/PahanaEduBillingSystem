package dao;

import java.util.List;
import model.Item;

public interface ItemDAO {
    boolean addItem(Item item);
    boolean updateItem(Item item);
    boolean deleteItem(int id);
    Item getItemById(int id);
    List<Item> getAllItems();
}
