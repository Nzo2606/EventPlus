create table event(
    id bigint not null auto_increment,
    title varchar(100) not null,
    description varchar(100),
    start_date datetime not null,
    end_date datetime not null,
    price decimal (10, 2),
    max_participants int,
    company_phone varchar(20) not null,
    verified tinyint not null default 1,

    street varchar(100) not null,
    neighborhood varchar(100) not null,
    zip_code varchar(100) not null,
    city varchar(100) not null,
    state char(2) not null,
    complement varchar(100),
    number varchar(20),

    category_id bigint not null,
    organizer_id bigint not null,

    primary key (id),
    foreign key (category_id) references category(id),
    foreign key (organizer_id) references organizer(id)
);