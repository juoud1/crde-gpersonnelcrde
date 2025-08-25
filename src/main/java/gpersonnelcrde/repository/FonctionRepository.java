package gpersonnelcrde.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.Fonction;

@Repository
@Transactional
public interface FonctionRepository extends JpaRepository<Fonction, Long> {
	Optional<Fonction> findByFonctionCode(String fonctionCode);
}
