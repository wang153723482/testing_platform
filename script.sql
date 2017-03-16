/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : testing_platform

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2017-03-16 14:46:44
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `create_time` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of run_plan
-- ----------------------------
INSERT INTO `run_plan` VALUES ('1', null, '2', '10', '1', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\jmx\\2017\\03\\tp_20170314203222742.jmx', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\jtl\\2017\\03\\tp_20170314203222742_14203230146.jtl', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\log\\2017\\03\\tp_20170314203222742_14203230147.log', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\html\\2017\\03\\tp_20170314203222742_14203230147', null);
INSERT INTO `run_plan` VALUES ('2', '15', '2', '10', '1', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\jmx\\2017\\03\\tp_20170314205508227.jmx', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\jtl\\2017\\03\\tp_20170314205508227_14205509298.jtl', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\log\\2017\\03\\tp_20170314205508227_14205509298.log', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\html\\2017\\03\\tp_20170314205508227_14205509298', null);
INSERT INTO `run_plan` VALUES ('3', '15', '2', '10', '1', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\jmx\\2017\\03\\tp_20170315150203888.jmx', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\jtl\\2017\\03\\tp_20170315150203888_15150203893.jtl', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\log\\2017\\03\\tp_20170315150203888_15150203893.log', 'D:\\workspace_HelloWorld\\testing_platform\\jmeter\\html\\2017\\03\\tp_20170315150203888_15150203893', null);
INSERT INTO `run_plan` VALUES ('4', '15', '1', '10', '1', '/2017/03/tp_20170315175408896.jmx', '\\2017\\03\\tp_20170315175408896_15175408951.jtl', '\\2017\\03\\tp_20170315175408896_15175408951.log', '/2017/03/tp_20170315175408896_15175408951', null);

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
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test_plan
-- ----------------------------
INSERT INTO `test_plan` VALUES ('9', 'sdfsd1', 'sdf1', 'df1', 'df22', 'http', '127.0.0.1', '80', '/index');
INSERT INTO `test_plan` VALUES ('10', 'sdfsd2', 'sdf2', 'df2', 'df22', 'http', '127.0.0.1', '80', '/index');
INSERT INTO `test_plan` VALUES ('11', 'sdfdfd', 'fdfd', 'fdfd', 'fdf', 'http', '127.0.0.1', '80', '/index');
INSERT INTO `test_plan` VALUES ('12', '的方法方法2222', 'sdfsd', 'fsdf', 'sdfs', 'http', '127.0.0.1', '80', '/index');
INSERT INTO `test_plan` VALUES ('13', 'sd士大夫撒旦发', 'sd撒旦发送地方', 'sdfsdfssssdfsdfssssdfsdfsss', 'sdfsdfsdf', 'http', '127.0.0.1', '80', '/index');
INSERT INTO `test_plan` VALUES ('14', 'sdfsdfs的', '士大夫s', 'aaaaaaa', 'sdfsdfsdf', 'http', '127.0.0.1', '80', '/index');
INSERT INTO `test_plan` VALUES ('15', 'zol', 'http://www.zol.com.cn', '中关村在线', '无', 'http', '127.0.0.1', '80', '/');
INSERT INTO `test_plan` VALUES ('16', 'sdf地方', 'http://weibo.com/ajaxlo', '234', 'sdfsdfsdf', 'http', '127.0.0.1', '80', '/index');
INSERT INTO `test_plan` VALUES ('17', '23', null, null, null, null, null, null, null);
