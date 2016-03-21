-- MySQL dump 10.13  Distrib 5.5.11, for Win64 (x86)
--
-- Host: localhost    Database: topweb
-- ------------------------------------------------------
-- Server version	5.5.11

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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment` varchar(1024) NOT NULL,
  `poster_id` int(11) NOT NULL,
  `postdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `poster_id` (`poster_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`poster_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'Bienvenido a la nueva red social ',1,'2013-05-12 03:10:32'),(2,'Este es un comentario de rotceh',6,'2013-05-12 03:10:56'),(3,'testeando 1 2 3',4,'2013-05-12 03:11:11'),(4,'testeando 3 2 1',4,'2013-05-12 03:11:16'),(5,'Fright lined dining room - arctic monkeys',3,'2013-05-12 03:11:53'),(6,'Fright lined dining room - arctic monkeys x 2',3,'2013-05-12 03:12:06'),(7,'el mike y la ale se besan',10,'2013-05-14 00:26:14'),(15,'Mostrando la aplicacion',1,'2013-05-14 01:21:41');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relationship`
--

DROP TABLE IF EXISTS `relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relationship` (
  `id1` int(11) NOT NULL,
  `id2` int(11) NOT NULL,
  KEY `id1` (`id1`,`id2`),
  KEY `id2` (`id2`),
  CONSTRAINT `relationship_ibfk_1` FOREIGN KEY (`id1`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relationship_ibfk_2` FOREIGN KEY (`id2`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relationship`
--

LOCK TABLES `relationship` WRITE;
/*!40000 ALTER TABLE `relationship` DISABLE KEYS */;
INSERT INTO `relationship` VALUES (1,3),(1,4),(1,6),(1,7),(3,4),(3,6),(5,1),(5,6),(9,8),(10,1),(10,4),(10,6);
/*!40000 ALTER TABLE `relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(55) NOT NULL,
  `email` varchar(75) NOT NULL,
  `password` varchar(42) NOT NULL,
  `valid` tinyint(1) DEFAULT '1',
  `sex` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1 MALE, 0 FEMALE',
  `lastLogin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'MiichaelD','michael.duarte@hotmail.com','*4C8B3B63DB087B629288A3BF651BAB54EFDA00BA',1,1,'2013-05-15 00:58:02'),(3,'test1','test1@hotmail.com','*FE6E97A9D8ABB51AF3C8A3873100F0E136147F6A',1,1,'2013-05-11 19:52:59'),(4,'test2','test2@hotmail.com','*2C3156F4498A3D3C246A13F055A5B1D4E203F64C',1,0,'2013-05-11 19:53:21'),(5,'test3','test3@hotmail.com','*23C0589D06AA8AB6C8A282A61C543AE78D6C73A8',1,0,'2013-05-11 19:53:37'),(6,'rotceh','rotceh_1203@hotmail.com','*1F9D511664690F7596FEBE9D1B5736544820019A',1,1,'2013-05-11 20:34:18'),(7,'Alexis','alexis.duarte@hotmail.com','*A25C8B47B141EAD275DBB9AA4BD99FE45CFE4CF8',1,1,'2013-05-12 23:19:53'),(8,'biancca','bnnqaa_8@hotmail.es','*955EEC386B4450E3221ABB09CB16F0E9E27766C1',1,0,'2013-05-13 03:07:52'),(9,'doraduarte12','doralena.duarte@hotmail.com','*9DAA56C695C3CD8D69DDAEFC7FED37AE310BC3ED',1,0,'2013-05-13 03:13:06'),(10,'mike_soto','mikeÂ²jfdsj','*DB23B1B65A58790E0B06685EB2EA820AC1A68CBE',1,1,'2013-05-14 01:21:57');
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

-- Dump completed on 2013-05-14 18:14:39
