CREATE DATABASE  IF NOT EXISTS `step_course_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `step_course_db`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: step_course_db
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `action`
--

DROP TABLE IF EXISTS `action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `action` (
  `id_action` int(11) NOT NULL AUTO_INCREMENT,
  `action_type` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_action`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `action`
--

LOCK TABLES `action` WRITE;
/*!40000 ALTER TABLE `action` DISABLE KEYS */;
INSERT INTO `action` VALUES (1,'getNewStudentJsp'),(2,'getNewTeacherJsp'),(3,'getNewCourseJsp'),(4,'getEditStudentJsp'),(5,'getEditTeacherJsp'),(6,'getEditCourseJsp'),(7,'login'),(8,'register'),(9,'getAllCourse'),(10,'deleteCourse'),(11,'addCourse'),(12,'editCourse'),(13,'getAllStudent'),(14,'deleteStudent'),(15,'addStudent'),(16,'editStudent'),(17,'search'),(18,'getAllTeacher'),(19,'deleteTeacher'),(20,'getTeachersByCourseId'),(21,'addTeacher'),(22,'editTeacher'),(23,'doRegister'),(24,'activate'),(25,'doLogin');
/*!40000 ALTER TABLE `action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course` (
  `id_course` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `desc` text,
  `duration` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_course`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Java','Java SE, Java EE, Web Services, Frameworks',12),(3,'Graphic Design','Adobe Photoshop, Adobe Illustrator, Adobe Indesign, 3Ds Max, Corel draw',12),(4,'Web Development','HTML, CSS, PHP, MySql',12),(6,'Help desk','Help desc',3);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `role_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_MANAGER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_action`
--

DROP TABLE IF EXISTS `role_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role_action` (
  `id_role_action` int(11) NOT NULL AUTO_INCREMENT,
  `id_role` int(11) DEFAULT NULL,
  `id_action` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_role_action`),
  KEY `fk_ra_role_idx` (`id_role`),
  KEY `fk_ra_action_idx` (`id_action`),
  CONSTRAINT `fk_ra_action` FOREIGN KEY (`id_action`) REFERENCES `action` (`id_action`),
  CONSTRAINT `fk_ra_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_action`
--

LOCK TABLES `role_action` WRITE;
/*!40000 ALTER TABLE `role_action` DISABLE KEYS */;
INSERT INTO `role_action` VALUES (4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(12,1,12),(13,1,13),(16,1,16),(17,1,17),(18,1,18),(20,1,20),(22,1,22),(23,1,23),(24,1,24),(25,1,25),(35,2,4),(36,2,5),(37,2,6),(38,2,7),(39,2,8),(40,2,9),(43,2,12),(44,2,13),(47,2,16),(48,2,17),(49,2,18),(51,2,20),(53,2,22),(54,2,23),(55,2,24),(56,2,25);
/*!40000 ALTER TABLE `role_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `id_student` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `id_teacher` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_student`),
  KEY `fk_student_teacher_idx` (`id_teacher`),
  CONSTRAINT `fk_student_teacher` FOREIGN KEY (`id_teacher`) REFERENCES `teacher` (`id_teacher`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Aslan','Ehmedov','1997-01-02',1),(2,'Fatime','Gurbanova','1998-09-26',1),(3,'Elman','Qehremanov','1996-07-11',1),(8,'Eli','Veliyev','1999-11-11',1),(9,'Aslan','Mahmudov','1999-12-12',3),(10,'Ebulfez','Agayev','2100-12-31',5),(11,'Cahangir','Esedov','3010-12-14',7),(13,'Ilhame','Orucova','1989-12-04',6),(14,'Qehreman','Niftullayev','1999-02-02',6);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher` (
  `id_teacher` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `id_course` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_teacher`),
  KEY `fk_teacher_course_idx` (`id_course`),
  CONSTRAINT `fk_teacher_course` FOREIGN KEY (`id_course`) REFERENCES `course` (`id_course`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Coshqun','Huseynov',1),(3,'Ekbber','Aliyev',3),(5,'Ehtiram','Namazov',4),(6,'Elman','Eliyev',6),(7,'Senan','Senanli',4),(8,'Fatime','Umudova',4),(9,'Tofiq','Nazarli',3);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `id_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  KEY `fk_user_role_idx` (`id_role`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'adasd@sdasd.dsd','vVbCn0Lj3SnKul7BpGJNVQ==','0edd947f-868b-47a3-936a-7596c21e31ba',2,1),(7,'cosqunhsynv@gmail.com','uAvFjCmeGmi56SS6uU5uNw==','4fead7f4-e1f6-4522-bff8-69607619ac42',2,1),(13,'testovtest4699@gmail.com','uAvFjCmeGmi56SS6uU5uNw==','4c561bdf-a60d-4b7a-ae84-59e20b46b2d2',1,1);
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

-- Dump completed on 2018-10-13 13:28:21
