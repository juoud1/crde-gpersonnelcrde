package gpersonnelcrde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.Conge;

@Repository
@Transactional
public interface CongeRepository extends JpaRepository<Conge, Long> {

}
