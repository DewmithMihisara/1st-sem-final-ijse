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

CREATE TABLE Attendance(
    EmployeeID VARCHAR(6) NOT NULL,
    OtTime DOUBLE(2,2),
    TimeOut TIME,
    TimeIn TIME,
    TtlWorkHours DOUBLE(2,2),
    AP VARCHAR(2),
    Date DATE,
    CONSTRAINT FOREIGN KEY(EmployeeID) REFERENCES Employee(EmployeeID)
);

CREATE TABLE Fund(
    EmployeeID VARCHAR(6) NOT NULL,
    Etf DOUBLE(10,2),
    Epf DOUBLE(10,2),
    Month INT(2),
    Year INT(4),
    CONSTRAINT FOREIGN KEY(EmployeeID) REFERENCES Employee(EmployeeID)
);

CREATE TABLE Salary(
    SalaryID VARCHAR(6)NOT NULL ,
    EmployeeID VARCHAR(6) NOT NULL,
    Tranceport DOUBLE(10,2),
    Attendance DOUBLE(10,2),
    Ot DOUBLE(10,2),
    Skill DOUBLE(10,2),
    Rent DOUBLE(10,2),
    CONSTRAINT PRIMARY KEY (SalaryID),
    CONSTRAINT FOREIGN KEY(EmployeeID) REFERENCES Employee(EmployeeID)
);