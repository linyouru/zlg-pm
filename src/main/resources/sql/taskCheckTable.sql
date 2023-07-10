CREATE TABLE `task_check`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `tasId`      int(11)    DEFAULT NULL COMMENT '任务id',
    `type`       tinyint(4) DEFAULT NULL COMMENT '1验收，2问题',
    `isTimely`   tinyint(4) DEFAULT NULL COMMENT '0不及时，1及时',
    `createTime` datetime   DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

