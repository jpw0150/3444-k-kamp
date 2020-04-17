CREATE TABLE menu (
	id int NOT NULL,
	name varchar(50) NOT NULL,
	cost float NOT NULL,
	descrip text,
	CONSTRAINT menu_pk PRIMARY KEY (id)
	
);

CREATE TABLE employee (
	empid int NOT NULL,
	password text NOT NULL,
	name varchar(50),
	wage float NOT NULL,
	role varchar(10) NOT NULL,
	time float NOT NULL,
	tips float,
	compmeals int,
	CONSTRAINT employee_pk PRIMARY KEY (id)
);

CREATE TABLE customer (
	id int NOT NULL AUTO_INCREMENT,
	phone varchar(15) NOT NULL,
	name varchar(50) NOT NULL,
	password text NOT NULL,
	birthday varchar(15),
	visited int NOT NULL,
	credits int NOT NULL,
	CONSTRAINT customer_pk PRIMARY KEY (id)
);

CREATE TABLE orders (
	id int NOT NULL AUTO_INCREMENT,
	tableNum int NOT NULL,
	entree text,
	side text,
	drink text,
	note text,
	orderTotal float NOT NULL,
	CONSTRAINT orders_pk PRIMARY KEY (id)
);

CREATE TABLE ingredients (
	id int NOT NULL,
	food text NOT NULL,
	amount int NOT NULL,
	CONSTRAINT ingredients_pk PRIMARY KEY (id)
);

CREATE TABLE storeTables (
	number int NOT NULL,
	status varchar(30) NOT NULL,
	needHelp bit,
	needRefill bit,
	orderTotal float,
	CONSTRAINT table_pk PRIMARY KEY (number)
);


INSERT INTO ingredients
(id, food, amount)
VALUES(
	1,
	'bone in',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	2,
	'boneless',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	3,
	'tenders',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	10,
	'barbecue sauce',
	-1
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	20,
	'buffalo sauce',
	-1
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	30,
	'cajun sauce',
	-1
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	40,
	'garlic parmesan sauce',
	-1
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	50,
	'hawaiian sauce',
	-1
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	60,
	'lemmon pepper sauce ',
	-1
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	100,
	'ranch',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	200,
	'blue cheese',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	300,
	'honey mustard',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	401,
	'french fries',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	402,
	'veggie sticks',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	403,
	'corn',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	501,
	'soda',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	502,
	'tea',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	503,
	'lemonade',
	100
);

INSERT INTO ingredients
(id, food, amount)
VALUES(
	504,
	'water',
	100
);

INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	1,
	'empty',
	0,
	0,
	0.00
	
);

INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	2,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	3,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	4,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	5,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	6,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	7,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	8,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	9,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	10,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	11,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	12,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	13,
	'empty',
	0,
	0,
	0.00
	
);


INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	14,
	'empty',
	0,
	0,
	0.00
	
);

INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	15,
	'waiting',
	1,
	0,
	0.00
	
);

INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	16,
	'waiting',
	0,
	1,
	0.00
	
);

INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	17,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	18,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	19,
	'empty',
	0,
	0,
	0.00
	
);
INSERT INTO storeTables
(number, status, needHelp, needRefill, orderTotal)
VALUES(
	20,
	'empty',
	0,
	0,
	0.00
	
);


INSERT INTO employee
(empid, password, name, wage, role, hours, tips, compmeals)
VALUES(
	1234,
	'office',
	'Michael Scott',
	40.0,
	'manager',
	16.0
	0.0
	0
);

INSERT INTO employee
(empid, password, name, wage, role, hours, tips, compmeals)
VALUES(
	5678,
	'beets',
	'Dwight Schrute',
	10.0,
	'waiter',
	10.0
	0.0
	0
);

INSERT INTO employee
(empid, password, name, wage, role, hours, tips, compmeals)
VALUES(
	4321,
	'pam',
	'Jim Halpert',
	10.0,
	'waiter',
	40.0
	0.0
	0
);

INSERT INTO employee
(empid, password, name, wage, role, hours, tips, compmeals)
VALUES(
	9876,
	'chilli',
	'Kevin Malone',
	20.0,
	'Employee',
	40.0
	0.0
	0
);
