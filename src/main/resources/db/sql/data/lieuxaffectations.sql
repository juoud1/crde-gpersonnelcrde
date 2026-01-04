BEGIN TRANSACTION;
insert into lieu_affectation (LIEU_AFFECT_CODE, LIEU_AFFECT)
values ('CAB', 'Cabinet'); 
--ON CONFLICT ON CONSTRAINT uniq_lieuaffect DO NOTHING;
insert into lieu_affectation (LIEU_AFFECT_CODE, LIEU_AFFECT)
values ('DSE', 'Direction Sécurité Extérieure'); 
--ON CONFLICT ON CONSTRAINT uniq_lieuaffect DO NOTHING;
insert into lieu_affectation (LIEU_AFFECT_CODE, LIEU_AFFECT)
values ('DBAN', 'Direction Bangui'); 
--ON CONFLICT ON CONSTRAINT uniq_lieuaffect DO NOTHING;
insert into lieu_affectation (LIEU_AFFECT_CODE, LIEU_AFFECT)
values ('DPRF', 'Direction Préfectures'); 
--WHEN OTHERS THEN
    COMMIT;
--ELSE
--	COMMIT;
--IF @make_error <> 0 THEN
    --ROLLBACK;
--ELSE
  --  COMMIT;
--END IF;
--ON CONFLICT ON CONSTRAINT uniq_lieuaffect DO NOTHING;
--END TRY
--BEGIN CATCH
    -- Capture and display the error.
  --  PRINT 'Constraint violation: ' + ERROR_MESSAGE();
--END CATCH;