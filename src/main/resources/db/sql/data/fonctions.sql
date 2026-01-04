BEGIN TRANSACTION;
insert into fonction (FONCTION_CODE, FONCTION) 
values ('MIN', 'Ministre'); 
--ROLLBACK;
--ON CONFLICT ON CONSTRAINT uniq_fonction DO NOTHING;
--BEGIN;
insert into fonction (FONCTION_CODE, FONCTION) 
values ('SP', 'Secrétaire particulier'); 
--ON CONFLICT ON CONSTRAINT uniq_fonction DO NOTHING;
insert into fonction (FONCTION_CODE, FONCTION) 
values ('ASS', 'Assistant'); 
--ON CONFLICT ON CONSTRAINT uniq_fonction DO NOTHING;
insert into fonction (FONCTION_CODE, FONCTION) 
values ('DIR', 'Directeur'); 
--ON CONFLICT ON CONSTRAINT uniq_fonction DO NOTHING;
insert into fonction (FONCTION_CODE, FONCTION)
values ('CSV', 'Chef de service'); 
--ON CONFLICT ON CONSTRAINT uniq_fonction DO NOTHING;
insert into fonction (FONCTION_CODE, FONCTION)
values ('CSB', 'Chef de bureau'); 
--ON CONFLICT ON CONSTRAINT uniq_fonction DO NOTHING;
insert into fonction (FONCTION_CODE, FONCTION)
values ('PERS', 'Personnel'); 
--ON CONFLICT ON CONSTRAINT uniq_fonction DO NOTHING;
insert into fonction (FONCTION_CODE, FONCTION)
values ('ACAMP', 'Aide de camp'); 
--ON CONFLICT ON CONSTRAINT uniq_fonction DO NOTHING;
insert into fonction (FONCTION_CODE, FONCTION)
values ('CHAUF', 'Chauffeur'); 
--ON CONFLICT ON CONSTRAINT uniq_fonction DO NOTHING;
insert into fonction (FONCTION_CODE, FONCTION)
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
--ON CONFLICT ON CONSTRAINT uniq_fonction DO NOTHING;
--END TRY
--BEGIN CATCH
    -- Capture and display the error.
  --  PRINT 'Constraint violation: ' + ERROR_MESSAGE();
--END CATCH;