package service;

import client.SearchItemClient;
import client.SearchItemClientRestImpl;
import model.Item;
import util.ItemException;

import java.util.Collection;

public interface ItemService {

    SearchItemClient CLIENT = new SearchItemClientRestImpl();

    void addItem(Item item) throws ItemException;
    Collection<Item> getItems(String searchQuery) throws ItemException;
    Collection<Item> getItems() throws ItemException;
    Item getItem(String id) throws ItemException;
    Item editItem(Item item, String id) throws ItemException;
    void deleteItem(String id) throws ItemException;
    boolean itemExists(String id);
    Collection<String> getTitles() throws ItemException;
}
