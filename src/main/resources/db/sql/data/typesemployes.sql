--BEGIN TRY
BEGIN TRANSACTION;
insert into type_employe (TYPE_EMP_CODE, TYPE_EMP) 
values ('CIV', 'Civile'); 
--ON CONFLICT ON CONSTRAINT uniq_typeemp DO NOTHING;
insert into type_employe (TYPE_EMP_CODE, TYPE_EMP) 
values ('POL', 'Policier'); 
--ON CONFLICT ON CONSTRAINT uniq_typeemp DO NOTHING;
insert into type_employe (TYPE_EMP_CODE, TYPE_EMP) 
values ('GEN', 'Gendarme'); 
--ON CONFLICT ON CONSTRAINT uniq_typeemp DO NOTHING;
insert into type_employe (TYPE_EMP_CODE, TYPE_EMP)
values ('MIL', 'Militaire'); 
--ON CONFLICT ON CONSTRAINT uniq_typeemp DO NOTHING;
insert into type_employe (TYPE_EMP_CODE, TYPE_EMP) 
values ('AUT', 'Autre'); 
--WHEN OTHERS THEN
    COMMIT;
--ELSE
--	COMMIT;
--ELSE
  --  COMMIT;
--END IF;
--ON CONFLICT ON CONSTRAINT uniq_typeemp DO NOTHING;
--END TRY
--BEGIN CATCH
    -- Capture and display the error.
    --PRINT 'Constraint violation: ' + ERROR_MESSAGE();
--END CATCH;