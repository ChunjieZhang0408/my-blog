/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.26 : Database - mybatisplus
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mybatisplus` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mybatisplus`;

/*Table structure for table `article_comment` */

DROP TABLE IF EXISTS `article_comment`;

CREATE TABLE `article_comment` (
  `id` varchar(50) NOT NULL,
  `comment_content` mediumtext,
  `reply_comment_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '回复评论id',
  `comment_user_id` varchar(50) NOT NULL,
  `support` int(11) DEFAULT NULL COMMENT '支持（赞）',
  `oppose` int(11) DEFAULT NULL COMMENT '反对（踩）',
  `comment_user_name` varchar(50) NOT NULL,
  `comment_user_avatar` varchar(255) DEFAULT NULL COMMENT '用户头像地址',
  `create_time` varchar(50) DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `article_info` */

DROP TABLE IF EXISTS `article_info`;

CREATE TABLE `article_info` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章标题',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `cover_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章封面图片',
  `qrcode_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章专属二维码地址',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '文章内容',
  `top` tinyint(1) DEFAULT '0' COMMENT '是否置顶',
  `type_id` bigint(20) unsigned NOT NULL COMMENT '类型',
  `status` tinyint(1) unsigned DEFAULT '1' COMMENT '状态',
  `recommended` tinyint(1) unsigned DEFAULT '0' COMMENT '是否推荐',
  `original` tinyint(1) unsigned DEFAULT '1' COMMENT '是否原创',
  `description` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章简介，最多200字',
  `keywords` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章关键字，优化搜索',
  `comment` tinyint(1) unsigned DEFAULT '1' COMMENT '是否开启评论',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `article_type` */

DROP TABLE IF EXISTS `article_type`;

CREATE TABLE `article_type` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `status` tinyint(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `member_info` */

DROP TABLE IF EXISTS `member_info`;

CREATE TABLE `member_info` (
  `id` varchar(50) NOT NULL COMMENT 'ID',
  `nickname` varchar(36) DEFAULT NULL COMMENT '昵称',
  `account` varchar(36) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `mobile` varchar(30) DEFAULT NULL COMMENT '电话号码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `description` varchar(125) DEFAULT NULL COMMENT '描述',
  `gender` int(2) DEFAULT NULL COMMENT '0未知，1男，2女',
  `birthday` varchar(30) DEFAULT NULL COMMENT '生日',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `scope` int(11) DEFAULT NULL COMMENT '积分',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色id',
  `account_type` enum('USER','ADMIN') DEFAULT 'USER' COMMENT '账户类型',
  `register_time` varchar(50) DEFAULT NULL COMMENT '注册时间',
  `status` tinyint(2) DEFAULT NULL COMMENT '状态：1正常',
  `last_login_ip` varchar(25) DEFAULT NULL COMMENT '最近登录ip',
  `last_login_time` varchar(50) DEFAULT NULL COMMENT '最近登录时间',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
  `id` varchar(50) NOT NULL,
  `user_id` varchar(50) NOT NULL COMMENT '被通知的用户ID',
  `status` enum('RELEASE','NOT_RELEASE') DEFAULT 'NOT_RELEASE' COMMENT '通知状态',
  `title` varchar(200) DEFAULT NULL COMMENT '通知的标题',
  `content` mediumtext COMMENT '通知的内容',
  `create_time` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='系统公告表';

/*Table structure for table `sys_resources` */

DROP TABLE IF EXISTS `sys_resources`;

CREATE TABLE `sys_resources` (
  `id` varchar(50) NOT NULL COMMENT 'id',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `type` varchar(50) DEFAULT NULL COMMENT '资源类型 menu，button',
  `url` varchar(255) DEFAULT NULL COMMENT '连接',
  `permission` varchar(50) DEFAULT NULL COMMENT '权限',
  `parent_id` varchar(50) DEFAULT '0' COMMENT '父id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `available` tinyint(2) DEFAULT '1' COMMENT '1可用，0不可用',
  `create_time` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL COMMENT '角色名',
  `description` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `create_time` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_role_resources` */

DROP TABLE IF EXISTS `sys_role_resources`;

CREATE TABLE `sys_role_resources` (
  `id` varchar(50) NOT NULL,
  `role_id` varchar(50) NOT NULL,
  `resources_id` varchar(50) NOT NULL,
  `create_time` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `update_time` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
