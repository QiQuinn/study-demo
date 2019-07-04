CREATE database IF NOT exists sercurity;
use sercurity;


DROP TABLE IF exists `security_article_type`;
CREATE TABLE `security_article_type`(
	`id` int NOT NULL AUTO_INCREMENT,
    `article_type` varchar(12) NOT NULL,
    PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ADAS
DROP TABLE IF EXISTS `security_article`;
CREATE TABLE `security_article` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext DEFAULT NULL,
  `ishander` tinyint(1) DEFAULT NULL,
  `culomn_id` int DEFAULT NULL,
  `create_date` varchar(25) not null,
  `modify_date` varchar(25) not null,
  `page_views` int not null default 0,
  PRIMARY KEY (`id`),
  foreign key (culomn_id) references security_article_type(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `security_systembasicinfo`;

CREATE TABLE `security_systembasicinfo` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `webname` varchar(255) DEFAULT NULL,
  `weblogo` varchar(255) DEFAULT NULL,
  `icpcode` varchar(255) DEFAULT NULL,
  `copyright` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `discribe` varchar(255) DEFAULT NULL,
  `indentify_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SELECT  * FROM `security_systembasicinfo`;

/*  创建栏目表 */
DROP TABLE IF EXISTS `security_colum`;
CREATE TABLE `security_colum` (
  `colunm_id` smallint(6) NOT NULL AUTO_INCREMENT,
  `colunm_name` varchar(255) DEFAULT NULL,
  `column_pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`colunm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into `security_colum` (colunm_name,column_pid) value ('前台',0);
insert into `security_colum` (colunm_name,column_pid) value ('后台',0);
insert into `security_colum` (colunm_name,column_pid) value ('移动端',1);
insert into `security_colum` (colunm_name,column_pid) value ('PC端',1);
insert into `security_colum` (colunm_name,column_pid) value ('Android',3);
insert into `security_colum` (colunm_name,column_pid) value ('IOS',3);
insert into `security_colum` (colunm_name,column_pid) value ('微信',3);
insert into `security_colum` (colunm_name,column_pid) value ('网页版',4);
insert into `security_colum` (colunm_name,column_pid) value ('客户端',4);
insert into `security_colum` (colunm_name,column_pid) value ('JAVA',2);
insert into `security_colum` (colunm_name,column_pid) value ('C++',2);
insert into `security_colum` (colunm_name,column_pid) value ('PHP',2);

insert into `security_article_type` (article_type) value('通知公告');
insert into `security_article_type` (article_type) value('热点法规');
insert into `security_article_type` (article_type) value('政策法规');
insert into `security_article_type` (article_type) value('热点稿件');

insert into security_article (`title`,content,isHander,culomn_id,createdate,modifydate) value('热烈庆祝型中国成立70周年','习大大发表讲话:dsafasfsafsafasdfasfasfasfsafadsf十大发生发撒的肥肉我去饿饿哇
f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发f十大发生发
',0,3,'2019-03-04','2019-03-21');

insert into security_article (`title`,content,isHander,culomn_id,createdate,modifydate) value('热烈庆祝型中国成立70周年','习大大发表讲话:dsafasfsafsafasdfasfasfasfsafadsf十大发生发撒的肥肉我去饿饿哇',1,3,'2019-03-04','2019-03-21');
insert into security_article (`title`,content,isHander,culomn_id,createdate,modifydate) value('关于XXX的通知','位于2019:热天耐热然而父亲的而且我发的',0,1,'2019-03-04','2019-03-21');
insert into security_article (`title`,content,isHander,culomn_id,createdate,modifydate) value('关于Xqy的通知','XQY is QiQiuinn:dsafasfsafsafasdfasfasfasfsafadsf十大发生发撒的肥肉我去饿饿哇',0,1,'2019-05-04','2019-05-21');
insert into security_article (`title`,content,isHander,culomn_id,createdate,modifydate) value('法律1','法律一',0,2,'2019-03-04','2019-03-21');
insert into security_article (`title`,content,isHander,culomn_id,createdate,modifydate) value('法律2','法律二:',0,2,'2019-11-04','2019-11-11');
insert into security_article (`title`,content,isHander,culomn_id,createdate,modifydate) value('法律3','法律三:',0,2,'2019-11-04','2019-11-11');