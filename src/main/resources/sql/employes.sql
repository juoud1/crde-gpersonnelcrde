--CREATE SCHEMA IF NOT EXISTS;
CREATE TABLE IF NOT EXISTS employe (id SERIAL PRIMARY KEY, 
emp_matricule VARCHAR(15) UNIQUE, 
emo_nom VARCHAR(30), 
emo_pren VARCHAR(30), 
emo_civilite VARCHAR(20), 
type_employe_id long,
status_id long, 
lieu_affectation_id long,
fonction_id long,
emp_cree_le TIMESTAMP(9) WITH TIME ZONE, emp_cree_par VARCHAR(15), 
emp_modifie_le TIMESTAMP(9) WITH TIME ZONE, emp_modifie_par VARCHAR(15),
constraint fk_emp_type_emp FOREIGN key (type_employe_id) references type_employe(id),
constraint fk_emp_status FOREIGN key (status_id) references status(id),
constraint fk_emp_lieu_affect FOREIGN key (lieu_affectation_id) references lieu_affectation(id),
constraint fk_emp_fonction FOREIGN key (fonction_id) references fonction(id)
);