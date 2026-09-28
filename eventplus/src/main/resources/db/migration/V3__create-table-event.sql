create table event(
	id bigint not null auto_increment,
	title varchar (100) not null,
	description varchar (200),
	start_date datetime not null,
	end_date datetime not null,
	value double not null,
	category_id bigint not null,
	organizer_id bigint not null,
	primary key (id),
	constraint fk_category_id foreign key (category_id) references category (id),
	constraint fk_organizer_id foreign key (organizer_id) references organizer (id)
);