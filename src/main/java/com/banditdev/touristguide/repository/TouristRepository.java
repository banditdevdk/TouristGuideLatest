package com.banditdev.touristguide.repository;
import com.banditdev.touristguide.model.TouristAttraction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
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
        String sql = """
                INSERT INTO tourist_attraction (name, description, cities_id)
                VALUES (?, ?, ?)
                """;

        KeyHolder kh = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, touristAttraction.getName());
            ps.setString(2, touristAttraction.getDescription());
            ps.setInt(3, getCityIdByName(touristAttraction.getCityName()));
            return ps;
        }, kh);


        Number key = kh.getKey();
        if (key == null) {
            throw new IllegalStateException("Failed to get KeyHolder id.");
        }

        for (String t: touristAttraction.getAttractionTags()) {
            int tag_id = getTagIdByDescription(t);

            String sqlTags = """
                    INSERT INTO attraction_tags (attraction_id, tag_id)
                    VALUES (?, ?)
                    """;

            jdbcTemplate.update(sqlTags, key.intValue(), tag_id);
        }

        return new TouristAttraction(key.intValue(), touristAttraction.getName(), touristAttraction.getDescription(), touristAttraction.getCityName());
    }
/* old method
    public void deleteTouristAttraction(String name) {
        touristAttractions.removeIf(t ->
                t.getName().equalsIgnoreCase(name));
    }
 */
    //method to delete an attraction:
    public boolean deleteTouristAttractionById(int id) {
        String sql = """
              DELETE FROM tourist_attraction
              WHERE attraction_id = ?
              """;
        int rowsDeleted = jdbcTemplate.update(sql, id);
        return  rowsDeleted > 0;
    }


    public TouristAttraction findTouristAttractionByName(String nameToFind) {
        for (TouristAttraction t : touristAttractions) {
            if (nameToFind.equalsIgnoreCase(t.getName())) {
                return t;
            }
        }
        return null;
    }

    public int getTagIdByDescription(String tagDescription) {
        String sql = "SELECT tag_id FROM tags WHERE tag_description = ?";
        Integer tagId = jdbcTemplate.queryForObject(sql, Integer.class, tagDescription);

        if (tagId == null) {
            throw new IllegalArgumentException("getTagIdByDescription method failed, couldn't find tagId");
        }

        return tagId;
    }

    public int getCityIdByName(String cityName) {
        String sql = "SELECT cities_id FROM cities WHERE city_name = ?";
        Integer cityId = jdbcTemplate.queryForObject(sql, Integer.class, cityName);

        if (cityId == null) {
            throw new IllegalArgumentException("getCityIdByName method failed, couldn't find cityId");
        }

        return cityId;
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


    public boolean updateTouristAttraction(TouristAttraction touristAttraction) {
        String sql = """
                UPDATE tourist_attractions
                SET name = ?, description = ?, cities_id(SELECT cities_id FROM cities WHERE city_name = ?)
                WHERE attraction_id = ?
                """;

        int rowsUpdated = jdbcTemplate.update(
                sql,
                touristAttraction.getName(),
                touristAttraction.getCityName(),
                touristAttraction.getDescription(),
                touristAttraction.getId()
        );

        return rowsUpdated > 0;
    }

}
