CREATE TABLE IF NOT EXISTS `project_version`
(
    `id`      int(8)      NOT NULL AUTO_INCREMENT,
    `version` varchar(16) NOT NULL COMMENT '项目版本号',
    `pid`     int(8)      NOT NULL COMMENT '项目id',
    `remark`  varchar(64) DEFAULT NULL COMMENT '备注',
    `createTime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
