# CRUD_Spring

First you need to create DB;

Second you need to crearte tables:

CREATE TABLE IF NOT EXISTS Employee (
    employeeid INT AUTO_INCREMENT,
    firstname VARCHAR(255)  ,
    lastname VARCHAR(255),
    city VARCHAR(255),
    salary VARCHAR(255),
    PRIMARY KEY (employeeid)
)  ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS Employee (
    id INT AUTO_INCREMENT,
    username VARCHAR(255)  ,
    password VARCHAR(255),
    role VARCHAR(255),
    enabled VARCHAR(255),
    PRIMARY KEY (id)
)  ENGINE=INNODB;


Third you need to change DB Connection String in path #CRUD_Spring/src/main/resources/application.properties

Now just run the appliction and it's will run 

# Note

This application is backend not full stack
