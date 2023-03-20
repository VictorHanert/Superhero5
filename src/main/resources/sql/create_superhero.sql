DROP DATABASE IF EXISTS superhero_database;
CREATE DATABASE superhero_database;
use superhero_database;

CREATE TABLE city (
  city_id INT not null auto_increment PRIMARY KEY,
  name VARCHAR(255),
  unique index (name)
);

CREATE TABLE superpower (
  power_id int not null auto_increment PRIMARY KEY,
  name VARCHAR(255),
  unique index (name)
);

CREATE TABLE superhero (
hero_id integer not null AUTO_INCREMENT,
  heroname VARCHAR(255) not null,
  realName VARCHAR(255) not null,
  creation_year int,
  city_id int,
  PRIMARY KEY (hero_id),
  FOREIGN KEY (city_id) REFERENCES city (city_id),
  unique index (heroname)
);

CREATE TABLE superhero_power (
  hero_id integer not null,
  power_id integer not null,
  level enum ('low', 'medium','high'),
  FOREIGN KEY (hero_id) REFERENCES superhero(hero_id),
  FOREIGN KEY (power_id) REFERENCES superpower(power_id),
  PRIMARY KEY (hero_id, power_id)
);

