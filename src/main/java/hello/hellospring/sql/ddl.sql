drop table if exists member cascade;
create table member
(
    id bigint auto_increment,
    name varchar(255),
    primary key(id)
);

drop table if exists post cascade;
create table post
(
    id bigint auto_increment,
    title varchar(255) not null,
    content text,
    author varchar(255) not null,
    created_at timestamp not null,
    primary key(id)
);