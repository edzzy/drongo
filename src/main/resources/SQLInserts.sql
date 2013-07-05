insert into client_types (name,version,created,updated) values ('interne',0,now(),now()),('académique',0,now(),now()),('privé',0,now(),now());

insert into research_units (name,version,created,updated) values ('UMR 1087',0,now(),now());
insert into research_units (name,version,created,updated) values ('U892',0,now(),now());
insert into research_units (name,version,created,updated) values ('U306',0,now(),now());

insert into research_teams (name,version,research_unit,created,updated) values ('équipe4 - Redon',0,(select id from research_units where name='UMR 1087'),now(),now()),('équipe3 - Schott',0,(select id from research_units where name='UMR 1087'),now(),now());
insert into research_teams (name,version,research_unit,created,updated) values ('équipe1',0,(select id from research_units where name='U892'),now(),now()),('équipe2',0,(select id from research_units where name='U892'),now(),now());
insert into research_teams (name,version,research_unit,created,updated) values ('équipe8',0,(select id from research_units where name='U306'),now(),now()),('équipe9',0,(select id from research_units where name='U306'),now(),now());

