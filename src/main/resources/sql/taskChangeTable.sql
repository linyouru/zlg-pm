CREATE TABLE IF NOT EXISTS `task_change`
(
    `id`              int(8)      NOT NULL AUTO_INCREMENT,
    `taskId`          int(8)      NOT NULL COMMENT '任务id',
    `uid`             int(8)      NOT NULL COMMENT '变更者id',
    `auditorId`       int(8)      NOT NULL COMMENT '审核者id',
    `auditorName`     varchar(16) NOT NULL COMMENT '审核者昵称',
    `status`          int(8)      NOT NULL COMMENT '状态:1待审核,2通过,3未通过',
    `beforeStartTime` varchar(64)          DEFAULT NULL COMMENT '变更前开始时间',
    `beforeEndTime`   varchar(64)          DEFAULT NULL COMMENT '变更前结束时间',
    `afterStartTime`  varchar(64)          DEFAULT NULL COMMENT '变更后开始时间',
    `afterEndTime`    varchar(64)          DEFAULT NULL COMMENT '变更后结束时间',
    `reason`          varchar(255)         DEFAULT NULL COMMENT '变更原因',
    `updateTime`      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `createTime`      datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

