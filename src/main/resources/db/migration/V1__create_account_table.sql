CREATE TABLE account(
	id 		SERIAL PRIMARY KEY ,
	account_type    VARCHAR(30),
	balance		NUMERIC(10,2),
	create_date 	date default CURRENT_DATE,
	employee_id	INTEGER NOT NULL
);