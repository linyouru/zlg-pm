CREATE TABLE IF NOT EXISTS `user_project`
(
    `uid` int(8) NOT NULL COMMENT '用户id',
    `pid` int(8) NOT NULL COMMENT '项目id'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;