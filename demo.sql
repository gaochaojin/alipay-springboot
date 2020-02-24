CREATE TABLE `t_account` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `amount` int(11) DEFAULT NULL COMMENT '金额',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `t_message` (
  `message_id` int(11) NOT NULL COMMENT '消息id',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `amount` int(11) DEFAULT NULL COMMENT '金额',
  `state` varchar(20) DEFAULT NULL COMMENT '状态（unconfirm,confirm）',
  PRIMARY KEY (`message_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

