package com.travel.moldova.service.dto;

import com.travel.moldova.domain.Assets;
import com.travel.moldova.domain.enumeration.Type;

import java.time.Instant;
import java.util.Objects;
import java.util.Set;

public class CreateEventDTO {
    private String title;

    private Double rating;

    private Integer noOfTours;

    private String preViewImg;


    private String description;


    private Type type;

    private String subType;

    private Instant eventDate;

    private String lat;
    private String longitudine;

    private String url;


    private String location;

    private Set<String> assets;


    public CreateEventDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getNoOfTours() {
        return noOfTours;
    }

    public void setNoOfTours(Integer noOfTours) {
        this.noOfTours = noOfTours;
    }

    public String getPreViewImg() {
        return preViewImg;
    }

    public void setPreViewImg(String preViewImg) {
        this.preViewImg = preViewImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Instant getEventDate() {
        return eventDate;
    }

    public void setEventDate(Instant eventDate) {
        this.eventDate = eventDate;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(String longitudine) {
        this.longitudine = longitudine;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<String> getAssets() {
        return assets;
    }

    public void setAssets(Set<String> assets) {
        this.assets = assets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateEventDTO that = (CreateEventDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(rating, that.rating) && Objects.equals(noOfTours, that.noOfTours) && Objects.equals(preViewImg, that.preViewImg) && Objects.equals(description, that.description) && type == that.type && Objects.equals(subType, that.subType) && Objects.equals(eventDate, that.eventDate) && Objects.equals(lat, that.lat) && Objects.equals(longitudine, that.longitudine) && Objects.equals(url, that.url) && Objects.equals(location, that.location) && Objects.equals(assets, that.assets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rating, noOfTours, preViewImg, description, type, subType, eventDate, lat, longitudine, url, location, assets);
    }

    @Override
    public String toString() {
        return "CreateEventDTO{" +
            "title='" + title + '\'' +
            ", rating=" + rating +
            ", noOfTours=" + noOfTours +
            ", preViewImg='" + preViewImg + '\'' +
            ", description='" + description + '\'' +
            ", type=" + type +
            ", subType='" + subType + '\'' +
            ", eventDate=" + eventDate +
            ", lat='" + lat + '\'' +
            ", longitudine='" + longitudine + '\'' +
            ", url='" + url + '\'' +
            ", location='" + location + '\'' +
            ", assets=" + assets +
            '}';
    }
}
