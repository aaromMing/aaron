/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : chat

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-07-07 10:47:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `get_id` decimal(10,0) DEFAULT NULL,
  `send_id` decimal(10,0) DEFAULT NULL,
  `message` varchar(1024) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for onlineuser
-- ----------------------------
DROP TABLE IF EXISTS `onlineuser`;
CREATE TABLE `onlineuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `ip` varchar(30) DEFAULT NULL,
  `lasttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `State` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of onlineuser
-- ----------------------------
INSERT INTO `onlineuser` VALUES ('1', '0', null, '2015-07-06 22:50:16', '0');
INSERT INTO `onlineuser` VALUES ('2', '1', '169.254.156.25', '2015-07-06 23:36:24', '0');

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  `pass` varchar(10) CHARACTER SET gbk DEFAULT NULL,
  `petname` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  `mail` varchar(50) CHARACTER SET gbk DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `power` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'aarona', '123', 'ming', '376043895@qq.com', '1', '普通用户');
