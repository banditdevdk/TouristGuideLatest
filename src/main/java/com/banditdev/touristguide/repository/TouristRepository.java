package com.banditdev.touristguide.repository;

import com.banditdev.touristguide.model.AttractionTags;
import com.banditdev.touristguide.model.Cities;
import com.banditdev.touristguide.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class TouristRepository {
    private final ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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

    public List<String> getCities() {
        String sql = "SELECT city_name FROM tourist_db.cities";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                rs.getString("city_name")
        );
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
