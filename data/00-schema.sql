
CREATE TABLE registration_request (
        id SERIAL PRIMARY KEY,
        confirmation_token VARCHAR(255),
        created_date TIMESTAMP,
        email VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        status BOOLEAN NOT NULL
);