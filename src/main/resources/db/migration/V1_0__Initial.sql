
-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_MENU`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_MENU`;
CREATE TABLE `SYS_MENU` (
`ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL ,
`CATALOG_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '所属目录' ,
`MENU_NAME`  VARCHAR(50) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '菜单名称' ,
`URL`  VARCHAR(100) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL ,
`ICON_PATH`  VARCHAR(50) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '菜单路径' ,
`DESCRIPTION`  VARCHAR(250) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '描述' ,
`CREATE_TIME`  DATETIME NULL DEFAULT NULL COMMENT '创建时间' ,
`STATUS`  VARCHAR(10) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '状态' ,
`BACK_URL`  VARCHAR(200) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '后台接口' ,
PRIMARY KEY (`ID`)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='菜单'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_MENU_PERMISSION`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_MENU_PERMISSION`;
CREATE TABLE `SYS_MENU_PERMISSION` (
`MENU_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '菜单ID' ,
`PERMISSION_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '权限ID' 
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='菜单权限'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_MESSAGE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_MESSAGE`;
CREATE TABLE `SYS_MESSAGE` (
`ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL ,
`CONTACT_USER`  VARCHAR(40) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '联系人' ,
`CONTACT_TEL`  VARCHAR(40) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '联系电话' ,
`TITLE`  VARCHAR(50) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '标题' ,
`SEND_TIME`  DATETIME NULL DEFAULT NULL COMMENT '发送时间' ,
`SEND_NUM`  INT(11) NULL DEFAULT NULL COMMENT '发送次数' ,
`RESEND_NUM`  INT(11) NULL DEFAULT NULL COMMENT '重发次数' ,
`STATUS`  VARCHAR(10) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '发送状态' ,
`CREATE_TIME`  DATETIME NULL DEFAULT NULL COMMENT '创建时间' ,
`MESSAGE_TYPE`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '消息类型' ,
`CONTENT`  VARCHAR(500) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '消息内容' ,
`DEVICE_ID`  VARCHAR(50) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '设备ID' ,
`READ_FLAG`  VARCHAR(2) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '是否已读。0，未读，1，已读' ,
`WARN_TYPE`  VARCHAR(2) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '告警类型' ,
PRIMARY KEY (`ID`)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='短信通知'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_OPTION_LOG`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_OPTION_LOG`;
CREATE TABLE `SYS_OPTION_LOG` (
`ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL ,
`USER_ID`  VARCHAR(40) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '操作人' ,
`OPTION_TIME`  DATETIME NULL DEFAULT NULL COMMENT '操作时间' ,
`OPTION_URL`  VARCHAR(50) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '操作URL' ,
`OPTION_VALUE`  VARCHAR(50) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '操作内容' ,
`OPTION_INFO`  VARCHAR(250) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '操作详情' ,
PRIMARY KEY (`ID`)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='操作日志'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_PERMISSION`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_PERMISSION`;
CREATE TABLE `SYS_PERMISSION` (
`ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL ,
`CODE`  VARCHAR(5) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '标识' ,
`NAME`  VARCHAR(50) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '名称' ,
`DESCRIPTION`  VARCHAR(50) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '描述' ,
PRIMARY KEY (`ID`)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='权限'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_RESOURCE_DATA`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_RESOURCE_DATA`;
CREATE TABLE `SYS_RESOURCE_DATA` (
`ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL ,
`TYPE`  VARCHAR(10) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '资源类型' ,
`RESOUR_ID`  VARCHAR(40) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '资源ID' ,
`RELATION_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '关联ID' ,
`CREATE_TIME`  DATETIME NULL DEFAULT NULL COMMENT '创建时间' ,
`STATUS`  VARCHAR(10) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '状态' ,
PRIMARY KEY (`ID`)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='资源数据'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE`;
CREATE TABLE `SYS_ROLE` (
`ROLE_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL ,
`ROLE_CODE`  VARCHAR(10) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL COMMENT '角色标识' ,
`CREATE_TIME`  DATETIME NULL DEFAULT NULL COMMENT '创建时间' ,
`STATUS`  CHAR(1) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '1:有效;0:无效' ,
`ROLE_NAME`  VARCHAR(50) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '角色名称' ,
`DESCRIPTION`  VARCHAR(250) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '描述' ,
`UPDATE_TIME`  DATETIME NULL DEFAULT NULL COMMENT '更新时间' ,
PRIMARY KEY (`ROLE_ID`)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='角色信息表'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_ROLE_MENU`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE_MENU`;
CREATE TABLE `SYS_ROLE_MENU` (
`ROLE_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '角色ID' ,
`MENU_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '菜单ID' 
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='角色菜单'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_ROLE_PERMISSION`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE_PERMISSION`;
CREATE TABLE `SYS_ROLE_PERMISSION` (
`ROLE_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '角色ID' ,
`PERMISSION_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '权限ID' 
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='角色权限'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_USER`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
`USER_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL COMMENT '用户编号' ,
`LOGIN_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL COMMENT '登录号' ,
`NAME`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '姓名' ,
`MOBILE`  VARCHAR(15) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '手机号码' ,
`EMAIL`  VARCHAR(30) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT 'EMAIL' ,
`PASSWORD`  VARCHAR(128) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL ,
`STATE`  CHAR(1) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '0:初始化;1:正常在用;2:锁定;9:已删除;' ,
`CREATE_DATE`  DATE NULL DEFAULT NULL COMMENT '创建时间' ,
`LOGIN_TIME`  DATETIME NULL DEFAULT NULL COMMENT '最后登录时间' ,
`LOGIN_IP`  VARCHAR(15) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '最后登录IP' ,
`IS_LOGIN`  CHAR(1) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT 'Y:已登录;N:未登录' ,
`PHOTO_PATH`  VARCHAR(128) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '头像图片地址' ,
`CHANGE_PASSWORD_TIME`  DATETIME NULL DEFAULT NULL COMMENT '最后更改密码时间' ,
`UPDATE_TIME`  DATETIME NULL DEFAULT NULL COMMENT '最后信息修改时间' ,
PRIMARY KEY (`USER_ID`)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='操作用户信息表'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_USER_LOG`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER_LOG`;
CREATE TABLE `SYS_USER_LOG` (
`LOG_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL COMMENT '登录ID' ,
`USER_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '用户ID' ,
`ACTION`  VARCHAR(100) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '动作描述' ,
`LOG_TIME`  DATETIME NULL DEFAULT NULL COMMENT '登录描述' ,
PRIMARY KEY (`LOG_ID`)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='用户日志'

;

-- ----------------------------
-- TABLE STRUCTURE FOR `SYS_USER_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER_ROLE`;
CREATE TABLE `SYS_USER_ROLE` (
`ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NOT NULL ,
`ROLE_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL ,
`USER_ID`  VARCHAR(20) CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI NULL DEFAULT NULL COMMENT '用户编号' ,
PRIMARY KEY (`ID`)
)
ENGINE=INNODB
DEFAULT CHARACTER SET=UTF8 COLLATE=UTF8_GENERAL_CI
COMMENT='人员角色'

;

INSERT INTO `sys_user` (`USER_ID`,`LOGIN_ID`,`NAME`,`MOBILE`,`EMAIL`,`PASSWORD`,`STATE`,`CREATE_DATE`,`LOGIN_TIME`,`LOGIN_IP`,`IS_LOGIN`) VALUES ('1001','admin','系统管理员','','','211e4e6efefe582a2f39f1be09f9bd670efab945439566148a1a42fe8166719226f8f66bd8b5b3f4','1','2017-11-11','2017-11-22 10:22:54','10.0.2.251','Y');
INSERT INTO `sys_user_role`(`ID`, `ROLE_ID`, `USER_ID`) VALUES ('1', '1', '1001');



