CREATE TABLE IF NOT EXISTS type_employe (id SERIAL PRIMARY KEY, type_emp_code VARCHAR(4) UNIQUE, type_emp VARCHAR(50), type_emp_cree_le TIMESTAMP(9) WITH TIME ZONE, type_emp_cree_par VARCHAR(15), type_emp_modifie_le TIMESTAMP(9) WITH TIME ZONE, type_emp_modifie_par VARCHAR(15));
insert into type_employe (ID, TYPE_EMP_CODE, TYPE_EMP) 
values (1L, 'CIV', 'Civile');
insert into type_employe (ID, TYPE_EMP_CODE, TYPE_EMP) 
values (2L, 'POL', 'Policier');
insert into type_employe (ID, TYPE_EMP_CODE, TYPE_EMP) 
values (3L, 'GEN', 'Gendarme');
insert into type_employe (ID, TYPE_EMP_CODE, TYPE_EMP)
values (4L, 'MIL', 'Militaire');
insert into type_employe (ID, TYPE_EMP_CODE, TYPE_EMP) 
values (5L, 'AUT', 'Autre');