CREATE TABLE IF NOT EXISTS `permission`
(
    `id`         int(8)       NOT NULL,
    `url`        varchar(255) NOT NULL COMMENT '资源链接',
    `name`       varchar(64)  NOT NULL COMMENT '资源名',
    `remark`     varchar(255) DEFAULT NULL COMMENT '备注',
    `updateTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `createTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

