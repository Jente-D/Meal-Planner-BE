create table Users (
    id int generated always as identity,
    name varchar(250) unique not null,
    primary key (id)
);
