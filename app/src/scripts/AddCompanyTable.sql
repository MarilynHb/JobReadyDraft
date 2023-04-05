CREATE TABLE Company (
	Id bigint NOT NULL AUTO_INCREMENT,
	UserId bigint NOT NULL,
	Description nvarchar(20) NOT NULL,
	Location nvarchar(20) NOT NULL,
	PRIMARY KEY (Id),
	CONSTRAINT FK_Company_User FOREIGN KEY(UserId) REFERENCES User(Id)
);

CREATE INDEX IX_FK_Company_UserId ON Company (UserId);