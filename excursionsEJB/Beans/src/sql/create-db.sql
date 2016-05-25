CREATE TABLE `test`.`excursion` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `GuideId` INT NOT NULL,
  `Price` INT NOT NULL,
  `Name` NVARCHAR(64) NOT NULL,
  `Description` NVARCHAR(256) NOT NULL,
  PRIMARY KEY (`Id`)
);
CREATE TABLE `test`.`user` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `UserName` NVARCHAR(64) NOT NULL,
  `FirstName` NVARCHAR(64) NOT NULL,
  `LastName` NVARCHAR(64) NOT NULL,
  `Password` NVARCHAR(32) NOT NULL,
  `UserType` INT NOT NULL,
  PRIMARY KEY (`Id`)
);
CREATE TABLE `test`.`booking` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `UserId` INT NOT NULL,
  `ExcursionId` INT NOT NULL,
  PRIMARY KEY (`Id`)
);
