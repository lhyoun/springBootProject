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
-- Table structure for table `battle`
--

DROP TABLE IF EXISTS `battle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `battle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `info` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `matchDate` varchar(255) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `requestTeam` int(11) DEFAULT NULL,
  `responseTeam` int(11) DEFAULT NULL,
  `users` int(11) DEFAULT NULL,
  `users2` int(11) DEFAULT NULL,
  `winerTeam_id` int(11) DEFAULT NULL,
  `createDate` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs3wmuo8ub6k55mg547y7r435r` (`requestTeam`),
  KEY `FKg4mpo1d1qn2j33k3wmw8l5i45` (`responseTeam`),
  KEY `FKfuibsrxjcf3xpfb4p03pglr86` (`users`),
  KEY `FKhmfpeahev64t1l4m05ve0y03n` (`users2`),
  KEY `FKcvr1d3jwhwdsdpv8iwlhex5rl` (`winerTeam_id`),
  CONSTRAINT `FKcvr1d3jwhwdsdpv8iwlhex5rl` FOREIGN KEY (`winerTeam_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKfuibsrxjcf3xpfb4p03pglr86` FOREIGN KEY (`users`) REFERENCES `teaminfo` (`id`),
  CONSTRAINT `FKg4mpo1d1qn2j33k3wmw8l5i45` FOREIGN KEY (`responseTeam`) REFERENCES `team` (`id`),
  CONSTRAINT `FKhmfpeahev64t1l4m05ve0y03n` FOREIGN KEY (`users2`) REFERENCES `teaminfo` (`id`),
  CONSTRAINT `FKs3wmuo8ub6k55mg547y7r435r` FOREIGN KEY (`requestTeam`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `battle`
--

LOCK TABLES `battle` WRITE;
/*!40000 ALTER TABLE `battle` DISABLE KEYS */;
INSERT INTO `battle` VALUES (1,'매너게임 좀','창원 시민생활체육관','2020-12-25 오후3시',2,4,1,1,3,NULL,'2020-11-09 09:36:49.030000'),(2,'','','',1,1,4,2,NULL,NULL,'2020-11-09 10:08:43.529000'),(3,NULL,'창원시민생활체육관','2020-11-11 오후 3시',2,4,1,4,5,NULL,'2020-11-09 11:12:37.328000'),(4,NULL,'부산 파이낸스센터','2020-12-25 오후2시',1,4,2,6,NULL,NULL,'2020-11-09 12:31:15.224000'),(5,'매너게임 합시다\n심판ㄴ ㄴㄴ','부산파이낸스셑너	','202-12*-25 오후2시',1,1,3,7,NULL,NULL,'2020-11-09 12:53:04.861000'),(6,NULL,'지역적고','날짜적고',2,1,4,8,9,NULL,'2020-11-09 13:45:19.303000'),(7,NULL,'it센터 402','2020 - 11 -11 ',2,4,1,10,11,1,'2020-11-09 14:09:37.070000');
/*!40000 ALTER TABLE `battle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-09 14:22:34
