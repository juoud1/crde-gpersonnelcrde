package gpersonnelcrde.repository;

import java.util.List;
import java.util.Optional;

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
	List<Mission> findByNumOrdreMission (String numOrdreMission);
	//Optional<Mission> findByNumOrdreMissionAndEmployeMatricule(String numOrdreMission, String employeMatricule);
}
