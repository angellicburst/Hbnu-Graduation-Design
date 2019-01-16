/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : localhost:3306
 Source Schema         : gradesign

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 16/01/2019 10:50:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acdemic
-- ----------------------------
DROP TABLE IF EXISTS `acdemic`;
CREATE TABLE `acdemic`  (
  `id` int(11) NOT NULL COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教务员姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别\r\n            ---0：男\r\n            ---1：女',
  `phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `user_Id` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_acdemic_user`(`user_Id`) USING BTREE,
  CONSTRAINT `FK_acdemic_user` FOREIGN KEY (`user_Id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教务员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `exam_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '考试地点',
  `status` int(11) DEFAULT NULL COMMENT '使用状态\r\n            ---0：使用中\r\n            ---1：未使用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考试地点信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `course` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '课程名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '课程表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `department` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '院系名称',
  `major` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专业名称',
  `class` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '院系专业班级表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for exam
-- ----------------------------
DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `exam_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '考试名称',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `course_id` int(11) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL COMMENT '考试地点',
  `start_date` datetime(0) DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime(0) DEFAULT NULL COMMENT '结束时间',
  `creater_id` int(11) DEFAULT NULL COMMENT '创建人ID',
  `create_date` datetime(0) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_exam_address`(`address_id`) USING BTREE,
  INDEX `FK_exam_course`(`course_id`) USING BTREE,
  CONSTRAINT `FK_exam_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_exam_course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考试信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for exam_class
-- ----------------------------
DROP TABLE IF EXISTS `exam_class`;
CREATE TABLE `exam_class`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `exam_id` int(11) DEFAULT NULL COMMENT '考试信息ID',
  `department_id` int(11) DEFAULT NULL COMMENT '考试班级ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_class_exam`(`exam_id`) USING BTREE,
  INDEX `FK_exam_class`(`department_id`) USING BTREE,
  CONSTRAINT `FK_class_exam` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_exam_class` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '考试班级表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for exam_teacher
-- ----------------------------
DROP TABLE IF EXISTS `exam_teacher`;
CREATE TABLE `exam_teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `exam_id` int(11) DEFAULT NULL COMMENT '考试ID',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_rela_teacher_exam`(`teacher_id`) USING BTREE,
  INDEX `FK_teacher_exam`(`exam_id`) USING BTREE,
  CONSTRAINT `FK_rela_teacher_exam` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_teacher_exam` FOREIGN KEY (`exam_id`) REFERENCES `exam` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '监考老师人员表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `permission` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 'all');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin');
INSERT INTO `role` VALUES (2, 'acdemic');
INSERT INTO `role` VALUES (3, 'teacher');
INSERT INTO `role` VALUES (4, 'student');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_permission_relation`(`permission_id`) USING BTREE,
  INDEX `FK_role_relation`(`role_id`) USING BTREE,
  CONSTRAINT `FK_permission_relation` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_role_relation` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限对应表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL COMMENT '学生ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生姓名',
  `gender` int(11) DEFAULT NULL COMMENT '学生性别\r\n            ---0：男\r\n            ---1：女',
  `phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生邮箱',
  `department_id` int(11) DEFAULT NULL COMMENT '院系ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_student_department`(`department_id`) USING BTREE,
  INDEX `FK_student_user`(`user_id`) USING BTREE,
  CONSTRAINT `FK_student_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_student_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学生表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师姓名',
  `gender` int(11) DEFAULT NULL COMMENT '教师性别\r\n            ---0：男\r\n            ---1：女',
  `phone` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '教师邮箱',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_teacher_user`(`user_id`) USING BTREE,
  CONSTRAINT `FK_teacher_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '教师表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for teacher_department
-- ----------------------------
DROP TABLE IF EXISTS `teacher_department`;
CREATE TABLE `teacher_department`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师ID',
  `department_id` int(11) DEFAULT NULL COMMENT '院系ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_rela_department`(`department_id`) USING BTREE,
  INDEX `FK_rela_teacher`(`teacher_id`) USING BTREE,
  CONSTRAINT `FK_rela_department` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_rela_teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '老师对应院系专业班级表\r\n一个老师可以不同院系不同专业不同班级' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gender` int(5) DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_date` datetime(0) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '方溢铖', 1, '18727277587', 'angellicburst@outlook.com', '湖北省宜昌市长阳土家族自治县', '2019-01-04 15:39:17', 1);
INSERT INTO `test` VALUES (2, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (3, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (4, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (5, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (6, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (7, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (8, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (9, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (10, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (11, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (12, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (13, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (14, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (15, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (16, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (17, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (18, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (19, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (20, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (21, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (22, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (23, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (24, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (25, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (26, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (27, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (28, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (29, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (30, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (31, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (32, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (33, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (34, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (35, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (36, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (37, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (38, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (39, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (40, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (41, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (42, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (43, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (44, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (45, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (46, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (47, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (48, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (49, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (50, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (51, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (52, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (53, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (54, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (55, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (56, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (57, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (58, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (59, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (60, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (61, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (62, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (63, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (64, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (65, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (66, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (67, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (68, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (69, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (70, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (71, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (72, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (73, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (74, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (75, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (76, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (77, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (78, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (79, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (80, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (81, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (82, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (83, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (84, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (85, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (86, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (87, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (88, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (89, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (90, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (91, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (92, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (93, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (94, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (95, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (96, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (97, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (98, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (99, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (100, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (101, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (102, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (103, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (104, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (105, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (106, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (107, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (108, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (109, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (110, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);
INSERT INTO `test` VALUES (111, '杨正祥', 2, '15805744196', '41601881@qq.com', '云南省 临沧地区 耿马傣族佤族自治县', '2019-01-04 15:40:39', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `status` int(11) DEFAULT NULL COMMENT '状态\r\n            0--启用\r\n            1--停用',
  `type` int(11) DEFAULT NULL COMMENT '用户类别\r\n            ---0：管理员\r\n            ---1：老师\r\n            ---2：学生',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '1', 0, NULL);
INSERT INTO `user` VALUES (2, 'acdemic', '1', 0, 0);
INSERT INTO `user` VALUES (3, 'teacher', '1', 0, 1);
INSERT INTO `user` VALUES (4, 'student', '1', 0, 2);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_6`(`user_id`) USING BTREE,
  INDEX `FK_Reference_7`(`role_id`) USING BTREE,
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色对应表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1);
INSERT INTO `user_role` VALUES (2, 2, 2);
INSERT INTO `user_role` VALUES (3, 3, 3);
INSERT INTO `user_role` VALUES (4, 4, 4);

SET FOREIGN_KEY_CHECKS = 1;
