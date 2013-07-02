insert into client_types (name,version) values ('interne',0),('académique',0),('privé',0);

insert into research_units (name,version) values ('UMR 1087',0);
insert into research_units (name,version) values ('U892',0);
insert into research_units (name,version) values ('U306',0);

insert into research_teams (name,version,research_unit) values ('équipe4 - Redon',0,(select id from research_units where name='UMR 1087')),('équipe3 - Schott',0,(select id from research_units where name='UMR 1087'));
insert into research_teams (name,version,research_unit) values ('équipe1',0,(select id from research_units where name='U892')),('équipe2',0,(select id from research_units where name='U892'));
insert into research_teams (name,version,research_unit) values ('équipe8',0,(select id from research_units where name='U306')),('équipe9',0,(select id from research_units where name='U306'));

