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