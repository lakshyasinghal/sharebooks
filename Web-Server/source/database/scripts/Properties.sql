DROP TABLE IF EXISTS `WebProperties`;


CREATE TABLE IF NOT EXISTS `WebProperties` (
	Name VARCHAR(50) NOT NULL,
	Value VARCHAR(1000) NOT NULL
)ENGINE=INNODB;




INSERT INTO `WebProperties` VALUES('host','localhost');
INSERT INTO `WebProperties` VALUES('port','8090');
INSERT INTO `WebProperties` VALUES('booksUploadDir','/Users/lakshya.singhal/Desktop/uploads');
INSERT INTO `WebProperties` VALUES('staticResourcePaths','["public/resources/images","public/resources/images/background","public/resources/images/books","public/styles","public/lib/js","public/view-scripts"]');
INSERT INTO `WebProperties` VALUES('sessionExpiry','300');
INSERT INTO `WebProperties` VALUES('sessionSecret','secretkey');

