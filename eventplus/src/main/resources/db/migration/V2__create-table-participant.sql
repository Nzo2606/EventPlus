create table participant(
    id bigint not null,
    gender varchar(20) not null,
    ssn varchar(6) not null unique,

    primary key(id),
    foreign key (id) references users(id)
);