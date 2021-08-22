-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: liberty
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `accounting_instructions`
--

DROP TABLE IF EXISTS `accounting_instructions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounting_instructions` (
  `accounting_instructions_id` int NOT NULL AUTO_INCREMENT,
  `book_keeping` tinyint DEFAULT NULL,
  `payroll` tinyint DEFAULT NULL,
  `preparer_tax_return` text,
  `due_date_tax_return` datetime DEFAULT NULL,
  `date_completed_tax_return` datetime DEFAULT NULL,
  `sent_tax_return` text,
  `preparer_cost_report` text NOT NULL,
  `due_date_cost_report` datetime DEFAULT NULL,
  `date_completed_cost_report` datetime DEFAULT NULL,
  `additional_info_tax_return` text,
  `additional_info_cost_report` text,
  `sent_cost_report` text,
  `agency_id` int NOT NULL,
  `intermediary` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`accounting_instructions_id`),
  KEY `account_agency_idx` (`agency_id`),
  CONSTRAINT `account_agency` FOREIGN KEY (`agency_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounting_instructions`
--

LOCK TABLES `accounting_instructions` WRITE;
/*!40000 ALTER TABLE `accounting_instructions` DISABLE KEYS */;
INSERT INTO `accounting_instructions` VALUES (4,1,0,'tewst','2020-05-26 00:00:00','2020-05-26 00:00:00','1','test','2020-05-26 00:00:00','2020-05-26 00:00:00','','lll','1',30,'CGS'),(5,1,1,'','2020-06-11 00:00:00','2020-06-11 00:00:00','1','','2020-06-11 00:00:00','2020-06-11 00:00:00','','fdfd','1',31,NULL),(6,1,0,'','2020-06-11 00:00:00','2020-06-11 00:00:00','0','','2020-06-11 00:00:00','2020-06-11 00:00:00','','','0',31,NULL);
/*!40000 ALTER TABLE `accounting_instructions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agency_emp`
--

DROP TABLE IF EXISTS `agency_emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency_emp` (
  `agency_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`agency_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `agency_emp_ibfk_1` FOREIGN KEY (`agency_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `agency_emp_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_emp`
--

LOCK TABLES `agency_emp` WRITE;
/*!40000 ALTER TABLE `agency_emp` DISABLE KEYS */;
INSERT INTO `agency_emp` VALUES (1,1),(2,1),(3,1),(4,1),(2,2),(6,2),(14,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,7),(21,7),(22,7),(23,7),(24,7),(25,7),(26,7),(27,7),(28,7),(29,7),(30,7),(31,7);
/*!40000 ALTER TABLE `agency_emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agency_list`
--

DROP TABLE IF EXISTS `agency_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency_list` (
  `agency_list_id` int NOT NULL AUTO_INCREMENT,
  `client_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`agency_list_id`),
  KEY `client_id` (`client_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `agency_list_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `agency_list_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_list`
--

LOCK TABLES `agency_list` WRITE;
/*!40000 ALTER TABLE `agency_list` DISABLE KEYS */;
INSERT INTO `agency_list` VALUES (2,30,7),(3,21,7),(4,22,7),(6,31,7),(8,28,7),(9,29,7),(10,2,7),(11,1,7),(12,3,7);
/*!40000 ALTER TABLE `agency_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agency_notes`
--

DROP TABLE IF EXISTS `agency_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency_notes` (
  `agency_notes_id` int NOT NULL AUTO_INCREMENT,
  `notes` text,
  `user_id` int NOT NULL,
  `inserted_date` date NOT NULL DEFAULT (curdate()),
  `agency_id` int NOT NULL,
  PRIMARY KEY (`agency_notes_id`),
  KEY `agency_notes_users_idx` (`user_id`),
  KEY `agency_notes_client_idx` (`agency_id`),
  CONSTRAINT `agency_notes_client` FOREIGN KEY (`agency_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `agency_notes_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_notes`
--

LOCK TABLES `agency_notes` WRITE;
/*!40000 ALTER TABLE `agency_notes` DISABLE KEYS */;
INSERT INTO `agency_notes` VALUES (1,'test client 6',7,'0000-00-00',1),(2,'test 30 updated1',7,'2020-06-12',30),(3,'test 29 update',7,'2020-06-12',29),(4,'fgfg',7,'2020-06-13',31),(5,'df',7,'2020-06-16',2);
/*!40000 ALTER TABLE `agency_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agency_quick_info`
--

DROP TABLE IF EXISTS `agency_quick_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agency_quick_info` (
  `agency_quick_info_id` int NOT NULL AUTO_INCREMENT,
  `client_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`agency_quick_info_id`),
  KEY `client_id` (`client_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `agency_quick_info_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `agency_quick_info_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agency_quick_info`
--

LOCK TABLES `agency_quick_info` WRITE;
/*!40000 ALTER TABLE `agency_quick_info` DISABLE KEYS */;
INSERT INTO `agency_quick_info` VALUES (1,27,7),(3,28,7),(4,29,7);
/*!40000 ALTER TABLE `agency_quick_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_comm_notes`
--

DROP TABLE IF EXISTS `client_comm_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_comm_notes` (
  `client_comm_notes_id` int NOT NULL AUTO_INCREMENT,
  `client_comm_notes_desc` text,
  `client_id` int DEFAULT NULL,
  PRIMARY KEY (`client_comm_notes_id`),
  KEY `client_id_comm_notes_idx` (`client_id`),
  CONSTRAINT `client_id_comm_notes` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_comm_notes`
--

LOCK TABLES `client_comm_notes` WRITE;
/*!40000 ALTER TABLE `client_comm_notes` DISABLE KEYS */;
INSERT INTO `client_comm_notes` VALUES (1,'note 1',22),(2,'test',27),(3,'note 2',6);
/*!40000 ALTER TABLE `client_comm_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `client_id` int NOT NULL AUTO_INCREMENT,
  `agency_name` text NOT NULL,
  `address` text,
  `phone` varchar(32) DEFAULT NULL,
  `fax` varchar(32) DEFAULT NULL,
  `npi` text,
  `ptan` text,
  `tax_id` text,
  `comments` text,
  `primary_biller` varchar(45) DEFAULT NULL,
  `primary_accountant` varchar(45) DEFAULT NULL,
  `secondary_biller` varchar(45) DEFAULT NULL,
  `secondary_accountant` varchar(45) DEFAULT NULL,
  `deleted` tinyint DEFAULT '0',
  `dde_login` varchar(128) DEFAULT NULL,
  `dde_password` varchar(128) DEFAULT NULL,
  `software_login` varchar(128) DEFAULT NULL,
  `software_password` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'client6','dddddd','93459','1',NULL,'21','2','2','fewfw','biller 6','acc6',NULL,0,NULL,NULL,NULL,NULL),(2,'client222','aaa','1','2','21','2','1','fewfw','biller 2','acc2',NULL,NULL,0,'dfgdfg','fdfgdf','dfgdfg','dfg'),(3,'client 3 edited','Rruga Bardhyl Rruga Bardhyl\nTirane 1001 Rruga Bardhyl\nAlbania Rruga Bardhyl Rruga Bardhyl','1','2','21','2','1','fewfw','biller 3','acc3',NULL,NULL,0,NULL,NULL,NULL,NULL),(4,'client4','fgfdg','1','2','21','2','32','fewfw','biller 4','acc4',NULL,NULL,0,NULL,NULL,NULL,NULL),(5,'client5','fgfdg','1','2','21','2','32','fewfw','biller 5','acc5',NULL,NULL,0,NULL,NULL,NULL,NULL),(6,'client6 edit','fgfdg','1','2','21','2','1','fewfw','biller 6','acc6',NULL,NULL,1,'11','11','11','11'),(7,'client7','fgfdg','1','2','21','2','32','fewfw','biller 7','acc7',NULL,NULL,1,NULL,NULL,NULL,NULL),(8,'client7','fgfdg','1','2','21','2','0','fewfw',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL),(13,'fgfd','gdf','','','0','0','0','','fdg','dfg','','',1,NULL,NULL,NULL,NULL),(14,'dfgd','fdg','','','0','0','0','','dfg','dfg','','',1,NULL,NULL,NULL,NULL),(15,'agency test','rtetre','','','0','0','0','','ertret','retretretre','','',1,NULL,NULL,NULL,NULL),(16,'dsfdsf','sdfsdfsd','','','0','0','0','','sdfsd','dsfsdf','','',1,NULL,NULL,NULL,NULL),(17,'dfsdf','sdfdsfs','','','0','0','0','','sdfsdf','sdfsdfs','','',1,NULL,NULL,NULL,NULL),(18,'rtretret','retretre','','','0','0','0','','erterter','ertrete','','',1,NULL,NULL,NULL,NULL),(19,'nensi 44','nensi 44','','','0','0','0','','nensi 44','nensi 44','','',1,NULL,NULL,NULL,NULL),(20,'test1','etst1','','','0.0','0.0','0.0','','aaa','test','','',1,NULL,NULL,NULL,NULL),(21,'test','1212','','','0.0','0.0','0.0','','453','435','','',1,NULL,NULL,NULL,NULL),(22,'ewrwerewr','fdfgf','88888','','0.0','0.0','88888','','564','5645','','',1,NULL,NULL,NULL,NULL),(23,'rrrr','jkbfskj','','','0.0','0.0','0.0','','fgdg','rrrrrrrr','','',1,NULL,NULL,NULL,NULL),(24,'ngdfkjgfdjk','frgre','','','0.0','0.0','0.0','','rgerg','rggrg','','',1,NULL,NULL,NULL,NULL),(25,'kv f,dfd','fvdfvdf','','','0.0','0.0','0.0','','vfdfvfd','fvdfvd','','',1,NULL,NULL,NULL,NULL),(26,'hfhhf','hfhhf','','','0.0','0.0','0.0','','jnvfj','hfbvfh','','',1,NULL,NULL,NULL,NULL),(27,'nensi',' es s','j','','0.0','0.0','0.0','','gfg','fdgfd','','',1,NULL,NULL,NULL,NULL),(28,'nensi12','nensi','jjj','','0.0','0.0','0.0','','kjnfkjwf','nensi','','',1,NULL,NULL,NULL,NULL),(29,'jvdskjvskdjv','dfgfdgdf','','','0.0','0.0','0.0','','fdgdfgd','dfgdfgfd','','',0,NULL,NULL,NULL,NULL),(30,'sssss','a','','','0.0','0.0','0.0','','sdfsddsfsd','sdfdsfsd','','',0,'tessss','testgrgrgrgrgrgrgrrgrgrgrgr','test',NULL),(31,'testttt','test	','te','','0.0','0.0','0.0','','','','','',0,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_persons`
--

DROP TABLE IF EXISTS `contact_persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_persons` (
  `contact_id` int NOT NULL AUTO_INCREMENT,
  `contact_name` text,
  `phone_number` varchar(16) DEFAULT NULL,
  `client_id` int NOT NULL,
  `primary_contact` tinyint DEFAULT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `client_contact_idx` (`client_id`),
  CONSTRAINT `client_contact` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_persons`
--

LOCK TABLES `contact_persons` WRITE;
/*!40000 ALTER TABLE `contact_persons` DISABLE KEYS */;
INSERT INTO `contact_persons` VALUES (1,'contact 1','89764949',1,1),(2,'sdfsdfsdfsd','dsfsdfsfsd',17,1),(3,'testttt','44654656',3,0),(4,'retertert','',18,1),(5,'nensi 44','',19,1),(6,'test','',20,1),(7,'w5435','',21,1),(8,'564','',22,1),(9,'rgre','',23,1),(10,'rwgrgr','',24,1),(11,'vdvfd','',25,1),(12,'ffvdf','',26,1),(13,'ff','',27,1),(14,'jfdbfjbfdk','',28,1),(15,'fgdfgfdgfd','',29,1),(16,'dsfsfdsfds','',30,1),(17,'effrgerger','',31,1);
/*!40000 ALTER TABLE `contact_persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insurances`
--

DROP TABLE IF EXISTS `insurances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insurances` (
  `insurance_id` int NOT NULL AUTO_INCREMENT,
  `insurance_name` text,
  `instructions` text,
  `user_id` int NOT NULL,
  `agency_id` int NOT NULL,
  PRIMARY KEY (`insurance_id`),
  KEY `insurance_employee_idx` (`user_id`),
  KEY `insurance_agency_idx` (`agency_id`),
  CONSTRAINT `insurance_agency` FOREIGN KEY (`agency_id`) REFERENCES `clients` (`client_id`),
  CONSTRAINT `insurance_employee` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insurances`
--

LOCK TABLES `insurances` WRITE;
/*!40000 ALTER TABLE `insurances` DISABLE KEYS */;
INSERT INTO `insurances` VALUES (1,'name1','instr1',1,22),(2,'updated','updated',1,21),(3,'fgdf updated','fgfdg updated',2,30),(4,'666','666',2,27),(5,'updated 5','updated 5',7,30),(6,'yay 6','yay 6',7,31),(8,'updated 8','updated 8',7,27),(9,'jjj','jjj',7,28);
/*!40000 ALTER TABLE `insurances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `full_name` text NOT NULL,
  `password` text NOT NULL,
  `group_access` varchar(32) DEFAULT NULL,
  `user_status` varchar(32) DEFAULT 'Active',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Admin Admin','JXpFYEEyy2q5i7xLwS616A==','Admin','Active'),(2,'User User','JXpFYEEyy2q5i7xLwS616A==','Employee','Active'),(3,'user3','JXpFYEEyy2q5i7xLwS616A==','Employee','Active'),(4,'user4','JXpFYEEyy2q5i7xLwS616A==','Employee','Active'),(7,'nensi','gAA5FYjDg88Vzn6UsDNOIQ==','Employee','Active');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-16 22:39:02
