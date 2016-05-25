DROP TABLE IF EXISTS `REVINFO`;
DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `persistent_logins` ;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;

CREATE TABLE `REVINFO` (
  `REV` int(11) NOT NULL AUTO_INCREMENT,
  `REVTSTMP` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`REV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `role` (
  `roleId` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8;


CREATE TABLE `user_roles` (
  `username` varchar(255) NOT NULL,
  `roleId` int(11) NOT NULL,
  FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `username` VARCHAR(255) NOT NULL,
  `series` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP NOT NULL,
  PRIMARY KEY (`series`),
  FOREIGN KEY (`username`) 
  REFERENCES `user` (`username`))
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;