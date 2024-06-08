create table users(
    id int generated always as identity,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    primary key (id)
);

CREATE TABLE potential_users (
        id int GENERATED ALWAYS AS identity,
        confirmation_token VARCHAR(255),
        created_date TIMESTAMP,
        email VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        status BOOLEAN NOT NULL,
        primary key (id)
);

CREATE TABLE meals (
        id INT GENERATED ALWAYS AS IDENTITY,
        title VARCHAR(255),
        mealType VARCHAR(255),
        calories INT,
        img_url VARCHAR(255),
        PRIMARY KEY (id)
);

