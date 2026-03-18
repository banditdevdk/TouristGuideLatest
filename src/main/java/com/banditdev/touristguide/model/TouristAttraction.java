package com.banditdev.touristguide.model;
import java.util.List;

public class TouristAttraction {
    private int attractionId;
    private String name;
    private String description;
    private String cityName;
    private List<String> attractionTags;


    public TouristAttraction() {}

    public TouristAttraction(int attractionId, String name, String description, String cityName, List<String> attractionTags) {
        this.attractionId = attractionId;
        this.name = name;
        this.description = description;
        this.cityName = cityName;
        this.attractionTags = attractionTags;
    }


    public int getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(int attractionId) {
        this.attractionId = attractionId;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<String> getAttractionTags() {
        return attractionTags;
    }

    public void setAttractionTags(List<String> attractionTags) {
        this.attractionTags = attractionTags;
    }


    @Override
    public String toString() {
        return "TouristAttraction{" +
                "attractionId=" + attractionId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cityName='" + cityName + '\'' +
                ", attractionTags=" + attractionTags +
                '}';
    }

}
