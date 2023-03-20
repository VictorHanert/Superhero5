INSERT INTO city (name) VALUES
('Gotham'),
('Metropolis'),
('Central City'),
('Star City'),
('Wakanda'),
('New York City'),
('Chicago'),
('Los Angeles');

INSERT INTO superpower (name) VALUES
('Flight'),
('Super Strength'),
('Super Speed'),
('Invisibility'),
('Telekinesis'),
('Rich');

INSERT INTO superhero (heroname, realName, creation_year, city_id) VALUES
('Batman', 'Bruce Wayne', 1939, 1),
('Superman', 'Clark Kent', 1938, 2),
('The Flash', 'Barry Allen', 1956, 3),
('Green Arrow', 'Oliver Queen', 1941, 4),
('Wonder Woman', 'Diana Prince', 1941, 1),
('Black Panther', 'TChalla', 1999, '5'),
('Green Lantern', 'Hal Jordan', 1959, 2),
('The Atom', 'Ray Palmer', 2010, 3),
('Iron Man', 'Tony Stark', 2007, 8),
('Doctor Strange', 'Stephen Strange', 1983, 6),
('Spider-Man', 'Peter Parker', 1972, 6),
('Cyclops', 'Scott Summers', 1993, 7),
('Phoenix', 'Jean Grey', 1963, 7),
('Daredevil', 'Matt Murdock', 1974, 6),
('The Thing', 'Ben Grimm', 1988, 6),
('Invisible Woman', 'Sue Storm', 1991, 3);

INSERT INTO superhero_power (hero_id, power_id, level) VALUES
(1, 2, 'high'),
(1, 4, 'medium'),
(2, 1, 'high'),
(2, 2, 'high'),
(2, 3, 'high'),
(3, 3, 'high'),
(4, 2, 'medium'),
(4, 5, 'low'),
(5, 2, 'high'),
(5, 3, 'medium'),
(6, 6, 'high'),
(6, 2, 'medium'),
(7, 2, 'medium'),
(8, 5, 'low'),
(8, 3, 'low'),
(9, 1, 'high'),
(9, 6, 'high'),
(10, 2, 'high'),
(10, 3, 'medium'),
(11, 3, 'high'),
(12, 3, 'high'),
(12, 4, 'medium'),
(12, 5, 'high'),
(13, 1, 'medium'),
(13, 3, 'low'),
(14, 2, 'medium'),
(14, 4, 'high'),
(15, 1, 'high'),
(15, 2, 'low');




