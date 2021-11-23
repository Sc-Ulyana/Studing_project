SELECT *
FROM owner.users;

SELECT *
FROM owner.users
WHERE dateofbirth IS NOT NULL;

SELECT *
FROM owner.users
WHERE dateofbirth BETWEEN '1995-01-01' AND '2002-01-01';

SELECT COUNT(*), age
FROM owner.users
GROUP BY age;

SELECT COUNT(*), age
FROM owner.users
GROUP BY age
HAVING COUNT(*) > 1
ORDER BY COUNT(*) DESC;

SELECT owner.users.id, owner.users.login, owner.users.roleid, owner.roles.name
FROM owner.users,
     owner.roles
WHERE owner.users.roleid = owner.roles.id
  AND roleid IS NOT NULL;

SELECT owner.users.id, owner.users.login, owner.users.roleid, owner.roles.name
FROM owner.users
         LEFT OUTER JOIN owner.roles
                         ON owner.users.roleid = owner.roles.id;

SELECT owner.users.*, owner.roles.name AS roleName
FROM owner.users,
     owner.roles
WHERE owner.users.roleid = owner.roles.id
LIMIT 5;

SELECT owner.users.*, owner.roles.name AS roleName
FROM owner.users,
     owner.roles
WHERE owner.users.roleid = owner.roles.id
LIMIT 5 OFFSET 3;
