package com.banditdev.touristguide.model;

public enum Cities {
    KØBENHAVN("København"),
    AARHUS("Aarhus"),
    ODENSE("Odense"),
    AALBORG("Aalborg"),
    ESBJERG("Esbjerg"),
    ROSKILDE("Roskilde"),
    HELSINGØR("Helsingør"),
    HERNING("Herning"),
    VEJLE("Vejle"),
    KOLDING("Kolding");


    private final String description;

    Cities(String description) {
        this.description = description;
    }

    public String getCityDescription() {
        return description;
    }

}
