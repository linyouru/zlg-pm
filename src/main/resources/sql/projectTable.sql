CREATE TABLE IF NOT EXISTS `project`
(
    `id`         int(8)      NOT NULL,
    `name`       varchar(32) NOT NULL COMMENT '项目名',
    `version`    varchar(8)  NOT NULL COMMENT '项目版本',
    `uid`        int(8)      NOT NULL COMMENT '用户id',
    `status`     int(8)      NOT NULL COMMENT '1开发中；2已完成',
    `remark`     varchar(255) DEFAULT NULL COMMENT '备注',
    `updateTime` varchar(64) NOT NULL,
    `createTime` varchar(64) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

