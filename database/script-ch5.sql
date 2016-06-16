CREATE TABLE `ch5_employee` (
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
	CONSTRAINT `FK1_employeeId` FOREIGN KEY (`employeeId`) REFERENCES `ch5_employee` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;

CREATE TABLE `ch5_employee_vacations` (
	`employeeId` INT NOT NULL,
	`startDate` DATE NOT NULL,
	`days` INT NULL,
	PRIMARY KEY (`employeeId`, `startDate`),
	CONSTRAINT `FK1_employeeId_ch5` FOREIGN KEY (`employeeId`) REFERENCES `ch5_employee` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;

CREATE TABLE `ch5_department` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;

ALTER TABLE `ch5_employee`
	ADD COLUMN `deptoId` INT NULL DEFAULT NULL AFTER `salary`,
	ADD CONSTRAINT `FK1_ch5_depto_id` FOREIGN KEY (`deptoId`) REFERENCES `ch5_department` (`id`);
	
CREATE TABLE `ch5_phones` (
	`phoneId` INT NOT NULL AUTO_INCREMENT,
	`type` VARCHAR(50) NULL,
	`val` VARCHAR(50) NULL,
	`employeeId` INT NULL,
	PRIMARY KEY (`phoneId`),
	CONSTRAINT `FK1_employee_phone` FOREIGN KEY (`employeeId`) REFERENCES `ch5_employee` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;