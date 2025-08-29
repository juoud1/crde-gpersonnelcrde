package gpersonnelcrde.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.entities.Employe;
import gpersonnelcrde.domain.entities.Mission;

@Repository
@Transactional
public interface MissionRepository extends JpaRepository<Mission, Long> {
	List<Mission> findByEmploye(Employe employe);
	List<Mission> findByMotifMission(String motifMission);
}
