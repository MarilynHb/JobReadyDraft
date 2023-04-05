CREATE TABLE User (
	Id bigint NOT NULL AUTO_INCREMENT,
	Email nvarchar(20) NOT NULL,
	Name nvarchar(20) NOT NULL,
	Password nvarchar(20) NULL,
	RegistrationDate datetime(7),
	PRIMARY KEY (Id)
);