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