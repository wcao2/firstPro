CREATE TABLE employee (
    /*id              INTEGER NOT NULL default nextval('employee_id_seq'),*/
    id              BIGSERIAL  PRIMARY KEY,
    name            VARCHAR(30) not null unique,
    first_name      VARCHAR(30),
    last_name       VARCHAR(30),
    email           VARCHAR(50),
    address         VARCHAR(150),
    hired_date      date default CURRENT_DATE,
    department_id   BIGINT NOT NULL
);