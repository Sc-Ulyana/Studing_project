CREATE TABLE owner.users
(
    id          SERIAL PRIMARY KEY,
    login       varchar(15) UNIQUE NOT NULL,
    password    varchar(15)        NOT NULL,
    name        varchar(30)        NOT NULL,
    dateOfBirth date
);

CREATE TABLE owner.roles
(
    id   SERIAL PRIMARY KEY,
    name varchar(15) NOT NULL
);

