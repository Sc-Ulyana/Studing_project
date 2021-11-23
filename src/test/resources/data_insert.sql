INSERT INTO owner.roles(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER'),
       ('ROLE_DEVELOPER'),
       ('ROLE_MANAGER'),
       ('ROLE_TRAINEE');

INSERT INTO owner.users(login, password, name, dateofbirth, age, salary, roleid)
VALUES ('admin', 'admin', 'Admin', '1990-08-08', '30', '3000', '1'),
       ('petya', '12345', 'Petr', '2000-10-12', '21', null, '2'),
       ('kolya', '12345', 'Nikolay', null, '26', null, '5'),
       ('borya', '12345', 'Boris', '1997-11-17', '24', '1200', null),
       ('tolya', '12345', 'Anatoliy', '1984-09-06', '35', '1500', '3'),
       ('kostya', '12345', 'Konstantin', '1987-02-24', '34', '8000', '3'),
       ('anya', '12345', 'Anna', '1998-12-11', '23', '1200', '3'),
       ('vanya', '12345', 'Ivan', null, '57', '1800', '4'),
       ('tanya', '12345', 'Tanya', '2002-12-08', '18', null, null),
       ('katya', '12345', 'Ekaterina', '2002-11-09', '19', '2100', '4'),
       ('nastya', '12345', 'Anastasia', '1997-04-12', '24', null, '5'),
       ('vasya', '12345', 'Vasily', '2000-01-18', '21', '1250', null),
       ('jenya', '12345', 'Evgen', null, '31', '900', '3'),
       ('ilya', '12345', 'Ilya', null, '23', '8000', '4'),
       ('misha', '12345', 'Mikhail', '2000-05-06', '21', '12000', '3');

