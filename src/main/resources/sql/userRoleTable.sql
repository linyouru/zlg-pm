CREATE TABLE IF NOT EXISTS `user_role`
(
    `uid` int(8) NOT NULL COMMENT '用户id',
    `rid` int(8) NOT NULL COMMENT '角色id'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

insert into `user_role` (uid, rid)
SELECT 1, 1
FROM DUAL
WHERE NOT EXISTS(SELECT * FROM `user_role` WHERE uid = 1);