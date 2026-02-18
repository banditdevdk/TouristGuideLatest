package com.banditdev.touristguide.repository;

import com.banditdev.touristguide.model.AttractionTags;
import com.banditdev.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TouristRepository {
    private final ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository() {
        populateTouristAttractions();
    }

    public void populateTouristAttractions() {

        TouristAttraction t1 = new TouristAttraction("Den Lille Havfrue", "Verdenskendt statue inspireret af H.C. Andersens eventyr, beliggende ved Københavns havn.", "København");
        TouristAttraction t2 = new TouristAttraction("Nyhavn", "Farverig havnefront med historiske bygninger, restauranter og caféer.", "København");
        TouristAttraction t3 = new TouristAttraction("Rundetårn", "Historisk tårn med spiralrampe og udsigt over Københavns skyline.", "København");
        TouristAttraction t4 = new TouristAttraction("Amalienborg", "De danske kongers vinterresidens og et centralt symbol på monarkiet.", "København");
        TouristAttraction t5 = new TouristAttraction("Nationalmuseet", "Danmarks største kulturhistoriske museum med udstillinger fra hele verden.", "København");
        TouristAttraction t6 = new TouristAttraction("Christiania", "Unikt fristadssamfund kendt for alternativ livsstil, kunst og kultur.", "København");

        // Tilføj til liste
        touristAttractions.add(t1);
        touristAttractions.add(t2);
        touristAttractions.add(t3);
        touristAttractions.add(t4);
        touristAttractions.add(t5);
        touristAttractions.add(t6);

        //Den Lille Havfrue
        t1.addTag(AttractionTags.GRATIS);
        t1.addTag(AttractionTags.SEVÆRDIGHED);
        t1.addTag(AttractionTags.FOTOVENLIG);
        t1.addTag(AttractionTags.HAVN);

        //Nyhavn
        t2.addTag(AttractionTags.HAVN);
        t2.addTag(AttractionTags.FOTOVENLIG);
        t2.addTag(AttractionTags.CENTRUM);

        //Rundetårn
        t3.addTag(AttractionTags.HISTORISK);
        t3.addTag(AttractionTags.ARKITEKTUR);
        t3.addTag(AttractionTags.SEVÆRDIGHED);
        t3.addTag(AttractionTags.GUIDET_TUR);

        //Amalienborg
        t4.addTag(AttractionTags.HISTORISK);
        t4.addTag(AttractionTags.ARKITEKTUR);
        t4.addTag(AttractionTags.SEVÆRDIGHED);
        t4.addTag(AttractionTags.CENTRUM);

        //Nationalmuseet
        t5.addTag(AttractionTags.KULTUR);
        t5.addTag(AttractionTags.HISTORISK);
        t5.addTag(AttractionTags.GUIDET_TUR);

        //Christiania
        t6.addTag(AttractionTags.KULTUR);
        t6.addTag(AttractionTags.GRATIS);
        t6.addTag(AttractionTags.SEVÆRDIGHED);
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
