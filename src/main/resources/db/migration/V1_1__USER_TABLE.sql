create table `user` (
  user_id int primary key,
  first_name varchar(255) null,
  last_name varchar(255) null,
  email varchar(255) not null unique,
  password varchar(255) not null,
  location_id int not null,
  constraint fk_user_locationid foreign key (location_id) references location(location_id),
  unique key uk_user_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;