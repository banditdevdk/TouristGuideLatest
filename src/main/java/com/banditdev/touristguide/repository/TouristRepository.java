package com.banditdev.touristguide.repository;
import com.banditdev.touristguide.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class TouristRepository {

    private final ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();

    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<TouristAttraction> touristAttractionRowMapper = (rs, rowNum) ->
            new TouristAttraction(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getString("city_name")
            );




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
                            rs.getString("city_name")
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


    public TouristAttraction findTouristAttractionById(int idToFind) {
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
        WHERE ta.attraction_id = ?
        """;

        return jdbcTemplate.query(sql, rs -> {
            TouristAttraction attraction = null;

            while (rs.next()) {
                if (attraction == null) {
                    attraction = new TouristAttraction(
                            rs.getInt("attraction_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("city_name")
                    );
                }

                String tag = rs.getString("tag_description");
                if (tag != null) {
                    attraction.addTag(tag);
                }
            }

            return attraction;
        }, idToFind);
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
