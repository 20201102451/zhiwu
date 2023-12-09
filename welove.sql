/*
Navicat MySQL Data Transfer

Source Server         : ttt
Source Server Version : 80027
Source Host           : localhost:3306
Source Database       : welove

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2023-12-09 20:14:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `admin_name` varchar(80) DEFAULT NULL,
  `admin_password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'labibobola@gmail.com', 'admin1', '123456');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `post_id` int DEFAULT NULL,
  `comment_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for email
-- ----------------------------
DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `email_id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int DEFAULT NULL,
  `email_content` text,
  `receiver_id` int DEFAULT NULL,
  PRIMARY KEY (`email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of email
-- ----------------------------

-- ----------------------------
-- Table structure for file_post
-- ----------------------------
DROP TABLE IF EXISTS `file_post`;
CREATE TABLE `file_post` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `post_id` int DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of file_post
-- ----------------------------

-- ----------------------------
-- Table structure for file_wall
-- ----------------------------
DROP TABLE IF EXISTS `file_wall`;
CREATE TABLE `file_wall` (
  `file_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of file_wall
-- ----------------------------
INSERT INTO `file_wall` VALUES ('43', '1529982984', 'http://localhost:9090/file_wall/get/f69968af1498434b8dc074ac5e1493b6.jpg', '2023-12-09 17:03:17', '6cf6246165138a3d87941f9b2dcc8c2b');

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `msg_id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int DEFAULT NULL,
  `receiver_id` int DEFAULT NULL,
  `msg_content` varchar(255) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `msg_list_id` int DEFAULT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of msg
-- ----------------------------

-- ----------------------------
-- Table structure for msg_list
-- ----------------------------
DROP TABLE IF EXISTS `msg_list`;
CREATE TABLE `msg_list` (
  `msg_list_id` int NOT NULL AUTO_INCREMENT,
  `creater_id` int DEFAULT NULL,
  `associater_id` int DEFAULT NULL,
  PRIMARY KEY (`msg_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of msg_list
-- ----------------------------

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT,
  `sender_id` int DEFAULT NULL,
  `notice_content` varchar(255) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('24', '1', 'oik;jabfdokbaikjdbakwbdkjabkwedbkawjbdkjabwjkdawd', '2023-12-09 17:49:31');

-- ----------------------------
-- Table structure for plate
-- ----------------------------
DROP TABLE IF EXISTS `plate`;
CREATE TABLE `plate` (
  `plate_id` int NOT NULL AUTO_INCREMENT,
  `plate_title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`plate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of plate
-- ----------------------------

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `plate_id` int DEFAULT NULL,
  `create_id` int DEFAULT NULL,
  `post_content` text,
  `post_title` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int unsigned NOT NULL AUTO_INCREMENT,
  `student_id` varchar(30) DEFAULT NULL COMMENT '学号',
  `user_name` varchar(80) DEFAULT NULL,
  `user_password` varchar(32) DEFAULT NULL,
  `sex` int DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `height` int DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `user_intro` text COMMENT '个人简介',
  `declaration_love` text COMMENT '恋爱宣言',
  `password_question` varchar(255) DEFAULT NULL COMMENT '密保问题',
  `password_answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1529982986 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1529982984', '20201102451', 'user1', '123456', '1', '18647377201', '110', '110', 'http://localhost:9090/file_wall/get/93a4fe044a97488ea127f225d5f4cbdf.jpg', 'adlkboanwkdnalwdouqhg3oirwbdioqauwbdiubaiudbiuawbdiuabwiudbaiwubdiuabwdiubaiudbaiubdiuabdibaiudbiad', 'kjaibikdbaikwbdikjab', '这是一个问题', '这是一个答案');
INSERT INTO `user` VALUES ('1529982985', '20201102466', '李丁', '123456', null, null, null, null, null, null, null, '这是第二个问题', ' 这是第二个答案');

-- ----------------------------
-- Table structure for user_love
-- ----------------------------
DROP TABLE IF EXISTS `user_love`;
CREATE TABLE `user_love` (
  `user1_id` int DEFAULT NULL,
  `user2_id` int DEFAULT NULL,
  `determine_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of user_love
-- ----------------------------
INSERT INTO `user_love` VALUES ('1529982984', '1529982985', '2023-12-09 19:48:42');
