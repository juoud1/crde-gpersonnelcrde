--CREATE SCHEMA IF NOT EXISTS;
CREATE TABLE IF NOT EXISTS employe (id SERIAL PRIMARY KEY, 
emp_matricule VARCHAR(15) UNIQUE, 
emp_nom VARCHAR(30),
emp_pren VARCHAR(30),
emp_civilite VARCHAR(20),
emp_telephone VARCHAR(15),
emp_email VARCHAR(30),
type_employe_id long,
emp_status_id long,
emp_lieu_affectation_id long,
emp_fonction_id long,
emp_cree_le TIMESTAMP(9) WITH TIME ZONE, 
emp_cree_par VARCHAR(15), 
emp_modifie_le TIMESTAMP(9) WITH TIME ZONE, 
emp_modifie_par VARCHAR(15),
constraint fk_emp_type_emp FOREIGN key (type_employe_id) references type_employe(id),
constraint fk_emp_status FOREIGN key (emp_status_id) references status(id),
constraint fk_emp_lieu_affect FOREIGN key (emp_lieu_affectation_id) references lieu_affectation(id),
constraint fk_emp_fonction FOREIGN key (emp_fonction_id) references fonction(id)
);
insert into employe (ID, EMP_MATRICULE, EMP_NOM, EMP_PREN, EMP_CIVILITE, EMP_TELEPHONE, EMP_EMAIL, TYPE_EMPLOYE_ID, EMP_STATUS_ID, EMP_LIEU_AFFECTATION_ID, EMP_FONCTION_ID) 
values (1L, 'M86OP582', 'W-L', 'Hri', 'Gl', '88664422', 'gahwl@min.gouv.cf', 4L, 1L, 1L, 1L);
insert into employe (ID, EMP_MATRICULE, EMP_NOM, EMP_PREN, EMP_CIVILITE, EMP_TELEPHONE, EMP_EMAIL, TYPE_EMPLOYE_ID, EMP_STATUS_ID, EMP_LIEU_AFFECTATION_ID, EMP_FONCTION_ID) 
values (2L, 'CIV0125', 'AAAAA', 'BBBBB', 'Monsieur', '2564566', 'gdjs@emp.gouv.cf', 1L, 1L, 3L, 4L);
insert into employe (ID, EMP_MATRICULE, EMP_NOM, EMP_PREN, EMP_CIVILITE, EMP_TELEPHONE, EMP_EMAIL, TYPE_EMPLOYE_ID, EMP_STATUS_ID, EMP_LIEU_AFFECTATION_ID, EMP_FONCTION_ID) 
values (3L, 'P86OP582', 'KKK', 'TUTUY', 'Lt de police', '5897566', 'l5pl@pc.gouv.cf', 2L, 1L, 2L, 6L);