CREATE TABLE IF NOT EXISTS `permission`
(
    `id`         int(8)       NOT NULL AUTO_INCREMENT,
    `url`        varchar(255) NOT NULL COMMENT '资源链接',
    `name`       varchar(64)  NOT NULL COMMENT '资源名',
    `remark`     varchar(255) DEFAULT NULL COMMENT '备注',
    `updateTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `createTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

