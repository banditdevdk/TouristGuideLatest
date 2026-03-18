package com.banditdev.touristguide.repository;

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
        String sql = "SELECT ta.attraction_id, ta.name, ta.description, c.city_name " +
                "FROM tourist_db.tourist_attraction ta " +
                "JOIN tourist_db.cities c ON ta.cities_id = c.cities_id";
        return new ArrayList<>(jdbcTemplate.query(sql, (rs, rowNum) -> {
            TouristAttraction t = new TouristAttraction(
                    rs.getInt("attraction_id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("city_name")
            );
            t.setAttractionTags(getTagsForAttraction(rs.getInt("attraction_id")));
            return t;
        }));
    }

    //hjælpe metode til getTouristAttractions()
    private List<String> getTagsForAttraction(int attractionId) {
        String sql = "SELECT t.tag_description FROM tourist_db.tags t " +
                "JOIN tourist_db.attraction_tags at ON t.tag_id = at.tag_id " +
                "WHERE at.attraction_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("tag_description"), attractionId);
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
        String sql = "SELECT tag_description FROM tourist_db.tags";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                rs.getString("tag_description"));
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
