package helper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import model.Item;
import model.StandarResponse;
import spark.Request;
import util.ItemException;

import java.nio.charset.MalformedInputException;
import java.util.Collection;

public abstract class JsonHelper {

    public static Item getItem(Request request) throws ItemException {
        try {
            return new Gson().fromJson(request.body(), Item.class);
        }catch (JsonSyntaxException e){
            e.printStackTrace();
            throw new ItemException(e.getMessage());
        }
    }

    public static String getJsonResponse(StandarResponse standarResponse){
        return new Gson().toJson(standarResponse);
    }

    public static <T> JsonElement getJsonElement(Collection<T> elements){
        return new Gson().toJsonTree(elements);
    }

    public static JsonElement getJsonElement(Item item){
        return new Gson().toJsonTree(item);
    }
}
