create table `help` (
  help_id int primary key AUTO_INCREMENT NOT NULL,
  needy_id int not null,
  description varchar(500) null,
  file_id int not null,
  constraint fk_help_needyid foreign key (needy_id) references needy(needy_id),
  constraint fk_help_fileid foreign key (file_id) references file_metadata(file_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;