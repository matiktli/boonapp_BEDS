create table `file_metadata` (
  file_id int primary key AUTO_INCREMENT NOT NULL,
  name varchar(500) not null,
  path varchar(999) not null,
  active bit default 1,
  unique key uk_file_metadata_path (path)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;