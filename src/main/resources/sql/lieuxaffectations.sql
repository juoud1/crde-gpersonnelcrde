CREATE TABLE IF NOT EXISTS lieu_affectation (id SERIAL PRIMARY KEY, lieu_affect_code VARCHAR(4) UNIQUE, lieu_affect VARCHAR(50), lieu_affect_cree_le TIMESTAMP(9) WITH TIME ZONE, lieu_affect_cree_par VARCHAR(15), lieu_affect_modifie_le TIMESTAMP(9) WITH TIME ZONE, lieu_affect_modifie_par VARCHAR(15));
--CREATE TABLE IF NOT EXISTS member (id SERIAL PRIMARY KEY, member_first_name VARCHAR(30), member_last_name VARCHAR(30), member_email VARCHAR(50), member_phone_number VARCHAR(20), member_username VARCHAR(20) UNIQUE, member_password VARCHAR(150), member_role VARCHAR(15), member_status VARCHAR(15), member_created TIMESTAMP(9) WITH TIME ZONE, member_created_by VARCHAR(15), member_last_updated TIMESTAMP(9) WITH TIME ZONE, member_last_updated_by VARCHAR(15));
insert into lieu_affectation (ID, LIEU_AFFECT_CODE, LIEU_AFFECT)
values (1L, 'CAB', 'Cabinet');
insert into lieu_affectation (ID, LIEU_AFFECT_CODE, LIEU_AFFECT)
values (2L, 'DSE', 'Direction Sécurité Extérieure');
insert into lieu_affectation (ID, LIEU_AFFECT_CODE, LIEU_AFFECT)
values (3L, 'DBAN', 'Direction Bangui');
insert into lieu_affectation (ID, LIEU_AFFECT_CODE, LIEU_AFFECT)
values (4L, 'DPRF', 'Direction Préfectures');