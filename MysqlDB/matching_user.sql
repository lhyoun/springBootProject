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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `image` longtext,
  `joindate` datetime(6) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `loginid` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `teams_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7n0ddjsbkg4u86g5kx5plh4ps` (`loginid`),
  UNIQUE KEY `UK_cdd273rg61diywe30f4k0t5mo` (`nickname`),
  KEY `FKgda3y8ywcqbvi97cncjkxgbc9` (`teams_id`),
  CONSTRAINT `FKgda3y8ywcqbvi97cncjkxgbc9` FOREIGN KEY (`teams_id`) REFERENCES `team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'kms@com','iamge4.jpg','2020-11-08 12:58:10.840000','부산진구','admin01','재현잉','1234','010123456','공격수','USER','한재현',1),(2,'kms@com','iamge3.jpg','2020-11-08 12:58:17.844000','해운대구','admin02','반달밤','1234','01024581254','수비수','USER','이하윤',2),(3,'dhfjabq123@com','iamge5.jpg','2020-11-08 12:58:22.047000','부산진구','admin03','냄새제로','1234','01054742218','수비수','USER','강나래',3),(4,'kms@com','iamge2.jpg','2020-11-08 12:58:26.635000','영도구','admin04','나래나래','1234','01024581254','골키퍼','USER','고명수',5),(5,'kms@com','iamge6.jpg','2020-11-08 12:58:34.660000','부산진구','admin05','포테이토','1234','01035548756','골키퍼','USER','이현지',7),(6,'kms@com','iamge7.jpg','2020-11-08 12:58:38.819000','해운대구','admin06','핫바','1234','01024581254','수비수','USER','문주완',8),(7,'kms@com','iamge8.jpg','2020-11-08 12:58:42.787000','해운대구','admin07','맛살','1234','01035548756','골키퍼','USER','김소연',9),(8,'dhfjabq123@com','iamge9.jpg','2020-11-08 12:58:46.686000','부산진구','admin08','하윤하윤','1234','01035548756','공격수','USER','이만세',4),(9,'dhfjabq123@com','iamge10.jpg','2020-11-08 12:58:50.840000','해운대구','admin09','보살','1234','01024581254','수비수','USER','박건희',10),(10,'dhfjabq123@com','iamge11.jpg','2020-11-08 12:58:57.146000','부산진구','admin10','찐빵','1234','01035548756','수비수','USER','제준서',2),(11,'kms@com','iamge12.jpg','2020-11-08 12:59:00.805000','부산진구','admin11','호떡','1234','01035548756','골키퍼','USER','정수미',2),(12,'kms@com','iamge13.jpg','2020-11-08 12:59:04.292000','부산진구','admin12','호빵','1234','01024581254','수비수','USER','이동우',4),(13,'kms@com','iamge14.jpg','2020-11-08 12:59:08.186000','해운대구','admin13','카프리스','1234','01074474452','공격수','USER','노희정',5),(14,'dhfjabq123@com','iamge15.jpg','2020-11-08 12:59:12.212000','해운대구','admin14','메이리프','1234','01074474452','수비수','USER','박경희',5),(15,'khg@com','image1.jpg','2020-11-08 12:59:16.341000','부산진구','admin15','핫도그','1234','01024581254','골키퍼','USER','이태규',4),(16,'khg@com','이미지 001.png','2020-11-08 12:59:20.454000','해운대구','admin16','리엑트','1234','01039367371','수비수','USER','정무곤',4),(17,'khg@com','이미지 002.png','2020-11-08 12:59:23.892000','영도구','admin17','스프링부트','1234','01074474452','공격수','USER','허준석',6),(18,'khg@com','이미지 003.png','2020-11-08 12:59:27.482000','영도구','admin18','떡볶이','1234','01024581254','공격수','USER','김재학',7),(19,'jahwqh@com','이미지 004.png','2020-11-08 12:59:31.171000','해운대구','admin19','피자','1234','01074474452','공격수','USER','곽현갑',7),(20,'dhfjabq123@com','이미지 005.png','2020-11-08 12:59:36.157000','영도구','admin20','치킨','1234','01074474452','공격수','USER','전재현',8),(21,'jahwqh@com','이미지 006.png','2020-11-08 12:59:44.104000','해운대구','admin21','탕수육','1234','01039367371','공격수','USER','카사딘',8),(22,'dhfjabq123@com','이미지 007.png','2020-11-08 12:59:47.908000','해운대구','admin22','토스트','1234','01064222642','수비수','USER','말자하',1),(23,'khg@com','이미지 008.png','2020-11-08 12:59:54.244000','영도구','admin23','재학이','1234','01039367371','수비수','USER','제라스',1),(24,'khg@com','이미지 009.png','2020-11-08 12:59:58.852000','영도구','admin24','나문주완아니다','1234','01039367371','수비수','USER','브랜드',1),(25,'khg@com','이미지 010.png','2020-11-08 13:00:03.141000','영도구','admin25','싸움독학','1234','01039367371','공격수','USER','누누',1),(26,'khg@com','이미지 011.png','2020-11-08 13:00:07.842000','해운대구','admin26','일렉시드','1234','01039367371','미드필더','USER','사일러스',4),(27,'khg@com','이미지 012.png','2020-11-08 13:00:11.836000','영도구','admin27','에스원','1234','01022487756','미드필더','USER','애쉬',2),(28,'jahwqh@com','이미지 013.png','2020-11-08 13:00:15.527000','남구','admin28','짱구','1234','01074474452','공격수','USER','베이가',4),(29,'jahwqh@com','이미지 014.png','2020-11-08 13:00:19.512000','해운대구','admin29','철구','1234','01022487756','미드필더','USER','릴리아',9),(30,'jahwqh@com','이미지 015.png','2020-11-08 13:00:24.227000','남구','admin30','소룡이','1234','01022487756','미드필더','USER','세라핀',2),(31,'dhfjabq123@com','이미지 016.png','2020-11-08 13:00:28.709000','남구','admin31','깨박이','1234','01074474452','공격수','USER','판테온',9),(32,'jahwqh@com','이미지 017.png','2020-11-08 13:00:32.815000','남구','admin32','요구르트','1234','01022487756','미드필더','USER','빅토르',4),(33,'jahwqh@com','이미지 018.png','2020-11-08 13:00:37.779000','남구','admin33','햄벅','1234','01022487756','미드필더','USER','아칼리',4),(34,'dhfjabq123@com','이미지 019.png','2020-11-08 13:00:41.694000','해운대구','admin34','최악의세대','1234','01022487756','미드필더','USER','레넥톤',1),(35,'ggjqj@com','이미지 020.png','2020-11-08 13:00:46.683000','남구','admin35','옥수수','1234','01074474452','미드필더','USER','가렌',4),(36,'dhqbc@com','이미지 021.png','2020-11-08 13:00:50.786000','서구','admin36','수염차','1234','01074474452','미드필더','USER','요릭',1),(37,'ggjqj@com','이미지 022.png','2020-11-08 13:00:55.533000','남구','admin37','헛개','1234','01054742218','미드필더','USER','하이머',4),(38,'dhqbc@com','이미지 023.png','2020-11-08 13:00:59.799000','북구','admin38','매실','1234','01022487756','미드필더','USER','애니',1),(39,'ggjqj@com','이미지 024.png','2020-11-08 13:01:03.601000','서구','admin39','라흐마니노프','1234','01022487756','골키퍼','USER','알리스타',1),(40,'dhqbc@com','이미지 025.png','2020-11-08 13:01:12.723000','북구','admin40','까꿍','1234','01022487756','골키퍼','USER','노틸러스',3),(41,'ggjqj@com','이미지 026.png','2020-11-08 13:01:17.375000','서구','admin41','메이플','1234','01054742218','골키퍼','USER','쓰레쉬',10),(42,'dhqbc@com','이미지 027.png','2020-11-08 13:01:21.666000','북구','admin42','타락파워전사','1234','01044558876','골키퍼','USER','레오나',3),(43,'ggjqj@com','이미지 028.png','2020-11-08 13:01:25.832000','북구','admin43','푸른나무','1234','01044558876','골키퍼','USER','마오카이',5),(44,'dhqbc@com','image1.jpg','2020-11-08 13:01:29.826000','북구','admin44','아시안느','1234','01044558876','미드필더','USER','야스오',4),(45,'dhqbc@com','iamge4.jpg','2020-11-08 13:01:35.090000','서구','admin45','믹스골렘','1234','01044558876','미드필더','USER','요네',3),(46,'ggjqj@com','iamge12.jpg','2020-11-08 13:01:39.733000','서구','admin46','주황버섯','1234','01054742218','미드필더','USER','럭스',3),(47,'ggjqj@com','iamge11.jpg','2020-11-08 13:01:43.710000','서구','admin47','자쿰','1234','01044558876','미드필더','USER','페이커',1),(48,'dhqbc@com','iamge13.jpg','2020-11-08 13:01:47.565000','서구','admin48','거북선','1234','01054742218','미드필더','USER','너구리',1),(49,'dhqbc@com','iamge15.jpg','2020-11-08 13:01:52.232000','북구','admin49','마린','1234','01044558876','미드필더','USER','허수',3),(50,'dhqbc@com','이미지 026.png','2020-11-08 13:01:57.469000','북구','admin50','오스트랄로피테쿠스','1234','01044558876','미드필더','USER','쇼메이커',5),(51,'aaa@naver.com','default.png','2020-11-09 14:04:25.147000','부산광역시서면','hayoun01','nick1','1234','01050239050','공격','USER','1234',1),(52,'aaa@naver2.com','21dd2b7d-0765-439a-82e0-7315ca4cde3e.jpg','2020-11-09 14:05:54.468000','busan','hayoun02','nick02','1234','111','수비수','USER','name',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
