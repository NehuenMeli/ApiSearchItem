package model;

import com.google.gson.JsonElement;
import util.StatusResponse;

public class StandarResponse {
    private StatusResponse statusResponse;
    private String message;
    private JsonElement jsonElement;

    public StandarResponse(){
        // Constructor vacío por defecto
    }

    public StandarResponse(StatusResponse statusResponse) {
        this.statusResponse = statusResponse;
    }

    public StandarResponse(StatusResponse statusResponse, String message) {
        this.statusResponse = statusResponse;
        this.message = message;
    }

    public StandarResponse(StatusResponse statusResponse, JsonElement jsonElement) {
        this.statusResponse = statusResponse;
        this.jsonElement = jsonElement;
    }

    public StatusResponse getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(StatusResponse statusResponse) {
        this.statusResponse = statusResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonElement getJsonElement() {
        return jsonElement;
    }

    public void setJsonElement(JsonElement jsonElement) {
        this.jsonElement = jsonElement;
    }
}
