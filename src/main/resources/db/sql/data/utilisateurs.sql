BEGIN;
insert into utilisateur (utilisateur_firstname, utilisateur_lastname, utilisateur_email, utilisateur_phone_number, utilisateur_nom_connexion, utilisateur_mot_de_passe, utilisateur_role, utilisateur_status) 
values ('admin', 'super admin', 'admin@gmail.com', '740000000', 'admin', 'admin123', 'ADMIN', 'Actif');
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