CREATE DATABASE University_Fest;
USE University_Fest;

CREATE TABLE Fest (
    Fest_ID INT PRIMARY KEY,
    Fest_name VARCHAR(100) NOT NULL,
    Year INT NOT NULL,
    Head_teamID INT
);

DESC Fest;

CREATE TABLE Teams (
    Team_ID INT PRIMARY KEY,
    Team_name VARCHAR(100) NOT NULL,
    Team_type VARCHAR(50),
    FestID INT
);

Desc Teams;

ALTER TABLE Fest
ADD CONSTRAINT fk_fest_headteam
FOREIGN KEY (Head_teamID) REFERENCES Teams(Team_ID);

ALTER TABLE Teams
ADD CONSTRAINT fk_team_fest
FOREIGN KEY (FestID) REFERENCES Fest(Fest_ID);



CREATE TABLE Members (
    Mem_ID INT PRIMARY KEY,
    Mem_name VARCHAR(100) NOT NULL,
    DOB DATE,
    super_MemID INT,
    TeamID INT
);

ALTER TABLE Members ADD CONSTRAINT fk_members_id FOREIGN KEY (super_MemID) REFERENCES Members(Mem_ID);
ALTER TABLE Members ADD CONSTRAINT fk_team_id FOREIGN KEY (TeamID) REFERENCES Teams(Team_ID);

Desc Members;

CREATE TABLE Event (
    Event_ID INT PRIMARY KEY,
    Event_name VARCHAR(100) NOT NULL,
    Building VARCHAR(50),
    Floor INT,
    Room_no VARCHAR(20),
    Price DECIMAL(10,2),
    TeamID INT
);
ALTER TABLE Event ADD CONSTRAINT fk_event_id FOREIGN KEY (TeamID) REFERENCES Teams(Team_ID);
Desc Event;

CREATE TABLE Event_conduction (
    Event_ID INT,
    Date_of_conduction DATE NOT NULL,
    PRIMARY KEY (Event_ID, Date_of_conduction),
    FOREIGN KEY (Event_ID) REFERENCES Event(Event_ID)
);

Desc Event_conduction;

CREATE TABLE Participants (
    SRN VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50),
    semester INT,
    gender VARCHAR(10)
);

Desc Participants;

CREATE TABLE Visitors (
    SRN VARCHAR(20),
    Name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    PRIMARY KEY (SRN),
    FOREIGN KEY (SRN) REFERENCES Participants(SRN)
);

Desc Visitors;

CREATE TABLE Registration (
    RegistrationID INT PRIMARY KEY,
    Event_ID INT,
    SRN VARCHAR(20),
    FOREIGN KEY (Event_ID) REFERENCES Event(Event_ID),
    FOREIGN KEY (SRN) REFERENCES Participants(SRN)
);
Desc Registration;

CREATE TABLE Stall (
    Stall_ID INT PRIMARY KEY,
    name VARCHAR(100),
    Fest_ID INT,
    FOREIGN KEY (Fest_ID) REFERENCES Fest(Fest_ID)
);

Desc Stall;

CREATE TABLE Item (
    Name VARCHAR(100) PRIMARY KEY,
    Type VARCHAR(50)
);

Desc Item;

CREATE TABLE Stall_items (
    Stall_ID INT,
    Item_name VARCHAR(100),
    Price_per_unit DECIMAL(10,2),
    Total_quantity INT,
    PRIMARY KEY (Stall_ID, Item_name),
    FOREIGN KEY (Stall_ID) REFERENCES Stall(Stall_ID),
    FOREIGN KEY (Item_name) REFERENCES Item(Name)
);


DESC Stall_items;

CREATE TABLE Purchased(
	SRN INT,
    Stall_ID INT,
    Item_Name VARCHAR(100),
    Timestamp DATETIME,
    Quantity INT,
    PRIMARY KEY (SRN, Stall_ID, Item_Name, Timestamp),
    FOREIGN KEY (SRN) REFERENCES Participants(SRN),
    FOREIGN KEY (Stall_ID) REFERENCES Stall(Stall_ID),
    FOREIGN KEY (Item_Name) REFERENCES Item(Name)
);

Desc Purchased;


-- TASK 2 
-- 1.
ALTER TABLE Participants MODIFY gender ENUM('M', 'F', 'O') AFTER Name;
ALTER TABLE Visitors MODIFY gender ENUM('M', 'F', 'O') AFTER Name;
Desc Participants;
Desc Visitors;

-- 2.
ALTER TABLE Stall_Items MODIFY Price_per_unit DECIMAL(10,2) NOT NULL DEFAULT 50;
Desc Stall_Items;

-- 3.
ALTER TABLE Stall_Items ADD CONSTRAINT chk_max_stock CHECK (Total_quantity <= 150);
Desc Stall_Items;

-- 4.
RENAME TABLE Event_conduction TO Event_schedule;
Desc Event_schedule;

-- 5.
ALTER TABLE Event_schedule MODIFY Date_of_conduction DATE FIRST;
Desc Event_schedule;


