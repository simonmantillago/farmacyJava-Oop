INSERT INTO IdentificationTypes (Description) VALUES ('ID Card');
INSERT INTO IdentificationTypes (Description) VALUES ('Underage ID Card');
INSERT INTO IdentificationTypes (Description) VALUES ('Passport');
INSERT INTO IdentificationTypes (Description) VALUES ('Driver License');

INSERT INTO Cities (CityName) VALUES ('Bogotá');
INSERT INTO Cities (CityName) VALUES ('Medellín');
INSERT INTO Cities (CityName) VALUES ('Cali');

INSERT INTO Neighborhoods (NeighborhoodName, CityID) VALUES ('Chapinero', 1); 
INSERT INTO Neighborhoods (NeighborhoodName, CityID) VALUES ('El Poblado', 2);  
INSERT INTO Neighborhoods (NeighborhoodName, CityID) VALUES ('San Antonio', 3);  


INSERT INTO Customers (IdentificationNumber, TypeID, FirstName, LastName, Age, BirthDate, CityID, NeighborhoodID)
VALUES ('12345678','ID Card' , 'John', 'Doe', 30, '1993-05-10', 1, 1);

INSERT INTO Customers (IdentificationNumber, TypeID, FirstName, LastName, Age, BirthDate, CityID, NeighborhoodID)
VALUES ('87654321', 'Passport', 'Jane', 'Smith', 25, '1998-01-15', 2, 2);

INSERT INTO Customers (IdentificationNumber, TypeID, FirstName, LastName, Age, BirthDate, CityID, NeighborhoodID)
VALUES ('11223344','Driver License', 'Robert', 'Brown', 45, '1978-08-20', 3, 3);

