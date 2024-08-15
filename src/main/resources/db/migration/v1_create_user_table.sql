create schema if not exists users;

create table users.user (
      id bigserial primary key,
      nome varchar(100) not null,
      sobrenome varchar(100) not null,
      cpf varchar(100) not null,
      data_cadastro timestamp not null
)