/*
 Navicat MySQL Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : hotel

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 08/03/2019 10:58:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accommodation
-- ----------------------------
DROP TABLE IF EXISTS `accommodation`;
CREATE TABLE `accommodation`  (
  `id` int(11) NOT NULL,
  `commission` double NOT NULL,
  `customer_evaluate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `customer_statisfaction` int(11) NOT NULL,
  `damage` double NOT NULL,
  `damage_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tax` double NOT NULL,
  `member_member_id` int(11) NULL DEFAULT NULL,
  `room_detail_room_number` int(11) NULL DEFAULT NULL,
  `member_id` int(11) NOT NULL,
  `room_number` int(11) NOT NULL,
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK7pr8fw9cmdqmuafvw0ab4irpe`(`member_member_id`) USING BTREE,
  INDEX `FKk5a85now5lmxj73apquyxc3n5`(`room_detail_room_number`) USING BTREE,
  CONSTRAINT `FK7pr8fw9cmdqmuafvw0ab4irpe` FOREIGN KEY (`member_member_id`) REFERENCES `member` (`member_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKk5a85now5lmxj73apquyxc3n5` FOREIGN KEY (`room_detail_room_number`) REFERENCES `room_detail` (`room_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accommodation
-- ----------------------------
INSERT INTO `accommodation` VALUES (3, 3, '公司电话', 5, 3, '、photo', 2, 1, 501, 1, 501, NULL);
INSERT INTO `accommodation` VALUES (4, 3, '公司电话', 5, 3, '、photo', 2, 1, 501, 1, 501, '2019-03-02');

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (16);
INSERT INTO `hibernate_sequence` VALUES (16);
INSERT INTO `hibernate_sequence` VALUES (16);
INSERT INTO `hibernate_sequence` VALUES (16);

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel`  (
  `hotelid` int(11) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotel_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `star_level` int(11) NOT NULL,
  `landline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `leader` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wechat` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`hotelid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES (888, '江西南昌', '品牌', '北京天安酒店', 4, '0791-4343454', '245502345@qq.com', '张三', '131523459', '散仙');

-- ----------------------------
-- Table structure for lose_goods
-- ----------------------------
DROP TABLE IF EXISTS `lose_goods`;
CREATE TABLE `lose_goods`  (
  `date` datetime(0) NOT NULL,
  `dispose` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dispose_worker` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `result` tinyint(4) NOT NULL,
  `worker` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `member_member_id` int(11) NULL DEFAULT NULL,
  `room_detail_room_number` int(11) NULL DEFAULT NULL,
  `member_id` int(11) NOT NULL,
  `room_number` int(11) NOT NULL,
  PRIMARY KEY (`date`) USING BTREE,
  INDEX `FK93ks3lk48cukjwr49hax41iev`(`member_member_id`) USING BTREE,
  INDEX `FKh6s7g83glqsf6rjs8k1oyk1ih`(`room_detail_room_number`) USING BTREE,
  CONSTRAINT `FK93ks3lk48cukjwr49hax41iev` FOREIGN KEY (`member_member_id`) REFERENCES `member` (`member_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKh6s7g83glqsf6rjs8k1oyk1ih` FOREIGN KEY (`room_detail_room_number`) REFERENCES `room_detail` (`room_number`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lose_goods
-- ----------------------------
INSERT INTO `lose_goods` VALUES ('2019-03-02 00:00:00', '1', '1', 1, '1', 1, 501, 1, 501);

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `member_id` int(11) NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificate_front_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificate_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificate_reverse_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `certificate_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `credit` int(11) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` tinyint(4) NOT NULL,
  `tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, '江西南昌', '1999-1-10', '/photo', '431223143321423232', '/photo', 'a', 100, '1324124@qq.com', '李四', 0, '1324311343', 'hello');
INSERT INTO `member` VALUES (2, '江西南昌', '1999-1-10', '/photo', '431223143321423232', '/photo', 'a', 100, '1324124@qq.com', '张三', 0, '1324311343', 'hello');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `num` int(11) NOT NULL,
  `order_number` int(11) NOT NULL,
  `add_server` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bits` tinyint(4) NOT NULL,
  `leave_date` datetime(0) NULL DEFAULT NULL,
  `live_date` datetime(0) NULL DEFAULT NULL,
  `order_date` datetime(0) NULL DEFAULT NULL,
  `member_member_id` int(11) NULL DEFAULT NULL,
  `room_hotelid` int(11) NULL DEFAULT NULL,
  `room_room_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`num`) USING BTREE,
  INDEX `FK4shribj1m1wfamr4xvfhv1nof`(`member_member_id`) USING BTREE,
  INDEX `FK5wbkk979rcaxw4fe45bkpj8wm`(`room_hotelid`, `room_room_type`) USING BTREE,
  CONSTRAINT `FK4shribj1m1wfamr4xvfhv1nof` FOREIGN KEY (`member_member_id`) REFERENCES `member` (`member_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK5wbkk979rcaxw4fe45bkpj8wm` FOREIGN KEY (`room_hotelid`, `room_room_type`) REFERENCES `room` (`hotelid`, `room_type`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `hotelid` int(11) NOT NULL,
  `room_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `general_price` double NOT NULL,
  `general_tax` double NOT NULL,
  `group_price` double NOT NULL,
  `group_tax` double NOT NULL,
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `hotel_hotelid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`hotelid`, `room_type`) USING BTREE,
  INDEX `FKnp5i2rbdqrns545c9r75r6k8x`(`hotel_hotelid`) USING BTREE,
  CONSTRAINT `FKnp5i2rbdqrns545c9r75r6k8x` FOREIGN KEY (`hotel_hotelid`) REFERENCES `hotel` (`hotelid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (888, 'a', 25, 2, 6, 6, '/photo/*', 3, 888);
INSERT INTO `room` VALUES (888, 'b', 25, 2, 6, 6, '/photo/*', 3, 888);

-- ----------------------------
-- Table structure for room_detail
-- ----------------------------
DROP TABLE IF EXISTS `room_detail`;
CREATE TABLE `room_detail`  (
  `room_number` int(11) NOT NULL,
  `feacture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wifi` tinyint(4) NOT NULL,
  `window` tinyint(4) NOT NULL,
  `window_orientation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `room_hotelid` int(11) NULL DEFAULT NULL,
  `room_room_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotelid` int(11) NOT NULL,
  `room_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `flag` tinyint(4) NOT NULL,
  PRIMARY KEY (`room_number`) USING BTREE,
  INDEX `FKduqomdm4p14g4urb4rp3841f4`(`room_hotelid`, `room_room_type`) USING BTREE,
  CONSTRAINT `FKduqomdm4p14g4urb4rp3841f4` FOREIGN KEY (`room_hotelid`, `room_room_type`) REFERENCES `room` (`hotelid`, `room_type`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room_detail
-- ----------------------------
INSERT INTO `room_detail` VALUES (501, '好大', 1, 1, '朝北', 888, 'b', 888, 'b', 1);
INSERT INTO `room_detail` VALUES (502, '好大', 1, 1, '朝北', 888, 'b', 888, 'b', 1);

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale`  (
  `hotelid` int(11) NOT NULL,
  `room_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birthday_room_sum` int(11) NOT NULL,
  `commission` int(11) NOT NULL,
  `general_sum` int(11) NOT NULL,
  `group_sum` int(11) NOT NULL,
  `married_sum` int(11) NOT NULL,
  `other_room_sum` int(11) NOT NULL,
  `propose_room_sum` int(11) NOT NULL,
  `room_hotelid` int(11) NULL DEFAULT NULL,
  `room_room_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birthday_price` double NOT NULL,
  `married_price` double NOT NULL,
  `other_price` double NOT NULL,
  `propose_price` double NOT NULL,
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`hotelid`, `room_type`) USING BTREE,
  INDEX `FKi3x34q5itthiuebgn3rsvkwo3`(`room_hotelid`, `room_room_type`) USING BTREE,
  CONSTRAINT `FKi3x34q5itthiuebgn3rsvkwo3` FOREIGN KEY (`room_hotelid`, `room_room_type`) REFERENCES `room` (`hotelid`, `room_type`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sale
-- ----------------------------
INSERT INTO `sale` VALUES (888, 'b', 3, 6, 1, 2, 3, 6, 3, 888, 'b', 321, 200, 2.3, 213, NULL);

-- ----------------------------
-- Table structure for server
-- ----------------------------
DROP TABLE IF EXISTS `server`;
CREATE TABLE `server`  (
  `callid` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `satisfaction` int(11) NOT NULL,
  `appraisal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `call_server` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `call_server_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `call_time` datetime(0) NULL DEFAULT NULL,
  `finish_status` tinyint(4) NOT NULL,
  `finish_time` int(11) NOT NULL,
  `response_status` tinyint(4) NOT NULL,
  `response_time` int(11) NOT NULL,
  `workid` int(11) NOT NULL,
  `accommodation_id` int(11) NULL DEFAULT NULL,
  `staff_workid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`callid`) USING BTREE,
  INDEX `FKg7j9w5kh5u3q6u6984r6xpmbw`(`accommodation_id`) USING BTREE,
  INDEX `FKprevqi98mr9xgvnpltunv3rnt`(`staff_workid`) USING BTREE,
  CONSTRAINT `FKg7j9w5kh5u3q6u6984r6xpmbw` FOREIGN KEY (`accommodation_id`) REFERENCES `accommodation` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKprevqi98mr9xgvnpltunv3rnt` FOREIGN KEY (`staff_workid`) REFERENCES `staff` (`workid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of server
-- ----------------------------
INSERT INTO `server` VALUES (1, 3, 1, '1', 'er', 'e', '2019-03-02 13:21:54', 1, 1, 1, 1, 11, 3, NULL);
INSERT INTO `server` VALUES (2, 3, 1, '1', 'er', 'e', '2019-03-02 13:22:27', 1, 1, 1, 1, 15, 3, NULL);
INSERT INTO `server` VALUES (3, 3, 1, '1', 'er', 'e', '2019-03-02 13:24:02', 1, 1, 1, 1, 15, 3, NULL);
INSERT INTO `server` VALUES (4, 3, 1, '1', 'er', 'e', '2019-03-02 13:25:20', 1, 1, 1, 1, 15, 3, 15);
INSERT INTO `server` VALUES (5, 3, 1, '1', 'er', 'e', '2019-03-02 13:25:46', 1, 1, 1, 1, 13, 3, NULL);

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `workid` int(11) NOT NULL,
  `cardid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `hotel_hotelid` int(11) NULL DEFAULT NULL,
  `hotelid` int(11) NOT NULL,
  PRIMARY KEY (`workid`) USING BTREE,
  INDEX `FKcx7mbir6i8t1u3s6rr4vu6vep`(`hotel_hotelid`) USING BTREE,
  CONSTRAINT `FKcx7mbir6i8t1u3s6rr4vu6vep` FOREIGN KEY (`hotel_hotelid`) REFERENCES `hotel` (`hotelid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (11, '213', '123', '321', 888, 888);
INSERT INTO `staff` VALUES (14, '213', '123', '321', 888, 888);
INSERT INTO `staff` VALUES (15, '213', '123', '321', 888, 888);

SET FOREIGN_KEY_CHECKS = 1;
