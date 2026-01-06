BEGIN;
insert into autorisation_sortie (as_num, as_date_depart, as_date_retour, motif_sortie, as_ville, as_pays, status_as, date_status_as, conge_id, as_cree_le, as_cree_par, as_modifie_le, as_modifie_par) 
values ('021', '20250813', '20250910', 'Visite familiale', 'Toumbouctou', 'Mali', 'Approuvé', '20250806', 1L, '20250803', 'admin', '20250806', 'admin');
--WHEN OTHERS THEN
    --COMMIT;
--ELSE
--	COMMIT;
--IF @make_error <> 0 THEN
   -- ROLLBACK;
--ELSE
  --  COMMIT;
--END IF;
--END TRY
--BEGIN CATCH
    -- Capture and display the error.
  --  PRINT 'Constraint violation: ' + ERROR_MESSAGE();
--END CATCH;
ROLLBACK;