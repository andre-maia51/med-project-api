create table doctors (
    id bigserial not null,
    name varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    specialty varchar(100) not null,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    cep varchar(9) not null,
    city varchar(100) not null,
    uf varchar(2) not null,
    number varchar(20),
    complement varchar(100),

    primary key(id)
);