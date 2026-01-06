package gpersonnelcrde.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.Conge;
import gpersonnelcrde.domain.entities.Employe;

@Repository
@Transactional
public interface CongeRepository extends JpaRepository<Conge, Long> {
	List<Conge> findByEmploye(Employe employe);
	Optional<Conge> findByDateDebutCongeAndDateFinCongeAndEmploye(LocalDate dateDebutConge, LocalDate dateFinConge, Employe employe);
}
