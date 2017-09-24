--Hotels

CREATE TABLE Hotels(
  Name VARCHAR(25) PRIMARY KEY NOT NULL,
  Category VARCHAR(15) NOT NULL,
  Address VARCHAR(35) NOT NULL,
  Phone VARCHAR(20) NOT NULL,
  Email VARCHAR(30) NOT NULL
);

CREATE TABLE Rooms(
  Hotel_Name VARCHAR(25) NOT NULL,
  Room_Number VARCHAR(255) NOT NULL,
  Type VARCHAR(20) NOT NULL,
  Price VARCHAR(255) NOT NULL,
  FOREIGN KEY (Hotel_Name) REFERENCES Hotels(Name),
  CONSTRAINT roomsKey PRIMARY KEY (Hotel_Name,Room_Number)
);

CREATE TABLE Room_Images(
	Hotel_Name VARCHAR(25) NOT NULL,
	Room_Number VARCHAR(25) NOT NULL,
	Image_Name VARCHAR(200) NOT NULL,
	Image BLOB,
	FOREIGN KEY (Hotel_Name,Room_Number) REFERENCES Rooms(Hotel_Name,Room_Number),
	PRIMARY KEY (Hotel_Name,Room_Number,Image_Name)
);

CREATE TABLE RoomServices(
	Hotel_Name VARCHAR(25) NOT NULL ,
	Room_Number VARCHAR(255) NOT NULL ,
	Services VARCHAR(100) NOT NULL,
	FOREIGN KEY (Hotel_Name,Room_Number) REFERENCES Rooms(Hotel_Name,Room_Number),
	CONSTRAINT roomServiceKey PRIMARY KEY (Hotel_Name,Room_Number)
);


--Rentals

CREATE TABLE Rental_Offices(
	Name VARCHAR(25) CONSTRAINT rentalOfficeKey PRIMARY KEY NOT NULL,
	Address VARCHAR(35) NOT NULL,
	Phone VARCHAR(20) NOT NULL
);

CREATE TABLE Cars(
	Rental_Office VARCHAR(25) NOT NULL,
	Car_id VARCHAR(10) NOT NULL,
	Car_cc VARCHAR(15) NOT NULL,
	Passengers VARCHAR(10) NOT NULL,
	Category VARCHAR(10) NOT NULL,
	FOREIGN KEY (Rental_Office) REFERENCES Rental_Offices(Name),
	CONSTRAINT carsKey PRIMARY KEY (Rental_Office,Car_id)
);

CREATE TABLE Motorcycles(
	Rental_Office VARCHAR(25) NOT NULL,
	Motorcycle_id VARCHAR(10) NOT NULL,
	Motorcycle_cc VARCHAR(15) NOT NULL,
	Category VARCHAR(10) NOT NULL,
	FOREIGN KEY (Rental_Office) REFERENCES Rental_Offices(Name),
	CONSTRAINT motocyclesKey PRIMARY KEY (Rental_Office,Motorcycle_id)
);

CREATE TABLE Car_Charges(
	Rental_Office VARCHAR(25) NOT NULL,
	Car_id VARCHAR(10) NOT NULL,
	Days VARCHAR(30) NOT NULL,
	Charge NUMBER NOT NULL,
	FOREIGN KEY (Rental_Office,Car_id) REFERENCES Cars(Rental_Office,Car_id),
	CONSTRAINT carChargesKey PRIMARY KEY (Rental_Office,Car_id,Days,Charge)
);

CREATE TABLE Motorcycle_Charges(
	Rental_Office VARCHAR(25) NOT NULL,
	Motorcycle_id VARCHAR(10) NOT NULL,
	Days VARCHAR(30) NOT NULL,
	Charge NUMBER NOT NULL,
	FOREIGN KEY (Rental_Office,Motorcycle_id) REFERENCES Motorcycles(Rental_Office,Motorcycle_id),
	CONSTRAINT motorChargesKey PRIMARY KEY (Rental_Office,Motorcycle_id,Days,Charge)
);

--Museums

CREATE TABLE Museums(
	Name VARCHAR(20) PRIMARY KEY NOT NULL,
	Address VARCHAR(35) NOT NULL,
	Phone VARCHAR(20) NOT NULL,
	Adults_Ticket NUMBER NOT NULL,
	Childrens_Ticket NUMBER NOT NULL,
	Opening_Time VARCHAR(20) NOT NULL,
	Closing_Time VARCHAR(20) NOT NULL
);

--Landmarks

CREATE TABLE Landmarks(
	Name VARCHAR(20) PRIMARY KEY NOT NULL,
	Address VARCHAR(35) NOT NULL,
	Phone VARCHAR(20) NOT NULL,
	Adults_Ticket NUMBER NOT NULL,
	Childrens_Ticket NUMBER NOT NULL,
	Opening_Time VARCHAR(20) NOT NULL,
	Closing_Time VARCHAR(20) NOT NULL
);

--Flights

CREATE TABLE Flight_Companies(
	Name VARCHAR(20) PRIMARY KEY NOT NULL,
	Address VARCHAR(35) NOT NULL,
	Phone VARCHAR(20) NOT NULL,
	Email VARCHAR(30) NOT NULL
);

CREATE TABLE Airplanes(
	Company_Name VARCHAR(20) NOT NULL,
	Airplane_ID VARCHAR(20) NOT NULL,
	FOREIGN KEY (Company_Name) REFERENCES Flight_Companies(Name),
	PRIMARY KEY (Company_Name,Airplane_ID)
); 

CREATE TABLE Flight_Routes(
	Company_Name VARCHAR(20) NOT NULL,
	Airplane_ID VARCHAR(20) NOT NULL,
    Flight_ID VARCHAR(15) NOT NULL,
	Departure VARCHAR(20) NOT NULL,
	Departure_Date VARCHAR(20) NOT NULL,
	Departure_Time VARCHAR(20) NOT NULL, 
	Arrival VARCHAR(20) NOT NULL,
	Arrival_Date VARCHAR(20) NOT NULL,
	Arrival_Time VARCHAR(20) NOT NULL,
	Adults_Ticket NUMBER NOT NULL,
	Childrens_Ticket NUMBER NOT NULL,
    FOREIGN KEY (Company_Name,Airplane_ID) REFERENCES Airplanes(Company_Name,Airplane_ID),
    PRIMARY KEY (Company_Name,Airplane_ID,Flight_ID)
);

--Ships

CREATE TABLE Ship_Companies(
	Name VARCHAR(20) PRIMARY KEY NOT NULL,
	Address VARCHAR(35) NOT NULL,
	Phone VARCHAR(20) NOT NULL,
	Email VARCHAR(30) NOT NULL
);

CREATE TABLE Ships(
	Company_Name VARCHAR(20) NOT NULL,
	Ship_ID VARCHAR(20) NOT NULL,
	FOREIGN KEY (Company_Name) REFERENCES Ship_Companies(Name),
	PRIMARY KEY (Company_Name,Ship_ID)
); 

CREATE TABLE Ship_Routes(
	Company_Name VARCHAR(20) NOT NULL,
	Ship_ID VARCHAR(20) NOT NULL,
	Route_ID VARCHAR(15) NOT NULL,
	Departure VARCHAR(20) NOT NULL,
	Departure_Date VARCHAR(20) NOT NULL,
	Departure_Time VARCHAR(20) NOT NULL,
	Arrival VARCHAR(20) NOT NULL,
	Arrival_Date VARCHAR(20) NOT NULL,
	Arrival_Time VARCHAR(20) NOT NULL,
	Adults_Ticket NUMBER NOT NULL,
	Childrens_Ticket NUMBER NOT NULL,
	FOREIGN KEY (Company_Name,Ship_ID) REFERENCES Ships(Company_Name,Ship_ID),
	PRIMARY KEY (Company_Name,Ship_ID,Route_ID)
);

--Excursions

CREATE TABLE Excursion_Offices(
	Name VARCHAR(30) PRIMARY KEY NOT NULL,
	Address VARCHAR(35) NOT NULL,
	Phone VARCHAR(20) NOT NULL,
	Email VARCHAR(35) NOT NULL
);

CREATE TABLE Excursions(
	Excursion_Office VARCHAR(30),
	Excursion_ID VARCHAR(15),
	Departure VARCHAR(20) NOT NULL,
	Departure_Date VARCHAR(20) NOT NULL,
	Departure_Time VARCHAR(20) NOT NULL,
	Arrival VARCHAR(20) NOT NULL,
	Arrival_Date VARCHAR(20) NOT NULL,
	Arrival_Time VARCHAR(20) NOT NULL,
	Adults_Ticket NUMBER NOT NULL,
	Childrens_Ticket NUMBER NOT NULL,
	FOREIGN KEY (Excursion_Office) REFERENCES Excursion_Offices(Name),
	PRIMARY KEY (Excursion_Office,Excursion_ID)
);

--Bookings

CREATE TABLE Hotel_Bookings(
	Booking_ID NUMBER,
	Hotel_Name VARCHAR(30) NOT NULL,
	Room_Number  VARCHAR(255) NOT NULL,
	Check_In DATE NOT NULL,
	Check_Out DATE NOT NULL,
	Adults VARCHAR(5) NOT NULL,
	Kids VARCHAR(5) NOT NULL,
	Charge VARCHAR(255) NOT NULL,
	Name VARCHAR(30) NOT NULL,
	SurName VARCHAR(30) NOT NULL,
	Phone VARCHAR(30) NOT NULL,
	Email VARCHAR(35) NOT NULL,
	FOREIGN KEY (Hotel_Name,Room_Number) REFERENCES Rooms(Hotel_Name,Room_Number),
	PRIMARY KEY (Hotel_Name,Room_Number,Check_In,Check_Out,Phone)
);

CREATE SEQUENCE Booking_ID
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1;
  
  create or replace trigger booking_id_trigger
  before insert on Hotel_Bookings
  for each row
  begin
    select booking_id.nextval
    into :new.Booking_ID
    from dual;
  end;
  
  CREATE TABLE Car_Bookings(
	Booking_ID NUMBER,
	Rental_Office VARCHAR(30) NOT NULL,
	Car_ID  VARCHAR(255) NOT NULL,
	Pick_Up DATE NOT NULL,
	Drop_Off DATE NOT NULL,
	Charge NUMBER NOT NULL,
	Name VARCHAR(30) NOT NULL,
	Surname VARCHAR(30) NOT NULL,
	Phone VARCHAR(30) NOT NULL,
	Email VARCHAR(35) NOT NULL,
	FOREIGN KEY (Rental_Office,Car_ID) REFERENCES Cars(Rental_Office,Car_ID),
	PRIMARY KEY (Rental_Office,Car_ID,Pick_Up,Drop_Off,Phone)
);
  
CREATE SEQUENCE car_booking_id
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1;
  
  create or replace trigger car_booking_id_trigger
  before insert on Car_Bookings
  for each row
  begin
    select car_booking_id.nextval
    into :new.Booking_ID
    from dual;
  end;  
  
CREATE TABLE Motor_Bookings(
   	Booking_ID NUMBER,
	Rental_Office VARCHAR(30) NOT NULL,
	Motorcycle_ID  VARCHAR(255) NOT NULL,
	Pick_Up DATE NOT NULL,
	Drop_Off DATE NOT NULL,
	Charge NUMBER NOT NULL,
	Name VARCHAR(30) NOT NULL,
	Surname VARCHAR(30) NOT NULL,
	Phone VARCHAR(30) NOT NULL,
	Email VARCHAR(35) NOT NULL,
	FOREIGN KEY (Rental_Office,Motorcycle_ID) REFERENCES Motorcycles(Rental_Office,Motorcycle_ID),
	PRIMARY KEY (Rental_Office,Motorcycle_ID,Pick_Up,Drop_Off,Phone)
);
  
CREATE SEQUENCE motor_booking_id
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1;
  
  create or replace trigger motor_booking_id_trigger
  before insert on Motor_Bookings
  for each row
  begin
    select motor_booking_id.nextval
    into :new.Booking_ID
    from dual;
  end; 
  
CREATE TABLE Flight_Bookings(
    	Booking_ID NUMBER,
	Company_Name VARCHAR(20) NOT NULL,
	Airplane_ID VARCHAR(20) NOT NULL,
    	Flight_ID VARCHAR(15) NOT NULL,
	Adults_Ticket NUMBER NOT NULL,
	Childrens_Ticket NUMBER,
    	Name VARCHAR(30) NOT NULL,
	Surname VARCHAR(30) NOT NULL,
	Phone VARCHAR(30) NOT NULL,
	Email VARCHAR(35) NOT NULL,
	FOREIGN KEY (Company_Name,Airplane_ID,Flight_ID) REFERENCES Flight_Routes(Company_Name,Airplane_ID,Flight_ID),
	PRIMARY KEY (Company_Name,Airplane_ID,Flight_ID,Phone)
);
  
CREATE SEQUENCE flight_booking_id
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1;
  
  create or replace trigger flight_booking_id_trigger
  before insert on Flight_Bookings
  for each row
  begin
    select flight_booking_id.nextval
    into :new.Booking_ID
    from dual;
  end;
  
CREATE TABLE Ship_Bookings(
    	Booking_ID,
	Company_Name VARCHAR(20) NOT NULL,
	Ship_ID VARCHAR(20) NOT NULL,
    	Route_ID VARCHAR(15) NOT NULL,
	Adults_Ticket NUMBER NOT NULL,
	Childrens_Ticket NUMBER,
    	Name VARCHAR(30) NOT NULL,
	Surname VARCHAR(30) NOT NULL,
	Phone VARCHAR(30) NOT NULL,
	Email VARCHAR(35) NOT NULL,
	FOREIGN KEY (Company_Name,Ship_ID,Route_ID) REFERENCES Ship_Routes(Company_Name,Ship_ID,Route_ID),
	PRIMARY KEY (Company_Name,Ship_ID,Route_ID,Phone)
);
  
CREATE SEQUENCE ship_booking_id
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1;
  
  create or replace trigger ship_booking_id_trigger
  before insert on Ship_
s
  for each row
  begin
    select ship_booking_id.nextval
    into :new.Booking_ID
    from dual;
  end;
  
CREATE TABLE Excursion_Bookings(
    	Booking_ID NUMBER,
	Excursion_Office VARCHAR(20) NOT NULL,
	Excursion_ID VARCHAR(20) NOT NULL,
	Adults VARCHAR(30) NOT NULL,
	Kids VARCHAR(30),
   	Name VARCHAR(30) NOT NULL,
	Surname VARCHAR(30) NOT NULL,
	Phone VARCHAR(30) NOT NULL,
	Email VARCHAR(35) NOT NULL,
	FOREIGN KEY (Excursion_Office,Excursion_ID) REFERENCES Excursions(Excursion_Office,Excursion_ID),
	PRIMARY KEY (Excursion_Office,Excursion_ID,Phone)
);
  
CREATE SEQUENCE exc_booking_id
	MINVALUE 1
	START WITH 1
	INCREMENT BY 1;
  
  create or replace trigger exc_booking_id_trigger
  before insert on Excursion_Bookings
  for each row
  begin
    select exc_booking_id.nextval
    into :new.Booking_ID
    from dual;
  end;
  
 -- Users
 
 CREATE TABLE Users (
  Username VARCHAR(30),
  Pass VARCHAR(30),
  PRIMARY KEY (Username,Pass)
);
