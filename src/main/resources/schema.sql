DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS PHONE;
DROP TABLE IF EXISTS USER_PHONE;

CREATE TABLE  USER (
ID  INTEGER AUTO_INCREMENT PRIMARY KEY,
NAME VARCHAR(50) NOT NULL,
EMAIL VARCHAR(50) NOT NULL,
PASSWORD VARCHAR(50) NOT NULL,
CREATED DATE,
MODIFIED DATE,
LAST_LOGIN DATE,
TOKEN VARCHAR(1000),
IS_ACTIVE BOOLEAN NOT NULL,
UNIQUE (EMAIL)
);

CREATE TABLE   PHONE (
ID INTEGER AUTO_INCREMENT PRIMARY KEY,
NUMBER VARCHAR(50) NOT NULL,
CITY_CODE VARCHAR(50) NOT NULL,
COUNTRY_CODE VARCHAR(50) NOT NULL,
UNIQUE (NUMBER,CITY_CODE,COUNTRY_CODE)
);

CREATE TABLE  USER_PHONE (
ID_USER INTEGER NOT NULL,
ID_PHONE INTEGER NOT NULL,
UNIQUE (ID_USER,ID_PHONE),
foreign key (ID_USER) references USER(ID),
foreign key (ID_PHONE) references PHONE(ID)
);

CREATE TABLE   PARAMETER (
ID INTEGER  PRIMARY KEY,
NAME VARCHAR(50) NOT NULL,
VALUE VARCHAR(50) NOT NULL

);

