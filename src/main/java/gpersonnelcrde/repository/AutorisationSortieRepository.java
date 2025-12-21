package gpersonnelcrde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.AutorisationSortie;
import gpersonnelcrde.domain.entities.Conge;

@Repository
@Transactional
public interface AutorisationSortieRepository extends JpaRepository<AutorisationSortie, Long> {
	List<AutorisationSortie> findByConge(Conge conge);
}
