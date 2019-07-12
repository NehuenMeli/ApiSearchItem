package model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ItemFromMeli {

    private String id;
    @SerializedName("site_id")
    private String siteId;
    private String title;
    private Integer price;
    @SerializedName("currency_id")
    private String currencyId;
    @SerializedName("listing_type_id")
    private String listingTypeId;
    @SerializedName("stop_time")
    private Date stopTime;
    private String thumbnail;
    private String[] tags;

    public ItemFromMeli(String id, String siteId, String title, Integer price, String currencyId, String listingTypeId, Date stopTime, String thumbnail, String[] tags) {
        this.id = id;
        this.siteId = siteId;
        this.title = title;
        this.price = price;
        this.currencyId = currencyId;
        this.listingTypeId = listingTypeId;
        this.stopTime = stopTime;
        this.thumbnail = thumbnail;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getListingTypeId() {
        return listingTypeId;
    }

    public void setListingTypeId(String listingTypeId) {
        this.listingTypeId = listingTypeId;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
