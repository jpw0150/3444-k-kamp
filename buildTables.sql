CREATE TABLE menu (
	id int NOT NULL,
	name varchar(50) NOT NULL,
	cost float NOT NULL,
	descrip text,
	CONSTRAINT menu_pk PRIMARY KEY (id)
);

CREATE TABLE employee (
	id int NOT NULL AUTO_INCREMENT,
	password text NOT NULL,
	name varchar(50),
	wage float NOT NULL,
	role varchar(10) NOT NULL,
	time float NOT NULL,
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
	orderTotal float NOT NULL,
	CONSTRAINT orders_pk PRIMARY KEY (id)
);

CREATE TABLE ingredients (
	id int NOT NULL AUTO_INCREMENT,
	food text NOT NULL,
	amount int NOT NULL,
	CONSTRAINT ing_pk PRIMARY KEY (id)
);

CREATE TABLE ingredients (
	id int NOT NULL AUTO_INCREMENT,
	food text NOT NULL,
	amount int NOT NULL,
	CONSTRAINT ing_pk PRIMARY KEY (id)
);
