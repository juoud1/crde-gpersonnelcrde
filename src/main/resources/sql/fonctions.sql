CREATE TABLE IF NOT EXISTS fonction (id SERIAL PRIMARY KEY, fonction_code VARCHAR(5) UNIQUE, fonction VARCHAR(50), fonction_cree_le TIMESTAMP(9) WITH TIME ZONE, fonction_cree_par VARCHAR(15), fonction_modifie_le TIMESTAMP(9) WITH TIME ZONE, fonction_modifie_par VARCHAR(15));
insert into fonction (ID, FONCTION_CODE, FONCTION) 
values (1L, 'MIN', 'Ministre');
insert into fonction (ID, FONCTION_CODE, FONCTION) 
values (2L, 'SP', 'Secrétaire particulier');
insert into fonction (ID, FONCTION_CODE, FONCTION) 
values (3L, 'ASS', 'Assistant');
insert into fonction (ID, FONCTION_CODE, FONCTION) 
values (4L, 'DIR', 'Directeur');
insert into fonction (ID, FONCTION_CODE, FONCTION)
values (5L, 'CSV', 'Chef de service');
insert into fonction (ID, FONCTION_CODE, FONCTION)
values (6L, 'CSB', 'Chef de bureau');
insert into fonction (ID, FONCTION_CODE, FONCTION)
values (7L, 'PERS', 'Personnel');
insert into fonction (ID, FONCTION_CODE, FONCTION)
values (8L, 'ACAMP', 'Aide de camp');
insert into fonction (ID, FONCTION_CODE, FONCTION)
values (9L, 'CHAUF', 'Chauffeur');
insert into fonction (ID, FONCTION_CODE, FONCTION)
values (10L, 'AUT', 'Autre');