CREATE TABLE IF NOT EXISTS `user`
(
    `id`         bigint(8)   NOT NULL AUTO_INCREMENT,
    `userName`   varchar(32) NOT NULL COMMENT '用户名',
    `password`   varchar(16) NOT NULL COMMENT '密码',
    `nickName`   varchar(32) NOT NULL COMMENT '昵称',
    `email`      varchar(32) NOT NULL COMMENT '邮箱',
    `updateTime` varchar(64) NOT NULL,
    `createTime` varchar(64) NOT NULL,
    `status`     int(8)      NOT NULL COMMENT '1启用；2禁用',
    `remark`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = utf8;

insert into `user` (id, userName, password, nickName, email, updateTime, createTime, status, remark)
values (9999, "root", "zlg2022", "超级管理员", "", "", "", 1, "")
ON DUPLICATE KEY UPDATE id = 9999;
