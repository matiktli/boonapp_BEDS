create table `location` (
  location_id int primary key AUTO_INCREMENT NOT NULL,
  lat DECIMAL(10, 8) NOT NULL,
  lng DECIMAL(11, 8) NOT NULL,
  name varchar(255) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;