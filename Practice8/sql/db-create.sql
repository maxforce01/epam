CREATE database test;
use test;
create table users (
id integer auto_increment primary key, 
login varchar(256) not null);
create table teams(
id integer auto_increment primary key, 
name varchar(256) not null);
create table users_teams (
user_id integer not null, 
team_id integer not null, foreign key (user_id) 
references users(id),
foreign key (team_id) references teams(id));


insert into users (login) values ("ivanov");
insert into teams (name) values ("teamA");
