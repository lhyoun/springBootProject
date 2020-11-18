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
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `explaintation` varchar(255) NOT NULL,
  `image` longtext,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `owner_id` int(11) DEFAULT NULL,
  `score_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qp2b64w1p994jswu2fgfqa5yd` (`name`),
  KEY `FKif9l7ruhaaeweoapbmg1ygjqq` (`owner_id`),
  KEY `FKqcxe2xy4lt28t82mlnsu8k9xl` (`score_id`),
  CONSTRAINT `FKif9l7ruhaaeweoapbmg1ygjqq` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKqcxe2xy4lt28t82mlnsu8k9xl` FOREIGN KEY (`score_id`) REFERENCES `score` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'즐축, 매너게임, 20대 위주','팀이름 001.png','부산','부산IT교육센터',1,1),(2,'열축,수다방,재미있음','팀이름 002.png','부산','LCK',2,2),(3,'주1회경기,심판보유','팀이름 003.png','부산','402호',3,3),(4,'축사모,축구에 미치자','팀이름 004.png','부산','만세와아이들',8,4),(5,'친목,정기모임','팀이름 005.png','부산','라떼라떼',4,5),(6,'인싸, 인싸용어필수','팀이름 006.png','부산','FC포식베이가',17,6),(7,'잡담가능, 하지만적당한잡담만','팀이름 007.png','부산','FC호로록',5,7),(8,'정보공유,빅데이터아는사람만','팀이름 008.png','부산','FC빅데이터',6,8),(9,'스프링쉬운사람만오셈','팀이름 009.png','부산','스프링이제일쉬웠어요',7,9),(10,'자바마스터,가르쳐주기도함','팀이름 010.png','부산','자바보다쉬운건없어요',9,10);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
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
