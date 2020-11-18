-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: matching
-- ------------------------------------------------------
-- Server version	5.7.30-log

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
-- Table structure for table `mercenary`
--

DROP TABLE IF EXISTS `mercenary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mercenary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `createDate` datetime(6) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `team_id` int(11) DEFAULT NULL,
  `uesr2_id` int(11) DEFAULT NULL,
  `user1_id` int(11) DEFAULT NULL,
  `user3_id` int(11) DEFAULT NULL,
  `gameDate` varchar(255) DEFAULT NULL,
  `user2_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfa5v30a48j2dgqvv7jraft99d` (`team_id`),
  KEY `FK3flnlp5cdxmvxk0l8hdfjfp0n` (`uesr2_id`),
  KEY `FKec9uf9ervivhcqbkm51ed4a7m` (`user1_id`),
  KEY `FKimbi667o2cardp7rbm7cyc3jq` (`user3_id`),
  KEY `FKo04qjqwbpe05x5rhp3yakl05p` (`user2_id`),
  CONSTRAINT `FK3flnlp5cdxmvxk0l8hdfjfp0n` FOREIGN KEY (`uesr2_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKec9uf9ervivhcqbkm51ed4a7m` FOREIGN KEY (`user1_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfa5v30a48j2dgqvv7jraft99d` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKimbi667o2cardp7rbm7cyc3jq` FOREIGN KEY (`user3_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKo04qjqwbpe05x5rhp3yakl05p` FOREIGN KEY (`user2_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mercenary`
--

LOCK TABLES `mercenary` WRITE;
/*!40000 ALTER TABLE `mercenary` DISABLE KEYS */;
/*!40000 ALTER TABLE `mercenary` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-09 14:22:33
