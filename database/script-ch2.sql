DROP DATABASE IF EXISTS `jpahibernate`;
CREATE DATABASE IF NOT EXISTS `jpahibernate`;
USE `jpahibernate`;

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `salary` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


ALTER TABLE `employee`
	ADD COLUMN `phone` VARCHAR(50) NULL AFTER `salary`;
