CREATE DATABASE  IF NOT EXISTS `webtoondb` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `webtoondb`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: webtoondb
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `board_comment`
--

DROP TABLE IF EXISTS `board_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_comment` (
  `cm_id` int NOT NULL AUTO_INCREMENT,
  `bd_num` int NOT NULL,
  `cm_writer` varchar(45) NOT NULL,
  `cm_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cm_content` varchar(1000) DEFAULT NULL,
  `cm_like` int DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`cm_id`),
  KEY `fk_board_comment_toon_board1_idx` (`bd_num`),
  CONSTRAINT `fk_board_comment_toon_board` FOREIGN KEY (`bd_num`) REFERENCES `toon_board` (`bd_num`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_comment`
--

LOCK TABLES `board_comment` WRITE;
/*!40000 ALTER TABLE `board_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `board_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_toon`
--

DROP TABLE IF EXISTS `my_toon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_toon` (
  `mt_title` varchar(45) NOT NULL,
  `mt_user` varchar(45) NOT NULL,
  `mt_imgsrc` mediumtext,
  `mt_url` mediumtext,
  PRIMARY KEY (`mt_title`,`mt_user`),
  KEY `fk_my_toon_toon_user_idx` (`mt_user`),
  CONSTRAINT `fk_my_toon_toon_user` FOREIGN KEY (`mt_user`) REFERENCES `toon_user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_toon`
--

LOCK TABLES `my_toon` WRITE;
/*!40000 ALTER TABLE `my_toon` DISABLE KEYS */;
/*!40000 ALTER TABLE `my_toon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toon_board`
--

DROP TABLE IF EXISTS `toon_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `toon_board` (
  `bd_num` int NOT NULL AUTO_INCREMENT,
  `bd_email` varchar(45) NOT NULL,
  `bd_title` varchar(45) NOT NULL,
  `bd_writer` varchar(45) NOT NULL,
  `bd_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bd_view` int DEFAULT NULL,
  `bd_content` mediumtext,
  `bd_img` mediumtext,
  PRIMARY KEY (`bd_num`),
  KEY `fk_toon_board_toon_user1_idx` (`bd_email`),
  CONSTRAINT `fk_toon_board_toon_user` FOREIGN KEY (`bd_email`) REFERENCES `toon_user` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toon_board`
--

LOCK TABLES `toon_board` WRITE;
/*!40000 ALTER TABLE `toon_board` DISABLE KEYS */;
/*!40000 ALTER TABLE `toon_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toon_user`
--

DROP TABLE IF EXISTS `toon_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `toon_user` (
  `email` varchar(45) NOT NULL,
  `nick` varchar(45) NOT NULL,
  `pw` varchar(45) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toon_user`
--

LOCK TABLES `toon_user` WRITE;
/*!40000 ALTER TABLE `toon_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `toon_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `webtoon`
--

DROP TABLE IF EXISTS `webtoon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `webtoon` (
  `no` int NOT NULL AUTO_INCREMENT,
  `day` tinyint(1) NOT NULL DEFAULT '0',
  `title` varchar(45) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `thumb` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `fk_webtoon_my_toon1_idx` (`title`),
  CONSTRAINT `fk_webtoon_my_toon` FOREIGN KEY (`title`) REFERENCES `my_toon` (`mt_title`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=405 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `webtoon`
--

LOCK TABLES `webtoon` WRITE;
/*!40000 ALTER TABLE `webtoon` DISABLE KEYS */;
/*!40000 ALTER TABLE `webtoon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `webtoon_list`
--

DROP TABLE IF EXISTS `webtoon_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `webtoon_list` (
  `id` varchar(10) NOT NULL,
  `num` int NOT NULL AUTO_INCREMENT,
  `thumb` varchar(2000) NOT NULL,
  `title` varchar(30) NOT NULL,
  `url` varchar(255) NOT NULL,
  `day` varchar(10) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `webtoon_list`
--

LOCK TABLES `webtoon_list` WRITE;
/*!40000 ALTER TABLE `webtoon_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `webtoon_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `webtoon_view`
--

DROP TABLE IF EXISTS `webtoon_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `webtoon_view` (
  `num` int NOT NULL AUTO_INCREMENT,
  `img` varchar(2000) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`num`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `webtoon_view`
--

LOCK TABLES `webtoon_view` WRITE;
/*!40000 ALTER TABLE `webtoon_view` DISABLE KEYS */;
/*!40000 ALTER TABLE `webtoon_view` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-07 20:16:01
