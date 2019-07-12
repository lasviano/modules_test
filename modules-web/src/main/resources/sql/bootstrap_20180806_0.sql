# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: rm-2ze1dun12h2rt3301.mysql.rds.aliyuncs.com (MySQL 5.6.70)
# Database: bimface_file
# Generation Time: 2018-08-06 05:44:16 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `bimface_file`;

USE `bimface_file`;

# Dump of table append_file
# ------------------------------------------------------------

DROP TABLE IF EXISTS `append_file`;

CREATE TABLE `append_file` (
  `id` bigint(20) NOT NULL,
  `app_key` varchar(32) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `length` bigint(20) DEFAULT NULL,
  `source_id` varchar(32) DEFAULT NULL,
  `position` bigint(20) DEFAULT NULL COMMENT '续传位置',
  `status` varchar(32) DEFAULT NULL,
  `store_id` varchar(32) DEFAULT NULL,
  `file_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table file
# ------------------------------------------------------------

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
  `id` bigint(20) NOT NULL COMMENT '文件id',
  `app_key` varchar(32) DEFAULT NULL COMMENT '所属app',
  `name` varchar(256) DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(16) DEFAULT NULL COMMENT '文件后缀名',
  `length` bigint(20) DEFAULT NULL COMMENT '文件长度',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，0-正在上传，1-OK，-1失败',
  `upload_mode` tinyint(4) DEFAULT NULL COMMENT '上传模式，0-普通上传，1-断点续传，2-文件URL，3-OSS元数据',
  `source_id` varchar(32) DEFAULT NULL COMMENT '源文件的ID',
  `source_url` varchar(1024) DEFAULT NULL COMMENT '源文件的下载地址',
  `source_bucket` varchar(64) DEFAULT NULL COMMENT '源文件所在存储的Bucket',
  `source_object_key` varchar(512) DEFAULT NULL COMMENT '源文件所在存储的Key',
  `store_id` varchar(32) DEFAULT NULL COMMENT '存储的ID',
  `etag` varchar(32) DEFAULT NULL COMMENT '文件上传到存储后，存储返回的ETAG',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_source_id` (`app_key`,`update_time`,`source_id`),
  KEY `create_time` (`create_time`),
  KEY `upload` (`upload_mode`,`source_bucket`,`source_object_key`(255)),
  KEY `idx_store_id` (`store_id`),
  KEY `idx_app_key` (`app_key`,`source_id`),
  KEY `idx_app_key_only` (`app_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table oss_part
# ------------------------------------------------------------

DROP TABLE IF EXISTS `oss_part`;

CREATE TABLE `oss_part` (
  `store_id` varchar(32) NOT NULL COMMENT '存储的ID',
  `upload_id` varchar(32) DEFAULT NULL COMMENT 'OSS断点续传的任务标识',
  `part_number` int(4) DEFAULT NULL COMMENT '分片编号',
  `part_etag` varchar(32) DEFAULT NULL COMMENT 'OSS返回的分片的ETag',
  `create_time` datetime DEFAULT NULL,
  KEY `store_id` (`store_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table store
# ------------------------------------------------------------

DROP TABLE IF EXISTS `store`;

CREATE TABLE `store` (
  `id` varchar(32) NOT NULL,
  `suffix` varchar(16) DEFAULT NULL COMMENT '文件后缀名',
  `length` bigint(20) DEFAULT NULL COMMENT '文件长度',
  `length_saved` bigint(20) DEFAULT NULL COMMENT '已上传的文件长度',
  `part_size` int(4) DEFAULT NULL COMMENT '每片大小',
  `part_total` int(4) DEFAULT NULL COMMENT '分片总数',
  `part_number` int(4) DEFAULT NULL COMMENT '已上传的分片编号',
  `md5` varchar(32) DEFAULT NULL COMMENT '文件MD5',
  `etag` varchar(32) DEFAULT NULL COMMENT '文件上传到存储后，存储返回的ETAG',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `md5` (`md5`),
  KEY `etag` (`etag`),
  KEY `create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
