CREATE TABLE IF NOT EXISTS `operation_log`
(
    `id`         int(8)      NOT NULL,
    `user`       varchar(32) NOT NULL COMMENT '操作人',
    `record`     varchar(255) DEFAULT NULL COMMENT '备注',
    `createTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

