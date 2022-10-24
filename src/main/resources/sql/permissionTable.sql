CREATE TABLE IF NOT EXISTS `permission`
(
    `id`         int(8)       NOT NULL,
    `url`        varchar(255) NOT NULL COMMENT '资源链接',
    `name`       varchar(64)  NOT NULL COMMENT '资源名',
    `remark`     varchar(255) DEFAULT NULL COMMENT '备注',
    `createTime` varchar(64)  NOT NULL,
    `updateTime` varchar(64)  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

