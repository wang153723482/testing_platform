SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `test_plan`
-- ----------------------------
DROP TABLE IF EXISTS `test_plan`;
CREATE TABLE `test_plan` (
  `id` int(10) NOT NULL auto_increment,
  `tp_name` varchar(50) default NULL COMMENT '测试计划名称',
  `url` varchar(200) default NULL COMMENT '访问url',
  `description` varchar(50) default NULL COMMENT '描述',
  `generater` varchar(100) default NULL COMMENT '测试数据生成器',
  `protocol` varchar(10) default NULL COMMENT '请求协议http/https',
  `server_name_ip` varchar(50) default NULL COMMENT 'ip/域名',
  `port_num` varchar(6) default NULL COMMENT '端口',
  `path` varchar(100) default NULL COMMENT '路径',
  `default_vu` int(11) default NULL COMMENT '默认的虚拟用户数',
  `default_duration` int(11) default NULL COMMENT '默认的执行时间',
  `jmx_save_path` varchar(100) default NULL COMMENT 'jmx的保存全路径',
  `create_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `run_plan`
-- ----------------------------
DROP TABLE IF EXISTS `run_plan`;
CREATE TABLE `run_plan` (
  `id` int(11) NOT NULL auto_increment,
  `tp_id` int(11) default NULL COMMENT 'test_plan id',
  `duration` int(11) default NULL COMMENT '运行持续时间',
  `users_num` int(11) default NULL COMMENT '虚拟用户数量',
  `ramp_up` int(11) default NULL,
  `jmx_path` varchar(100) default NULL,
  `jtl_path` varchar(100) default NULL,
  `log_path` varchar(100) default NULL,
  `html_path` varchar(100) default NULL,
  `create_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
