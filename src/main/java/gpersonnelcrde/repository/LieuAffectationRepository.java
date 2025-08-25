package gpersonnelcrde.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.LieuAffectation;

@Repository
@Transactional
public interface LieuAffectationRepository extends JpaRepository<LieuAffectation, Long> {
	Optional<LieuAffectation> findByLieuAffectCode (String lieuAffectCode);
}
