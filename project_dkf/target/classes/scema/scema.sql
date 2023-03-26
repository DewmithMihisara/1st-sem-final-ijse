DROP DATABASE IF EXISTS dkf;
CREATE DATABASE dkf;
USE dkf;

CREATE TABLE User(
    UserID VARCHAR(6) NOT NULL,
    UserName VARCHAR(20) NOT NULL,
    Password VARCHAR(10) NOT NULL,
    UserEmail VARCHAR(50) NOT NULL ,
    UserContact VARCHAR(12) NOT NULL,
    CONSTRAINT PRIMARY KEY (UserID)
);

CREATE TABLE LogHistory(
    UserID VARCHAR(6) NOT NULL,
    LogIn DATETIME,
    logOut DATETIME,
    CONSTRAINT FOREIGN KEY(UserID) REFERENCES User(UserID)
);

CREATE TABLE Employee(
    EmployeeID VARCHAR(6) NOT NULL,
    UserID VARCHAR(6) NOT NULL,
    CategoryID VARCHAR(6) NOT NULL,
    EmpFirstName VARCHAR(15),
    EmpLastName VARCHAR(15),
    EmpDob DATE,
    joinDate DATE,
    EmpContact VARCHAR(12),
    EmpAddress TEXT,
    CONSTRAINT PRIMARY KEY (EmployeeID),
    CONSTRAINT FOREIGN KEY(UserID) REFERENCES User(UserID),
    CONSTRAINT FOREIGN KEY(CategoryID) REFERENCES Category(CategoryID)
);

CREATE TABLE Category(
    CategoryID VARCHAR(6) NOT NULL,
    Des TEXT,
    BasicSalary DOUBLE(11,2),
    CONSTRAINT PRIMARY KEY (CategoryID)
);
