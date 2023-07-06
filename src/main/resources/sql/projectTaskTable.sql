CREATE TABLE IF NOT EXISTS `project_task`
(
    `id`             int(8)     NOT NULL AUTO_INCREMENT,
    `taskType`       varchar(8) NOT NULL COMMENT '任务类型 1前端；2后端；3产品',
    `task`           varchar(1023) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '任务标题',
    `detail`         varchar(1023) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '任务详情',
    `status`         varchar(8) NOT NULL COMMENT '1已完成；2反馈; 3待验收；4开发中；5暂停; 6排队中',
    `uid`            int(8)                                   DEFAULT NULL COMMENT '用户id',
    `pid`            int(8)     NOT NULL COMMENT '项目id',
    `createdUid`     int(8)     NOT NULL COMMENT '任务发布者id',
    `accepterUid`    tinyint(4)                               DEFAULT NULL COMMENT '验收人id',
    `playStartTime`  varchar(64)                              DEFAULT NULL COMMENT '计划开始时间',
    `playEndTime`    varchar(64)                              DEFAULT NULL COMMENT '计划结束时间',
    `timely`         varchar(8)                               DEFAULT NULL COMMENT '及时性 1是；2否',
    `quality`        varchar(8)                               DEFAULT NULL COMMENT '质量分',
    `document`       varchar(8)                               DEFAULT NULL COMMENT '文档分',
    `remark`         varchar(255)                             DEFAULT NULL COMMENT '备注',
    `link`           varchar(255)                             DEFAULT NULL COMMENT '文档链接',
    `mid`            int(8)                                   DEFAULT NULL COMMENT '功能模块id',
    `vid`            int(8)                                   DEFAULT NULL COMMENT '项目版本id',
    `haveDocument`   int(8)     NOT NULL                      DEFAULT '0' COMMENT '是否需要文档，0否；1是',
    `acceptanceTime` varchar(64)                              DEFAULT NULL COMMENT '验收时间',
    `serialNumber`   int(8)                                   DEFAULT NULL COMMENT '任务序号',
    `level`          tinyint(4)                               DEFAULT NULL COMMENT '优先级，1低、2中、3高',
    `updateTime`     datetime   NOT NULL                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `createTime`     datetime   NOT NULL                      DEFAULT CURRENT_TIMESTAMP,
    `sendEmail2Creator`     tinyint(2)                               DEFAULT NULL COMMENT '任务完成时是否发送邮件给创建人,0否；1是',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

