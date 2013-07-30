insert into client_types (name,version,created,updated) values ('interne',0,now(),now()),('académique',0,now(),now()),('privé',0,now(),now());

insert into research_units (name,version,created,updated) values ('UMR 1087',0,now(),now());
insert into research_units (name,version,created,updated) values ('U892',0,now(),now());
insert into research_units (name,version,created,updated) values ('U306',0,now(),now());

insert into research_teams (name,version,research_unit,created,updated) values ('équipe4 - Redon',0,(select id from research_units where name='UMR 1087'),now(),now()),('équipe3 - Schott',0,(select id from research_units where name='UMR 1087'),now(),now());
insert into research_teams (name,version,research_unit,created,updated) values ('équipe1',0,(select id from research_units where name='U892'),now(),now()),('équipe2',0,(select id from research_units where name='U892'),now(),now());
insert into research_teams (name,version,research_unit,created,updated) values ('équipe8',0,(select id from research_units where name='U306'),now(),now()),('équipe9',0,(select id from research_units where name='U306'),now(),now());



insert into persons (firstname,lastname, email, created, updated,registered_on, version)
values
('Édouard', 'Hirchaud', 'edouard.hirchaud@univ-nantes.fr',now(),now(),now(),0),
('Éric', 'Charpentier', 'eric.charpentier@univ-nantes.fr',now(),now(),now(),0),
('Catherine', 'Chevalier', 'catherine.chevalier@inserm.fr',now(),now(),now(),0),
('Audrey', 'Bihouée' , 'audrey.bihouee.@univ-nantes.fr',now(),now(),now(),0), 
('Raluca', 'Teusan' , 'raluca.teusan@inserm.fr',now(),now(),now(),0),
('Marine', 'Cornec' , 'marine.cornec@univ-nantes.fr',now(),now(),now(),0),
('Audrey', 'Donnart' , 'audrey.donnart@univ-nantes.fr',now(),now(),now(),0),
('Françoise', 'Gros' , 'francois.gros@univ-nantes.fr',now(),now(),now(),0),
('Jade', 'Violleau' , 'jade.violleau@univ-nantes.fr',now(),now(),now(),0),
('Laëtitia', 'Duboscq-Bidot' , 'laëtitia.duboscq-Bidot@univ-nantes.fr',now(),now(),now(),0),
('Richard', 'Redon' , 'richard.redon@inserm.fr',now(),now(),now(),0)
;



insert into pf_members (id, deleted, office) 
values
((select id from persons where email='edouard.hirchaud@univ-nantes.fr'), false, '218a'),
((select id from persons where email='eric.charpentier@univ-nantes.fr'), false, '218a'),
((select id from persons where email='catherine.chevalier@inserm.fr'), false, '110'),
((select id from persons where email='audrey.bihouee.@univ-nantes.fr'), false, '218a'),
((select id from persons where email='raluca.teusan@inserm.fr'), false, '110'),
((select id from persons where email='marine.cornec@univ-nantes.fr'), false, '110'),
((select id from persons where email='audrey.donnart@univ-nantes.fr'), false, '210'),
((select id from persons where email='jade.violleau@univ-nantes.fr'), false, '210'),
((select id from persons where email='laëtitia.duboscq-Bidot@univ-nantes.fr'), false, '210'),
((select id from persons where email='richard.redon@inserm.fr'), false, '217')
;


insert into persons (firstname,lastname, email, created, updated, registered_on,version)
values
('Gilles', 'Toumaniantz', 'Gilles.toumaniantz@univ-nantes.fr',now(),now(),now(), 0);

insert into clients (id, type,  research_team) 
values((select id from persons where email='Gilles.toumaniantz@univ-nantes.fr'),
(select id from client_types where name='interne' ),
(select id from research_teams where name='équipe4 - Redon'));

# Type d'activité
insert into activity_types (name,version, referent, created, updated) 
values
('Analyse Expression puce',0, (select id from persons where email='edouard.hirchaud@univ-nantes.fr'), now(),now()),
('Experimental Expression puce',0,(select id from persons where email='catherine.chevalier@inserm.fr'), now(),now()),
('Analyse miRNA',0,(select id from persons where email='audrey.bihouee.@univ-nantes.fr'), now(),now()),
('Experimental miRNA',0,(select id from persons where email='marine.cornec@univ-nantes.fr'), now(),now()),
('Analyse CGH',0, (select id from persons where email='eric.charpentier@univ-nantes.fr'), now(),now()),
('Experimental CGH',0,(select id from persons where email='jade.violleau@univ-nantes.fr'), now(),now()),
('Analyse RNA Seq',0, (select id from persons where email='edouard.hirchaud@univ-nantes.fr'), now(),now()),
('Experimental RNA Seq',0,(select id from persons where email='audrey.donnart@univ-nantes.fr'), now(),now())
;

insert int projects (name, created, updated, version, begin_date, closed, end_date, signature_date, main_client)
value('P1', now(),now(),0, now(), '2013-10-10', 1, NULL,now(),(select id from persons where email='Gilles.toumaniantz@univ-nantes.fr'));