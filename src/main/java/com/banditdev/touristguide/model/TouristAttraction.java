package com.banditdev.touristguide.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private int id;
    private String name;
    private String description;
    private String cityName;
    private     List<String> attractionTags;


    public TouristAttraction() {}

    public TouristAttraction(int id, String name, String description, String cityName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cityName = cityName;
        this.attractionTags = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getAttractionTags() {
        return attractionTags;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setAttractionTags(List<String> attractionTags) {
        this.attractionTags = attractionTags;
    }

    public void addTag(String tag) {
        attractionTags.add(tag);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TouristAttraction{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cityName='" + cityName + '\'' +
                ", attractionTags=" + attractionTags +
                '}';
    }
}
