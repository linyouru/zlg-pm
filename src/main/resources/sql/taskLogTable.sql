CREATE TABLE IF NOT EXISTS `task_log`
(
    `id`         int(8)   NOT NULL AUTO_INCREMENT,
    `taskId`     int(8)   NOT NULL COMMENT '任务id',
    `uid`        int(8)   NOT NULL COMMENT '用户id',
    `workTime`   varchar(64)                              DEFAULT NULL COMMENT '工时',
    `progress`   varchar(64)                              DEFAULT NULL COMMENT '进度',
    `log`        varchar(1023)                            DEFAULT NULL COMMENT '日志内容',
    `feedback`   varchar(1023) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '反馈',
    `updateTime` datetime NOT NULL                        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `createTime` datetime NOT NULL                        DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY `index_taskId` (`taskId`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

