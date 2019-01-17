
create database `springbootdemo`;

use `springbootdemo`;

drop table if exists `user`;
create table `user`(
  id int(4) not null primary key auto_increment ,
  name varchar(20) comment '姓名',
  age int(4) comment '年龄'
)