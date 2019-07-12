package helper;

import model.Item;

import java.util.*;
import java.util.stream.Collectors;

public abstract class CollectionArrangeHelper {

    public static Collection<Item> orderItems(Collection<Item> items, String orderCriteria, String orderOrientation) {
        List<Item> itemList = new ArrayList<>(items);
        try {
            if (Item.OrderCriteria.LISTING_TYPE_ID.getCriteria().equalsIgnoreCase(orderCriteria)) {
                if (Item.OrderOrientation.DESC.getOrientation().equalsIgnoreCase(orderOrientation)) {
                    itemList.sort(Comparator.comparing(Item::getListingTypeId).reversed());
                } else {
                    itemList.sort(Comparator.comparing(Item::getListingTypeId));
                }

            }
            if (Item.OrderCriteria.PRICE.getCriteria().equalsIgnoreCase(orderCriteria)) {
                if (Item.OrderOrientation.DESC.getOrientation().equalsIgnoreCase(orderOrientation)) {
                    itemList.sort(Comparator.comparing(Item::getPrice).reversed());
                } else {
                    itemList.sort(Comparator.comparing(Item::getPrice));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return itemList;
    }

    public static Collection<Item> filterItems(Collection<Item> items, Integer precioMin, Integer precioMax, String tagFilter) {
        return items.stream()
                .filter(item ->
                        ((precioMin==null) || (item.getPrice()>precioMin)) &&
                        ((precioMax==null) || (item.getPrice()<precioMax)) &&
                        ((tagFilter==null) || (Arrays.asList(item.getTags()).contains(tagFilter)))
                )
                .collect(Collectors.toList());
    }
}
