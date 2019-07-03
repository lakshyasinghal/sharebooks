DROP TABLE IF EXISTS PaymentRequestWebhooks;

CREATE TABLE PaymentRequestWebhooks (
	Seq_Id BIGINT(19) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	
	Amount DOUBLE(10,3),
	Buyer VARCHAR(40),
	Buyer_Name VARCHAR(40),
	Buyer_Phone VARCHAR(10),
	Currency VARCHAR(20),
	Fees Float(10),
	Longurl VARCHAR(100) UNIQUE,
	Mac VARCHAR(20),
	Payment_Id VARCHAR(32),
	Payment_Request_Id VARCHAR(20),
	Purpose VARCHAR(20),
	Shorturl VARCHAR(20),
	Status TINYINT
) ENGINE=INNODB;