UPDATE owner.users
SET salary = '1000'
WHERE salary IS NULL;

SELECT (SELECT sum(salary)FROM owner.users)/(SELECT count(*)
FROM owner.users
WHERE age > 25)
UNION
SELECT (SELECT sum(salary)FROM owner.users)/(SELECT count(*)
FROM owner.users
WHERE age <= 25)

