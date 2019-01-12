create table `needy` (
  needy_id int primary key,
  name varchar(255) not null,
  type varchar(255) not null,
  location_id int not null,
  description varchar(500) null,
  notes varchar(999) null,
  constraint fk_needy_locationid foreign key (location_id) references location(location_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;