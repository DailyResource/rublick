CREATE TABLE `user_wechat` (
  `id` varchar(20) NOT NULL COMMENT '标识',
  `user_id` varchar(20) DEFAULT NULL DEFAULT ''  COMMENT '用户id',
  `wechat_open_id` varchar(255) DEFAULT NULL DEFAULT '' COMMENT '微信openid',
  `wechat_union_id` varchar(255) DEFAULT NULL DEFAULT '' COMMENT '微信用户union id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户-微信关联表';