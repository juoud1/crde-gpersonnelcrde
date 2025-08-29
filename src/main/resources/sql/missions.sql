--CREATE SCHEMA IF NOT EXISTS;
CREATE TABLE IF NOT EXISTS mission (id SERIAL PRIMARY KEY, 
nature_mission VARCHAR(50), 
cadre_mission VARCHAR(50),
date_depart TIMESTAMP(9) WITH TIME ZONE,
date_retour TIMESTAMP(9) WITH TIME ZONE, 
pays_mission VARCHAR(30), 
ville_mission VARCHAR(30),
motif_mission VARCHAR(30), 
info_supplementaires VARCHAR(80),  
employe_id long,
mission_creee_le TIMESTAMP(9) WITH TIME ZONE, mission_creee_par VARCHAR(15), 
mission_modifiee_le TIMESTAMP(9) WITH TIME ZONE, mission_modifiee_par VARCHAR(15),
constraint fk_miss_emp FOREIGN key (employe_id) references employe(id)
);
insert into mission (ID, nature_mission, cadre_mission, date_depart, date_retour, pays_mission, ville_mission, motif_mission, employe_id) 
values (1L, 'Seminaire échange', 'Échange international sur le développement', '20250828', '20250908', 'Afrique du Sud', 'Pretoria', 'Mission officielle', 3L);