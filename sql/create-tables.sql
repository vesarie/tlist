-- User appears to be a reserved word
-- -> changed to UserInfo and usr
-- Needs to be updated to the docs
create table UserInfo (
    id serial primary key,
    forename varchar(50) not null,
    surname varchar(50) not null,
    email varchar(30) not null,
    password varchar(100) not null
);

create table Project (
    id serial primary key,
    usr integer not null,
    name varchar(100) not null,
    color integer not null,
    foreign key (usr) references UserInfo(id)
);

create table Label (
    id serial primary key,
    usr integer not null,
    name varchar(100) not null,
    color integer not null,
    foreign key (usr) references UserInfo(id)
);

create table Task (
    id serial primary key,
    project integer not null,
    name varchar(250) not null,
    priority integer not null,
    schedule date,
    completed boolean not null,
    foreign key (project) references Project(id)
);

create table TaskLabel (
    task integer not null,
    label integer not null,
    foreign key (task) references Task(id),
    foreign key (label) references Label(id)
);