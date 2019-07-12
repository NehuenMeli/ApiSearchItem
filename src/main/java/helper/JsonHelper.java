package helper;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import model.Item;
import model.StandarResponse;
import spark.Request;

import java.util.Collection;

public abstract class JsonHelper {

    public static Item getItem(Request request){
        return new Gson().fromJson(request.body(), Item.class);
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
