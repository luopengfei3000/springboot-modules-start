SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` varchar(64) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123456', '13253517972');
INSERT INTO `t_user` VALUES ('2', '欣欣', '123456', '13253517973');
INSERT INTO `t_user` VALUES ('4', '康永红', '123456', '13253517970');
INSERT INTO `t_user` VALUES ('7', '网名', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('8', '测试', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('9', 'wangmin', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('10', '管理员', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('11', '用户测试', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('12', '网络', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('13', '美工设计', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('14', '前端开发', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('15', '后台开发', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('16', '渠道开发', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('17', '测试用户', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('18', '系统用户', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('19', '系统维护', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('20', '系统架构', '13253517970', '13253517970');
INSERT INTO `t_user` VALUES ('21', '软件开发', '13253517970', '13253517970');
