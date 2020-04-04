insert into roles (name, allowed_resource, allowed_read, allowed_create, allowed_update, allowed_delete) values
('Admin', '/', TRUE , TRUE, TRUE, TRUE),
('Manager', '/depts,/departments,/employees,/ems,/acnts,/accounts', TRUE, TRUE, TRUE, FALSE),
('user', '/employees,/ems,/acnts,/accounts', TRUE, FALSE, FALSE, FALSE)
;


ALTER TABLE employee
ADD password VARCHAR(100);


insert into employee (name, password, first_name, last_name, email,department_id) values
('dwang', '25f9e794323b453885f5181f1b624d0b', 'David', 'Wang', 'dwang@training.ascendingdc.com',1),
('rhang', '25f9e794323b453885f5181f1b624d0b', 'Ryo', 'Hang', 'rhang@training.ascendingdc.com',1),
('xyhuang', '25f9e794323b453885f5181f1b624d0b', 'Xinyue', 'Huang', 'xyhuang@training.ascendingdc.com',1)
;

insert into account(account_type,employee_id) values ('credit',1),('debit',2);




