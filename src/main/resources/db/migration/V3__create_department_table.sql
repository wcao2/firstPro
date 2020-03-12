CREATE TABLE department (
    /*id                INTEGER NOT NULL default nextval('department_id_seq'), */
    id                SERIAL  PRIMARY KEY,
    name              VARCHAR(30) not null unique,
    description       VARCHAR(150),
    location          VARCHAR(100)
);

