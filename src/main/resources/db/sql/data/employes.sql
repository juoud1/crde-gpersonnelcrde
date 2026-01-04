BEGIN;
insert into employe (EMP_MATRICULE, EMP_NOM, EMP_PREN, EMP_CIVILITE, EMP_TELEPHONE, EMP_EMAIL, reference_decret_entree, date_decret_entree, TYPE_EMPLOYE_ID, EMP_STATUS_ID, EMP_LIEU_AFFECTATION_ID, EMP_FONCTION_ID) 
values ('M86OP582', 'W-L', 'Hri', 'Gl', '88664422', 'gahwl@min.gouv.cf', '123', '20190315', 4L, 1L, 1L, 1L); 
--ROLLBACK;
--ON CONFLICT ON CONSTRAINT uniq_matricule DO NOTHING;

--BEGIN;
insert into employe (EMP_MATRICULE, EMP_NOM, EMP_PREN, EMP_CIVILITE, EMP_TELEPHONE, EMP_EMAIL, reference_decret_entree, date_decret_entree, TYPE_EMPLOYE_ID, EMP_STATUS_ID, EMP_LIEU_AFFECTATION_ID, EMP_FONCTION_ID) 
values ('CIV0125', 'AAAAA', 'BBBBB', 'Monsieur', '2564566', 'gdjs@emp.gouv.cf', '215', '20211005', 1L, 1L, 3L, 4L); 
--ROLLBACK;

--BEGIN;
--ON CONFLICT ON CONSTRAINT uniq_matricule DO NOTHING;
insert into employe (EMP_MATRICULE, EMP_NOM, EMP_PREN, EMP_CIVILITE, EMP_TELEPHONE, EMP_EMAIL, reference_decret_entree, date_decret_entree, TYPE_EMPLOYE_ID, EMP_STATUS_ID, EMP_LIEU_AFFECTATION_ID, EMP_FONCTION_ID) 
values ('P86OP582', 'KKK', 'TUTUY', 'Lt de police', '5897566', 'l5pl@pc.gouv.cf', '23', '20230709', 2L, 1L, 2L, 6L); 
--ON CONFLICT ON CONSTRAINT uniq_matricule DO NOTHING;
--WHEN OTHERS THEN 
    --COMMIT;
--ELSE
--	COMMIT;
--ELSE
  --  COMMIT;
--END IF;
ROLLBACK;