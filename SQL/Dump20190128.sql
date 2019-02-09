-- MySQL dump 10.13  Distrib 8.0.12, for macos10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: CarPark
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Booking`
--

DROP TABLE IF EXISTS `Booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Booking` (
  `bookingID` int(11) NOT NULL AUTO_INCREMENT,
  `carParkID` int(11) NOT NULL,
  `driverCarID` int(11) NOT NULL,
  `bookingTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `valid` tinyint(4) DEFAULT '0',
  `cancel` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`bookingID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Booking`
--

LOCK TABLES `Booking` WRITE;
/*!40000 ALTER TABLE `Booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `Booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CarPark`
--

DROP TABLE IF EXISTS `CarPark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CarPark` (
  `carParkID` int(11) NOT NULL AUTO_INCREMENT,
  `name` longtext,
  `address` longtext,
  `openTime` datetime DEFAULT NULL,
  `closeTime` datetime DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`carParkID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CarPark`
--

LOCK TABLES `CarPark` WRITE;
/*!40000 ALTER TABLE `CarPark` DISABLE KEYS */;
INSERT INTO `CarPark` VALUES (1,'Hello Car Park',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `CarPark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CarParkOwnerCarPark`
--

DROP TABLE IF EXISTS `CarParkOwnerCarPark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CarParkOwnerCarPark` (
  `userID` int(11) NOT NULL,
  `carParkID` int(11) NOT NULL,
  PRIMARY KEY (`userID`,`carParkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CarParkOwnerCarPark`
--

LOCK TABLES `CarParkOwnerCarPark` WRITE;
/*!40000 ALTER TABLE `CarParkOwnerCarPark` DISABLE KEYS */;
/*!40000 ALTER TABLE `CarParkOwnerCarPark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CarParkSlot`
--

DROP TABLE IF EXISTS `CarParkSlot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CarParkSlot` (
  `carParkSlotID` int(11) NOT NULL AUTO_INCREMENT,
  `carTypeID` int(11) NOT NULL,
  `statusID` int(11) NOT NULL,
  `carParkSlot` int(11) NOT NULL,
  `slotIndex` int(11) NOT NULL,
  PRIMARY KEY (`carParkSlotID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CarParkSlot`
--

LOCK TABLES `CarParkSlot` WRITE;
/*!40000 ALTER TABLE `CarParkSlot` DISABLE KEYS */;
/*!40000 ALTER TABLE `CarParkSlot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CarParkSlotInfo`
--

DROP TABLE IF EXISTS `CarParkSlotInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CarParkSlotInfo` (
  `carParkID` int(11) NOT NULL,
  `carTypeID` int(11) NOT NULL,
  `numOfSlot` int(11) NOT NULL,
  `Fee` double NOT NULL,
  PRIMARY KEY (`carParkID`,`carTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CarParkSlotInfo`
--

LOCK TABLES `CarParkSlotInfo` WRITE;
/*!40000 ALTER TABLE `CarParkSlotInfo` DISABLE KEYS */;
INSERT INTO `CarParkSlotInfo` VALUES (1,1,20,20);
/*!40000 ALTER TABLE `CarParkSlotInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CarType`
--

DROP TABLE IF EXISTS `CarType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CarType` (
  `carTypeID` int(11) NOT NULL AUTO_INCREMENT,
  `carType` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`carTypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CarType`
--

LOCK TABLES `CarType` WRITE;
/*!40000 ALTER TABLE `CarType` DISABLE KEYS */;
INSERT INTO `CarType` VALUES (1,'Private Car');
/*!40000 ALTER TABLE `CarType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Driver`
--

DROP TABLE IF EXISTS `Driver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Driver` (
  `driverID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `credit` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`driverID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Driver`
--

LOCK TABLES `Driver` WRITE;
/*!40000 ALTER TABLE `Driver` DISABLE KEYS */;
/*!40000 ALTER TABLE `Driver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DriverCar`
--

DROP TABLE IF EXISTS `DriverCar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `DriverCar` (
  `driverCarID` int(11) NOT NULL AUTO_INCREMENT,
  `driverID` int(11) NOT NULL,
  `carTypeID` int(11) NOT NULL,
  `licensePlateNum` varchar(45) NOT NULL,
  PRIMARY KEY (`driverCarID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DriverCar`
--

LOCK TABLES `DriverCar` WRITE;
/*!40000 ALTER TABLE `DriverCar` DISABLE KEYS */;
/*!40000 ALTER TABLE `DriverCar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Gift`
--

DROP TABLE IF EXISTS `Gift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Gift` (
  `giftID` int(11) NOT NULL AUTO_INCREMENT,
  `giftName` varchar(45) DEFAULT NULL,
  `available` tinyint(4) DEFAULT '1',
  `giftDescription` longtext,
  PRIMARY KEY (`giftID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Gift`
--

LOCK TABLES `Gift` WRITE;
/*!40000 ALTER TABLE `Gift` DISABLE KEYS */;
/*!40000 ALTER TABLE `Gift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GiftHistory`
--

DROP TABLE IF EXISTS `GiftHistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `GiftHistory` (
  `giftHistyoryID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `giftID` int(11) NOT NULL,
  `exchangeTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`giftHistyoryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GiftHistory`
--

LOCK TABLES `GiftHistory` WRITE;
/*!40000 ALTER TABLE `GiftHistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `GiftHistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Role` (
  `roleID` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Status`
--

DROP TABLE IF EXISTS `Status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Status` (
  `statusID` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`statusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Status`
--

LOCK TABLES `Status` WRITE;
/*!40000 ALTER TABLE `Status` DISABLE KEYS */;
/*!40000 ALTER TABLE `Status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Transaction`
--

DROP TABLE IF EXISTS `Transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Transaction` (
  `transactionID` int(11) NOT NULL AUTO_INCREMENT,
  `startTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `endTime` datetime DEFAULT NULL,
  `slotID` int(11) NOT NULL,
  `totalAmount` double NOT NULL,
  `licensePlateNum` varchar(45) NOT NULL,
  PRIMARY KEY (`transactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Transaction`
--

LOCK TABLES `Transaction` WRITE;
/*!40000 ALTER TABLE `Transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `Transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `User` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `roleID` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-28 21:17:10
