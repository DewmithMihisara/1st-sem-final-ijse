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
    Transport DOUBLE(10,2),
    Attendance DOUBLE(10,2),
    Ot DOUBLE(10,2),
    Skill DOUBLE(10,2),
    Rent DOUBLE(10,2),
    CONSTRAINT PRIMARY KEY (SalaryID),
    CONSTRAINT FOREIGN KEY(EmployeeID) REFERENCES Employee(EmployeeID)
);

CREATE TABLE Orders(
    OrderID VARCHAR(6)NOT NULL ,
    UserID VARCHAR(6) NOT NULL,
    BuyerID VARCHAR(6)NOT NULL ,
    TShirtColor VARCHAR(10),
    TShirtCC VARCHAR(10),
    PayTerm TEXT,
    OrderDate DATE,
    CONSTRAINT PRIMARY KEY (OrderID),
    CONSTRAINT FOREIGN KEY (UserID)REFERENCES User(UserID),
    CONSTRAINT FOREIGN KEY (BuyerID)REFERENCES Buyer(BuyerID)
);

CREATE TABLE Buyer(
    BuyerID VARCHAR(6)NOT NULL ,
    BuyerName TEXT,
    BuyerCN VARCHAR(12),
    BuyerAddress TEXT,
    CONSTRAINT PRIMARY KEY (BuyerID)
);

CREATE TABLE TrimCard(
    TrimId VARCHAR(6) NOT NULL ,
    OrderID VARCHAR(6)NOT NULL ,
    AssDetails TEXT,
    CONSTRAINT PRIMARY KEY (TrimId),
    CONSTRAINT FOREIGN KEY (OrderID)REFERENCES Orders(OrderID)
);

CREATE TABLE OrderRatio(
    RatioId VARCHAR(6) NOT NULL ,
    OrderID VARCHAR(6)NOT NULL ,
    Colour VARCHAR(10),
    SQty INT (20),
    MQty INT(20),
    LQty INT(20),
    XLQty INT(20),
    XXLQty INT(20),
    CONSTRAINT PRIMARY KEY (RatioId),
    CONSTRAINT FOREIGN KEY (OrderID)REFERENCES Orders(OrderID)
);

