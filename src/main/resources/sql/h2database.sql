DROP TABLE IF EXISTS cities;
CREATE TABLE cities (
                        cities_id INT AUTO_INCREMENT PRIMARY KEY,
                        city_name VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS tags;
CREATE TABLE tags (
                      tag_id INT AUTO_INCREMENT PRIMARY KEY,
                      tag_description VARCHAR(500) NOT NULL
);

DROP TABLE IF EXISTS tourist_attraction;
CREATE TABLE tourist_attraction (
                                    attraction_id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL,
                                    description VARCHAR(500),
                                    cities_id INT,
                                    FOREIGN KEY (cities_id) REFERENCES cities(cities_id)
                                        ON DELETE SET NULL
                                        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS attraction_tags;
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

INSERT INTO cities (city_name) VALUES
                                   ('København'),
                                   ('Aarhus'),
                                   ('Odense'),
                                   ('Aalborg'),
                                   ('Esbjerg'),
                                   ('Roskilde'),
                                   ('Helsingør'),
                                   ('Herning'),
                                   ('Vejle'),
                                   ('Kolding');

INSERT INTO tags (tag_description) VALUES
                                       ('Gratis'),
                                       ('Natur'),
                                       ('Historisk'),
                                       ('Arkitektur'),
                                       ('Havn'),
                                       ('Kultur'),
                                       ('Seværdighed'),
                                       ('Guidet-tur'),
                                       ('Fotovenlig'),
                                       ('Centrum'),
                                       ('Shopping');

INSERT INTO tourist_attraction (name, description, cities_id) VALUES
                                                                  ('Den Lille Havfrue', 'Verdenskendt statue inspireret af H.C. Andersens eventyr, beliggende ved Københavns havn.', 1),
                                                                  ('Nyhavn', 'Farverig havnefront med historiske bygninger, restauranter og caféer.', 1),
                                                                  ('Rundetårn', 'Historisk tårn med spiralrampe og udsigt over Københavns skyline.', 1),
                                                                  ('Amalienborg', 'De danske kongers vinterresidens og et centralt symbol på monarkiet.', 1),
                                                                  ('Nationalmuseet', 'Danmarks største kulturhistoriske museum med udstillinger fra hele verden.', 1),
                                                                  ('Christiania', 'Unikt fristadssamfund kendt for alternativ livsstil, kunst og kultur.', 1);


INSERT INTO attraction_tags (attraction_id, tag_id) VALUES
-- Den Lille Havfrue
(1,1),(1,7),(1,9),(1,5),

-- Nyhavn
(2,5),(2,9),(2,10),

-- Rundetårn
(3,3),(3,4),(3,7),(3,8),

-- Amalienborg
(4,3),(4,4),(4,7),(4,10),

-- Nationalmuseet
(5,6),(5,3),(5,8),

-- Christiania
(6,6),(6,1),(6,7);