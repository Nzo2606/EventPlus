create table organizer(
    id bigint not null,
    companyName varchar(100) not null,
    tradeName varchar(100),
    cnpj varchar(14) not null unique,
    description varchar(200),
    website varchar(200),
    company_email varchar(100) not null unique,
    company_phone varchar(20) not null,
    business_type varchar(100),
    business_sector varchar(100),
    verified tinyint not null default 0,

    primary key(id),
    foreign key(id) references users(id)
);