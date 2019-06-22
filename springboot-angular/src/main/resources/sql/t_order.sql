/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2019-06-22 09:37:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` varchar(64) DEFAULT NULL,
  `order_no` varchar(64) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `quantity` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
