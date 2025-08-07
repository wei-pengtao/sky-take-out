CREATE DATABASE IF NOT EXISTS `shop_db`;
USE `shop_db`;

DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`
(
    `id`          bigint                          NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '姓名',
    `username`    varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
    `password`    varchar(64) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
    `phone`       varchar(11) COLLATE utf8mb4_bin NOT NULL COMMENT '手机号',
    `sex`         varchar(2) COLLATE utf8mb4_bin  NOT NULL COMMENT '性别',
    `id_number`   varchar(18) COLLATE utf8mb4_bin NOT NULL COMMENT '身份证号',
    `status`      int                             NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:启用',
    `create_time` datetime                                 DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                                 DEFAULT NULL COMMENT '更新时间',
    `create_user` bigint                                   DEFAULT NULL COMMENT '创建人',
    `update_user` bigint                                   DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT ='员工信息';

INSERT INTO `employee`
VALUES (1, '管理员', 'admin', '$2a$12$/uoe9Bnv557m78dMiZi2auDBjqHduGubRhWM4s36sMk7SwtfK.KG2', '13812312312', '1',
        '110101199001010047', 1, '2022-02-15 15:51:20',
        '2022-02-17 09:16:20', 10, 1);
