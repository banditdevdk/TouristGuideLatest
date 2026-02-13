package com.banditdev.touristguide.model;

public enum AttractionTags {
    GRATIS("Gratis"),
    KUNST("Kunst"),
    NATUR("Natur"),
    BØRNEVENLIG("Børnevenligt"),
    MUSEUM("Museum"),
    HISTORISK("Historisk"),
    KONGELIG("Kongelig"),
    ARKITEKTUR("Arkitektur"),
    UDSIGT("Udsigt"),
    HAVN("Havn"),
    KULTUR("Kultur"),
    GASTRONOMI("Gastronomi"),
    NATTELIV("Natteliv"),
    FAMILIEVENLIG("Familievenlig"),
    SEVÆRDIGHED("Seværdighed"),
    GUIDET_TUR("Guidet-tur"),
    FOTOVENLIG("Fotovenlig"),
    CENTRUM("Centrum"),
    SHOPPING("Shopping"),
    UNDERHOLDNING("Underholdning");

    private final String description;

    AttractionTags(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
