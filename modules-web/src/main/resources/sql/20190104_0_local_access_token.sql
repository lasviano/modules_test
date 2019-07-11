USE `bimface`;

CREATE TABLE `local_access_token` (
  `access_token` VARCHAR(64) NOT NULL COMMENT 'ACCESS TOKEN',
  `app_key` varchar(32) NOT NULL COMMENT 'APP KEY',
	`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'access token 颁发的时间',
  PRIMARY KEY (`access_token`),
  KEY `ix_app_key` (`app_key`),
  KEY `ix_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

