CREATE TABLE IF NOT EXISTS `role`
(
    `id`         int(8)      NOT NULL,
    `name`       varchar(32) NOT NULL COMMENT '角色名',
    `remark`     varchar(255) DEFAULT NULL,
    `updateTime` varchar(64) NOT NULL,
    `createTime` varchar(64) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into `role` (id, name, remark, updateTime, createTime)
values (1, "root", "超级管理员", "", "")
ON DUPLICATE KEY UPDATE id = 1;

