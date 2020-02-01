create database if not exists teams;

use teams;

drop tables if exists members;
drop tables if exists teams;

create table teams (
    id int(10) not null auto_increment,
    name varchar(50) not null,
    primary key(id)

);

create table members (
    id int(10) not null auto_increment,
    first_name varchar(25) not null,
    last_name varchar(30) not null,
    team_id int(10) not null,
    primary key(id),
    foreign key(team_id) references teams(id)

);
