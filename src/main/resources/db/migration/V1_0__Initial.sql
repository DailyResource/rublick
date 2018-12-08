
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
-- TABLE STRUCTURE FOR `sys_menu_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_permission`;
CREATE TABLE `sys_menu_permission` (
`menu_id`  VARCHAR(20) DEFAULT NULL COMMENT '菜单id',
`permission_id`  VARCHAR(20) DEFAULT NULL COMMENT '权限id' 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单权限';

-- ----------------------------
-- TABLE STRUCTURE FOR `sys_message`
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message` (
`id`  VARCHAR(20)  NOT NULL,
`contact_user`  VARCHAR(40) DEFAULT NULL COMMENT '联系人',
`contact_tel`  VARCHAR(40) DEFAULT NULL COMMENT '联系电话',
`title`  VARCHAR(50) DEFAULT NULL COMMENT '标题',
`send_time`  DATETIME NULL DEFAULT NULL COMMENT '发送时间',
`send_num`  INT(11) NULL DEFAULT NULL COMMENT '发送次数',
`resend_num`  INT(11) NULL DEFAULT NULL COMMENT '重发次数',
`status`  VARCHAR(10) DEFAULT NULL COMMENT '发送状态',
`create_time`  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
`message_type`  VARCHAR(20) DEFAULT NULL COMMENT '消息类型',
`content`  VARCHAR(500) DEFAULT NULL COMMENT '消息内容',
`device_id`  VARCHAR(50) DEFAULT NULL COMMENT '设备id',
`read_flag`  VARCHAR(2) DEFAULT NULL COMMENT '是否已读。0，未读，1，已读',
`warn_type`  VARCHAR(2) DEFAULT NULL COMMENT '告警类型',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信通知';

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
-- TABLE STRUCTURE FOR `sys_resource_data`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource_data`;
CREATE TABLE `sys_resource_data` (
`id`  VARCHAR(20)  NOT NULL,
`type`  VARCHAR(10) DEFAULT NULL COMMENT '资源类型',
`resour_id`  VARCHAR(40) DEFAULT NULL COMMENT '资源id',
`relation_id`  VARCHAR(20) DEFAULT NULL COMMENT '关联id',
`create_time`  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
`status`  VARCHAR(10) DEFAULT NULL COMMENT '状态',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源数据';

-- ----------------------------
-- TABLE STRUCTURE FOR `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`role_id`  VARCHAR(20)  NOT NULL,
`role_code`  VARCHAR(10)  NOT NULL COMMENT '角色标识',
`create_time`  DATETIME NULL DEFAULT NULL COMMENT '创建时间',
`status`  CHAR(1) DEFAULT NULL COMMENT '1:有效;0:无效',
`role_name`  VARCHAR(50) DEFAULT NULL COMMENT '角色名称',
`description`  VARCHAR(250) DEFAULT NULL COMMENT '描述',
`update_time`  DATETIME NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`role_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

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
-- TABLE STRUCTURE FOR `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
`role_id`  VARCHAR(20) DEFAULT NULL COMMENT '角色id',
`permission_id`  VARCHAR(20) DEFAULT NULL COMMENT '权限id' 
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限';

-- ----------------------------
-- TABLE STRUCTURE FOR `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`user_id`  VARCHAR(20)  NOT NULL COMMENT '用户编号',
`login_id`  VARCHAR(20)  NOT NULL COMMENT '登录号',
`name`  VARCHAR(20) DEFAULT NULL COMMENT '姓名',
`mobile`  VARCHAR(15) DEFAULT NULL COMMENT '手机号码',
`email`  VARCHAR(30) DEFAULT NULL COMMENT 'email',
`password`  VARCHAR(128) DEFAULT NULL,
`state`  CHAR(1) DEFAULT NULL COMMENT '0:初始化;1:正常在用;2:锁定;9:已删除;',
`create_date`  DATE NULL DEFAULT NULL COMMENT '创建时间',
`login_time`  DATETIME NULL DEFAULT NULL COMMENT '最后登录时间',
`login_ip`  VARCHAR(15) DEFAULT NULL COMMENT '最后登录ip',
`is_login`  CHAR(1) DEFAULT NULL COMMENT 'Y:已登录;N:未登录',
`photo_path`  VARCHAR(128) DEFAULT NULL COMMENT '头像图片地址',
`change_password_time`  DATETIME NULL DEFAULT NULL COMMENT '最后更改密码时间',
`update_time`  DATETIME NULL DEFAULT NULL COMMENT '最后信息修改时间',
`remark`  VARCHAR(50) DEFAULT NULL  COMMENT '备注',
PRIMARY KEY (`user_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作用户信息表';

-- ----------------------------
-- TABLE STRUCTURE FOR `sys_user_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_log`;
CREATE TABLE `sys_user_log` (
`log_id`  VARCHAR(20)  NOT NULL COMMENT '登录id',
`user_id`  VARCHAR(20) DEFAULT NULL COMMENT '用户id',
`action`  VARCHAR(100) DEFAULT NULL COMMENT '动作描述',
`log_time`  DATETIME NULL DEFAULT NULL COMMENT '登录描述',
PRIMARY KEY (`log_id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户日志';

-- ----------------------------
-- TABLE STRUCTURE FOR `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`id`  VARCHAR(20)  NOT NULL,
`role_id`  VARCHAR(20) DEFAULT NULL,
`user_id`  VARCHAR(20) DEFAULT NULL COMMENT '用户编号',
PRIMARY KEY (`id`)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员角色';

INSERT INTO `sys_user` (`user_id`,`login_id`,`name`,`mobile`,`email`,`password`,`state`,`create_date`,`login_time`,`login_ip`,`is_login`) VALUES ('1001','rubick','超级管理员','','','dda7aeb29384dbac1bc3373bcd0dd038851a87b4e86eeba1f11eae358391abc779deafafbf181a85','1','2018-11-11','2018-11-22 10:22:54','','Y');
INSERT INTO `sys_user_role`(`id`, `role_id`, `user_id`) VALUES ('1', '1', '1001');
