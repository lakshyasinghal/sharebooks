CREATE TABLE IF NOT EXISTS `BookCategories` (
	Id INT AUTO_INCREMENT PRIMARY KEY,
	Category VARCHAR(50) NOT NULL UNIQUE
) ENGINE=INNODB;



INSERT INTO `BookCategories`(Category) values("Arts & Music");
INSERT INTO `BookCategories`(Category) values("Biographies");
INSERT INTO `BookCategories`(Category) values("Business");
INSERT INTO `BookCategories`(Category) values("Kids");
INSERT INTO `BookCategories`(Category) values("Comics");
INSERT INTO `BookCategories`(Category) values("Computers & Tech");
INSERT INTO `BookCategories`(Category) values("Cooking");
INSERT INTO `BookCategories`(Category) values("Hobbies & Crafts");
INSERT INTO `BookCategories`(Category) values("Gay & Lesbian");
INSERT INTO `BookCategories`(Category) values("Health & Fitness");
INSERT INTO `BookCategories`(Category) values("History");
INSERT INTO `BookCategories`(Category) values("Home & Garden");
INSERT INTO `BookCategories`(Category) values("Entertainment");
INSERT INTO `BookCategories`(Category) values("Literature & Fiction");
INSERT INTO `BookCategories`(Category) values("Medical");
INSERT INTO `BookCategories`(Category) values("Mysteries");
INSERT INTO `BookCategories`(Category) values("Parenting");
INSERT INTO `BookCategories`(Category) values("Social Sciences");
INSERT INTO `BookCategories`(Category) values("Religion");
INSERT INTO `BookCategories`(Category) values("Romance");
INSERT INTO `BookCategories`(Category) values("Science & Math");
INSERT INTO `BookCategories`(Category) values("Sports");
INSERT INTO `BookCategories`(Category) values("Travel");