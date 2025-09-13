--CREATE SCHEMA IF NOT EXISTS;
CREATE TABLE IF NOT EXISTS visite (id SERIAL PRIMARY KEY, 
civilite_visiteur VARCHAR(30), 
nom_visiteur VARCHAR(30),
prenom_visiteur VARCHAR(30),
fonction_visiteur VARCHAR(30),
pays_visiteur VARCHAR(30),
date_debut TIMESTAMP(9) WITH TIME ZONE,
date_fin TIMESTAMP(9) WITH TIME ZONE,
duree INT,
date_prise_service TIMESTAMP(9) WITH TIME ZONE,
but_visite VARCHAR(60),
visite_est_acceptee boolean, visite_est_acceptee_le TIMESTAMP(9) WITH TIME ZONE,  
visite_creee_le TIMESTAMP(9) WITH TIME ZONE, visite_creee_par VARCHAR(15), 
visite_modifiee_le TIMESTAMP(9) WITH TIME ZONE, visite_modifiee_par VARCHAR(15)
);