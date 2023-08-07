CREATE TABLE IF NOT EXISTS `user`
(
    `id`         bigint(8)    NOT NULL AUTO_INCREMENT,
    `userName`   varchar(32)  NOT NULL COMMENT '用户名',
    `password`   varchar(128) NOT NULL COMMENT '密码',
    `nickName`   varchar(32)  NOT NULL COMMENT '昵称',
    `email`      varchar(32)  NOT NULL COMMENT '邮箱',
    `updateTime` datetime  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `createTime` datetime  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `status`     int(8)       NOT NULL COMMENT '1启用；2禁用',
    `remark`     varchar(255)          DEFAULT NULL,
    `custom`  varchar(255)          DEFAULT NULL COMMENT '存储前端自定义json',
    `position` tinyint(4) NOT NULL COMMENT '职位:1产品,2前端,3后端,4预留',
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_username_unique` (`userName`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

insert into `user` (id, userName, password, nickName, email, status, remark, custom,position)
values (1, "root", "12ebd7d90c3401436385674d9b3c86eb", "超级管理员", "", 1, "", "",4)
ON DUPLICATE KEY UPDATE id = 1;
