/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50640
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50640
File Encoding         : 65001

Date: 2021-12-12 17:12:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for att_info
-- ----------------------------
DROP TABLE IF EXISTS `att_info`;
CREATE TABLE `att_info` (
  `att_id` int(11) NOT NULL,
  `att_name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`att_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of att_info
-- ----------------------------
INSERT INTO `att_info` VALUES ('123', '管理员1', '123');

-- ----------------------------
-- Table structure for book_info
-- ----------------------------
DROP TABLE IF EXISTS `book_info`;
CREATE TABLE `book_info` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `kp1_id` int(11) DEFAULT NULL,
  `kp2_id` int(11) DEFAULT NULL,
  `shelf_id` int(11) DEFAULT NULL,
  `is_exist` int(11) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_info
-- ----------------------------
INSERT INTO `book_info` VALUES ('1', '1020301', '数据库原理', '1', '2', '3', '-1', '1');
INSERT INTO `book_info` VALUES ('2', '1010101', 'JAVA程序设计', '1', '1', '1', '3', '1');
INSERT INTO `book_info` VALUES ('3', '1010201', 'C++程序设计', '1', '1', '2', '3', '1');

-- ----------------------------
-- Table structure for borrow_info
-- ----------------------------
DROP TABLE IF EXISTS `borrow_info`;
CREATE TABLE `borrow_info` (
  `borrow_id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) DEFAULT NULL,
  `stu_id` int(11) DEFAULT NULL,
  `borrow_time` date DEFAULT NULL,
  `need_return_time` date DEFAULT NULL,
  `real_return_time` date DEFAULT NULL,
  `is_return` int(11) DEFAULT NULL,
  `cost` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`borrow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow_info
-- ----------------------------
INSERT INTO `borrow_info` VALUES ('1', '1020301', '209050919', '2021-12-11', '2022-03-11', null, '0', '0.00');

-- ----------------------------
-- Table structure for course_info
-- ----------------------------
DROP TABLE IF EXISTS `course_info`;
CREATE TABLE `course_info` (
  `course_id` int(11) NOT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_info
-- ----------------------------
INSERT INTO `course_info` VALUES ('1', '计算机');
INSERT INTO `course_info` VALUES ('2', '课程2');
INSERT INTO `course_info` VALUES ('3', '课程3');
INSERT INTO `course_info` VALUES ('4', '课程4');
INSERT INTO `course_info` VALUES ('5', '课程5');

-- ----------------------------
-- Table structure for kp1_info
-- ----------------------------
DROP TABLE IF EXISTS `kp1_info`;
CREATE TABLE `kp1_info` (
  `kp1_id` int(11) NOT NULL,
  `kp1_name` varchar(255) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`kp1_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kp1_info
-- ----------------------------
INSERT INTO `kp1_info` VALUES ('1', '高级程序设计', '1');
INSERT INTO `kp1_info` VALUES ('2', '数据库', '1');
INSERT INTO `kp1_info` VALUES ('3', '课程2子知识点1-1', '2');
INSERT INTO `kp1_info` VALUES ('4', '课程2子知识点1-2', '2');
INSERT INTO `kp1_info` VALUES ('5', '课程3子知识点1-1', '3');
INSERT INTO `kp1_info` VALUES ('6', '课程3子知识点1-2', '3');
INSERT INTO `kp1_info` VALUES ('7', '课程4子知识点1-1', '4');
INSERT INTO `kp1_info` VALUES ('8', '课程4子知识点1-2', '4');
INSERT INTO `kp1_info` VALUES ('9', '课程5子知识点1-1', '5');
INSERT INTO `kp1_info` VALUES ('10', '课程5子知识点1-2', '5');

-- ----------------------------
-- Table structure for kp2_info
-- ----------------------------
DROP TABLE IF EXISTS `kp2_info`;
CREATE TABLE `kp2_info` (
  `kp2_id` int(11) NOT NULL,
  `kp2_name` varchar(255) DEFAULT NULL,
  `kp1_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`kp2_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of kp2_info
-- ----------------------------
INSERT INTO `kp2_info` VALUES ('1', 'JAVA程序设计', '1');
INSERT INTO `kp2_info` VALUES ('2', 'C++程序设计', '1');
INSERT INTO `kp2_info` VALUES ('3', '数据库原理', '2');
INSERT INTO `kp2_info` VALUES ('4', '数据库实践', '2');
INSERT INTO `kp2_info` VALUES ('5', 'kp2-5', '3');
INSERT INTO `kp2_info` VALUES ('6', 'kp2-6', '3');
INSERT INTO `kp2_info` VALUES ('7', 'kp2-7', '4');
INSERT INTO `kp2_info` VALUES ('8', 'kp2-8', '4');
INSERT INTO `kp2_info` VALUES ('9', 'kp2-9', '5');
INSERT INTO `kp2_info` VALUES ('10', 'kp2-10', '5');
INSERT INTO `kp2_info` VALUES ('11', 'kp2-11', '6');
INSERT INTO `kp2_info` VALUES ('12', 'kp2-12', '6');
INSERT INTO `kp2_info` VALUES ('13', 'kp2-13', '7');
INSERT INTO `kp2_info` VALUES ('14', 'kp2-14', '7');
INSERT INTO `kp2_info` VALUES ('15', 'kp2-15', '8');
INSERT INTO `kp2_info` VALUES ('16', 'kp2-16', '8');
INSERT INTO `kp2_info` VALUES ('17', 'kp2-17', '9');
INSERT INTO `kp2_info` VALUES ('18', 'kp2-18', '9');
INSERT INTO `kp2_info` VALUES ('19', 'kp2-19', '10');
INSERT INTO `kp2_info` VALUES ('20', 'kp2-20', '10');

-- ----------------------------
-- Table structure for nation_info
-- ----------------------------
DROP TABLE IF EXISTS `nation_info`;
CREATE TABLE `nation_info` (
  `nation_id` int(11) NOT NULL AUTO_INCREMENT,
  `nation_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`nation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of nation_info
-- ----------------------------
INSERT INTO `nation_info` VALUES ('1', '汉族');
INSERT INTO `nation_info` VALUES ('2', '少数民族');

-- ----------------------------
-- Table structure for pro_info
-- ----------------------------
DROP TABLE IF EXISTS `pro_info`;
CREATE TABLE `pro_info` (
  `pro_id` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pro_info
-- ----------------------------
INSERT INTO `pro_info` VALUES ('1', '计算机');
INSERT INTO `pro_info` VALUES ('2', '经济');
INSERT INTO `pro_info` VALUES ('3', '管理');
INSERT INTO `pro_info` VALUES ('4', '电子');

-- ----------------------------
-- Table structure for shelf_info
-- ----------------------------
DROP TABLE IF EXISTS `shelf_info`;
CREATE TABLE `shelf_info` (
  `shelf_id` int(11) NOT NULL,
  `shelf_code` varchar(255) DEFAULT NULL,
  `pos` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`shelf_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shelf_info
-- ----------------------------
INSERT INTO `shelf_info` VALUES ('0', 'K', '仓库');
INSERT INTO `shelf_info` VALUES ('1', 'A-1', '一楼');
INSERT INTO `shelf_info` VALUES ('2', 'A-2', '一楼');
INSERT INTO `shelf_info` VALUES ('3', 'A-3', '一楼');
INSERT INTO `shelf_info` VALUES ('4', 'B-1', '一楼');
INSERT INTO `shelf_info` VALUES ('5', 'B-2', '一楼');
INSERT INTO `shelf_info` VALUES ('6', 'B-3', '一楼');
INSERT INTO `shelf_info` VALUES ('7', 'C-1', '一楼');
INSERT INTO `shelf_info` VALUES ('8', 'C-2', '一楼');
INSERT INTO `shelf_info` VALUES ('9', 'C-3', '一楼');
INSERT INTO `shelf_info` VALUES ('10', 'D-1', '一楼');
INSERT INTO `shelf_info` VALUES ('11', 'D-2', '一楼');
INSERT INTO `shelf_info` VALUES ('12', 'D-3', '一楼');
INSERT INTO `shelf_info` VALUES ('13', 'E-1', '一楼');
INSERT INTO `shelf_info` VALUES ('14', 'E-2', '一楼');
INSERT INTO `shelf_info` VALUES ('15', 'E-3', '一楼');
INSERT INTO `shelf_info` VALUES ('16', 'F-1', '一楼');
INSERT INTO `shelf_info` VALUES ('17', 'F-2', '一楼');
INSERT INTO `shelf_info` VALUES ('18', 'F-3', '一楼');
INSERT INTO `shelf_info` VALUES ('19', 'G-1', null);
INSERT INTO `shelf_info` VALUES ('20', 'G-2', null);
INSERT INTO `shelf_info` VALUES ('21', 'G-3', null);
INSERT INTO `shelf_info` VALUES ('22', 'H-1', null);
INSERT INTO `shelf_info` VALUES ('23', 'H-2', null);
INSERT INTO `shelf_info` VALUES ('24', 'H-3', null);
INSERT INTO `shelf_info` VALUES ('25', 'I-1', null);
INSERT INTO `shelf_info` VALUES ('26', 'I-2', null);
INSERT INTO `shelf_info` VALUES ('27', 'I-3', null);

-- ----------------------------
-- Table structure for stu_info
-- ----------------------------
DROP TABLE IF EXISTS `stu_info`;
CREATE TABLE `stu_info` (
  `stu_id` int(11) NOT NULL,
  `pwd` varchar(30) DEFAULT NULL,
  `stu_name` varchar(5) DEFAULT NULL,
  `sex` varchar(2) DEFAULT '',
  `nation_id` int(3) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `home` varchar(50) DEFAULT NULL,
  `rx_date` date DEFAULT NULL,
  `xy_id` int(3) DEFAULT NULL,
  `post` varchar(20) DEFAULT NULL,
  `pro_id` int(3) DEFAULT NULL,
  `cost` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_info
-- ----------------------------
INSERT INTO `stu_info` VALUES ('209050918', '58178919', '鲁翔', '男', '1', '2002-01-01', '安徽', '2020-09-20', '1', '无', '1', null);
INSERT INTO `stu_info` VALUES ('209050919', '123', '罗天怡', '男', '2', '2002-04-03', '广东', '2020-09-20', '1', '无', '1', null);
INSERT INTO `stu_info` VALUES ('209050921', '233', '庞sb', '男', '1', '2002-05-28', '荷兰', '2020-09-20', '1', '开水果摊的', '1', null);

-- ----------------------------
-- Table structure for xy_info
-- ----------------------------
DROP TABLE IF EXISTS `xy_info`;
CREATE TABLE `xy_info` (
  `xy_id` int(11) NOT NULL AUTO_INCREMENT,
  `xy_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`xy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xy_info
-- ----------------------------
INSERT INTO `xy_info` VALUES ('1', '计算机学院');
INSERT INTO `xy_info` VALUES ('2', '管理学院');
INSERT INTO `xy_info` VALUES ('3', '经济学院');
INSERT INTO `xy_info` VALUES ('4', '机械学院');
INSERT INTO `xy_info` VALUES ('5', '电子学院');
INSERT INTO `xy_info` VALUES ('6', '文理学院');
