CREATE DATABASE  IF NOT EXISTS `ceis400_groupc_maintsys` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ceis400_groupc_maintsys`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: ceis400_groupc_maintsys
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `equipment_checkout`
--

DROP TABLE IF EXISTS `equipment_checkout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment_checkout` (
  `CheckoutID` int NOT NULL AUTO_INCREMENT,
  `UserID` int DEFAULT NULL,
  `EquipmentIDChar` varchar(10) DEFAULT NULL,
  `EquipmentIDNum` int DEFAULT NULL,
  `CheckoutDate` date DEFAULT NULL,
  `ReturnDate` date DEFAULT NULL,
  `Status` enum('Checked out','Returned') NOT NULL,
  PRIMARY KEY (`CheckoutID`),
  KEY `UserID` (`UserID`),
  KEY `EquipmentIDChar` (`EquipmentIDChar`,`EquipmentIDNum`),
  CONSTRAINT `equipment_checkout_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user_authoization` (`UserID`),
  CONSTRAINT `equipment_checkout_ibfk_2` FOREIGN KEY (`EquipmentIDChar`, `EquipmentIDNum`) REFERENCES `equipment` (`EquipmentIDChar`, `EquipmentIDNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment_checkout`
--

LOCK TABLES `equipment_checkout` WRITE;
/*!40000 ALTER TABLE `equipment_checkout` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment_checkout` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-30 17:49:54
