package gpersonnelcrde.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import gpersonnelcrde.domain.entities.MissionEmploye;
import gpersonnelcrde.domain.entities.MissionEmployePk;

public interface MissionEmployeRepository extends JpaRepository<MissionEmploye, MissionEmployePk>   {

}