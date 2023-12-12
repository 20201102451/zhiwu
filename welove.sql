/*
Navicat MySQL Data Transfer

Source Server         : ttt
Source Server Version : 80027
Source Host           : localhost:3306
Source Database       : welove

Target Server Type    : MYSQL
Target Server Version : 80027
File Encoding         : 65001

Date: 2023-12-12 16:23:01
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '1529982984', '2', '这是第一条测试评论发布');
INSERT INTO `comment` VALUES ('2', '1529982984', '2', '这是第二条测试评论发布');
INSERT INTO `comment` VALUES ('3', '1529982984', '1', '这是第三条测试评论');
INSERT INTO `comment` VALUES ('4', '1529982984', '3', '这是第一条测试评论------');
INSERT INTO `comment` VALUES ('5', '1529982984', '1', 'djavjudvajwd ');
INSERT INTO `comment` VALUES ('6', '1529982984', '17', 'shi');

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of email
-- ----------------------------
INSERT INTO `email` VALUES ('9', '1529982985', '这是李丁测试', '1529982984');
INSERT INTO `email` VALUES ('10', '1529982984', '', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of file_wall
-- ----------------------------
INSERT INTO `file_wall` VALUES ('43', '1529982984', 'http://localhost:9090/file_wall/get/f69968af1498434b8dc074ac5e1493b6.jpg', '2023-12-09 17:03:17', '6cf6246165138a3d87941f9b2dcc8c2b');
INSERT INTO `file_wall` VALUES ('45', '1529982984', 'http://localhost:9090/file_wall/get/601a07e7d2944be2b20546eb234a1515.png', '2023-12-12 14:26:49', 'd038b7867cb139b73d8ed8d507c7ba59');
INSERT INTO `file_wall` VALUES ('46', '1529982984', 'http://localhost:9090/file_wall/get/0603a57d7c5340828944db8822557159.jpg', '2023-12-12 16:22:00', 'e0968e81edb9e9fed9b3b933884448c0');

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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('24', '1', 'oik;jabfdokbaikjdbakwbdkjabkwedbkawjbdkjabwjkdawd', '2023-12-09 17:49:31');
INSERT INTO `notice` VALUES ('25', '1', 'dlkjabkda', '2023-12-11 22:46:34');

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('3', '0', '1529982984', '阿里看到哪里到哪里我大拉到奥兰多看那里我电脑兰德里的的阿文大阿达啊', '空间打开大王大啊');
INSERT INTO `post` VALUES ('8', '0', '1529982984', '这是第六条测试内容', '这是第六条测试标题');
INSERT INTO `post` VALUES ('9', '0', '1529982984', '这是第八条帖子的内容！！！！！！----测试', '这是第七条帖子的标题');
INSERT INTO `post` VALUES ('10', '0', '1529982984', '这是第八条帖子的内容-----------测试', '这是第八条帖子的标题');
INSERT INTO `post` VALUES ('11', '0', '1529982984', '这是第九条帖子的内容嘻嘻哈哈', '这是第九条帖子的标题');
INSERT INTO `post` VALUES ('12', '0', '1529982984', '这是第十条测试帖子内容', '这是第十条测试帖子标题');
INSERT INTO `post` VALUES ('13', '0', '1529982984', '这是第十一条测试帖子内容！！！', '这是第十一条测试帖子');
INSERT INTO `post` VALUES ('14', '0', '1529982984', '这是第n个测试帖子，测试全局状态', '这是一个测试帖子');
INSERT INTO `post` VALUES ('15', '1', '1529982984', '打卢克拿到了你忘了看到你拉开那里的那完了纳杜拉的', '嗲货币的哇必备的');
INSERT INTO `post` VALUES ('16', '0', '1529982985', 'zoiehoidbiabidbaiubwiudbiuabidbaibdiad', '这是测试的内容-----user李丁');
INSERT INTO `post` VALUES ('18', '0', '1529982984', 'daoiianodnoawd ', 'odahnondawd ');
INSERT INTO `post` VALUES ('19', '1', '1529982984', 'da3d21a3w1d3aw1d3aw16d351a563wda', 'daolpikdbaklwd');

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
INSERT INTO `user` VALUES ('1529982984', '20201102451', 'user1', '123456', '1', '18647377201', '110', '110', 'http://localhost:9090/file_wall/get/70e7243c280f449e99af1b1d9e419009.png', 'adlkboanwkdnalwdouqhg3oirwbdioqauwbdiubaiudbiuawbdiuabwiudbaiwubdiuabwdiubaiudbaiubdiuabdibaiudbiad', 'kjaibikdbaikwbdikjab', '这是一个问题', '这是一个答案');
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
