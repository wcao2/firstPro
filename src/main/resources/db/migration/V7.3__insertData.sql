insert into department(id,name,location) values(1, 'Human Resource', 'Room 100, 999 Washington Ave. Falls Church, VA');

insert into department(id,name,description,location)  values
(2,'R&D', 'Research and Development', 'Room 101, 999 Washington Ave. Falls Church, VA');


INSERT INTO employee(id,name,first_name,last_name,email,address,department_id)
values(1,'Wei Cao','Wei','Cao','wcao2@gmu.edu','9871_SUN_ROAD', 1);

INSERT INTO account(account_type,balance,employee_id)
values('normal',1000,1);