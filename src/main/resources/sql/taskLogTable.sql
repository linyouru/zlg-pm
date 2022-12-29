CREATE TABLE IF NOT EXISTS `task_log`
(
    `id`         int(8)      NOT NULL AUTO_INCREMENT,
    `taskId`     int(8)      NOT NULL COMMENT '任务id',
    `uid`        int(8)      NOT NULL COMMENT '用户id',
    `workTime`   varchar(64)          DEFAULT NULL COMMENT '工时',
    `progress`   varchar(64)          DEFAULT NULL COMMENT '进度',
    `log`        varchar(1023)        DEFAULT NULL COMMENT '日志内容',
    `updateTime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `createTime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

