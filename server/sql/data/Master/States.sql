CREATE TABLE IF NOT EXISTS `States` (
  `Id` TINYINT unsigned NOT NULL AUTO_INCREMENT,
  `State` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

--
-- Dumping data for table `state_list`
--

INSERT INTO `States` (`Id`, `State`) VALUES
( 1 , 'Andaman & Nicobar Islands' ),
( 2 , 'Andhra Pradesh' ),
( 3 , 'Arunachal Pradesh' ),
( 4 , 'Assam' ),
( 5 , 'Bihar' ),
( 6 , 'Chandigarh' ),
( 7 , 'Chhattisgarh' ),
( 8 , 'Dadra & Nagar Haveli' ),
( 9 , 'Daman & Diu' ),
( 10 , 'Delhi' ),
( 11 , 'Goa' ),
( 12 , 'Gujarat' ),
( 13 , 'Haryana' ),
( 14 , 'Himachal Pradesh' ),
( 15 , 'Jammu & Kashmir' ),
( 16 , 'Jharkhand' ),
( 17 , 'Karnataka' ),
( 18 , 'Kerala' ),
( 19 , 'Lakshadweep' ),
( 20 , 'Madhya Pradesh' ),
( 21 , 'Maharashtra' ),
( 22 , 'Manipur' ),
( 23 , 'Meghalaya' ),
( 24 , 'Mizoram' ),
( 25 , 'Nagaland' ),
( 26 , 'Odisha' ),
( 27 , 'Puducherry' ),
( 28 , 'Punjab' ),
( 29 , 'Rajasthan' ),
( 30 , 'Sikkim' ),
( 31 , 'Tamil Nadu' ),
( 32 , 'Telangana' ),
( 33 , 'Tripura' ),
( 34 , 'Uttar Pradesh' ),
( 35 , 'Uttarakhand' ),
( 36 , 'West Bengal' );