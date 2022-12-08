DROP TABLE IF EXISTS book;
DROP SCHEMA IF EXISTS bookarchive;

CREATE SCHEMA bookarchive AUTHORIZATION sa;

CREATE TABLE book ( 
   id INT auto_increment NOT NULL, 
   title VARCHAR(50) NOT NULL,
   series VARCHAR(20),
   author VARCHAR(20) NOT NULL,
   illustrator VARCHAR(20),
   genre VARCHAR(20)
);