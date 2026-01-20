package gpersonnelcrde.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.Employe;
import gpersonnelcrde.domain.entities.TypeEmploye;

@Repository
@Transactional
public interface EmployeRepository extends JpaRepository<Employe, Long> {
	Optional<Employe> findByEmpMatricule(String empMatricule);
	Optional<Employe> findByEmpNomAndEmpPren(String empNom, String empPren);
	Optional<Employe> findByEmpMatriculeAndTypeEmploye(String empMatricule, TypeEmploye typeEmploye);
	//Optional<Employe> findByEmpNomAndEmpPrenAndEmpCiviliteAndEmpMatriculeAndTypeEmploye(String empNom, String empPren, String empCivilite, String empMatricule, TypeEmploye typeEmploye);
}
