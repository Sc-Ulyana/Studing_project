SELECT user_id, role_id, owner.roles.name, owner.users.login
FROM owner.user_roles
         RIGHT JOIN owner.roles on owner.roles.id = user_roles.role_id
         RIGHT JOIN owner.users on owner.users.id = user_roles.user_id;

SELECT user_id, role_id, owner.roles.name, owner.users.login
FROM owner.user_roles
         LEFT OUTER JOIN owner.roles on owner.roles.id = user_roles.role_id
         LEFT OUTER JOIN owner.users on owner.users.id = user_roles.user_id;

-- получаем роли юзера
SELECT role_id, owner.roles.name
FROM owner.user_roles
         LEFT OUTER JOIN owner.roles on owner.roles.id = user_roles.role_id
         LEFT OUTER JOIN owner.users on owner.users.id = user_roles.user_id where user_id=1;

SELECT owner.users.*, owner.roles.name
       FROM owner.users JOIN owner.user_roles ur on users.id = ur.user_id JOIN owner.roles on ur.role_id = owner.roles.id WHERE owner.users.login = 'katya' ;


SELECT user_id, role_id, owner.roles.name FROM owner.user_roles LEFT OUTER JOIN owner.roles on owner.roles.id = user_roles.role_id LEFT OUTER JOIN owner.users on owner.users.id = user_roles.user_id where user_id=1;

SELECT *, owner.roles.name FROM owner.users LEFT JOIN owner.user_roles ur on users.id = ur.user_id LEFT JOIN owner.roles r on r.id = ur.role_id WHERE login = 'vasya';

SELECT user_id, role_id, owner.roles.name FROM owner.user_roles LEFT OUTER JOIN owner.roles on owner.roles.id = user_roles.role_id LEFT OUTER JOIN owner.users on owner.users.id = user_roles.user_id where login='petya';

INSERT INTO owner.users(login, password, name, dateofbirth, age, salary) VALUES ('kat','pass','name','2000-12-12','24',3000);
INSERT INTO owner.user_roles(user_id, role_id) VALUES ('16','1');


INSERT INTO owner.user_roles(user_id, role_id) VALUES ('1','1'),
('1','2'),
('1','3');
