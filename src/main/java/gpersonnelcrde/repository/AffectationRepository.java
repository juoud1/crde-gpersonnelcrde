package gpersonnelcrde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.Affectation;
import gpersonnelcrde.domain.entities.Employe;
import gpersonnelcrde.domain.entities.Fonction;
import gpersonnelcrde.domain.entities.LieuAffectation;

@Repository
@Transactional
public interface AffectationRepository extends JpaRepository<Affectation, Long>{
	List<Affectation> findByEmploye(Employe employe);
	List<Affectation> findByLieuAffectation(LieuAffectation lieuAffectation);
	List<Affectation> findByFonction(Fonction fonction);
}
