DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
      id int generated always as identity,
      email VARCHAR(255) NOT NULL UNIQUE,
      password VARCHAR(255) NOT NULL
);