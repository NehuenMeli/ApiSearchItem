package model;

import com.google.gson.annotations.SerializedName;

public class Currency {
    private String id;
    private String symbol;
    private String description;
    @SerializedName("decimal_places")
    private String decimalPlaces;

    public Currency(){
        // Constructor vacío por defecto
    }

    public Currency(String id, String symbol, String description, String decimalPlaces) {
        this.id = id;
        this.symbol = symbol;
        this.description = description;
        this.decimalPlaces = decimalPlaces;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(String decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }
}
