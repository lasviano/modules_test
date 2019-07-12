set global innodb_file_format=BARRACUDA;

alter table bimface_file.file row_format=dynamic;
alter table bimface_file.file row_format=compressed;

alter table bimface_file.file add index index_Name(`name`(255));
