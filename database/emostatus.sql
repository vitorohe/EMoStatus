-- MySQL dump 10.13  Distrib 5.5.34, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: emostatusdb
-- ------------------------------------------------------
-- Server version	5.5.34-0ubuntu0.13.04.1

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
-- Table structure for table `chunks`
--

DROP TABLE IF EXISTS `chunks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chunks` (
  `id` int(11) NOT NULL,
  `name` varchar(500) NOT NULL,
  `recording_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_chunks_1` (`recording_id`),
  CONSTRAINT `fk_chunks_1` FOREIGN KEY (`recording_id`) REFERENCES `recordings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chunks`
--

LOCK TABLES `chunks` WRITE;
/*!40000 ALTER TABLE `chunks` DISABLE KEYS */;
INSERT INTO `chunks` VALUES (70,'../data/trytalking1/output001.wav',0),(71,'../data/trytalking1/output002.wav',0),(72,'../data/trytalking1/output003.wav',0),(73,'../data/trytalking1/output004.wav',0),(74,'../data/trytalking1/output005.wav',0),(75,'../data/trytalking1/output006.wav',0),(76,'../data/trytalking1/output007.wav',0),(77,'../data/trytalking1/output008.wav',0),(78,'../data/trytalking1/output009.wav',0),(79,'../data/trytalking1/output010.wav',0),(80,'../data/trytalking1/output011.wav',0),(81,'../data/trytalking1/output012.wav',0),(82,'../data/trytalking1/output013.wav',0),(83,'../data/trytalking1/output014.wav',0),(84,'../data/trytalking1/output015.wav',0),(85,'../data/trytalking1/output016.wav',0),(86,'../data/trytalking1/output017.wav',0),(87,'../data/trytalking1/output018.wav',0),(88,'../data/trytalking1/output019.wav',0),(89,'../data/trytalking1/output020.wav',0),(90,'../data/trytalking1/output021.wav',0),(91,'../data/trytalking1/output022.wav',0),(92,'../data/trytalking1/output023.wav',0),(93,'../data/trytalking1/output024.wav',0),(94,'../data/trytalking1/output025.wav',0),(95,'../data/trytalking1/output026.wav',0),(96,'../data/trytalking1/output027.wav',0),(97,'../data/trytalking1/output028.wav',0),(98,'../data/trytalking1/output029.wav',0);
/*!40000 ALTER TABLE `chunks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email_alert_monitored_users`
--

DROP TABLE IF EXISTS `email_alert_monitored_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_alert_monitored_users` (
  `monitored_user_id` int(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`monitored_user_id`),
  KEY `fk_email_alert_monitored_users` (`monitored_user_id`),
  CONSTRAINT `fk_email_alert_monitored_users` FOREIGN KEY (`monitored_user_id`) REFERENCES `monitored_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_alert_monitored_users`
--

LOCK TABLES `email_alert_monitored_users` WRITE;
/*!40000 ALTER TABLE `email_alert_monitored_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_alert_monitored_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emotions`
--

DROP TABLE IF EXISTS `emotions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emotions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `first_class_group_id` int(11) NOT NULL,
  `second_class_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_emotions_1` (`first_class_group_id`),
  KEY `fk_emotions_2` (`second_class_group_id`),
  CONSTRAINT `fk_emotions_1` FOREIGN KEY (`first_class_group_id`) REFERENCES `first_classification_groups` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_emotions_2` FOREIGN KEY (`second_class_group_id`) REFERENCES `second_classification_groups` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emotions`
--

LOCK TABLES `emotions` WRITE;
/*!40000 ALTER TABLE `emotions` DISABLE KEYS */;
INSERT INTO `emotions` VALUES (1,'anger',1,1),(2,'fear',2,1),(3,'happiness',1,2),(4,'surprise',1,2),(5,'neutral',3,4),(6,'sadness',3,3);
/*!40000 ALTER TABLE `emotions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `first_classification_groups`
--

DROP TABLE IF EXISTS `first_classification_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `first_classification_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `first_classification_groups`
--

LOCK TABLES `first_classification_groups` WRITE;
/*!40000 ALTER TABLE `first_classification_groups` DISABLE KEYS */;
INSERT INTO `first_classification_groups` VALUES (1,'emphatic','Emotions more emphatic'),(2,'fear','Emotion of fear'),(3,'soft','Emotions that are softer');
/*!40000 ALTER TABLE `first_classification_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monitored_users`
--

DROP TABLE IF EXISTS `monitored_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monitored_users` (
  `id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `description` mediumtext NOT NULL,
  `monitoring_activated` tinyint(1) NOT NULL,
  `monit_skype_activated` tinyint(1) NOT NULL,
  `monit_record_activated` tinyint(1) NOT NULL,
  `alert_sms_activated` tinyint(1) NOT NULL,
  `alert_email_activated` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monitored_users`
--

LOCK TABLES `monitored_users` WRITE;
/*!40000 ALTER TABLE `monitored_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `monitored_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monitoring_modes`
--

DROP TABLE IF EXISTS `monitoring_modes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monitoring_modes` (
  `id` int(11) NOT NULL,
  `mode` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monitoring_modes`
--

LOCK TABLES `monitoring_modes` WRITE;
/*!40000 ALTER TABLE `monitoring_modes` DISABLE KEYS */;
INSERT INTO `monitoring_modes` VALUES (1,'skype'),(2,'mic_any_moment');
/*!40000 ALTER TABLE `monitoring_modes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `monitorings`
--

DROP TABLE IF EXISTS `monitorings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monitorings` (
  `user_id` int(11) NOT NULL,
  `monitorized_user_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`monitorized_user_id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_monitorized_user_id` (`monitorized_user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_monitorized_user_id` FOREIGN KEY (`monitorized_user_id`) REFERENCES `monitored_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monitorings`
--

LOCK TABLES `monitorings` WRITE;
/*!40000 ALTER TABLE `monitorings` DISABLE KEYS */;
/*!40000 ALTER TABLE `monitorings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recordings`
--

DROP TABLE IF EXISTS `recordings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recordings` (
  `id` int(11) NOT NULL,
  `path` varchar(500) NOT NULL,
  `date` datetime NOT NULL,
  `duration` int(11) DEFAULT NULL,
  `monitoring_mode` int(11) DEFAULT NULL,
  `status_process` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_recordings_1` (`monitoring_mode`),
  CONSTRAINT `fk_recordings_1` FOREIGN KEY (`monitoring_mode`) REFERENCES `monitoring_modes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recordings`
--

LOCK TABLES `recordings` WRITE;
/*!40000 ALTER TABLE `recordings` DISABLE KEYS */;
INSERT INTO `recordings` VALUES (0,'/home/vito/Documentos/Memoria/pruebas/sox/trytalking1.wav','2014-01-22 13:16:19',180,2,'WAITING');
/*!40000 ALTER TABLE `recordings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `second_classification_groups`
--

DROP TABLE IF EXISTS `second_classification_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `second_classification_groups` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(45) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `second_classification_groups`
--

LOCK TABLES `second_classification_groups` WRITE;
/*!40000 ALTER TABLE `second_classification_groups` DISABLE KEYS */;
INSERT INTO `second_classification_groups` VALUES (1,'arousalpos-valenceneg','Emotions with positive arousal and negative valence'),(2,'arousalpos-valencepos','Emotions with positive arousal and positive valence'),(3,'arousalneg-valenceneg','Emotions with negative arousal and negative valence'),(4,'arousalneg-valencepos','Emotions with negative arousal and positive valence');
/*!40000 ALTER TABLE `second_classification_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sms_alert_monitored_users`
--

DROP TABLE IF EXISTS `sms_alert_monitored_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sms_alert_monitored_users` (
  `monitored_user_id` int(11) NOT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`monitored_user_id`),
  KEY `fk_sms_alert_monitored_users` (`monitored_user_id`),
  CONSTRAINT `fk_sms_alert_monitored_users` FOREIGN KEY (`monitored_user_id`) REFERENCES `monitored_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sms_alert_monitored_users`
--

LOCK TABLES `sms_alert_monitored_users` WRITE;
/*!40000 ALTER TABLE `sms_alert_monitored_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `sms_alert_monitored_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `monitoring_mode` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `emotion_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_monitoring_mode` (`monitoring_mode`),
  KEY `fk_emotion_id` (`emotion_id`),
  CONSTRAINT `fk_monitoring_mode` FOREIGN KEY (`monitoring_mode`) REFERENCES `monitoring_modes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_emotion_id` FOREIGN KEY (`emotion_id`) REFERENCES `emotions` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status_monitored_users`
--

DROP TABLE IF EXISTS `status_monitored_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status_monitored_users` (
  `status_id` int(11) NOT NULL,
  `monitored_user_id` int(11) NOT NULL,
  PRIMARY KEY (`status_id`,`monitored_user_id`),
  KEY `fk_status_id` (`status_id`),
  KEY `fk_monitorized_user_id` (`monitored_user_id`),
  CONSTRAINT `fk_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_monitorized_user_id0` FOREIGN KEY (`monitored_user_id`) REFERENCES `monitored_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status_monitored_users`
--

LOCK TABLES `status_monitored_users` WRITE;
/*!40000 ALTER TABLE `status_monitored_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `status_monitored_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `full_name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
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

-- Dump completed on 2014-01-29 17:36:38
