alter table event add active tinyint;
update event set active = 1;