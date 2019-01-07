-- ----------------------------
-- TABLE STRUCTURE FOR `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`user_id`  VARCHAR(20)  NOT NULL COMMENT '用户编号',
`sex` varchar(5) DEFAULT NULL COMMENT '性别',
`login_id`  VARCHAR(20)  NOT NULL COMMENT '登录号',
`name`  VARCHAR(20) DEFAULT NULL COMMENT '姓名',
`password`  VARCHAR(128) DEFAULT NULL COMMENT '密码',
`photo_path`  VARCHAR(128) DEFAULT NULL COMMENT '头像图片地址',
`mobile`  VARCHAR(15) DEFAULT NULL COMMENT '手机号码',
`email`  VARCHAR(30) DEFAULT NULL COMMENT 'email',
`state`  CHAR(1) DEFAULT NULL COMMENT '0:初始化;1:正常在用;2:锁定;9:已删除;',
`login_time`  DATETIME NULL DEFAULT NULL COMMENT '最后登录时间',
`login_ip`  VARCHAR(15) DEFAULT NULL COMMENT '最后登录ip',
`is_login`  CHAR(1) DEFAULT NULL COMMENT 'Y:已登录;N:未登录',
`change_password_time`  DATETIME NULL DEFAULT NULL COMMENT '最后更改密码时间',
`update_time`  DATETIME NULL DEFAULT NULL COMMENT '最后信息修改时间',
`create_date`  DATE NULL DEFAULT NULL COMMENT '创建时间',
`remark`  VARCHAR(50) DEFAULT NULL  COMMENT '备注',
PRIMARY KEY (`user_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';


-- ----------------------------
-- TABLE STRUCTURE FOR `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`id`  VARCHAR(20)  NOT NULL,
`role_id`  VARCHAR(20) DEFAULT NULL COMMENT '角色编号',
`user_id`  VARCHAR(20) DEFAULT NULL COMMENT '用户编号',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员角色关联表';


-- ----------------------------
-- TABLE STRUCTURE FOR `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`role_id`  VARCHAR(20)  NOT NULL COMMENT '角色标识',
`status`  CHAR(1) DEFAULT NULL COMMENT '1:有效;0:无效',
`role_name`  VARCHAR(50) DEFAULT NULL COMMENT '角色名称',
`description`  VARCHAR(250) DEFAULT NULL COMMENT '描述',
`update_time`  DATETIME NULL DEFAULT NULL COMMENT '更新时间',
`create_time`  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
PRIMARY KEY (`role_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';


-- ----------------------------
-- TABLE STRUCTURE FOR `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
`id`  VARCHAR(20)  NOT NULL,
`catalog_id`  VARCHAR(20) DEFAULT NULL COMMENT '所属目录',
`menu_name`  VARCHAR(50) DEFAULT NULL COMMENT '菜单名称',
`url`  VARCHAR(100) DEFAULT NULL,
`icon_path`  VARCHAR(50) DEFAULT NULL COMMENT '菜单路径',
`description`  VARCHAR(250) DEFAULT NULL COMMENT '描述',
`create_time`  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
`status`  VARCHAR(10) DEFAULT NULL COMMENT '状态',
`back_url`  VARCHAR(200) DEFAULT NULL COMMENT '后台接口',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';


-- ----------------------------
-- TABLE STRUCTURE FOR `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
`role_id`  VARCHAR(20) DEFAULT NULL COMMENT '角色id',
`menu_id`  VARCHAR(20) DEFAULT NULL COMMENT '菜单id' 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单';


-- ----------------------------
-- TABLE STRUCTURE FOR `sys_menu_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_permission`;
CREATE TABLE `sys_menu_permission` (
`menu_id`  VARCHAR(20) DEFAULT NULL COMMENT '菜单id',
`permission_id`  VARCHAR(20) DEFAULT NULL COMMENT '权限id' 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限';

-- ----------------------------
-- TABLE STRUCTURE FOR `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
`id`  VARCHAR(20)  NOT NULL,
`code`  VARCHAR(5) DEFAULT NULL COMMENT '标识',
`name`  VARCHAR(50) DEFAULT NULL COMMENT '名称',
`description`  VARCHAR(50) DEFAULT NULL COMMENT '描述',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';

-- ----------------------------
-- TABLE STRUCTURE FOR `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
`role_id`  VARCHAR(20) DEFAULT NULL COMMENT '角色id',
`permission_id`  VARCHAR(20) DEFAULT NULL COMMENT '权限id' 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限';


-- ----------------------------
-- TABLE STRUCTURE FOR `sys_user_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_log`;
CREATE TABLE `sys_user_log` (
`log_id`  VARCHAR(20)  NOT NULL COMMENT '登录id',
`user_id`  VARCHAR(20) DEFAULT NULL COMMENT '用户id',
`action`  VARCHAR(100) DEFAULT NULL COMMENT '描述',
`log_time`  DATETIME NULL DEFAULT NULL COMMENT '登录时间',
`log_ip` VARCHAR(32) DEFAULT NULL COMMENT '登录IP',
`log_machine` VARCHAR(20) DEFAULT NULL COMMENT '登录终端',
PRIMARY KEY (`log_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录日志';


-- ----------------------------
-- TABLE STRUCTURE FOR `sys_message`
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
`id`  VARCHAR(20)  NOT NULL,
`contact_user`  VARCHAR(40) DEFAULT NULL COMMENT '联系人',
`title`  VARCHAR(50) DEFAULT NULL COMMENT '标题',
`message_type`  VARCHAR(20) DEFAULT NULL COMMENT '消息类型',
`content`  VARCHAR(500) DEFAULT NULL COMMENT '消息内容',
`read_flag`  VARCHAR(2) DEFAULT NULL COMMENT '是否已读。0，未读，1，已读',
`create_user` VARCHAR(20) DEFAULT NULL COMMENT '创建人',
`create_time`  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
`update_time`  DATETIME NULL DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统通知表';

-- ----------------------------
-- TABLE STRUCTURE FOR `sys_option_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_option_log`;
CREATE TABLE `sys_option_log` (
`id`  VARCHAR(20)  NOT NULL,
`user_id`  VARCHAR(40) DEFAULT NULL COMMENT '操作人',
`option_time`  DATETIME NULL DEFAULT NULL COMMENT '操作时间',
`option_url`  VARCHAR(50) DEFAULT NULL COMMENT '操作url',
`option_value`  VARCHAR(50) DEFAULT NULL COMMENT '操作内容',
`option_info`  VARCHAR(250) DEFAULT NULL COMMENT '操作详情',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- TABLE STRUCTURE FOR `sys_resource_data`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_data`;
CREATE TABLE `sys_resource_data` (
`id`  VARCHAR(20)  NOT NULL,
`type`  VARCHAR(10) DEFAULT NULL COMMENT '资源类型',
`status`  VARCHAR(10) DEFAULT NULL COMMENT '状态',
`resour_id`  VARCHAR(40) DEFAULT NULL COMMENT '资源id',
`relation_id`  VARCHAR(20) DEFAULT NULL COMMENT '关联id',
`create_time`  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
`update_time`  DATETIME NULL DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源数据';



INSERT INTO `sys_user` (`user_id`,`login_id`,`name`,`mobile`,`email`,`password`,`state`,`create_date`) VALUES ('0','rubick','GM','19965502527','291696153@qq.com','def240abb91b572271eb87777bfbf52f62106534d7beb63f4b9a1cb29732245661acfba7bcc96e21','1','2018-12-12');
INSERT INTO `sys_user_role`(`id`, `role_id`, `user_id`) VALUES ('0', '0', '0');
INSERT INTO `sys_role`(`role_id`, `status`, `role_name`, `description`) VALUES ('0', '0', '超级管理员', '超级管理员');
INSERT INTO `sys_role`(`role_id`, `status`, `role_name`, `description`) VALUES ('1', '1', '系统管理员', '系统管理员');
