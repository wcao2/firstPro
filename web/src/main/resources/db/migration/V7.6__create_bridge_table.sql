create table employees_roles(
     employee_id BIGINT NOT NULL,
     role_id BIGINT NOT NULL
);

ALTER TABLE employees_roles
        ADD CONSTRAINT employee_fk FOREIGN KEY(employee_id)
        REFERENCES employee(id)  on delete cascade;

ALTER TABLE employees_roles
        ADD CONSTRAINT role_fk FOREIGN KEY(role_id)
        REFERENCES roles(id);

