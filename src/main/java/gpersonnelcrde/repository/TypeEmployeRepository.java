package gpersonnelcrde.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.TypeEmploye;

@Repository
@Transactional
public interface TypeEmployeRepository extends JpaRepository<TypeEmploye, Long> {
	Optional<TypeEmploye> findByTypeEmpCode(String typeEmpCode);
}
