DROP TABLE IF EXISTS BookRequests;

CREATE TABLE BookRequests (
	Id BIGINT(19) UNSIGNED AUTO_INCREMENT UNIQUE,
	Uid VARCHAR(50) NOT NULL,
	Type TINYINT NOT NULL,
	Status TINYINT NOT NULL,
	BookUid VARCHAR(50) NOT NULL,
	BookOwnerUid VARCHAR(50) NOT NULL,
	RequesterUid VARCHAR(50) NOT NULL,
	RequiredPeriod INT,
	Comments VARCHAR(255),
	CreationTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	LastModificationTime DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (Uid)
) ENGINE=INNODB;


CREATE INDEX SEARCH_BY_OWNER_UID ON BookRequests (bookOwnerUid);

CREATE INDEX SEARCH_BY_REQUESTER_UID ON BookRequests (requesterUid);











