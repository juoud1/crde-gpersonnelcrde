--CREATE SCHEMA IF NOT EXISTS;
CREATE TABLE IF NOT EXISTS affectation (id SERIAL PRIMARY KEY, 
reference_affect VARCHAR(50), 
emplacement_affect VARCHAR(50),
date_debut_affect TIMESTAMP(9) WITH TIME ZONE,
date_fin_affect TIMESTAMP(9) WITH TIME ZONE,
date_prise_service TIMESTAMP(9) WITH TIME ZONE,
info_supplementaires VARCHAR(60),  
employe_id long,
lieu_affectation_id long,
fonction_id long,
affect_creee_le TIMESTAMP(9) WITH TIME ZONE, affect_creee_par VARCHAR(15), 
affect_modifiee_le TIMESTAMP(9) WITH TIME ZONE, affect_modifiee_par VARCHAR(15),
constraint fk_affect_emp FOREIGN key (employe_id) references employe(id),
constraint fk_affect_lieu_affect FOREIGN key (lieu_affectation_id) references lieu_affectation(id),
constraint fk_affect_fonction FOREIGN key (fonction_id) references fonction(id)
);

insert into affectation (ID, reference_affect, emplacement_affect, date_debut_affect, date_fin_affect, date_prise_service, info_supplementaires, employe_id, lieu_affectation_id, fonction_id) 
values (1L, 'Decret 1/2019', 'Présidence', '20190130', '20190130', '20190205', 'Décret présidentiel', 1L, 1L, 1L);
insert into affectation (ID, reference_affect, emplacement_affect, date_debut_affect, date_fin_affect, date_prise_service, info_supplementaires, employe_id, lieu_affectation_id, fonction_id) 
values (2L, 'Décision 06/2023', 'Aeroport de BG', '20230607', '20230607', '20230617', 'Note de service', 2L, 3L, 4L);
insert into affectation (ID, reference_affect, emplacement_affect, date_debut_affect, date_fin_affect, date_prise_service, info_supplementaires, employe_id, lieu_affectation_id, fonction_id) 
values (3L, 'Arrt 11/2022', 'Aeroport de BG', '20221115', '20221115', '20230105', 'Note de service', 3L, 2L, 6L);