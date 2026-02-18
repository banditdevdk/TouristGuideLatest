package com.banditdev.touristguide.model;

public enum AttractionTags {
    GRATIS("Gratis"),
    NATUR("Natur"),
    HISTORISK("Historisk"),
    ARKITEKTUR("Arkitektur"),
    HAVN("Havn"),
    KULTUR("Kultur"),
    SEVÆRDIGHED("Seværdighed"),
    GUIDET_TUR("Guidet-tur"),
    FOTOVENLIG("Fotovenlig"),
    CENTRUM("Centrum"),
    SHOPPING("Shopping");

    private final String description;

    AttractionTags(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
