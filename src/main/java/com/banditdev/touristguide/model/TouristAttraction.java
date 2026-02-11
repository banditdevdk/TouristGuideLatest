package com.banditdev.touristguide.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
//    private List<String> tags = new ArrayList<String>();
    private ArrayList<AttractionTag> attractionTags;


    public TouristAttraction(String name, String description) {
        this.name = name;
        this.description = description;
        attractionTags = new ArrayList<>();
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

    public void addTag(AttractionTag attractionTag) {
        attractionTags.add(attractionTag);
    }

    public ArrayList<AttractionTag> getAttractionTags() {
        return attractionTags;
    }

}
