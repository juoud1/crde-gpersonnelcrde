--CREATE SCHEMA IF NOT EXISTS;
CREATE TABLE IF NOT EXISTS conge (id SERIAL PRIMARY KEY, 
date_debut_conge TIMESTAMP(9) WITH TIME ZONE,
date_fin_conge TIMESTAMP(9) WITH TIME ZONE,
info_supplementaires VARCHAR(80),  
employe_id long,
conge_cree_le TIMESTAMP(9) WITH TIME ZONE, conge_cree_par VARCHAR(15), 
conge_modifie_le TIMESTAMP(9) WITH TIME ZONE, conge_modifie_par VARCHAR(15),
constraint fk_conge_emp FOREIGN key (employe_id) references employe(id)
);
insert into conge (ID, date_debut_conge, date_fin_conge, info_supplementaires, employe_id) 
values (1L, '20250812', '20250912', 'Vaccances de 1 mois avec possibilité de faire un déplacemt à ext.', 2L);