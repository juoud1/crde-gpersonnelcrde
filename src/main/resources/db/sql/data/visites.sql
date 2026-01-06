BEGIN TRANSACTION;
insert into visite (date_debut_visite, date_fin_visite, duree_visite, civilite_visiteur, but_visite, nom_visiteur, prenom_visiteur, fonction_visiteur, pays_visiteur, employe_id, visite_creee_le, visite_creee_par, status_visite, date_status_visite) 
values ('20250913', '20250928', '15', 'Mr', 'Renforcement de coopérations', 'Coq', 'Martinez', 'Envoyé spécial', 'France', 1L, '20250904', 'admin', 'Approuvé', '20250910');
--IF @make_error <> 0 THEN
--WHEN OTHERS THEN
    --COMMIT;
--ELSE
--	COMMIT;
--ELSE
  --  COMMIT;
--END IF;
--END TRY
--BEGIN CATCH
    -- Capture and display the error.
  --  PRINT 'Constraint violation: ' + ERROR_MESSAGE();
--END CATCH;
ROLLBACK;