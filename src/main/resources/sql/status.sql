--CREATE SCHEMA IF NOT EXISTS;
CREATE TABLE IF NOT EXISTS status (id SERIAL PRIMARY KEY, status_code VARCHAR(4) UNIQUE, status VARCHAR(50), status_cree_le TIMESTAMP(9) WITH TIME ZONE, status_cree_par VARCHAR(15), status_modifie_le TIMESTAMP(9) WITH TIME ZONE, status_modifie_par VARCHAR(15));
--CREATE TABLE IF NOT EXISTS member (id SERIAL PRIMARY KEY, member_first_name VARCHAR(30), member_last_name VARCHAR(30), member_email VARCHAR(50), member_phone_number VARCHAR(20), member_username VARCHAR(20) UNIQUE, member_password VARCHAR(150), member_role VARCHAR(15), member_status VARCHAR(15), member_created TIMESTAMP(9) WITH TIME ZONE, member_created_by VARCHAR(15), member_last_updated TIMESTAMP(9) WITH TIME ZONE, member_last_updated_by VARCHAR(15));

insert into status (ID, STATUS_CODE, STATUS) 
values (1L, 'SVCE', 'EN service');
insert into status (ID, STATUS_CODE, STATUS)
values (2L, 'CGE', 'EN congé');
insert into status (ID, STATUS_CODE, STATUS)
values (3L, 'MSN', 'EN mission');
insert into status (ID, STATUS_CODE, STATUS)
values (4L, 'STG', 'Stagiaire');
insert into status (ID, STATUS_CODE, STATUS)
values (5L, 'AUT', 'Autre');