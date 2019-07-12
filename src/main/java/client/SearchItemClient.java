package client;

import model.Currency;
import model.Item;

import java.util.Collection;

public interface SearchItemClient {

    public Collection<Item> getItems(String searchQuery);

    public Currency getCurrency(String currencyId);

    public Currency[] getCurrencies();
}
