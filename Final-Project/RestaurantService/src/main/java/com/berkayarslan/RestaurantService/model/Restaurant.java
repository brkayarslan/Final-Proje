package com.berkayarslan.RestaurantService.model;


import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "restaurants")
public class Restaurant {

    @Id
    @Indexed(name = "id")
    private String id;

    @Indexed(name = "name")
    private String name;

    @Indexed(name = "description")
    private String description;

    @Indexed(name = "longitude")
    private Double longitude;

    @Indexed(name = "latitude")
    private Double latitude;
    @Indexed(name = "location")
    private String location;
    @Indexed(name = "foodScore")
    private Integer foodScore;

    @Indexed(name = "deliveryScore")
    private Integer deliveryScore;

    @Indexed(name = "presentationScore")
    private Integer presentationScore;

    @Indexed(name = "avarageScore")
    private Integer avarageScore;

    @Indexed(name = "reviewCount")
    private Integer reviewCount;

    public Restaurant(String id, String name, String description, Double latitude, Double longitude,  Integer foodScore, Integer deliveryScore, Integer presentationScore, Integer avarageScore,Integer reviewCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = latitude+","+longitude;
        this.foodScore = foodScore;
        this.deliveryScore = deliveryScore;
        this.presentationScore = presentationScore;
        this.avarageScore = avarageScore;
        this.reviewCount = reviewCount;
    }

    public Restaurant() {
    }

    public Restaurant(String name, String description, Double latitude,Double longitude) {
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.location = latitude+","+longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getFoodScore() {
        return foodScore;
    }

    public void setFoodScore(Integer foodScore) {
        this.foodScore = foodScore;
    }

    public Integer getDeliveryScore() {
        return deliveryScore;
    }

    public void setDeliveryScore(Integer deliveryScore) {
        this.deliveryScore = deliveryScore;
    }

    public Integer getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(Integer presentationScore) {
        this.presentationScore = presentationScore;
    }

    public Integer getAvarageScore() {
        return avarageScore;
    }

    public void setAvarageScore(Integer avarageScore) {
        this.avarageScore = avarageScore;
    }
    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }
}
