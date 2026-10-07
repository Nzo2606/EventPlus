create table users(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(100) not null,
    phone_number varchar(20) not null,
    active tinyint not null default 1,

    primary key(id)
);