package service;

import model.Item;
import util.ItemException;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

import static helper.FormatterHelper.getString;
import static util.StaticValues.ResponseMessage.*;

public class ItemServiceMapImpl implements ItemService {

    private HashMap<String,Item> itemHashMap;

    public ItemServiceMapImpl() {
        itemHashMap = new HashMap<>();
    }

    @Override
    public void addItem(Item item) throws ItemException {
        if (itemExists(item.getId())) throw new ItemException(getString(ERROR_CREAR_ITEM,item.getId()));
        this.itemHashMap.put(item.getId(),item);
    }

    @Override
    public Collection<Item> getItems(String searchQuery) throws ItemException {
        itemHashMap.clear();
        Collection<Item> items = CLIENT.getItems(searchQuery);

        if (items==null || items.isEmpty()) throw new ItemException(getString(ERROR_BUSCAR_ITEM,searchQuery));
        
        items.forEach(item -> this.itemHashMap.put(item.getId(),item));

        return items;
    }

    @Override
    public Collection<Item> getItems() throws ItemException {
        if (itemHashMap.isEmpty()) throw new ItemException(getString(ERROR_SIN_DATOS));
        return itemHashMap.values();
    }

    @Override
    public Item getItem(String id) throws ItemException {
        if (!itemExists(id)) throw new ItemException(getString(ERROR_OBTENER_ITEM,id));
        return itemHashMap.get(id);
    }

    @Override
    public Item editItem(Item item, String id) throws ItemException {
        if (!itemExists(id)) throw new ItemException(getString(ERROR_EDITAR_ITEM,id));
        return itemHashMap.replace(id,item);
    }

    @Override
    public void deleteItem(String id) throws ItemException {
        if (!itemExists(id)) throw new ItemException(getString(ERROR_ELIMINAR_ITEM,id));
        itemHashMap.remove(id);
    }

    @Override
    public boolean itemExists(String id){
        return itemHashMap.containsKey(id);
    }

    @Override
    public Collection<String> getTitles() throws ItemException {
        if (itemHashMap.isEmpty()) throw new ItemException(getString(ERROR_SIN_DATOS));
        return itemHashMap.values().stream().map(Item::getTitle).collect(Collectors.toList());
    }
}
