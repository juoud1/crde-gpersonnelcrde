package gpersonnelcrde.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.Status;
import java.util.Optional;


@Repository
@Transactional
public interface StatusRepository extends JpaRepository<Status, Long> {
	Optional<Status> findByStatusCode(String statusCode);
}
