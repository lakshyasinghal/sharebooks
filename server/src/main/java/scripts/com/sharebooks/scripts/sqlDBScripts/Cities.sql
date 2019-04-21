DROP TABLE IF EXISTS Cities;

CREATE TABLE Cities (
	Id int AUTO_INCREMENT,
	Name VARCHAR(20) NOT NULL,
	StateId int NOT NULL,
	PRIMARY KEY (Id),
	CONSTRAINT UNIQUE_CITY UNIQUE (Name , StateId),
	FOREIGN KEY (StateId) REFERENCES States(Id)
) ENGINE=INNODB;