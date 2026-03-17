CREATE DATABASE IF NOT EXISTS tourist_db;
USE tourist_db;

CREATE TABLE cities (
                        cities_id INT AUTO_INCREMENT PRIMARY KEY,
                        city_name VARCHAR(100) NOT NULL
);

CREATE TABLE tags (
                      tag_id INT AUTO_INCREMENT PRIMARY KEY,
                      tag_description VARCHAR(500) NOT NULL
);

CREATE TABLE tourist_attraction (
                                    attraction_id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL,
                                    description VARCHAR(500),
                                    cities_id INT,
                                    FOREIGN KEY (cities_id) REFERENCES cities(cities_id)
                                        ON DELETE SET NULL
                                        ON UPDATE CASCADE
);

CREATE TABLE attraction_tags (
                                 attraction_id INT,
                                 tag_id INT,
                                 PRIMARY KEY (attraction_id, tag_id),
                                 FOREIGN KEY (attraction_id) REFERENCES tourist_attraction(attraction_id)
                                     ON DELETE CASCADE
                                     ON UPDATE CASCADE,
                                 FOREIGN KEY (tag_id) REFERENCES tags(tag_id)
                                     ON DELETE CASCADE
                                     ON UPDATE CASCADE
);