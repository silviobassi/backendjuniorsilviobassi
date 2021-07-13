set foreign_key_checks = 0;

delete from sectors;
delete from collaborators;

set foreign_key_checks = 1;

alter table sectors auto_increment = 1;
alter table collaborators auto_increment = 1;

insert into sectors (description, update_at) values ('Obras', utc_timestamp);
insert into sectors (description, update_at) values ('Administração', utc_timestamp);
insert into sectors (description, update_at) values ('Operacional', utc_timestamp);
insert into sectors (description, update_at) values ('Planejamento', utc_timestamp);

insert into collaborators (name, cpf, email, date_of_birth, age, telephone, sector_id) 
values ('Bob Madson', '29883794509','silviobassi1@hotmail.com', '1972-04-02 00:00:00', 
timestampdiff(year, date_of_birth, utc_timestamp), '17996079654', 1);

insert into collaborators (name, cpf, email, date_of_birth, age, telephone, sector_id) 
values ('Nanda Tompson', '12375698756', 'luizbassi@gmail.com', '2000-06-18 00:00:00', 
timestampdiff(year, date_of_birth, utc_timestamp), '17998765234', 2);

insert into collaborators (name, cpf, email, date_of_birth, age, telephone, sector_id) 
values ('Selina Benson', '29839909889', 'cleiton@yahoo.com', '1989-08-20 00:00:00', 
timestampdiff(year, date_of_birth, utc_timestamp), '17997687865', 1);

insert into collaborators (name, cpf, email, date_of_birth, age, telephone, sector_id) 
values ('Ana Paula', '36539074798','paulaanabassi@hotmail.com', '1993-08-20 00:00:00', 
timestampdiff(year, date_of_birth, utc_timestamp), '17997863798', 2);

insert into collaborators (name, cpf, email, date_of_birth, age, telephone, sector_id)
values ('Helio Souza', '10955253870','helio2@@gmail.com', '1999-11-19 00:00:00',
timestampdiff(year, date_of_birth, utc_timestamp), '17991130699', 3);

insert into collaborators (name, cpf, email, date_of_birth, age, telephone, sector_id)
values ('Julio Rosa', '511.934.560-37','julio_rosa2@gmail.com', '1939-11-19 00:00:00',
timestampdiff(year, date_of_birth, utc_timestamp), '17991130699', 3);