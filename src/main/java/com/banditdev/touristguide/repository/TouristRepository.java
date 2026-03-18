package com.banditdev.touristguide.repository;

import com.banditdev.touristguide.model.AttractionTags;
import com.banditdev.touristguide.model.Cities;
import com.banditdev.touristguide.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class TouristRepository {

    private final ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristRepository() {
        populateTouristAttractions();
    }

    public void populateTouristAttractions() {

        TouristAttraction t1 = new TouristAttraction("Den Lille Havfrue", "Verdenskendt statue inspireret af H.C. Andersens eventyr, beliggende ved Københavns havn.", Cities.KØBENHAVN);
        TouristAttraction t2 = new TouristAttraction("Nyhavn", "Farverig havnefront med historiske bygninger, restauranter og caféer.", Cities.KØBENHAVN);
        TouristAttraction t3 = new TouristAttraction("Rundetårn", "Historisk tårn med spiralrampe og udsigt over Københavns skyline.", Cities.KØBENHAVN);
        TouristAttraction t4 = new TouristAttraction("Amalienborg", "De danske kongers vinterresidens og et centralt symbol på monarkiet.", Cities.KØBENHAVN);
        TouristAttraction t5 = new TouristAttraction("Nationalmuseet", "Danmarks største kulturhistoriske museum med udstillinger fra hele verden.", Cities.KØBENHAVN);
        TouristAttraction t6 = new TouristAttraction("Christiania", "Unikt fristadssamfund kendt for alternativ livsstil, kunst og kultur.", Cities.KØBENHAVN);

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





    public List<TouristAttraction> findAll() {
        String sql = """
        SELECT
            ta.attraction_id,
            ta.name,
            ta.description,
            c.city_name,
            t.tag_description
        FROM tourist_attraction ta
        LEFT JOIN cities c 
            ON ta.cities_id = c.cities_id
        LEFT JOIN attraction_tags at 
            ON ta.attraction_id = at.attraction_id
        LEFT JOIN tags t 
            ON at.tag_id = t.tag_id
        """;

        return jdbcTemplate.query(sql, rs -> {
            Map<Integer, TouristAttraction> map = new HashMap<>();

            while (rs.next()) {
                int id = rs.getInt("attraction_id");

                TouristAttraction attraction = map.get(id);

                if (attraction == null) {
                    attraction = new TouristAttraction(
                            id,
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("city_name"),
                            new ArrayList<>()
                    );
                    map.put(id, attraction);
                }

                String tag = rs.getString("tag_description");
                if (tag != null) {
                    attraction.getAttractionTags()
                            .add(tag); //".trim().toLowerCase()" tilføj for normalisering!
                }
            }

            return new ArrayList<>(map.values());
        });
    }



    public ArrayList<TouristAttraction> getTouristAttractions() {
        return touristAttractions;
    }


    public TouristAttraction addTouristAttraction(TouristAttraction touristAttraction) {
        touristAttractions.add(touristAttraction);
        return touristAttraction;
    }

    public void deleteTouristAttraction(String name) {
        touristAttractions.removeIf(t ->
                t.getName().equalsIgnoreCase(name));
    }


    public TouristAttraction findTouristAttractionByName(String nameToFind) {
        for (TouristAttraction t : touristAttractions) {
            if (nameToFind.equalsIgnoreCase(t.getName())) {
                return t;
            }
        }
        return null;
    }


    //måske skal vi ændre det fra strings til ENUMS, så det passer til konstruktøren.
    public List<String> getCities() {
        List<String> results = new ArrayList<>();

        for (Cities city : Cities.values()) {
            results.add(city.getCityDescription());
        }
        return results;
    }

    public List<String> getTags() {
        List<String> results = new ArrayList<>();

        for (AttractionTags tag : AttractionTags.values()) {
            results.add(tag.getDescription());
        }
        return results;
    }


    public TouristAttraction updateTouristAttraction(TouristAttraction touristAttraction) {
        for (int i = 0; i < touristAttractions.size(); i++) {
            TouristAttraction ta = touristAttractions.get(i);

            if (touristAttraction.getName().equals(ta.getName())) {
                touristAttractions.set(i, touristAttraction);

                return touristAttraction;
            }
        }
        return null;
    }

}
