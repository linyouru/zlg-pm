CREATE TABLE IF NOT EXISTS `project_modules`
(
    `id`     int(8) NOT NULL AUTO_INCREMENT,
    `pid`    int(8) NOT NULL COMMENT '项目id',
    `module` varchar(32) DEFAULT NULL COMMENT '模块名',
    `level`  int(8)      DEFAULT NULL COMMENT '权重值0-100',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

