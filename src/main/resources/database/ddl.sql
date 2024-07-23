CREATE DATABASE farmacy;

USE farmacy;

CREATE TABLE Cities (
    CityID INT PRIMARY KEY AUTO_INCREMENT,
    CityName VARCHAR(50) NOT NULL
);

CREATE TABLE Neighborhoods (
    NeighborhoodID INT PRIMARY KEY AUTO_INCREMENT,
    NeighborhoodName VARCHAR(50) NOT NULL,
    CityID INT,
    FOREIGN KEY (CityID) REFERENCES Cities(CityID)
);

CREATE TABLE IdentificationTypes (
    Description VARCHAR(50) PRIMARY KEY NOT NULL
);

CREATE TABLE Customers (
    IdentificationNumber VARCHAR(50) PRIMARY KEY,
    TypeID VARCHAR(50) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Age INT NOT NULL,
    BirthDate DATE NOT NULL,
    RegistrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CityID INT,
    NeighborhoodID INT,
    FOREIGN KEY (TypeID) REFERENCES IdentificationTypes(Description),
    FOREIGN KEY (CityID) REFERENCES Cities(CityID),
    FOREIGN KEY (NeighborhoodID) REFERENCES Neighborhoods(NeighborhoodID)
);
