package gpersonnelcrde.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.dto.MissionDto;
import gpersonnelcrde.domain.entities.Employe;
import gpersonnelcrde.domain.entities.Mission;
import gpersonnelcrde.repository.EmployeRepository;
import gpersonnelcrde.repository.MissionRepository;
import gpersonnelcrde.utilitaires.dataMapper;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class MissionService {
	private final MissionRepository missionRepository;
	private final EmployeRepository employeRepository;
	private final EmployeService employeService;

	public MissionService(MissionRepository missionRepository, EmployeRepository employeRepository, EmployeService employeService) {
		this.missionRepository = missionRepository;
		this.employeRepository = employeRepository;
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

	public Optional<Mission> saveMissionEmploye(final String MissEmpMatricule, String natureDeplacement, String cadreMission,
												String precisionCadreMiss, LocalDate dateDepartMiss, LocalDate dateRetourMiss, 
												String destVille, String destPays, String motifMission, String infoSupplmission){
		var missionDto = getMissionDtoFromWebParm(MissEmpMatricule, natureDeplacement, cadreMission, precisionCadreMiss, dateDepartMiss, dateRetourMiss, destVille, destPays, motifMission, infoSupplmission);
		
		return saveMissionEmploye(missionDto);
	}

	public Optional<Mission> saveMissionEmploye(final MissionDto missionDtoToSave){
		if (Objects.isNull(missionDtoToSave)) {
			return Optional.empty();
		}
		var missionToSave = missionDtoMapper(missionDtoToSave);
		//missionToSave.setId(computeNumOrdreMission());
		missionToSave = missionRepository.save(missionToSave);

		return Optional.of(missionToSave);
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
	
	private MissionDto getMissionDtoFromWebParm(final String missEmpMatricule, String natureDeplacement, String cadreMission,
												String precisionCadreMiss, LocalDate dateDepartMiss, LocalDate dateRetourMiss, 
												String destVille, String destPays, String motifMission, String infoSupplmission){
		
		var missEmp = employeService.getEmployeByMatricule(missEmpMatricule);

		var mDto = new MissionDto();
		mDto.setCadreMission(cadreMission);
		mDto.setDateDepart(dateDepartMiss);
		mDto.setDateRetour(dateRetourMiss);
		mDto.setEmploye(missEmp.orElseThrow(EntityNotFoundException::new));
		mDto.setInfoSupplementaires(infoSupplmission);
		mDto.setMotifMission(motifMission);
		mDto.setNatureMission(natureDeplacement);
		mDto.setPaysMission(destPays);
		mDto.setVilleMission(destVille);
		mDto.setInfoSupplementaires(infoSupplmission);

		return mDto;
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

	private Mission missionDtoMapper(MissionDto missionDto){
		var optEmploye = employeRepository.findByEmpMatricule(missionDto.getEmploye().getEmpMatricule());
		
		var missionToSave = new Mission();
		missionToSave.setCadreMission(missionDto.getCadreMission());
		missionToSave.setDateDepart(missionDto.getDateDepart());
		missionToSave.setDateRetour(missionDto.getDateRetour());
		missionToSave.setEmploye(optEmploye.orElseThrow(EntityNotFoundException::new));
		missionToSave.setInfoSupplementaires(missionDto.getInfoSupplementaires());
		missionToSave.setMotifMission(missionDto.getMotifMission());
		missionToSave.setNatureMission(missionDto.getNatureMission());
		missionToSave.setVilleMission(missionDto.getVilleMission());
		missionToSave.setPaysMission(missionDto.getPaysMission());
		missionToSave.setMissionCreeeLe(LocalDateTime.now());
		missionToSave.setMissionCreeePar("admin");
		missionToSave.setMissionModifieeLe(LocalDateTime.now());
		missionToSave.setMissionModifieePar("admin");

		return missionToSave;
	}
}
