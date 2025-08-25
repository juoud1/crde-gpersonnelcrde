package gpersonnelcrde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.Employe;

@Repository
@Transactional
public interface EmployeRepository extends JpaRepository<Employe, Long> {

}
