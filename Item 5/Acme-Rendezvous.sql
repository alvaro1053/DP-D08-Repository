start transaction;

drop database if exists `Acme-Rendezvous`;
create database `Acme-Rendezvous`;

use `Acme-Rendezvous`;

drop user 'acme-user'@'%';

drop user 'acme-manager'@'%';

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';


grant select, insert, update, delete 
	on `Acme-Rendezvous`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter, 
	create temporary tables, lock tables, create view, create routine,
	alter routine, execute, trigger, show view
	on `Acme-Rendezvous`.* to 'acme-manager'@'%';


-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Rendezvous
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dateBirth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dateBirth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gfgqmtp2f9i5wsojt33xm0uth` (`userAccount_id`),
  CONSTRAINT `FK_gfgqmtp2f9i5wsojt33xm0uth` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (4,0,'','1957-05-22','admintodopoderoso@email.com','Manuel','','Bartual Moreno',3);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `rende_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_ft302xb1b7mw4x0ilisk99iba` (`moment`),
  KEY `FK_si8t5ffwoy04gvo7gxuwt3j1j` (`rende_id`),
  CONSTRAINT `FK_si8t5ffwoy04gvo7gxuwt3j1j` FOREIGN KEY (`rende_id`) REFERENCES `rende` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `rende_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rj67lt8n85hpsty80mg2xbnyu` (`rende_id`),
  KEY `FK_jhvt6d9ap8gxv67ftrmshdfhj` (`user_id`),
  CONSTRAINT `FK_jhvt6d9ap8gxv67ftrmshdfhj` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_rj67lt8n85hpsty80mg2xbnyu` FOREIGN KEY (`rende_id`) REFERENCES `rende` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_replycomment`
--

DROP TABLE IF EXISTS `comment_replycomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_replycomment` (
  `Comment_id` int(11) NOT NULL,
  `repliesComments_id` int(11) NOT NULL,
  UNIQUE KEY `UK_kw590ygahtdyj273cvgxvvw4f` (`repliesComments_id`),
  KEY `FK_96574bjnl4v0u39ax36uchcbf` (`Comment_id`),
  CONSTRAINT `FK_96574bjnl4v0u39ax36uchcbf` FOREIGN KEY (`Comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `FK_kw590ygahtdyj273cvgxvvw4f` FOREIGN KEY (`repliesComments_id`) REFERENCES `replycomment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_replycomment`
--

LOCK TABLES `comment_replycomment` WRITE;
/*!40000 ALTER TABLE `comment_replycomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment_replycomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentform`
--

DROP TABLE IF EXISTS `commentform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commentform` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `rende` int(11) NOT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentform`
--

LOCK TABLES `commentform` WRITE;
/*!40000 ALTER TABLE `commentform` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `question` varchar(255) DEFAULT NULL,
  `rende_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_jqsrv6dqt7ncd24m4bv3hmsdk` (`question`),
  KEY `FK_slm40v2b733netdoytip1yr1s` (`rende_id`),
  CONSTRAINT `FK_slm40v2b733netdoytip1yr1s` FOREIGN KEY (`rende_id`) REFERENCES `rende` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_replyquestion`
--

DROP TABLE IF EXISTS `question_replyquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_replyquestion` (
  `Question_id` int(11) NOT NULL,
  `replyQuestions_id` int(11) NOT NULL,
  UNIQUE KEY `UK_cj5a657q4hex2da7dmscb7l0s` (`replyQuestions_id`),
  KEY `FK_bwcu808n905alirelhjmhynk3` (`Question_id`),
  CONSTRAINT `FK_bwcu808n905alirelhjmhynk3` FOREIGN KEY (`Question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `FK_cj5a657q4hex2da7dmscb7l0s` FOREIGN KEY (`replyQuestions_id`) REFERENCES `replyquestion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_replyquestion`
--

LOCK TABLES `question_replyquestion` WRITE;
/*!40000 ALTER TABLE `question_replyquestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_replyquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rende`
--

DROP TABLE IF EXISTS `rende`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rende` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `adultOnly` bit(1) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isDeleted` bit(1) DEFAULT NULL,
  `isDraft` bit(1) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_j9sx7pfxhq0erd83rcextl68a` (`adultOnly`),
  KEY `FK_kiaix3fbw0gcf6jd3xxl7r3nh` (`user_id`),
  CONSTRAINT `FK_kiaix3fbw0gcf6jd3xxl7r3nh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rende`
--

LOCK TABLES `rende` WRITE;
/*!40000 ALTER TABLE `rende` DISABLE KEYS */;
/*!40000 ALTER TABLE `rende` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rende_question`
--

DROP TABLE IF EXISTS `rende_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rende_question` (
  `Rende_id` int(11) NOT NULL,
  `questions_id` int(11) NOT NULL,
  UNIQUE KEY `UK_1n8k349bftfp6mnwlhkbf1uc2` (`questions_id`),
  KEY `FK_bqb466csbclv1eeqm97556o11` (`Rende_id`),
  CONSTRAINT `FK_bqb466csbclv1eeqm97556o11` FOREIGN KEY (`Rende_id`) REFERENCES `rende` (`id`),
  CONSTRAINT `FK_1n8k349bftfp6mnwlhkbf1uc2` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rende_question`
--

LOCK TABLES `rende_question` WRITE;
/*!40000 ALTER TABLE `rende_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `rende_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rende_rende`
--

DROP TABLE IF EXISTS `rende_rende`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rende_rende` (
  `Rende_id` int(11) NOT NULL,
  `linked_id` int(11) NOT NULL,
  KEY `FK_t8yc7r5ixegmf1by0lqus6srl` (`linked_id`),
  KEY `FK_plv5e8m1dbtaxirwuax9454lh` (`Rende_id`),
  CONSTRAINT `FK_plv5e8m1dbtaxirwuax9454lh` FOREIGN KEY (`Rende_id`) REFERENCES `rende` (`id`),
  CONSTRAINT `FK_t8yc7r5ixegmf1by0lqus6srl` FOREIGN KEY (`linked_id`) REFERENCES `rende` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rende_rende`
--

LOCK TABLES `rende_rende` WRITE;
/*!40000 ALTER TABLE `rende_rende` DISABLE KEYS */;
/*!40000 ALTER TABLE `rende_rende` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rende_user`
--

DROP TABLE IF EXISTS `rende_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rende_user` (
  `Rende_id` int(11) NOT NULL,
  `attendants_id` int(11) NOT NULL,
  KEY `FK_j6naoe7akpiymq111ttl0rljb` (`attendants_id`),
  KEY `FK_fcpnp5lky5afhh75sot1ipevm` (`Rende_id`),
  CONSTRAINT `FK_fcpnp5lky5afhh75sot1ipevm` FOREIGN KEY (`Rende_id`) REFERENCES `rende` (`id`),
  CONSTRAINT `FK_j6naoe7akpiymq111ttl0rljb` FOREIGN KEY (`attendants_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rende_user`
--

LOCK TABLES `rende_user` WRITE;
/*!40000 ALTER TABLE `rende_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `rende_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replycomment`
--

DROP TABLE IF EXISTS `replycomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `replycomment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `reply` varchar(255) DEFAULT NULL,
  `comment_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_k6jnogb7s3c8i0340e40h2dmo` (`comment_id`),
  KEY `FK_453pjccsxoaprm44hxdapmcse` (`user_id`),
  CONSTRAINT `FK_453pjccsxoaprm44hxdapmcse` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_k6jnogb7s3c8i0340e40h2dmo` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replycomment`
--

LOCK TABLES `replycomment` WRITE;
/*!40000 ALTER TABLE `replycomment` DISABLE KEYS */;
/*!40000 ALTER TABLE `replycomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replyquestion`
--

DROP TABLE IF EXISTS `replyquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `replyquestion` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `reply` varchar(255) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_irjy454mgw5i00ycff63ob69s` (`question_id`),
  KEY `FK_7mr8yqmcf599a8xe4onsuff7r` (`user_id`),
  CONSTRAINT `FK_7mr8yqmcf599a8xe4onsuff7r` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_irjy454mgw5i00ycff63ob69s` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replyquestion`
--

LOCK TABLES `replyquestion` WRITE;
/*!40000 ALTER TABLE `replyquestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `replyquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dateBirth` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_rende`
--

DROP TABLE IF EXISTS `user_rende`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_rende` (
  `User_id` int(11) NOT NULL,
  `rSVPS_id` int(11) NOT NULL,
  KEY `FK_a0w7qmvlntdbn6gfkge3mjnhp` (`rSVPS_id`),
  KEY `FK_1d93mbds6603a2cwvs2a4eqvk` (`User_id`),
  CONSTRAINT `FK_1d93mbds6603a2cwvs2a4eqvk` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_a0w7qmvlntdbn6gfkge3mjnhp` FOREIGN KEY (`rSVPS_id`) REFERENCES `rende` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_rende`
--

LOCK TABLES `user_rende` WRITE;
/*!40000 ALTER TABLE `user_rende` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_rende` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (3,0,'21232f297a57a5a743894a0e4a801fc3','admin');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (3,'ADMIN');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-27 16:08:32
