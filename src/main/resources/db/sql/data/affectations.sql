BEGIN;
insert into affectation (categorie_affect, ville_residence, pays_residence, num_note_service, reference_affect, emplacement_affect, date_debut_affect, date_fin_affect, date_prise_service, info_supplementaires, status_affect, date_status_affect, employe_id, lieu_affectation_id, fonction_id) 
values ('Affectation intérieur RCA', 'Bangui', 'Rép. Centrafricaine', '15', 'Decret 1/2019', 'Présidence', '20190130', '20190130', '20190205', 'Affectation initiale par Décret présidentiel', 'Approuvée', '20190130', 1L, 1L, 1L);
insert into affectation (categorie_affect, ville_residence, pays_residence, num_note_service, reference_affect, emplacement_affect, date_debut_affect, date_fin_affect, date_prise_service, info_supplementaires, status_affect, date_status_affect, employe_id, lieu_affectation_id, fonction_id) 
values ('Affectation intérieur RCA', 'Bangui', 'Rép. Centrafricaine', '21', 'Décision 06/2023', 'Aeroport de BG', '20230607', '20230607', '20230617', 'Affectation initiale par Note de service', 'Approuvée', '20230607', 2L, 3L, 4L);
insert into affectation (categorie_affect, ville_residence, pays_residence, num_note_service, reference_affect, emplacement_affect, date_debut_affect, date_fin_affect, date_prise_service, info_supplementaires, status_affect, date_status_affect, employe_id, lieu_affectation_id, fonction_id) 
values ('Affectation intérieur RCA', 'Bangui', 'Rép. Centrafricaine', '26', 'Arrt 11/2022', 'Aeroport de BG', '20221115', '20221115', '20230105', 'Affectation initiale par Note de service', 'Approuvée', '20221115', 3L, 2L, 6L);
--WHEN OTHERS THEN
   -- COMMIT;
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