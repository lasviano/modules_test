USE `bimface`;
CREATE TABLE IF NOT EXISTS `app` (
  `app_key` varchar(32) NOT NULL COMMENT 'APP KEY',
  `app_secret` varchar(32) DEFAULT NULL COMMENT 'APP SECRET',
  `name` varchar(64) DEFAULT NULL COMMENT '应用名称',
  `website` varchar(128) DEFAULT NULL COMMENT '应用网址',
  `description` varchar(256) DEFAULT NULL COMMENT '应用描述',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`app_key`),
  KEY ix_user_id (`user_id`),
  KEY ix_create_time (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `app_permission` (
  `app_key` varchar(32) NOT NULL,
  `max_length` bigint(20) NOT NULL COMMENT '文件长度上限,单位Byte',
  `support_types` varchar(512) NOT NULL COMMENT '支持的文件格式',
  `priority` varchar(32) NOT NULL COMMENT '任务优先级	',
  PRIMARY KEY (`app_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;