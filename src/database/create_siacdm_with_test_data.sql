-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: siacdm
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `slot_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_id` int(11) NOT NULL,
  `technician_id` varchar(40) NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `updated_by` varchar(40) NOT NULL,
  PRIMARY KEY (`slot_id`),
  UNIQUE KEY `start_UNIQUE` (`start`),
  UNIQUE KEY `end_UNIQUE` (`end`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (1,1,'karthik','Flight A123','2017-10-23 06:00:00','2017-10-23 10:00:00','rachel'),(2,2,'vipul','Flight A234','2017-10-24 10:05:00','2017-10-24 14:00:00','rachel'),(3,3,'karthik','Flight A345','2017-10-25 17:00:00','2017-10-25 20:00:00','rachel'),(4,4,'vipul','Flight 876','2017-10-26 14:05:00','2017-10-26 16:55:00','gaurav'),(5,5,'vipul','Flight 123','2017-10-27 21:00:00','2017-10-27 23:55:00','gaurav'),(6,6,'karthik','flight 345','2017-10-28 06:00:00','2017-10-28 06:30:00','rachel'),(7,7,'vipul','flight xxx','2017-10-29 06:31:00','2017-10-29 07:00:00','gaurav'),(8,8,'karthik','flight 222','2017-10-30 07:01:00','2017-10-30 07:29:00','rachel'),(9,9,'vipul','flight 23','2017-10-31 07:30:00','2017-10-31 08:00:00','rachel'),(10,10,'karthik','flight 232','2017-11-01 08:01:00','2017-11-01 08:29:00','rachel'),(11,1,'karthik','flight sdknv','2017-11-02 08:30:00','2017-11-02 09:00:00','gaurav'),(12,2,'vipul','flight 231','2017-11-03 09:30:00','2017-11-03 10:30:00','gaurav'),(13,3,'karthik','flight 1123','2017-11-04 06:00:00','2017-11-04 10:00:00','gaurav'),(14,4,'vipul','flight 231','2017-11-05 10:05:00','2017-11-05 14:00:00','rachel');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `typical_duration` int(11) DEFAULT NULL,
  `updated_by` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'Seat Fix','Fix the broke seat',60,'rachel'),(2,'Carpet Clean','Clean the dirty carept',30,'rachel'),(3,'Window Door Fix','Window door fix',120,'gaurav'),(4,'Seat Clean','Seat cleaning',30,'gaurav'),(5,'TV Fix','Tv repair fix',120,'rachel'),(6,'Replace Seat Cover','Replace the seat cover',30,'rachel'),(7,'Replace TV','Replace the broken TV',240,'gaurav'),(8,'Replace Seat','Replace broken seat',180,'rachel'),(9,'Luggage Door Fix','Door above the passenger seat fix',120,'rachel'),(10,'Restroom Fix','Fix the problems in restroom',150,'gaurav');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` varchar(15) NOT NULL,
  `role_name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_id_UNIQUE` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('admin','System Administrator'),('supervisor','Supervisor'),('technician','technician who attends the defects');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` varchar(40) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('gaurav','gaurav','gaurav sharma', '90373886'),('vipul','vipul','vipul zambre', '90746618'),('karthik','karthik','karthik suriyan', '82002942'),('rachel','rachel','rachel raj', '81878660'),('admin','admin','administator', '82002942');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` varchar(40) NOT NULL,
  `role_id` varchar(15) NOT NULL,
  `updated_by` varchar(40) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_role_id_idx` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('rachel','admin','admin'),('rachel','supervisor','admin'),('gaurav','supervisor','admin'),('karthik','technician','admin'),('vipul','technician','admin'),('admin','admin','system');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'siacdm'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-27 18:55:01
