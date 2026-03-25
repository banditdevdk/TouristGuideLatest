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