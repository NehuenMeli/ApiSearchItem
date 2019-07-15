package client;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import helper.FormatterHelper;
import model.Currency;
import model.Item;
import model.ItemFromMeli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static util.StaticValues.Client.*;
import static util.StaticValues.HTTP.*;

public class SearchItemClientRestImpl implements SearchItemClient {
    private Currency[] currencies;

    @Override
    public Collection<Item> getItems(String searchQuery) {
        Collection<Item> items = null;
        try {
            URL url = new URL(URL_SEARCH_ITEM+searchQuery);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty(HEADER_ACCEPT, APPLICATION_JSON);

            if (urlConnection instanceof HttpURLConnection) {
                HttpURLConnection connection = (HttpURLConnection) urlConnection;
                String json = getJson(new InputStreamReader(connection.getInputStream()));

                Gson gson = new Gson();

                JsonObject jsonObject = gson.fromJson(json,JsonObject.class);
                JsonElement jsonElement = jsonObject.get(NODE_RESULTS);

                ItemFromMeli[] arrayOfItems = gson.fromJson(jsonElement, ItemFromMeli[].class);

                initializeCurrencies();

                items = fillItems(arrayOfItems);

            } else {
                System.out.println("Problemas con la URL");
            }

        } catch (MalformedURLException e) {
            System.out.println("Error en la url utilizada: " + URL_SEARCH_ITEM);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }

    private Collection<Item> fillItems(ItemFromMeli[] arrayOfItems) {
        Collection<Item> itemsResponse = new ArrayList<>();
        Arrays.stream(arrayOfItems).forEach(itemFromMeli -> {
            Currency currency = getCurrency(itemFromMeli.getCurrencyId());
            Item item = new Item(itemFromMeli.getId(), itemFromMeli.getSiteId(), itemFromMeli.getTitle(),
                    itemFromMeli.getPrice(), currency, itemFromMeli.getListingTypeId(), itemFromMeli.getStopTime(),
                    itemFromMeli.getThumbnail(), itemFromMeli.getTags());

            itemsResponse.add(item);
        });

        return itemsResponse;
    }

    @Override
    public Currency getCurrency(String currencyId) {
        return Arrays.stream(this.currencies)
                .filter(currency -> currency.getId().equals(currencyId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Obtiene el listado de {@link Currency} procedente del origen de datos definido para poder trabajar con ellas luego.
     * Se apoya en el método {@link SearchItemClientRestImpl#getJson(InputStreamReader)} para obtener el json con el
     * listado de currencies
     *
     * @return grupo de todas las {@link Currency} definidas en el cliente
     */
    @Override
    public Currency[] getCurrencies() {
        Currency[] currencies = null;
        try {
            URL url = new URL(URL_CURRENCIES);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty(HEADER_ACCEPT, APPLICATION_JSON);

            if (urlConnection instanceof HttpURLConnection) {
                HttpURLConnection connection = (HttpURLConnection) urlConnection;
                String json = getJson(new InputStreamReader(connection.getInputStream()));

                Gson gson = new Gson();

                currencies = gson.fromJson(json, Currency[].class);

            } else {
                System.out.println("Problemas con la URL");
            }

        } catch (MalformedURLException e) {
            System.out.println("Error en la url utilizada: " + URL_CURRENCIES);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currencies;
    }

    private String getJson(InputStreamReader inputStreamReader) throws IOException {
        String response = null;
        BufferedReader bufferedInputStream = null;
        try {
                bufferedInputStream = new BufferedReader(inputStreamReader);

                StringBuilder urlString = new StringBuilder();
                String currentLine;

                while ((currentLine = bufferedInputStream.readLine()) != null) {
                    urlString.append(currentLine);
                }

                response = urlString.toString();

        }finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    private void initializeCurrencies() {
        this.currencies = getCurrencies();
    }
}
