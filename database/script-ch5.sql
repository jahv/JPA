CREATE TABLE `Employee_chapter5` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NULL,
	`salary` DOUBLE NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;

CREATE TABLE `ch5_nicknames` (
	`nickName` VARCHAR(50) NOT NULL,
	`employeeId` INT NOT NULL,
	PRIMARY KEY (`nickName`, `employeeId`),
	CONSTRAINT `FK1_employeeId` FOREIGN KEY (`employeeId`) REFERENCES `employee_chapter5` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;

CREATE TABLE `ch5_employee_vacations` (
	`employeeId` INT NOT NULL,
	`startDate` DATE NOT NULL,
	`days` INT NULL,
	PRIMARY KEY (`employeeId`, `startDate`),
	CONSTRAINT `FK1_employeeId_ch5` FOREIGN KEY (`employeeId`) REFERENCES `employee_chapter5` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;