CREATE TABLE IF NOT EXISTS `task_relevance`
(
    `vid` int(8) DEFAULT NULL COMMENT '项目版本id',
    `tid` int(8) DEFAULT NULL COMMENT '任务id'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

