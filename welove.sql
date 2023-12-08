/*
Navicat MySQL Data Transfer

Source Server         : class_fee
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : welove

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2023-12-08 15:40:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `admin_name` varchar(80) DEFAULT NULL,
  `admin_password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  `comment_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for email
-- ----------------------------
DROP TABLE IF EXISTS `email`;
CREATE TABLE `email` (
  `email_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `email_content` text,
  `receiver_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`email_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file_post
-- ----------------------------
DROP TABLE IF EXISTS `file_post`;
CREATE TABLE `file_post` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file_wall
-- ----------------------------
DROP TABLE IF EXISTS `file_wall`;
CREATE TABLE `file_wall` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for msg
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `msg_content` varchar(255) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `msg_list_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for msg_list
-- ----------------------------
DROP TABLE IF EXISTS `msg_list`;
CREATE TABLE `msg_list` (
  `msg_list_id` int(11) NOT NULL AUTO_INCREMENT,
  `creater_id` int(11) DEFAULT NULL,
  `associater_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`msg_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `notice_content` varchar(255) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for plate
-- ----------------------------
DROP TABLE IF EXISTS `plate`;
CREATE TABLE `plate` (
  `plate_id` int(11) NOT NULL AUTO_INCREMENT,
  `plate_title` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`plate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `plate_id` int(11) DEFAULT NULL,
  `create_id` int(11) DEFAULT NULL,
  `post_content` text,
  `post_title` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` int(11) DEFAULT NULL COMMENT '学号',
  `user_name` varchar(80) DEFAULT NULL,
  `user_password` varchar(32) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `number` varchar(20) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `user_intro` text COMMENT '个人简介',
  `declaration_love` text COMMENT '恋爱宣言',
  `password_question` varchar(255) DEFAULT NULL COMMENT '密保问题',
  `password_answer` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1529982984 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_love
-- ----------------------------
DROP TABLE IF EXISTS `user_love`;
CREATE TABLE `user_love` (
  `user1_id` int(11) DEFAULT NULL,
  `user2_id` int(11) DEFAULT NULL,
  `determine_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
