create table organizer(
	id bigint not null auto_increment,
	name varchar(100) not null,
	email varchar (100) not null unique,
	phone_number varchar (20) not null,
	description varchar (100),

	primary key(id)
);
