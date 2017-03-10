/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50045
Source Host           : localhost:3306
Source Database       : testing_platform

Target Server Type    : MYSQL
Target Server Version : 50045
File Encoding         : 65001

Date: 2017-03-10 17:57:51
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
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of run_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `test_plan`
-- ----------------------------
DROP TABLE IF EXISTS `test_plan`;
CREATE TABLE `test_plan` (
  `id` int(10) NOT NULL auto_increment,
  `tp_name` varchar(50) default NULL COMMENT '测试计划名称',
  `url` varchar(50) default NULL COMMENT '访问url',
  `description` varchar(50) default NULL COMMENT '描述',
  `generater` varchar(100) default NULL COMMENT '测试数据生成器',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
