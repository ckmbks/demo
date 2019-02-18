/* 创建数据库 */
SET GLOBAL read_only = 0;


SET GLOBAL optimizer_switch = 'derived_merge=off';

CREATE DATABASE happylife DEFAULT CHARSET 'utf8mb4' COLLATE 'utf8mb4_unicode_ci';

/* 创建数据库表 */
SET SESSION FOREIGN_KEY_CHECKS = 0;

-- 用户表
CREATE TABLE user
(
  id          BIGINT         NOT NULL DEFAULT 0 COMMENT 'id',
  user_name   VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '用户名',
  password    VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '登录密码',
  phone       VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '手机号码',
  sex         INT            NOT NULL DEFAULT 0 COMMENT '用户性别',
  user_type   INT            NOT NULL DEFAULT 0 COMMENT '用户类型',
  create_user VARCHAR(50)    NOT NULL DEFAULT '' COMMENT '创建者',
  create_time DATETIME       NOT NULL DEFAULT now() COMMENT '创建时间',
  weight      DECIMAL(18, 2) NOT NULL DEFAULT 0 COMMENT '体重',
  PRIMARY KEY (id)
) COMMENT = '用户表';

-- 用户登录Token表
CREATE TABLE user_login_token
(
  token       VARCHAR(36) NOT NULL DEFAULT '' COMMENT 'Token',
  user_id     BIGINT      NOT NULL DEFAULT 0 COMMENT '用户Id',
  create_time DATETIME    NOT NULL DEFAULT now() COMMENT '创建时间',
  PRIMARY KEY (token)
) COMMENT = '用户登录Token表';

-- 角色表
CREATE TABLE role
(
  id          BIGINT      NOT NULL DEFAULT 0 COMMENT 'id',
  name        VARCHAR(50) NOT NULL DEFAULT '' COMMENT '名称',
  create_user VARCHAR(50) NOT NULL DEFAULT '' COMMENT '创建者',
  create_time DATETIME    NOT NULL DEFAULT now() COMMENT '创建时间',
  PRIMARY KEY (id)
) COMMENT = '角色表';

-- 用户角色关联表
CREATE TABLE user_role
(
  user_id BIGINT NOT NULL DEFAULT 0 COMMENT 'userId',
  role_id BIGINT NOT NULL DEFAULT 0 COMMENT 'roleId',
  PRIMARY KEY (user_id, role_id)
) COMMENT = '用户角色关联表';
