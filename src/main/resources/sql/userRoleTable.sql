CREATE TABLE IF NOT EXISTS `user_role`
(
    `uid` int(8) NOT NULL COMMENT '用户id',
    `rid` int(8) NOT NULL COMMENT '角色id'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

