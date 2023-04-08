#Industry
CREATE TABLE Industry (
	Id bigint NOT NULL AUTO_INCREMENT,
	Name varchar(20) NOT NULL,
	PRIMARY KEY (Id)
);

#User
CREATE TABLE User (
	Id bigint NOT NULL AUTO_INCREMENT,
	Email varchar(50) NOT NULL,
	Name varchar(30) NOT NULL,
	Password varchar(20) NULL,
	Level int NOT NULL,
	Headline varchar(20) NULL,
	About varchar(1500) NULL,
	Location varchar(20) NULL,
	IndustryId bigint NULL,
	CreatedOn datetime(6),
	PRIMARY KEY (Id),
	CONSTRAINT FK_User_Industry FOREIGN KEY(IndustryId) REFERENCES Industry(Id)
);

#Follower
CREATE TABLE Follower (
	Id BIGINT NOT NULL,
	UserId BIGINT NOT NULL,
	FollowingId BIGINT NOT NULL,
	HasNotificationOn BIT NOT NULL,
	FollowedOn datetime(6) NOT NULL,
	PRIMARY KEY (Id),
	CONSTRAINT FK_Follower_User FOREIGN KEY (UserId) REFERENCES User(Id),
	CONSTRAINT FK_Follower_Following FOREIGN KEY (FollowingId) REFERENCES User(Id)
);

#Post
CREATE TABLE Post (
	Id BIGINT NOT NULL AUTO_INCREMENT,
	UserId BIGINT NOT NULL,
	Content varchar(500) NOT NULL,
	PostedOn datetime(6) NOT NULL,
	PRIMARY KEY (Id),
	CONSTRAINT FK_Post_User FOREIGN KEY (UserId) REFERENCES User(Id)
);

#Comment
CREATE TABLE Comment (
	Id BIGINT NOT NULL AUTO_INCREMENT,
	UserId BIGINT NOT NULL,
	PostId BIGINT NOT NULL,
	Content varchar(500) NOT NULL,
	CommentedOn DATETIME(6),
	CONSTRAINT PK_Comment PRIMARY KEY (Id),
	CONSTRAINT FK_Comment_User FOREIGN KEY (UserId) REFERENCES User(Id),
	CONSTRAINT FK_Comment_Post FOREIGN KEY (PostId) REFERENCES Post(Id)
);

#Skill
CREATE TABLE Skill (
	Id BIGINT NOT NULL AUTO_INCREMENT,
	Name varchar(20) NOT NULL,
	PRIMARY KEY (Id)
);


#Job
CREATE TABLE Job (
	Id BIGINT NOT NULL AUTO_INCREMENT,
	CompanyId BIGINT NOT NULL,
	Description varchar(20) NOT NULL,
	JobLevelId INT NOT NULL,
	IsActive BIT NOT NULL,
	PostedOn datetime(6) NOT NULL,
	CONSTRAINT PK_Job PRIMARY KEY (Id),
	CONSTRAINT FK_Job_Company FOREIGN KEY (CompanyId) REFERENCES User(Id)
);

#JobSkill
CREATE TABLE JobSkill(
	Id BIGINT NOT NULL AUTO_INCREMENT,
	JobId BIGINT NOT NULL,
	SkillId BIGINT NOT NULL,
	CONSTRAINT PK_JobSkill PRIMARY KEY (Id),
	CONSTRAINT FK_JobSkill_Job FOREIGN KEY (JobId) REFERENCES Job(Id),
	CONSTRAINT FK_JobSkill_Skill FOREIGN KEY (SkillId) REFERENCES Skill(Id)
);

#UserSkill
CREATE TABLE UserSkill(
	Id BIGINT NOT NULL AUTO_INCREMENT,
	UserId BIGINT NOT NULL,
	SkillId BIGINT NOT NULL,
	CONSTRAINT PK_UserSkill PRIMARY KEY (Id),
	CONSTRAINT FK_UserSkill_User FOREIGN KEY (UserId) REFERENCES User(Id),
	CONSTRAINT FK_UserSkill_Skill FOREIGN KEY (SkillId) REFERENCES Skill(Id)
);

#JobApplication
CREATE TABLE JobApplication (
	Id BIGINT NOT NULL AUTO_INCREMENT,
	JobId BIGINT NOT NULL,
	StudentId BIGINT NOT NULL,
	CONSTRAINT PK_JobApplication PRIMARY KEY (Id),
	CONSTRAINT FK_JobApplication_Job FOREIGN KEY (JobId) REFERENCES Job(Id),
	CONSTRAINT FK_JobApplication_Student FOREIGN KEY (StudentId) REFERENCES User(Id)
);

#Recommendation
CREATE TABLE Recommendation(
	Id BIGINT NOT NULL AUTO_INCREMENT,
	StudentId BIGINT NOT NULL,
	InstructorId BIGINT NOT NULL,
	Request varchar(500) NOT NULL,
	Content varchar(500) NOT NULL,
	StatusId INT NOT NULL,
	CONSTRAINT PK_Recommendation PRIMARY KEY (Id),
	CONSTRAINT FK_Recommendation_Student FOREIGN KEY (StudentId) REFERENCES User(Id),
	CONSTRAINT FK_Recommendation_Instructor FOREIGN KEY (InstructorId) REFERENCES User(Id)
);
