CREATE TABLE `Employee_Ch5` (
	`user_id` INT NULL,
	`user_name` VARCHAR(50) NULL
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;

ALTER TABLE `employee_ch5`
	ADD COLUMN `double_data` DOUBLE NULL DEFAULT NULL AFTER `user_name`,
	ADD COLUMN `boolean_data` BIT NULL DEFAULT NULL AFTER `double_data`,
	ADD COLUMN `array_data` LONGBLOB NULL DEFAULT NULL AFTER `boolean_data`,
	ADD COLUMN `date_time_data` DATETIME NULL DEFAULT NULL AFTER `array_data`,
	ADD COLUMN `date_data` DATE NULL DEFAULT NULL AFTER `date_time_data`,
	ADD COLUMN `time_data` TIME NULL DEFAULT NULL AFTER `date_data`
	ADD COLUMN `enum_data` VARCHAR(50) NULL DEFAULT NULL AFTER `time_data`,
	ADD COLUMN `enum_data_2` VARCHAR(50) NULL DEFAULT NULL AFTER `enum_data`,
	ADD COLUMN `serializable_data` BLOB NULL DEFAULT NULL AFTER `enum_data_2`;
	
ALTER TABLE `employee_ch5`
	CHANGE COLUMN `user_id` `user_id` INT(11) NOT NULL AUTO_INCREMENT FIRST,
	ADD PRIMARY KEY (`user_id`);
	
--#############################
--# Table for generated id
--#############################
CREATE TABLE `EmployeeV2_id_generator` (
	`generator_name` VARCHAR(50) NOT NULL,
	`value` INT NOT NULL,
	PRIMARY KEY (`generator_name`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;	

INSERT INTO `jpahibernate`.`employeev2_id_generator` (`generator_name`, `value`) VALUES ('EmployeeV2', 1);

--#############################
--# Relations
--#############################
CREATE TABLE `department` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;

INSERT INTO `jpahibernate`.`department` (`name`) VALUES ('RH');
INSERT INTO `jpahibernate`.`department` (`name`) VALUES ('Development');

ALTER TABLE `employee_ch4`
	ADD COLUMN `depto_id` INT NULL AFTER `serializable_data`,
	ADD CONSTRAINT `FK1_department` FOREIGN KEY (`depto_id`) REFERENCES `department` (`id`) ON UPDATE CASCADE ON DELETE CASCADE;
	
