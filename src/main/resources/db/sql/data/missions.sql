BEGIN;
insert into mission (num_ordre_mission, type_ordre_mission, nature_mission, cadre_mission, date_depart, date_retour, duree_en_let_mission, pays_mission, ville_mission, motif_mission, status_mission, date_status_mission, mission_creee_le, mission_creee_par, mission_modifiee_le, mission_modifiee_par)--, employe_id) 
values ('12', 'Mission', 'Seminaire échange', 'Échange international sur le développement', '20250828', '20250908', 'onze (11) jours', 'Afrique du Sud', 'Pretoria', 'Mission officielle', 'Approuvée', '20250903', '20250827', 'admin', '20250903', 'admin');--, 3L);
--WHEN OTHERS THEN
    --COMMIT;
--ELSE
--	COMMIT;
--IF @make_error <> 0 THEN
    --ROLLBACK;
--ELSE
  --  COMMIT;
--END IF;
--END TRY
--BEGIN CATCH
    -- Capture and display the error.
  --  PRINT 'Constraint violation: ' + ERROR_MESSAGE();
--END CATCH;
ROLLBACK;