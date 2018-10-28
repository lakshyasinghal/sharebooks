DROP TABLE IF EXISTS Subjects;

CREATE TABLE Subjects (
	id INT AUTO_INCREMENT PRIMARY KEY,
	categoryId INT NOT NULL,
	subject VARCHAR(20) NOT NULL UNIQUE,
	FOREIGN KEY (categoryId) REFERENCES BookCategories (id)
) ENGINE=INNODB;



INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Science') , 'Physics');
INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Science') , 'Chemistry');
INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Science') , 'Maths');
INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Sports') , 'Cricket');
INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Literature') , 'Grammar');
INSERT INTO Subjects (categoryId,subject) values ((Select id from BookCategories where category='Literature') , 'Fiction');