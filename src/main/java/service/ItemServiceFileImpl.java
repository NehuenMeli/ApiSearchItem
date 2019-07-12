package service;

import model.Item;
import util.ItemException;

import java.util.Collection;

public class ItemServiceFileImpl implements ItemService {

    public void addItem(Item item) {

    }

    @Override
    public Collection<Item> getItems(String searchQuery) {
        return null;
    }

    public Collection<Item> getItems() {
        return null;
    }

    public Item getItem(String id) {
        return null;
    }

    public Item editItem(Item item, String id) throws ItemException {
        return null;
    }

    public void deleteItem(String id) {

    }

    public boolean itemExists(String id) {
        return false;
    }

    @Override
    public Collection<String> getTitles() {
        return null;
    }
}
