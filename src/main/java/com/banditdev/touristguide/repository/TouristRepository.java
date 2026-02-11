package com.banditdev.touristguide.repository;

import com.banditdev.touristguide.model.AttractionTag;
import com.banditdev.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TouristRepository {
    private final ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();
    private final List<String> tags = List.of("Gratis","Kunst","Museum","Natur","Børnevenlig","18+");
    private final ArrayList<AttractionTag> attractionTags = new ArrayList<>();
    private String touristAttractionName;

    public TouristRepository() {
        populateTouristAttractions();
    }

    public void populateTouristAttractions() {
        TouristAttraction t1 = new TouristAttraction("Den Lille Havfrue","Verdenskendt statue inspireret af H.C. Andersens eventyr, beliggende ved Københavns havn.");
        TouristAttraction t2 = new TouristAttraction("Nyhavn", "Farverig havnefront med historiske bygninger, restauranter og caféer.");
        TouristAttraction t3 = new TouristAttraction("Rundetårn", "Historisk tårn med spiralrampe og udsigt over Københavns skyline.");
        TouristAttraction t4 = new TouristAttraction("Amalienborg", "De danske kongers vinterresidens og et centralt symbol på monarkiet.");
        TouristAttraction t5 = new TouristAttraction("Nationalmuseet", "Danmarks største kulturhistoriske museum med udstillinger fra hele verden.");
        TouristAttraction t6 = new TouristAttraction("Christiania","Unikt fristadssamfund kendt for alternativ livsstil, kunst og kultur.");


        AttractionTag at1 = new AttractionTag("Gratis");
        AttractionTag at2 = new AttractionTag("Museum");
        AttractionTag at3 = new AttractionTag("Natur");
        AttractionTag at4 = new AttractionTag("Børnevenlig");
        AttractionTag at5 = new AttractionTag("18+");
        AttractionTag at6 = new AttractionTag("Kunst");

        t1.addTag(at1);
        t1.addTag(at2);
        t1.addTag(at3);

        t2.addTag(at2);


        touristAttractions.add(t1);
        touristAttractions.add(t2);
        touristAttractions.add(t3);
        touristAttractions.add(t4);
        touristAttractions.add(t5);
        touristAttractions.add(t6);
    }

    public List<String> getTags() {
        return tags;
    }

    public ArrayList<TouristAttraction> getTouristAttractions() {
        return touristAttractions;
    }

    public void addTouristAttraction(TouristAttraction touristAttraction) {
        touristAttractions.add(touristAttraction);
    }

    public TouristAttraction findTouristAttractionByName(String nameToFind) {
        for (TouristAttraction t : touristAttractions) {
            if (nameToFind.equalsIgnoreCase(t.getName())) {
                return t;
            }
        }
        return null;
    }


}
