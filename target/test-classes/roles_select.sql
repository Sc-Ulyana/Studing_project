SELECT user_id, role_id, owner.roles.name, owner.users.login
FROM owner.user_roles
         LEFT JOIN owner.roles on owner.roles.id = user_roles.role_id
         RIGHT JOIN owner.users on owner.users.id = user_roles.user_id;

INSERT INTO owner.user_roles
VALUES ('1', '2'),
       ('1', '3'),
       ('1', '4');

SELECT id, login
FROM owner.users LEFT JOIN owner.user_roles ur on users.id = ur.user_id
WHERE ur.user_id IS NOT NULL;

DELETE FROM owner.users
WHERE id=5;