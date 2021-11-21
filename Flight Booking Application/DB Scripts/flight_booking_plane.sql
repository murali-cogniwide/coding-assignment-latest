-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: flight_booking
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `plane`
--

DROP TABLE IF EXISTS `plane`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plane` (
  `plane_id` int NOT NULL AUTO_INCREMENT,
  `plane_manufacturer` varchar(255) NOT NULL,
  `airline_id` varchar(225) NOT NULL,
  `airline_name` varchar(45) DEFAULT NULL,
  `passenger_capacity` int DEFAULT '0',
  `origin_airport` varchar(255) DEFAULT NULL,
  `destination_airport` varchar(255) DEFAULT NULL,
  `fare` varchar(255) DEFAULT '0',
  `status` varchar(255) DEFAULT 'ACTIVE',
  PRIMARY KEY (`plane_id`),
  UNIQUE KEY `plane_id_UNIQUE` (`plane_id`),
  KEY `plane_idx` (`origin_airport`,`destination_airport`,`plane_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plane`
--

LOCK TABLES `plane` WRITE;
/*!40000 ALTER TABLE `plane` DISABLE KEYS */;
INSERT INTO `plane` VALUES (1,'BOEING','QA001','Qatar Airlines',150,'Doha','Chennai','1500.000','ACTIVE'),(2,'BOEING','QA001','Qatar Airlines',150,'Doha','Mumbai','1700.000','ACTIVE'),(3,'AIRBUS','QA001','Qatar Airlines',150,'Doha','New Delhi','1700.000','ACTIVE'),(4,'AIRBUS','QA001','Qatar Airlines',150,'Doha','Pune','1400.000','ACTIVE'),(5,'AIRBUS','QA001','Qatar Airlines',150,'Doha','Bangalore','1300.000','ACTIVE'),(6,'BOEING','SA002','Singapore Airlines',100,'Singapore','Chennai','1750.000','ACTIVE'),(7,'BOEING','SA002','Singapore Airlines',100,'Singapore','Mumbai','1750.000','ACTIVE'),(8,'BOEING','SA002','Singapore Airlines',100,'Singapore','Bangalore','1750.000','ACTIVE'),(9,'AIRBUS','SA002','Singapore Airlines',100,'Singapore','Pune','1750.000','ACTIVE'),(10,'AIRBUS','SA002','Singapore Airlines',100,'Singapore','Hyderabad','1750.000','ACTIVE'),(11,'AIRBUS','BA003','British Airways',250,'London','Chennai','1750.000','ACTIVE'),(12,'AIRBUS','BA003','British Airways',250,'London','Mumbai','1750.000','ACTIVE'),(13,'AIRBUS','BA003','British Airways',250,'London','Pune','1750.000','ACTIVE'),(14,'AIRBUS','BA003','British Airways',250,'London','Banglore','1480.000','ACTIVE'),(15,'BOEING','BA003','British Airways',250,'London','New Delhi','1980.000','ACTIVE'),(16,'BOEING','AA004','American Airlines',250,'Dallas','New Delhi','7980.000','ACTIVE'),(17,'BOEING','AA004','American Airlines',250,'Atlanta','New Delhi','7980.000','ACTIVE'),(18,'BOEING','AA004','American Airlines',250,'Philadelphia','London','5980.000','ACTIVE'),(19,'BOEING','AA004','American Airlines',250,'San Fransisco','Dubai','9980.000','ACTIVE'),(20,'AIRBUS','AA004','American Airlines',250,'New York City','Doha','7900.000','ACTIVE'),(21,'AIRBUS','MA005','Midway Airlines',250,'Boston','Doha','6900.000','ACTIVE'),(22,'AIRBUS','MA005','Midway Airlines',250,'Paris','Dubai','1900.000','ACTIVE'),(23,'BOEING','MA005','Midway Airlines',250,'Miami','Lisbon','6500.000','ACTIVE'),(24,'BOEING','MA005','Midway Airlines',250,'Philadelphia','Abhu Dhabi','8800.000','ACTIVE'),(25,'AIRBUS','MA005','Midway Airlines',250,'Denver','Panama City','3570.000','ACTIVE');
/*!40000 ALTER TABLE `plane` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-15 13:00:08
