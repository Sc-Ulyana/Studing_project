ALTER TABLE owner.users
    DROP COLUMN roleid;

CREATE TABLE owner.user_roles
(
    user_id INT NOT NULL,
    role_id INT,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_userid FOREIGN KEY (user_id) REFERENCES owner.users (id),
    CONSTRAINT fk_roleid FOREIGN KEY (role_id) REFERENCES owner.roles (id)
);

INSERT INTO owner.user_roles(user_id, role_id)
VALUES ('1', '1'),
       ('2', '2'),
       ('3', '5'),
       ('4', null),
       ('5', '3'),
       ('6', '3'),
       ('7', '3'),
       ('8', '4'),
       ('9', null),
       ('10', '4'),
       ('11', '5'),
       ('12', null),
       ('13', '3'),
       ('14', '4'),
       ('15', '3');