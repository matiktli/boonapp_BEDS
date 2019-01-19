ALTER TABLE `help` DROP FOREIGN KEY fk_help_fileid;
alter table `help` MODIFY COLUMN file_id int null;