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
-- Table structure for table `teaminfo`
--

DROP TABLE IF EXISTS `teaminfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teaminfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) DEFAULT NULL,
  `user1_id` int(11) DEFAULT NULL,
  `user10_id` int(11) DEFAULT NULL,
  `user11_id` int(11) DEFAULT NULL,
  `user2_id` int(11) DEFAULT NULL,
  `user3_id` int(11) DEFAULT NULL,
  `user4_id` int(11) DEFAULT NULL,
  `user5_id` int(11) DEFAULT NULL,
  `user6_id` int(11) DEFAULT NULL,
  `user7_id` int(11) DEFAULT NULL,
  `user8_id` int(11) DEFAULT NULL,
  `user9_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm5h13sm12r7rwlch3151u4drs` (`team_id`),
  KEY `FKodlkgy7qk54o0wt0ji16vsjit` (`user1_id`),
  KEY `FKi9o32hoc11x351bh8jdo6345g` (`user10_id`),
  KEY `FKdd9xbhkj8uacxvhtao31043x2` (`user11_id`),
  KEY `FKst8kh8kti7ylqhpmcin9euiix` (`user2_id`),
  KEY `FK71w5qaaveogne6wrg7ivtvuor` (`user3_id`),
  KEY `FKm5xl5ralmb4t0av2rjug1u59a` (`user4_id`),
  KEY `FK4yl9angkkcjvna6155eipchu1` (`user5_id`),
  KEY `FK3e09wea7auid9sf5m6t1i6ae3` (`user6_id`),
  KEY `FK7m7cl09fbk0os1en0c2tyr7rh` (`user7_id`),
  KEY `FKtcm2gqadaa32ijbakfigyswvu` (`user8_id`),
  KEY `FK6jvaypbrpexe5vcyr9th5310e` (`user9_id`),
  CONSTRAINT `FK3e09wea7auid9sf5m6t1i6ae3` FOREIGN KEY (`user6_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK4yl9angkkcjvna6155eipchu1` FOREIGN KEY (`user5_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK6jvaypbrpexe5vcyr9th5310e` FOREIGN KEY (`user9_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK71w5qaaveogne6wrg7ivtvuor` FOREIGN KEY (`user3_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK7m7cl09fbk0os1en0c2tyr7rh` FOREIGN KEY (`user7_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKdd9xbhkj8uacxvhtao31043x2` FOREIGN KEY (`user11_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKi9o32hoc11x351bh8jdo6345g` FOREIGN KEY (`user10_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKm5h13sm12r7rwlch3151u4drs` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`),
  CONSTRAINT `FKm5xl5ralmb4t0av2rjug1u59a` FOREIGN KEY (`user4_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKodlkgy7qk54o0wt0ji16vsjit` FOREIGN KEY (`user1_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKst8kh8kti7ylqhpmcin9euiix` FOREIGN KEY (`user2_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKtcm2gqadaa32ijbakfigyswvu` FOREIGN KEY (`user8_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teaminfo`
--

LOCK TABLES `teaminfo` WRITE;
/*!40000 ALTER TABLE `teaminfo` DISABLE KEYS */;
INSERT INTO `teaminfo` VALUES (1,4,8,37,44,12,15,16,26,28,32,33,35),(2,1,1,47,48,22,23,24,25,34,36,38,39),(3,1,1,47,48,22,23,24,25,34,36,38,39),(4,4,8,44,37,8,12,15,16,33,32,28,26),(5,1,1,47,48,22,23,24,25,34,36,38,39),(6,4,8,44,33,8,26,35,37,28,12,15,32),(7,1,1,47,48,22,23,24,25,34,36,38,39),(8,1,1,48,47,1,22,23,24,25,34,36,38),(9,4,8,37,44,12,15,16,26,28,32,33,35),(10,4,8,28,26,8,12,15,16,33,32,44,37),(11,1,1,47,48,22,23,24,25,34,36,38,39);
/*!40000 ALTER TABLE `teaminfo` ENABLE KEYS */;
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
