-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: pjdb
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_mst_department`
--

DROP TABLE IF EXISTS `t_mst_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_mst_department` (
  `DEPARTMENT_CD` varchar(16) NOT NULL,
  `DEPARTMENT_NAME_EN` varchar(32) DEFAULT NULL,
  `DEPARTMENT_NAME_JA` varchar(32) DEFAULT NULL,
  `EXPIRATION_START` date NOT NULL,
  `EXPIRATION_END` date DEFAULT NULL,
  `INSERT_TIMESTAMP` timestamp NULL DEFAULT NULL,
  `INSERT_USER` varchar(32) DEFAULT NULL,
  `UPDATE_TIMESTAMP` timestamp NULL DEFAULT NULL,
  `UPDATE_USER` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`DEPARTMENT_CD`,`EXPIRATION_START`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_mst_department`
--

LOCK TABLES `t_mst_department` WRITE;
/*!40000 ALTER TABLE `t_mst_department` DISABLE KEYS */;
INSERT INTO `t_mst_department` VALUES ('D10001','Human Resources','人事部','2022-06-01','9999-12-31','2022-06-04 02:05:14','admin','2022-06-04 02:05:14','admin'),('D20001','Engineering','技術開発部','2022-06-01','9999-12-31','2022-06-04 02:07:50','admin','2022-06-04 02:07:50','admin'),('D30001','Sales','営業部','2022-06-01','9999-12-31','2022-06-04 02:07:50','admin','2022-06-04 02:07:50','admin'),('D40001','General Affairs','総務部','2022-06-01','9999-12-31','2022-06-04 02:07:50','admin','2022-06-04 02:07:50','admin');
/*!40000 ALTER TABLE `t_mst_department` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-28 10:11:09
