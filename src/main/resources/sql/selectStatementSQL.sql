SELECT
    tourist_attraction.attraction_id,
    tourist_attraction.name,
    tourist_attraction.description,
    cities.cities_id,
    cities.city_name,
    tags.tag_id,
    tags.tag_description
FROM tourist_attraction
         LEFT JOIN cities
                   ON tourist_attraction.cities_id = cities.cities_id
         LEFT JOIN attraction_tags
                   ON tourist_attraction.attraction_id = attraction_tags.attraction_id
         LEFT JOIN tags
                   ON attraction_tags.tag_id = tags.tag_id
ORDER BY tourist_attraction.attraction_id;