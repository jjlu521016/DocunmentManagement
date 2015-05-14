/*
Navicat MySQL Data Transfer

Source Server         : liyanxing-PC
Source Server Version : 50521
Source Host           : localhost:3306
Source Database       : ntp

Target Server Type    : MYSQL
Target Server Version : 50521
File Encoding         : 65001

Date: 2014-07-06 17:58:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin_table`
-- ----------------------------
DROP TABLE IF EXISTS `admin_table`;
CREATE TABLE `admin_table` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) NOT NULL,
  `admin_password` varchar(255) NOT NULL,
  `admin_eamil` varchar(255) DEFAULT NULL,
  `admin_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_table
-- ----------------------------
INSERT INTO `admin_table` VALUES ('1', 'admin', 'admin', null, null);

-- ----------------------------
-- Table structure for `coursetype_table`
-- ----------------------------
DROP TABLE IF EXISTS `coursetype_table`;
CREATE TABLE `coursetype_table` (
  `coursetype_id` int(11) NOT NULL AUTO_INCREMENT,
  `coursetype_name` varchar(255) NOT NULL,
  PRIMARY KEY (`coursetype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of coursetype_table
-- ----------------------------

-- ----------------------------
-- Table structure for `courseware_table`
-- ----------------------------
DROP TABLE IF EXISTS `courseware_table`;
CREATE TABLE `courseware_table` (
  `courseware_id` int(11) NOT NULL AUTO_INCREMENT,
  `courseware_name` varchar(255) NOT NULL,
  `courseware_path` varchar(255) NOT NULL,
  `coursewar_size` varchar(255) DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  `courseware_size` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`courseware_id`),
  KEY `courseware_table_ibfk_1` (`course_id`),
  CONSTRAINT `courseware_table_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_table` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courseware_table
-- ----------------------------

-- ----------------------------
-- Table structure for `course_table`
-- ----------------------------
DROP TABLE IF EXISTS `course_table`;
CREATE TABLE `course_table` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_code` varchar(255) NOT NULL,
  `course_overview` longtext,
  `course_schedule` varchar(255) DEFAULT NULL,
  `course_analyze` longtext,
  `course_stime` datetime NOT NULL,
  `course_etime` datetime NOT NULL,
  `coursetype_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `user_id` (`user_id`),
  KEY `coursetype_id` (`coursetype_id`),
  CONSTRAINT `course_table_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`) ON DELETE SET NULL,
  CONSTRAINT `course_table_ibfk_3` FOREIGN KEY (`coursetype_id`) REFERENCES `coursetype_table` (`coursetype_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_table
-- ----------------------------

-- ----------------------------
-- Table structure for `course_user_table`
-- ----------------------------
DROP TABLE IF EXISTS `course_user_table`;
CREATE TABLE `course_user_table` (
  `course_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`course_user_id`),
  KEY `course_id` (`course_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `course_user_table_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_table` (`course_id`),
  CONSTRAINT `course_user_table_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_user_table
-- ----------------------------

-- ----------------------------
-- Table structure for `exercise_table`
-- ----------------------------
DROP TABLE IF EXISTS `exercise_table`;
CREATE TABLE `exercise_table` (
  `exercise_id` int(11) NOT NULL AUTO_INCREMENT,
  `exercise_name` varchar(255) NOT NULL,
  `exercise_subject` varchar(255) NOT NULL,
  `exercise_answer` varchar(255) NOT NULL,
  `exercise_qtype` varchar(255) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`exercise_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `exercise_table_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_table` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exercise_table
-- ----------------------------

-- ----------------------------
-- Table structure for `exercise_user_table`
-- ----------------------------
DROP TABLE IF EXISTS `exercise_user_table`;
CREATE TABLE `exercise_user_table` (
  `exercise_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `exercise_user_reply` varchar(255) DEFAULT NULL,
  `exercise_user_score` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `exercise_id` int(11) NOT NULL,
  PRIMARY KEY (`exercise_user_id`),
  KEY `user_id` (`user_id`),
  KEY `exercise_id` (`exercise_id`),
  CONSTRAINT `exercise_user_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`),
  CONSTRAINT `exercise_user_table_ibfk_2` FOREIGN KEY (`exercise_id`) REFERENCES `exercise_table` (`exercise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exercise_user_table
-- ----------------------------

-- ----------------------------
-- Table structure for `forum_table`
-- ----------------------------
DROP TABLE IF EXISTS `forum_table`;
CREATE TABLE `forum_table` (
  `forum_id` int(11) NOT NULL AUTO_INCREMENT,
  `forum_title` varchar(255) NOT NULL,
  `forum_content` longtext NOT NULL,
  `forum_time` datetime DEFAULT NULL,
  `forum_reply_number` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`forum_id`),
  KEY `course_id` (`course_id`),
  KEY `forum_table_ibfk_1` (`user_id`),
  CONSTRAINT `forum_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`) ON DELETE SET NULL,
  CONSTRAINT `forum_table_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course_table` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forum_table
-- ----------------------------

-- ----------------------------
-- Table structure for `forum_user_table`
-- ----------------------------
DROP TABLE IF EXISTS `forum_user_table`;
CREATE TABLE `forum_user_table` (
  `forum_user_id` int(11) NOT NULL AUTO_INCREMENT,
  `forum_user_time` datetime DEFAULT NULL,
  `forum_user_content` longtext NOT NULL,
  `forum_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`forum_user_id`),
  KEY `forum_id` (`forum_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `forum_user_table_ibfk_1` FOREIGN KEY (`forum_id`) REFERENCES `forum_table` (`forum_id`),
  CONSTRAINT `forum_user_table_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forum_user_table
-- ----------------------------

-- ----------------------------
-- Table structure for `notice_table`
-- ----------------------------
DROP TABLE IF EXISTS `notice_table`;
CREATE TABLE `notice_table` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(255) NOT NULL,
  `notice_content` longtext NOT NULL,
  `notice_time` datetime NOT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of notice_table
-- ----------------------------

-- ----------------------------
-- Table structure for `score_table`
-- ----------------------------
DROP TABLE IF EXISTS `score_table`;
CREATE TABLE `score_table` (
  `score_id` int(11) NOT NULL AUTO_INCREMENT,
  `score_score` int(11) NOT NULL,
  `score_state` varchar(255) NOT NULL,
  `score_suggest` longtext,
  `user_id` int(11) NOT NULL,
  `exercise_id` int(11) NOT NULL,
  PRIMARY KEY (`score_id`),
  KEY `user_id` (`user_id`),
  KEY `exercise_id` (`exercise_id`),
  CONSTRAINT `score_table_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`),
  CONSTRAINT `score_table_ibfk_2` FOREIGN KEY (`exercise_id`) REFERENCES `exercise_table` (`exercise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score_table
-- ----------------------------

-- ----------------------------
-- Table structure for `task_table`
-- ----------------------------
DROP TABLE IF EXISTS `task_table`;
CREATE TABLE `task_table` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_stime` datetime NOT NULL,
  `task_etime` datetime NOT NULL,
  `task_state` varchar(255) NOT NULL,
  `exercise_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`task_id`),
  KEY `exercise_id` (`exercise_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `task_table_ibfk_1` FOREIGN KEY (`exercise_id`) REFERENCES `exercise_table` (`exercise_id`),
  CONSTRAINT `task_table_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_table
-- ----------------------------

-- ----------------------------
-- Table structure for `user_table`
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_sex` varchar(255) DEFAULT NULL,
  `user_head` varchar(255) DEFAULT NULL,
  `user_birthday` datetime DEFAULT NULL,
  `user_type` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_table
-- ----------------------------

-- ----------------------------
-- Table structure for `video_table`
-- ----------------------------
DROP TABLE IF EXISTS `video_table`;
CREATE TABLE `video_table` (
  `video_id` int(11) NOT NULL AUTO_INCREMENT,
  `video_name` varchar(255) NOT NULL,
  `video_path` varchar(255) NOT NULL,
  `video_size` varchar(255) DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`video_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `video_table_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course_table` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of video_table
-- ----------------------------
