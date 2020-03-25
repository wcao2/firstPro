alter table employee
drop constraint employee_department_fk;

alter table account
drop constraint account_employee_fk;

alter table employee
ADD CONSTRAINT employee_department_fk
foreign key (department_id) references department(id) on delete cascade;

alter table account
ADD CONSTRAINT account_employee_fk
foreign key (employee_id) references employee(id)  on delete cascade;