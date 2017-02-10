DROP TABLE PUBLIC.MENUS IF EXISTS;
DROP TABLE PUBLIC.VOTES IF EXISTS;
DROP TABLE PUBLIC.RESTAURANT IF EXISTS;
DROP TABLE PUBLIC.ROLES IF EXISTS;
DROP TABLE PUBLIC.USERS IF EXISTS;

CREATE TABLE PUBLIC.USERS
(
  ID INTEGER PRIMARY KEY NOT NULL IDENTITY,
  NAME VARCHAR(100),
  EMAIL VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX "users_email_uindex" ON PUBLIC.USERS (EMAIL);

CREATE TABLE PUBLIC.ROLES
(
  ID INTEGER PRIMARY KEY NOT NULL IDENTITY,
  ROLE VARCHAR(10),
  USER_ID INTEGER,
  CONSTRAINT ROLES_USERS_ID_FK FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);

CREATE TABLE PUBLIC.RESTAURANT
(
  ID   INTEGER PRIMARY KEY NOT NULL IDENTITY,
  NAME VARCHAR(100)        NOT NULL
);

CREATE TABLE PUBLIC.MENUS
(
  ID INTEGER PRIMARY KEY NOT NULL IDENTITY,
  DISHNAME VARCHAR(300) NOT NULL,
  PRICE INTEGER DEFAULT 0 NOT NULL,
  DATE DATE NOT NULL,
  RESTAURANT_ID INTEGER NOT NULL,
  CONSTRAINT MENUS_RESTAURANT_ID_FK FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANT (ID)
);

CREATE TABLE PUBLIC.VOTES
(
  ID INTEGER PRIMARY KEY NOT NULL IDENTITY,
  VOTE BIT,
  USER_ID INTEGER,
  CONSTRAINT VOTES_USERS_ID_FK FOREIGN KEY (USER_ID) REFERENCES USERS (ID)
);