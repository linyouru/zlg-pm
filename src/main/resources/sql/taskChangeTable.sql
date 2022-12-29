CREATE TABLE IF NOT EXISTS `task_change`
(
    `id`         int(8)      NOT NULL AUTO_INCREMENT,
    `taskId`     int(8)      NOT NULL COMMENT '任务id',
    `uid`        int(8)      NOT NULL COMMENT '用户id',
    `beforeTime` varchar(64)          DEFAULT NULL COMMENT '变更前时间',
    `time`       varchar(64)          DEFAULT NULL COMMENT '变更时间',
    `reason`     varchar(255)         DEFAULT NULL COMMENT '变更原因',
    `updateTime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `createTime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

