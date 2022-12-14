CREATE TABLE IF NOT EXISTS `role`
(
    `id`         int(8)      NOT NULL AUTO_INCREMENT,
    `name`       varchar(32) NOT NULL COMMENT '角色名',
    `remark`     varchar(255) DEFAULT NULL,
    `updateTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    `createTime` datetime(3)  NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (`id`),
    UNIQUE KEY `index_name_unique` (`name`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into `role` (id, name, remark)
values (1, "root", "超级管理员")
ON DUPLICATE KEY UPDATE id = 1;

insert into `role` (id, name, remark)
values (2, "admin", "管理员")
ON DUPLICATE KEY UPDATE id = 2;

insert into `role` (id, name, remark)
values (3, "user", "普通用户")
ON DUPLICATE KEY UPDATE id = 3;

