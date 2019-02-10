use `springbootdemo`;

begin;
drop table if exists logging_event_property;
drop table if exists logging_event_exception;
drop table if exists logging_event;
commit;

begin;
create table logging_event
(
  `event_id`          bigint       not null auto_increment primary key,
  `timestamp`         bigint       not null,
  `formatted_message` text         not null,
  `logger_name`       varchar(255) not null,
  `level_string`      varchar(255) not null,
  `thread_name`       varchar(255),
  `reference_flag`    smallint,
  `arg0`              varchar(255),
  `arg1`              varchar(255),
  `arg2`              varchar(255),
  `arg3`              varchar(255),
  `caller_filename`   varchar(255) not null,
  `caller_class`      varchar(255) not null,
  `caller_method`     varchar(255) not null,
  `caller_line`       char(4)      not null
);
commit;

begin;
create table logging_event_property
(
  `event_id`     bigint       not null,
  `mapped_key`   varchar(255) not null,
  `mapped_value` text,
  primary key (`event_id`, `mapped_key`),
  foreign key (`event_id`) references logging_event (`event_id`)
);
commit;

begin;
create table logger_event_exception
(
  `event_id`   bigint       not null,
  `i`          smallint     not null,
  `trace_line` varchar(255) not null,
  primary key (`event_id`, `i`),
  foreign key (`event_id`) references logging_event (`event_id`)
);
commit;