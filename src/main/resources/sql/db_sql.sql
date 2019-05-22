DROP TABLE vesta_schema.user;
DROP TABLE vesta_schema.user_role;
DROP TABLE vesta_schema.role;

CREATE TABLE vesta_schema.user(
    id serial PRIMARY KEY,
    first_name VARCHAR (50) NOT NULL ,
    last_name VARCHAR (50) NOT NULL ,
    password VARCHAR (50) NOT NULL,
    email VARCHAR (100) UNIQUE NOT NULL,
    created_on TIMESTAMP NOT NULL,
    last_login TIMESTAMP
    );

CREATE TABLE vesta_schema.role(
    id serial PRIMARY KEY,
    name VARCHAR (100) UNIQUE NOT NULL
);

CREATE TABLE user_role(
    user_id INTEGER NOT NULL,
    role_id INTEGER NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES role (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES vesta_schema.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);


