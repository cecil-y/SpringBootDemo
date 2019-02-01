create database test1;
use test1;
drop table if exists `user`;
create table `user`(
   id int(4) not null primary key auto_increment ,
   name varchar(20) comment '姓名',
   age int(4) comment '年龄'
);

create database `test2`;
use `test2`;
drop table if exists `user`;
create table `user`(
  id int(4) not null primary key auto_increment ,
  name varchar(20) comment '姓名',
  age int(4) comment '年龄'
);