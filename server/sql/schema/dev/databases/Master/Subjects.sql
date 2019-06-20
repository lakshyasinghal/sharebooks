DROP TABLE IF EXISTS Subjects;

CREATE TABLE Subjects (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	CategoryId INT NOT NULL,
	Subject VARCHAR(20) NOT NULL UNIQUE
) ENGINE=INNODB;



INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Science') , 'Physics');
INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Science') , 'Chemistry');
INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Science') , 'Maths');
INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Sports') , 'Cricket');
INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Literature') , 'Grammar');
INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Literature') , 'Fiction');