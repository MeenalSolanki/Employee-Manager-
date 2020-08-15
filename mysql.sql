-- -----------------------------------------------------
-- Schema full-stack-ecommerce
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `manager-employee-mindbrowser`;

CREATE SCHEMA `manager-employee-mindbrowser`;
USE `manager-employee-mindbrowser` ;

-- -----------------------------------------------------
-- Employee table
-- -----------------------------------------------------

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `empid` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(90) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `mobile_no` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `manager_id` bigint DEFAULT NULL,
  PRIMARY KEY (`empid`),
  KEY `FKfemnv0llvsjg4adl4xl1m0cxv` (`manager_id`),
  CONSTRAINT `FKfemnv0llvsjg4adl4xl1m0cxv` FOREIGN KEY (`manager_id`) REFERENCES `manager` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;



-- -----------------------------------------------------
-- Manager table
-- -----------------------------------------------------


DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manager` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(90) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(40) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKch4c0h9mgdd2c5lategqkpsyi` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

