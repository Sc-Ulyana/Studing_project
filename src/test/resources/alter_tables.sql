ALTER TABLE owner.users
    ADD age INT NOT NULL CHECK ( age >= 18 );

ALTER TABLE owner.users
    ADD salary money NULL;

CREATE INDEX nameIndex on owner.users (name);

ALTER TABLE owner.users
    ADD roleId INT NULL;

ALTER TABLE owner.users
    ADD CONSTRAINT FK_USERS_ROLES FOREIGN KEY (roleId)
        REFERENCES owner.roles (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE;