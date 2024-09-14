create table patients (
    id bigserial not null,
    name varchar(100) not null,
    email varchar(100) not null unique,
    telephone varchar(20) not null,
    cpf varchar(11) not null unique,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    cep varchar(9) not null,
    city varchar(100) not null,
    uf varchar(2) not null,
    number varchar(20),
    complement varchar(100),
    active boolean,

    primary key(id)
);