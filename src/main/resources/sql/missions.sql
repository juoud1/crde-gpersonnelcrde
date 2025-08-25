--CREATE SCHEMA IF NOT EXISTS;
CREATE TABLE IF NOT EXISTS mission (id SERIAL PRIMARY KEY, 
nature_mission VARCHAR(40), 
cadre_mission VARCHAR(40),
date_depart TIMESTAMP(9) WITH TIME ZONE,
date_retour TIMESTAMP(9) WITH TIME ZONE, 
pays_mission VARCHAR(30), 
ville_mission VARCHAR(30),
motif_mission VARCHAR(30), 
info_supplementaires VARCHAR(60),  
employe_id long,
mission_creee_le TIMESTAMP(9) WITH TIME ZONE, mission_creee_par VARCHAR(15), 
mission_modifiee_le TIMESTAMP(9) WITH TIME ZONE, mission_modifiee_par VARCHAR(15),
constraint fk_miss_emp FOREIGN key (employe_id) references employe(id)
);