SELECT user_id, role_id, owner.roles.name, owner.users.login
FROM owner.user_roles
         RIGHT JOIN owner.roles on owner.roles.id = user_roles.role_id
         RIGHT JOIN owner.users on owner.users.id = user_roles.user_id;

SELECT user_id, role_id, owner.roles.name, owner.users.login
FROM owner.user_roles
         LEFT OUTER JOIN owner.roles on owner.roles.id = user_roles.role_id
         LEFT OUTER JOIN owner.users on owner.users.id = user_roles.user_id;
