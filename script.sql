CREATE database testing_platform;


-- ----------------------------
-- Table structure for `test_plan`
-- ----------------------------
DROP TABLE IF EXISTS `test_plan`;
CREATE TABLE `test_plan` (
  `id` int(10) NOT NULL auto_increment,
  `tp_name` varchar(50) default NULL,
  `url` varchar(50) default NULL,
  `description` varchar(50) default NULL,
  `generater` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;