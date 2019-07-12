package resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.Item;
import model.StandarResponse;
import service.ItemService;
import util.ItemException;
import helper.JsonHelper;
import util.StatusResponse;

import java.util.*;

import static helper.CollectionArrangeHelper.*;
import static helper.ErrorHelper.buildErrorResponse;
import static spark.Spark.*;
import static helper.FormatterHelper.*;
import static helper.ValidationHelper.*;
import static util.StaticValues.HTTP.*;
import static util.StaticValues.Resource.*;
import static util.StaticValues.ResponseMessage.*;

@Api
public class SearchItemResources {
    private ItemService service;
    

    public SearchItemResources(ItemService service) {
        this.service = service;
        setUpEndpoints();
    }

    private void setUpEndpoints() {

        get(BASE_PATH, ((request, response) -> {
            response.type(APPLICATION_JSON);
            String query = getParameter(request,PARAM_QUERY);
            String orderCriteria = getParameter(request,PARAM_ORDER_CRITERIA);
            String orderOrientation = getParameter(request,PARAM_ORDER_ORIENTATION);
            Integer precioMin = getInteger(getParameter(request,PARAM_PRECIO_MIN));
            Integer precioMax = getInteger(getParameter(request,PARAM_PRECIO_MAX));
            String tagFilter = validateTagFilter(getParameter(request,PARAM_TAG_FILTER));

            StandarResponse standarResponse;

            try {
                Collection<Item> items;
                if (query == null || query.isEmpty())items = service.getItems();
                else items = service.getItems(query);

                items = filterItems(items, precioMin, precioMax, tagFilter);
                if (orderCriteria!=null && !orderCriteria.isEmpty())items = orderItems(items, orderCriteria, orderOrientation);

                standarResponse = new StandarResponse(StatusResponse.SUCCESS,
                        JsonHelper.getJsonElement(items));

            }catch (ItemException e){
                standarResponse = buildErrorResponse(e);
            }


            return JsonHelper.getJsonResponse(standarResponse);
        }));

        post(BASE_PATH, (request, response) -> {
            response.type(APPLICATION_JSON);

            StandarResponse standarResponse;
            try {
                service.addItem(JsonHelper.getItem(request));
                standarResponse = new StandarResponse(StatusResponse.SUCCESS);
            }catch (ItemException e){
                standarResponse = buildErrorResponse(e);
            }

            return JsonHelper.getJsonResponse(standarResponse);
        });

        get(BASE_PATH+"/"+PARAM_ID, (((request, response) -> {
            response.type(APPLICATION_JSON);
            String id = request.params(PARAM_ID);

            StandarResponse standarResponse;
            try {
                Item item = service.getItem(id);
                standarResponse = new StandarResponse(StatusResponse.SUCCESS,
                        JsonHelper.getJsonElement(item));
            }catch (ItemException e){
                standarResponse = buildErrorResponse(e);
            }

            return JsonHelper.getJsonResponse(standarResponse);
        })));

        put(BASE_PATH+"/"+PARAM_ID, (((request, response) -> {
            response.type(APPLICATION_JSON);
            String id = request.params(PARAM_ID);

            Item item = JsonHelper.getItem(request);

            StandarResponse standarResponse = null;

            try{
                Item itemModificado = service.editItem(item, id);
                standarResponse = new StandarResponse(StatusResponse.SUCCESS,
                        JsonHelper.getJsonElement(itemModificado));
            }catch (ItemException e){
                standarResponse = buildErrorResponse(e);
            }

            return JsonHelper.getJsonResponse(standarResponse);
        })));

        delete(BASE_PATH+"/"+PARAM_ID, (((request, response) -> {
            response.type(APPLICATION_JSON);
            String id = request.params(PARAM_ID);

            StandarResponse standarResponse;

            try {
                service.deleteItem(id);
                String responseMessage = getString(ITEM_ELIMINADO,id);
                standarResponse = new StandarResponse(StatusResponse.SUCCESS,responseMessage);
            }catch (ItemException e){
                standarResponse = buildErrorResponse(e);
            }

            return JsonHelper.getJsonResponse(standarResponse);
        })));

        options(BASE_PATH+"/"+PARAM_ID, (((request, response) -> {
            response.type(APPLICATION_JSON);
            String id = request.params(PARAM_ID);

            String responseMessage;

            if (service.itemExists(id)){
                responseMessage = getString(ITEM_EXISTE,id);
            }
            else {
                responseMessage = getString(ITEM_NO_EXISTE,id);
            }

            StandarResponse standarResponse = new StandarResponse(StatusResponse.SUCCESS,responseMessage);

            return JsonHelper.getJsonResponse(standarResponse);
        })));

        get(TITLES_PATH,(((request, response) -> {
            response.type(APPLICATION_JSON);

            StandarResponse standarResponse;

            try {
                Collection<String> titles = service.getTitles();
                standarResponse = new StandarResponse(StatusResponse.SUCCESS,
                        JsonHelper.getJsonElement(titles));
            }catch (ItemException e){
                standarResponse = buildErrorResponse(e);
            }

            return JsonHelper.getJsonResponse(standarResponse);
        })));
    }
}
