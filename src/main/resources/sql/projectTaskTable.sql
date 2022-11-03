CREATE TABLE IF NOT EXISTS `project_task`
(
    `id`            int(8)      NOT NULL AUTO_INCREMENT,
    `taskType`      varchar(8)      NOT NULL COMMENT '任务类型 1前端；2后端；3产品',
    `task`          varchar(255) DEFAULT NULL COMMENT '任务内容',
    `status`        varchar(8)      NOT NULL COMMENT '1开发中；2待验收；3已完成；4排队中',
    `uid`           int(8)      NOT NULL COMMENT '用户id',
    `pid`           int(8)      NOT NULL COMMENT '项目id',
    `playStartTime` varchar(64)  DEFAULT NULL COMMENT '计划开始时间',
    `playEndTime`   varchar(64)  DEFAULT NULL COMMENT '计划结束时间',
    `startTime`     varchar(64)  DEFAULT NULL COMMENT '实际开始时间',
    `endTime`       varchar(64)  DEFAULT NULL COMMENT '实际结束时间',
    `timely`        varchar(8)       DEFAULT NULL COMMENT '及时性 1是；2否',
    `quality`       varchar(8)   DEFAULT NULL COMMENT '质量分',
    `document`      varchar(8)   DEFAULT NULL COMMENT '文档分',
    `remark`        varchar(255) DEFAULT NULL COMMENT '备注',
    `link`          varchar(255) DEFAULT NULL COMMENT '文档链接',
    `updateTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `createTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

