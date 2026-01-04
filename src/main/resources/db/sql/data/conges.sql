BEGIN;
insert into conge (num_note_service_conge, type_demande_conge, date_debut_conge, date_fin_conge, info_supplementaires, status_conge, date_status_conge, employe_id, conge_cree_le, conge_cree_par, conge_modifie_le, conge_modifie_par) 
values ('012', 'Congé et autorisation sortie', '20250812', '20250912', 'Vaccances de 1 mois avec possibilité de faire un déplacemt à ext.', 'Approuvé', '20250806', 2L, '20250803', 'admin', '20250806', 'admin');
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