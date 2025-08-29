package gpersonnelcrde.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.MissionDto;
import gpersonnelcrde.domain.entities.Mission;
import gpersonnelcrde.repository.MissionRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class MissionService {
	private final MissionRepository missionRepository;
	private final EmployeService employeService;

	public MissionService(MissionRepository missionRepository, EmployeService employeService) {
		this.missionRepository = missionRepository;
		this.employeService = employeService;
	}

	public List<MissionDto> getAllMissions() {
		return missionRepository.findAll().stream()
				.map((Mission mission) -> {
					var missEmploye = employeService.getEmployeByMatricule(mission.getEmploye().getEmpMatricule());
					var mDto = new MissionDto();
					mDto.setCadreMission(mission.getCadreMission());
					mDto.setDateDepart(mission.getDateDepart());
					mDto.setDateRetour(mission.getDateRetour());
					mDto.setEmploye(missEmploye.orElseThrow(EntityNotFoundException::new));
					mDto.setInfoSupplementaires(mission.getInfoSupplementaires());
					mDto.setMotifMission(mission.getMotifMission());
					mDto.setNatureMission(mission.getNatureMission());
					mDto.setPaysMission(mission.getPaysMission());
					mDto.setVilleMission(mission.getVilleMission());
					mDto.setNumOrderMission(String.valueOf(mission.getId())); ///Formule à determiner
					
					return mDto;
				})
				.toList();
	}

	public Optional<MissionDto> getMissionByNumOrdreMission (String numOrdreMission){
		if (StringUtils.isBlank(numOrdreMission) || !NumberUtils.isDigits(numOrdreMission)){
			return Optional.empty();
		}
		var optionalMission = missionRepository.findById(Long.valueOf(numOrdreMission));

		return missionMapper(optionalMission);
	}

	public List<MissionDto> getMissionByEmployeMatricule (final String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return Collections.emptyList();
		}
		
		return this.getAllMissions().stream()
									.filter(m -> empMatricule.equalsIgnoreCase(m.getEmploye().getEmpMatricule()))
									.sorted((m1, m2) -> m2.getDateDepart().compareTo(m1.getDateDepart()))
									.toList();
	}
	
	private Optional<MissionDto> missionMapper(Optional<Mission> optMission){
		if (!optMission.isPresent()){
			return Optional.empty();
		}

		var mission = optMission.get();
		var mDto = new MissionDto();
		var missEmp = employeService.getEmployeByMatricule(mission.getEmploye().getEmpMatricule());

		mDto.setCadreMission(mission.getCadreMission());
		mDto.setDateDepart(mission.getDateDepart());
		mDto.setDateRetour(mission.getDateDepart());
		mDto.setInfoSupplementaires(mission.getInfoSupplementaires());
		mDto.setMotifMission(mission.getMotifMission());
		mDto.setNatureMission(mission.getNatureMission());
		mDto.setPaysMission(mission.getPaysMission());
		mDto.setVilleMission(mission.getVilleMission());
		mDto.setEmploye(missEmp.orElseThrow(() -> new EntityNotFoundException()));
		
		return Optional.of(mDto);
	}
}
