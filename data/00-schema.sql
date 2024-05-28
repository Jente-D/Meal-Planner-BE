DROP TABLE IF EXISTS Users;

CREATE TABLE Users (
    id int generated always as identity,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);
