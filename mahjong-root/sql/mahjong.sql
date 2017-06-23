-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: mahjong
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `sys_resource`
--

DROP TABLE IF EXISTS `sys_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `update_date` datetime DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `resource_type` varchar(1) NOT NULL,
  `sequence` int(11) DEFAULT NULL,
  `url` varchar(64) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `icon_cls` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l7cuwqf1etsfq9810by7jixkx` (`create_user_id`),
  KEY `FK_5sm902tsmgst8wixjtx60jqhs` (`update_user_id`),
  KEY `FK_bs9ogivaguupf8gjvpklujrao` (`parent_id`),
  CONSTRAINT `FK_5sm902tsmgst8wixjtx60jqhs` FOREIGN KEY (`update_user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_bs9ogivaguupf8gjvpklujrao` FOREIGN KEY (`parent_id`) REFERENCES `sys_resource` (`id`),
  CONSTRAINT `FK_l7cuwqf1etsfq9810by7jixkx` FOREIGN KEY (`create_user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resource`
--

LOCK TABLES `sys_resource` WRITE;
/*!40000 ALTER TABLE `sys_resource` DISABLE KEYS */;
INSERT INTO `sys_resource` VALUES (1,NULL,'\0',NULL,'系统管理','3',9,NULL,NULL,NULL,21,'icon-users'),(2,NULL,'\0',NULL,'用户管理','3',1,'/User',NULL,NULL,1,'icon-users'),(3,NULL,'',NULL,'test','3',1,'/User/Find',NULL,NULL,1,'icon-users'),(4,'2017-05-10 14:44:46','','2017-05-10 14:44:46','操作日志','3',1,'/User/CreateUser',2,2,1,'icon-users'),(5,'2017-05-10 14:44:46','\0','2017-06-16 13:53:09','活动管理','3',10,NULL,2,1,21,'icon-users'),(6,'2017-05-10 14:44:46','\0','2017-05-10 14:44:46','资源管理','3',10,'/Resource',2,2,1,'icon-users'),(7,NULL,'\0','2017-06-13 11:57:40','新增资源','4',1,'/Resource/Create',NULL,1,6,'icon-add'),(8,NULL,'\0','2017-06-13 11:57:35','获取资源树','4',0,'/Resource/LoadTree',NULL,1,6,'icon-tip'),(9,NULL,'\0','2017-06-16 11:08:37','修改资源','4',2,'/Resource/Update',NULL,1,6,'icon-edit'),(10,NULL,'\0',NULL,'用户查询','4',1,'/User/Find',NULL,NULL,2,'icon-search'),(11,NULL,'\0',NULL,'新增用户','4',2,'/User/Create',NULL,NULL,2,'icon-add'),(12,NULL,'\0',NULL,'编辑用户','4',3,'/User/Update',NULL,NULL,2,'icon-edit'),(13,'2017-06-13 11:52:06','\0','2017-06-13 11:52:06','删除用户','4',4,'/User/Delete',1,1,2,'icon-delete'),(14,'2017-06-13 11:53:56','\0','2017-06-13 11:53:56','角色管理','3',2,'/Role',1,1,1,'icon-user'),(15,'2017-06-13 11:54:36','\0','2017-06-13 11:54:36','角色查询','4',1,'/Role/Find',1,1,14,'icon-search'),(16,'2017-06-13 11:55:05','\0','2017-06-13 11:55:05','新增角色','4',2,'/Role/Create',1,1,14,'icon-add'),(17,'2017-06-13 11:55:48','\0','2017-06-13 11:58:17','修改角色','4',3,'/Role/Update',1,1,14,'icon-update'),(18,'2017-06-13 11:57:09','\0','2017-06-13 11:58:24','删除角色','4',4,'/Role/Delete',1,1,14,'icon-delete'),(19,'2017-06-14 17:27:14','\0','2017-06-14 17:27:14','删除资源','4',3,'/Resource/Delete',1,1,6,'icon-delete'),(20,'2017-06-15 15:38:13','\0','2017-06-15 15:38:13','初始化资源','4',5,'/Role/InitRole',13,13,6,NULL),(21,'2017-06-16 11:07:30','\0','2017-06-16 11:07:30','管理后台','4',1,'/',1,1,NULL,NULL),(22,'2017-06-16 13:54:02','\0','2017-06-16 13:54:02','Test','3',1,'/Test',1,1,5,NULL),(23,'2017-06-16 14:40:42','\0','2017-06-16 14:40:42','官方网站','1',1,NULL,1,1,NULL,NULL),(24,'2017-06-16 14:40:57','\0','2017-06-16 14:40:57','支持论坛','1',1,NULL,1,1,NULL,NULL);
/*!40000 ALTER TABLE `sys_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_resource_role`
--

DROP TABLE IF EXISTS `sys_resource_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_resource_role` (
  `resource_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`resource_id`,`role_id`),
  KEY `FK_strueeboeb45v9q6lt5mshij7` (`role_id`),
  KEY `FK_lmkmjbfshkj63mklfef935kdf` (`resource_id`),
  CONSTRAINT `FK_lmkmjbfshkj63mklfef935kdf` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`),
  CONSTRAINT `FK_strueeboeb45v9q6lt5mshij7` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_resource_role`
--

LOCK TABLES `sys_resource_role` WRITE;
/*!40000 ALTER TABLE `sys_resource_role` DISABLE KEYS */;
INSERT INTO `sys_resource_role` VALUES (1,1),(2,1),(3,1),(5,1),(6,1),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(21,1),(22,1),(1,13),(2,13),(10,13),(11,13),(12,13),(13,13),(21,13),(1,14),(14,14),(15,14),(16,14),(17,14),(18,14),(21,14),(1,15),(5,15),(6,15),(7,15),(8,15),(9,15),(19,15),(20,15);
/*!40000 ALTER TABLE `sys_resource_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `update_date` datetime DEFAULT NULL,
  `code` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_plpigyqwsqfn7mn66npgf9ftp` (`code`),
  KEY `FK_9cb1e15typ7fav30i3kqr25up` (`create_user_id`),
  KEY `FK_eqsth9lbk7kphqas9phv97jmx` (`update_user_id`),
  CONSTRAINT `FK_9cb1e15typ7fav30i3kqr25up` FOREIGN KEY (`create_user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_eqsth9lbk7kphqas9phv97jmx` FOREIGN KEY (`update_user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES (1,NULL,'\0','2017-06-16 13:54:30','ROLE_ADMIN','系统管理员',NULL,1),(11,'2017-06-14 14:57:42','','2017-06-15 16:04:49','test','test',1,1),(12,'2017-06-15 16:05:18','','2017-06-15 16:05:36','初始化资源','11111',1,1),(13,'2017-06-16 11:12:35','\0','2017-06-16 13:55:40','ROLE_USER','用户管理',1,1),(14,'2017-06-16 11:12:52','\0','2017-06-16 14:00:43','ROLE_ROLE','角色管理',1,1),(15,'2017-06-16 11:13:11','\0','2017-06-16 11:16:43','ROLE_RESOURCE','资源管理',1,1);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `is_delete` bit(1) NOT NULL DEFAULT b'0',
  `update_date` datetime DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `sex` varchar(1) NOT NULL,
  `user_status` varchar(1) NOT NULL,
  `username` varchar(64) DEFAULT NULL,
  `create_user_id` bigint(20) DEFAULT NULL,
  `update_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`),
  KEY `FK_ptc5pnqkym3vfkfou419luy3x` (`create_user_id`),
  KEY `FK_4nn444k4vpvus5cwb2gvjm4x0` (`update_user_id`),
  CONSTRAINT `FK_4nn444k4vpvus5cwb2gvjm4x0` FOREIGN KEY (`update_user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_ptc5pnqkym3vfkfou419luy3x` FOREIGN KEY (`create_user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,NULL,'\0',NULL,'红中么么么么么么么么么么么么','f1b89de34df19d7e8840a815d0f42a41','1','1','manye',NULL,NULL),(2,'2017-05-07 20:16:28','\0','2017-05-07 20:16:28','红中','1b771698e9d4723bfd35818165db49b7','1','1','admin',NULL,NULL),(3,NULL,'',NULL,'11','f1a7f301bfbef5dba911bf1c067f50a4','1','1','11',NULL,NULL),(4,NULL,'',NULL,'abc','8f44409c204cb264e55f90c949d62f1d','1','1','abc',NULL,NULL),(5,NULL,'',NULL,'aaa','918a03bd58d0c0b886919205a1392df7','1','1','aaa',NULL,NULL),(7,NULL,'',NULL,'aaa','f396508bce36039339980e653d04ce1e','1','1','aaaa',NULL,NULL),(8,NULL,'',NULL,'aaa','285d7f9f976367112600f14cee1da121','1','1','aaaaa',NULL,NULL),(9,NULL,'',NULL,'ab','811acf4915e071946d8a003a39b2b441','1','1','ab',NULL,NULL),(10,NULL,'',NULL,'manman','7a0b4bd92db33ce023fa06254aaf7a06','1','1','man',NULL,NULL),(11,NULL,'',NULL,'tes','8030b3d8266d35fe9da1b9b43bd9af04','2','1','tesss',NULL,NULL),(12,NULL,'',NULL,'fsdfasdf','f24b29fb14cfbfd0214ecec174bbf3b9','2','1','afsdfs',NULL,NULL),(13,NULL,'',NULL,'1111111','bc5bfd05ee667c6c691a6eb8fc8405f6','1','2','1111111',NULL,NULL),(14,NULL,'\0',NULL,'adf','1ebe14af122bf951932d24cbdeeb8a6e','2','1','asdf',NULL,NULL),(15,'2017-06-16 11:14:04','\0','2017-06-16 11:14:04','用户管理员','abad2de54f7437d1fe8ffe6e6db2c5f2','2','1','useradmin',1,1),(16,'2017-06-16 13:56:26','\0','2017-06-16 14:36:21','角色管理员','f3e6490ba71f42784be4e0031f2de12b','1','1','roleadmin',1,1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_role`
--

DROP TABLE IF EXISTS `sys_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_fxu3td9m5o7qov1kbdvmn0g0x` (`role_id`),
  KEY `FK_fethvr269t6stivlddbo5pxry` (`user_id`),
  CONSTRAINT `FK_fethvr269t6stivlddbo5pxry` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_fxu3td9m5o7qov1kbdvmn0g0x` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_role`
--

LOCK TABLES `sys_user_role` WRITE;
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` VALUES (1,1),(2,1),(14,12),(15,13),(16,14);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mahjong'
--

--
-- Dumping routines for database 'mahjong'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-23 16:14:37
