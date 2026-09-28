create table registration(
	id bigint not null auto_increment,
	date datetime not null,
	attendance boolean not null,
	participant_id bigint not null,
	event_id bigint not null,
	primary key (id),
	foreign key (participant_id) references participant (id),
	foreign key (event_id) references event (id)
);