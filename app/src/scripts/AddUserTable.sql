CREATE TABLE User (
	Id bigint NOT NULL AUTO_INCREMENT,
	Email varchar(50) NOT NULL,
	Name varchar(30) NOT NULL,
	Password varchar(20) NULL,
	Headline varchar(20) NULL,
	About varchar(500) NULL,
	Location varchar(20) NULL,
	IndustryId bigint NULL,
	RegistrationDate datetime(6),
	PRIMARY KEY (Id)
);
