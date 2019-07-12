package service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import model.Item;
import util.ItemException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.stream.Collectors;

import static helper.FormatterHelper.getString;
import static util.StaticValues.FILE_NAME;
import static util.StaticValues.ResponseMessage.*;

public class ItemServiceFileImpl implements ItemService {

    @Override
    public void addItem(Item item) throws ItemException {
        Collection<Item> items = deserializeItems();
        if (itemExists(item.getId())) throw new ItemException(getString(ERROR_CREAR_ITEM,item.getId()));
        if (items!=null){
            items.add(item);
            serializeItems(items);
        }
    }

    @Override
    public Collection<Item> getItems(String searchQuery) throws ItemException {
        Collection<Item> items = CLIENT.getItems(searchQuery);

        if (items==null || items.isEmpty()) throw new ItemException(getString(ERROR_BUSCAR_ITEM,searchQuery));
        serializeItems(items);

        return items;
    }

    @Override
    public Collection<Item> getItems() throws ItemException {
        Collection<Item> items = deserializeItems();
        if (items==null||items.isEmpty()) throw new ItemException(getString(ERROR_SIN_DATOS));
        return items;
    }

    @Override
    public Item getItem(String id) throws ItemException {
        Collection<Item> items = deserializeItems();
        if (items==null || !itemExists(id)) throw new ItemException(getString(ERROR_OBTENER_ITEM,id));
        return items.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Item editItem(Item item, String id) throws ItemException {
        Collection<Item> items = deserializeItems();
        if (items==null || !itemExists(id)) throw new ItemException(getString(ERROR_EDITAR_ITEM,id));
        Collection<Item> modifiedItems = items.stream()
                .map(currentItem -> currentItem.getId().equals(id)?item:currentItem)
                .collect(Collectors.toList());
        serializeItems(modifiedItems);
        return item;
    }

    @Override
    public void deleteItem(String id) throws ItemException {
        Collection<Item> items = deserializeItems();
        if (items==null || !itemExists(id)) throw new ItemException(getString(ERROR_ELIMINAR_ITEM,id));
        Collection<Item> modifiedItems = items.stream()
                .filter(currentItem -> !currentItem.getId().equals(id))
                .collect(Collectors.toList());
        serializeItems(modifiedItems);
    }

    @Override
    public boolean itemExists(String id) {
        Collection<Item> items = deserializeItems();
        if (items==null) return false;
        return items.stream().filter(item -> item.getId().equals(id)).findAny().orElse(null)!=null;
    }

    @Override
    public Collection<String> getTitles() throws ItemException {
        Collection<Item> items = deserializeItems();
        if (items==null) throw new ItemException(getString(ERROR_SIN_DATOS));
        return items.stream().map(Item::getTitle).collect(Collectors.toList());
    }

    private Collection<Item> deserializeItems(){
        Gson gson = new Gson();
        try (JsonReader reader = new JsonReader(new FileReader(FILE_NAME));){
            Type listType = new TypeToken<Collection<Item>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void serializeItems(Collection<Item> modifiedItems) throws ItemException{
        try (Writer writer = new FileWriter(FILE_NAME,false)){
            Gson gson = new GsonBuilder().create();
            gson.toJson(modifiedItems, writer);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ItemException(e.getMessage());
        }
    }
}
