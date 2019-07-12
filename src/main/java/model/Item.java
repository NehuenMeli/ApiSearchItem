package model;

import java.util.Date;

public class Item {

    private String id;
    private String siteId;
    private String title;
    private Integer price;
    private Currency currency;
    private String listingTypeId;
    private Date stopTime;
    private String thumbnail;
    private String[] tags;

    public Item(){
        // Constructor vacío por defecto
    }

    public Item(String id, String siteId, String title, Integer price, Currency currency, String listingTypeId, Date stopTime, String thumbnail, String[] tags) {
        this.id = id;
        this.siteId = siteId;
        this.title = title;
        this.price = price;
        this.currency = currency;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
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


    public enum OrderCriteria{
        PRICE("price"),
        LISTING_TYPE_ID("listingTypeId");


        private String criteria;

        OrderCriteria(String criteria){
            this.criteria = criteria;
        }

        public String getCriteria() {
            return criteria;
        }

        public void setCriteria(String criteria) {
            this.criteria = criteria;
        }
    }
    public enum OrderOrientation{
        DESC("desc");


        private String orientation;

        OrderOrientation(String orientation){
            this.orientation = orientation.toLowerCase();
        }

        public String getOrientation() {
            return orientation;
        }

        public void setOrientation(String orientation) {
            this.orientation = orientation;
        }
    }
}
