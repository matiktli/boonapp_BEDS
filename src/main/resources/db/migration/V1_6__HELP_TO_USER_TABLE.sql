create table `help_to_user` (
  help_id int not null,
  user_id int not null,
  KEY `idx_help_to_user` (`help_id`,`user_id`),
  primary key(help_id, user_id),
  constraint fk_help_to_user_helpid foreign key (help_id)
  references help(help_id),
  constraint fk_help_to_user_userid foreign key (user_id)
  references user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;