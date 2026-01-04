--BEGIN TRY
--IF @make_error = 1
--ELSE
--	SELECT 1 AS id
--END TRY
--BEGIN CATCH
--	PRINT 'Constraint violation: IN STATUS TABLE';
--END CATCH;
BEGIN TRANSACTION;
insert into status (STATUS_CODE, STATUS) values ('SVCE', 'En service'); --ON CONFLICT (STATUS_CODE) DO NOTHING;
--ON CONFLICT ON CONSTRAINT uniq_status DO NOTHING;
insert into status (STATUS_CODE, STATUS) values ('CGE', 'En congé'); -- ON CONFLICT (STATUS_CODE) DO NOTHING;
--ON CONFLICT ON CONSTRAINT uniq_status DO NOTHING;
insert into status (STATUS_CODE, STATUS) values ('MSN', 'En mission'); -- ON CONFLICT (STATUS_CODE) DO NOTHING;
--ON CONFLICT ON CONSTRAINT uniq_status DO NOTHING;
insert into status (STATUS_CODE, STATUS) values ('STG', 'Stagiaire'); -- ON CONFLICT (STATUS_CODE) DO NOTHING;
--ON CONFLICT ON CONSTRAINT uniq_status DO NOTHING;
insert into status (STATUS_CODE, STATUS) values ('NACT', 'Non actif'); -- ON CONFLICT (STATUS_CODE) DO NOTHING;
--ON CONFLICT ON CONSTRAINT uniq_status DO NOTHING;
insert into status (STATUS_CODE, STATUS) values ('AUT', 'Autre'); -- ON CONFLICT (STATUS_CODE) DO NOTHING;
--WHEN OTHERS THEN
    COMMIT;
--ELSE
--	COMMIT;
--ON CONFLICT ON CONSTRAINT uniq_status DO NOTHING;
--IF @make_error <> 0 THEN
    --ROLLBACK;
--ELSE
  --  COMMIT;
--END IF;