CREATE TABLE IF NOT EXISTS `project`
(
    `id`         int(8)      NOT NULL AUTO_INCREMENT,
    `name`       varchar(32) NOT NULL COMMENT '项目名',
    `uid`        int(8)      NOT NULL COMMENT '用户id',
    `status`     varchar(8)  NOT NULL COMMENT '1开发中；2已完成',
    `remark`     varchar(255)                           DEFAULT NULL COMMENT '备注',
    `link`       varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `updateTime` datetime(3) NOT NULL                   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `createTime` datetime(3) NOT NULL                   DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_n` (`name`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

