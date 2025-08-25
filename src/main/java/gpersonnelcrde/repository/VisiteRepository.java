package gpersonnelcrde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.Visite;

@Repository
@Transactional
public interface VisiteRepository extends JpaRepository<Visite, Long>{

}
