CREATE TABLE IF NOT EXISTS `role_permission`
(
    `rid` int(8) NOT NULL COMMENT '角色id',
    `pid` int(8) NOT NULL COMMENT '权限id'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

