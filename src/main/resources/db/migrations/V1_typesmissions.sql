CREATE TABLE IF NOT EXISTS type_mission (id SERIAL PRIMARY KEY, type_miss_code VARCHAR(4) UNIQUE, type_miss VARCHAR(50), type_miss_cree_le TIMESTAMP(9) WITH TIME ZONE, type_miss_cree_par VARCHAR(15), type_miss_modifie_le TIMESTAMP(9) WITH TIME ZONE, type_miss_modifie_par VARCHAR(15));
insert into type_mission (ID, TYPE_MISS_CODE, TYPE_MISS) 
values (1L, 'MISS', 'Mission');
insert into type_mission (ID, TYPE_MISS_CODE, TYPE_MISS) 
values (2L, 'MIGP', 'Mission de groupe');
insert into type_employe (ID, TYPE_MISS_CODE, TYPE_MISS) 
values (3L, 'AUT', 'Autre');