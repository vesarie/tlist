create table Person (
    id serial primary key,
    forename varchar(50) not null,
    surname varchar(50) not null,
    email varchar(30) not null,
    password varchar(100) not null
);

create table Project (
    id serial primary key,
    person integer not null,
    name varchar(100) not null,
    foreign key (person) references Person(id)
);

create table Task (
    id serial primary key,
    name varchar(250) not null,
    priority integer not null default 4,
    schedule date,
    completed boolean not null
);

create table ProjectTask (
    project integer not null,
    task integer not null,
    foreign key (project) references Project(id),
    foreign key (task) references Task(id),
    primary key (project, task)
);
