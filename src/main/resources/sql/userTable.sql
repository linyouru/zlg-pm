CREATE TABLE IF NOT EXISTS `user`
(
    `id`         bigint(8)    NOT NULL AUTO_INCREMENT,
    `userName`   varchar(32)  NOT NULL COMMENT '用户名',
    `password`   varchar(128) NOT NULL COMMENT '密码',
    `nickName`   varchar(32)  NOT NULL COMMENT '昵称',
    `email`      varchar(32)  NOT NULL COMMENT '邮箱',
    `updateTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `createTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `status`     int(8)       NOT NULL COMMENT '1启用；2禁用',
    `remark`     varchar(255)          DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_username_unique` (`userName`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

insert into `user` (id, userName, password, nickName, email, status, remark)
values (1, "root", "f617b6f53bdea5bbbe3f73f13ba19cec", "超级管理员", "", 1, "")
ON DUPLICATE KEY UPDATE id = 1;
