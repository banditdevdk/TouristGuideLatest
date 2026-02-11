package com.banditdev.touristguide.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private String cityName;
    private List<AttractionTags> attractionTags;


    public TouristAttraction(String name, String description, String cityName) {
        this.name = name;
        this.description = description;
        this.cityName = cityName;
        this.attractionTags = new ArrayList<>();
    }

    public List<AttractionTags> getAttractionTags() {
        return attractionTags;
    }

    public void setAttractionTags(List<AttractionTags> attractionTags) {
        this.attractionTags = attractionTags;
    }

    public void addTag(AttractionTags tag) {
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
