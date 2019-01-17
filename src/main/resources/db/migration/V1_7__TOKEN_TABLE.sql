create table `token` (
  token_id int primary key AUTO_INCREMENT NOT NULL,
  user_id int not null,
  create_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  constraint fk_token_userid foreign key (user_id) references user(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;