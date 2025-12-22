package gpersonnelcrde.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.AutorisationSortie;
import gpersonnelcrde.domain.entities.Conge;

@Repository
@Transactional
public interface AutorisationSortieRepository extends JpaRepository<AutorisationSortie, Long> {
	List<AutorisationSortie> findByConge(Conge conge);
	Optional<AutorisationSortie> findByAsDateDepartAndAsDateRetourAndConge(LocalDate asDateDepart, LocalDate asDateRetour, Conge conge);
}
