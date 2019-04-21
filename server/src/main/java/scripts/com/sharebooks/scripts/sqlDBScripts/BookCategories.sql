DROP TABLE IF EXISTS BookCategories;

CREATE TABLE BookCategories (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Category VARCHAR(20) NOT NULL UNIQUE
) ENGINE=INNODB;



INSERT INTO BookCategories (category) values ('Science');
INSERT INTO BookCategories (category) values ('Literature');
INSERT INTO BookCategories (category) values ('Politics');
INSERT INTO BookCategories (category) values ('Sports');
INSERT INTO BookCategories (category) values ('Spiritual');
INSERT INTO BookCategories (category) values ('Computer Science');
INSERT INTO BookCategories (category) values ('Commerce');