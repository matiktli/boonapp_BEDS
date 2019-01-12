create table `needy_to_user` (
  needy_id int not null,
  user_id int not null,
  KEY `idx_needy_to_user` (`needy_id`,`user_id`),
  primary key(needy_id, user_id),
  constraint needy_to_user_needyid foreign key (needy_id)
  references needy(needy_id),
  constraint needy_to_user_userid foreign key (user_id)
  references user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;