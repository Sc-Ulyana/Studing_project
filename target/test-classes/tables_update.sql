UPDATE owner.users
SET salary=salary * 1.20
WHERE salary < CAST(3000 AS money);

UPDATE owner.users
SET roleId = 4
WHERE name LIKE '[I]%';