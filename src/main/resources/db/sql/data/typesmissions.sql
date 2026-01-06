--BEGIN TRY
BEGIN TRANSACTION;
insert into type_mission (TYPE_MISS_CODE, TYPE_MISS) 
values ('MISS', 'Mission'); 
--ON CONFLICT ON CONSTRAINT uniq_typemiss DO NOTHING;
insert into type_mission (TYPE_MISS_CODE, TYPE_MISS) 
values ('MIGP', 'Mission de groupe'); 
--ON CONFLICT ON CONSTRAINT uniq_typemiss DO NOTHING;
insert into type_employe (TYPE_MISS_CODE, TYPE_MISS) 
values ('AUT', 'Autre');
--WHEN OTHERS THEN
    COMMIT;
--ELSE
--	COMMIT;
--IF @make_error <> 0 THEN
    --ROLLBACK;
--ELSE
  --  COMMIT;
--END IF; 
--ON CONFLICT ON CONSTRAINT uniq_typemiss DO NOTHING;
--END TRY
--BEGIN CATCH
    -- Capture and display the error.
  --  PRINT 'Constraint violation: ' + ERROR_MESSAGE();
--END CATCH;