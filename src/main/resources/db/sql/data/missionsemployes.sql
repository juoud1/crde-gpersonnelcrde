BEGIN;
       --EXISTS(SELECT employe_id, mission_id FROM mission_employe em WHERE employe_id = 3 AND mission_id = 1)=TRUE 
		--ROLLBACK;
	--NOT IS EXISTS(SELECT employe_id, mission_id FROM mission_employe em WHERE employe_id = 3 AND mission_id = 1) 
	insert into mission_employe (employe_id, mission_id, is_employe_chef_mission, miss_emp_creee_le, miss_emp_creee_par, miss_emp_modifiee_le, miss_emp_modifiee_par) 
	values (3, 1, true, '20250827', 'admin', '20250903', 'admin');
	ROLLBACK;
--WHEN unique_violation THEN
	 --EXISTS(SELECT employe_id, mission_id FROM mission_employe em WHERE employe_id = 3 AND mission_id = 1) THEN
	--RAISE NOTICE 'Error handled: % %', SQLERRM, SQLSTATE;

--END;
--$$ 
--language 'plpgsql';

--foreign_key_violation
--unique_violation

--do $$       
  --    begin
    --    insert into ...
    --    insert into ...
    --  exception when others then 
    --    raise notice '% %', SQLERRM, SQLSTATE;
    --  end; $$ 
    --  language 'plpgsql';

--@CheckResult = UNIQUE (SELECT * FROM mission_employe em WHERE em.employe_id = 3 and em.mission_id = 1);
--CASE UNIQUE (SELECT employe_id, mission_id FROM mission_employe em WHERE employe_id = 3 AND mission_id = 1) 
--WHEN IS TRUE THEN
--SELECT employe_id, mission_id FROM mission_employe WHERE employe_id = 3 and mission_id = 1;
--ELSE
--insert into mission_employe (employe_id, mission_id, is_employe_chef_mission, miss_emp_creee_le, miss_emp_creee_par, miss_emp_modifiee_le, miss_emp_modifiee_par) 
--values (3, 1, true, '20250827', 'admin', '20250903', 'admin');
--END;
--COMMIT;
--COMMIT;
--WHEN OTHERS THEN
--ON DUPLICATE KEY UPDATE miss_emp_modifiee_le=VALUES(NOW());
--IF(DUPLICATE_KEY_1 <> 1)
-- COMMIT;
--ROLLBACK;
--COMMIT;
--ELSE
--	COMMIT;
--WHEN unique_index_violation || primary_key_violation THEN
--RAISE NOTICE 'Error handled: %', SQLERRM;
--COMMIT;
--IF @make_error <> 0 THEN
    --ROLLBACK;
--ELSE
  --  COMMIT;
--END IF;

--IF @@ERROR > 1 --@@ERROR > 0 --@@make_error == 1 --
--BEGIN
    --ROLLBACK; -- TRANSACTION insertCrdeMissEmpProcessing;
  --  PRINT 'Insertion de miss-emp a échoué.';
--END
--ELSE
--BEGIN
  --  COMMIT; --TRANSACTION insertCrdeMissEmpProcessing;
  --  PRINT 'Insertion de miss-emp avec succès.';
--END 
--ON CONFLICT ON CONSTRAINT pk_miss_emp_key DO NOTHING;
--END TRY
--BEGIN CATCH
    -- Capture and display the error.
  --  PRINT 'Constraint violation: ' + ERROR_MESSAGE();
--END CATCH;

--DO $$
--BEGIN
   -- BEGIN;
     --   insert into mission_employe (employe_id, mission_id, is_employe_chef_mission, miss_emp_creee_le, miss_emp_creee_par, miss_emp_modifiee_le, miss_emp_modifiee_par) 
		--values (3, 1, true, '20250827', 'admin', '20250903', 'admin'); -- Invalid CustomerID
    --EXCEPTION
      --  WHEN foreign_key_violation THEN
            --INSERT INTO ErrorLog (ErrorMessage, ErrorDate)
            --VALUES (
              --  'Invalid CustomerID: ' || SQLERRM,
                --CURRENT_TIMESTAMP
            -- );
        --    RAISE NOTICE 'Error handled: %', SQLERRM;
    --END;
--END;
--$$;

--SELECT insert_order();