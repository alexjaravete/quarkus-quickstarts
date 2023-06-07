INSERT INTO testschema.known_fruits(id, name) VALUES (1, 'Cherry');
INSERT INTO testschema.known_fruits(id, name) VALUES (2, 'Apple');
INSERT INTO testschema.known_fruits(id, name) VALUES (3, 'Banana');
ALTER SEQUENCE known_fruits_id_seq RESTART WITH 4;
