CREATE TABLE IF NOT EXISTS `task_feedback`
(
    `id`         int(8)      NOT NULL AUTO_INCREMENT,
    `feedback`   varchar(1023)        DEFAULT NULL COMMENT '反馈内容',
    `uid`        int(8)               DEFAULT NULL COMMENT '反馈人id',
    `tid`        int(8)      NOT NULL COMMENT '任务id',
    `remark`     varchar(255)         DEFAULT NULL COMMENT '备注',
    `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

