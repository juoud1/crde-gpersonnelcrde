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

import gpersonnelcrde.domain.dto.MissionDto;
import gpersonnelcrde.domain.entities.Mission;
import gpersonnelcrde.repository.EmployeRepository;
import gpersonnelcrde.repository.MissionRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class MissionService {
	private final MissionRepository missionRepository;
	private final EmployeRepository employeRepository;

	public MissionService(MissionRepository missionRepository, EmployeRepository employeRepository) {
		this.missionRepository = missionRepository;
		this.employeRepository = employeRepository;
	}

	public List<MissionDto> getAllMissions() {
		return missionRepository.findAll().stream()
				.map((Mission mission) -> {
					var mDto = missionToDtoMapper(mission);
					
					return mDto;
				})
				.toList();
	}

	public MissionDto saveMissionEmploye(final String MissEmpMatricule, String natureDeplacement, String cadreMission,
												LocalDate dateDepartMiss, LocalDate dateRetourMiss, 
												String destVille, String destPays, String motifMission, String infoSupplmission){
		var missionDto = getMissionDtoFromWebParm(MissEmpMatricule, natureDeplacement, cadreMission, dateDepartMiss, dateRetourMiss, destVille, destPays, motifMission, infoSupplmission);
		
		return saveMissionEmploye(missionDto);
	}

	public MissionDto saveMissionEmploye(final MissionDto missionDtoToSave){
		var missionToSave = missionDtoMapper(missionDtoToSave);
		//missionToSave.setId(computeNumOrdreMission());
		missionToSave = missionRepository.save(missionToSave);
		missionDtoToSave.setNumOrderMission(String.valueOf(missionToSave.getId()));
		return missionDtoToSave;
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
									.filter(m -> empMatricule.equalsIgnoreCase(m.getEmployeMatricule()))
									.sorted((m1, m2) -> m2.getDateDepart().compareTo(m1.getDateDepart()))
									.toList();
	}
	
	private MissionDto getMissionDtoFromWebParm(final String missEmpMatricule, String natureDeplacement, String cadreMission,
												LocalDate dateDepartMiss, LocalDate dateRetourMiss, 
												String destVille, String destPays, String motifMission, String infoSupplmission){
		
		var missEmploye = employeRepository.findByEmpMatricule(missEmpMatricule);
		var mDto = new MissionDto();
		mDto.setCadreMission(cadreMission);
		mDto.setDateDepart(dateDepartMiss);
		mDto.setDateRetour(dateRetourMiss);
		mDto.setEmployeMatricule(missEmploye.orElseThrow(EntityNotFoundException::new).getEmpMatricule());
		mDto.setEmployeCivilite(missEmploye.orElseThrow(EntityNotFoundException::new).getEmpCivilite());
		mDto.setEmployeNom(String.join(", ", missEmploye.orElseThrow(EntityNotFoundException::new).getEmpNom(),
				missEmploye.orElseThrow(EntityNotFoundException::new).getEmpPren()));
		mDto.setInfoSupplementaires(infoSupplmission);
		mDto.setMotifMission(motifMission);
		mDto.setNatureMission(natureDeplacement);
		mDto.setPaysMission(destPays);
		mDto.setVilleMission(destVille);
		mDto.setNumOrderMission(String.valueOf(missEmploye.orElseThrow(EntityNotFoundException::new).getId())); ///Formule à determiner

		return mDto;
	}

	private Optional<MissionDto> missionMapper(Optional<Mission> optMission){
		if (!optMission.isPresent()){
			return Optional.empty();
		}

		var mDto = missionToDtoMapper(optMission.get());

		return Optional.of(mDto);
	}

	private MissionDto missionToDtoMapper(final Mission mission){
	    if (Objects.isNull(mission)){
            throw new EntityNotFoundException("L'entité mission ne doit être null");
		}
	    
		var missEmploye = employeRepository.findByEmpMatricule(mission.getEmploye().getEmpMatricule());
		var mDto = new MissionDto();
	    mDto.setCadreMission(mission.getCadreMission());
		mDto.setDateDepart(mission.getDateDepart());
		mDto.setDateRetour(mission.getDateRetour());
		mDto.setEmployeMatricule(missEmploye.orElseThrow(EntityNotFoundException::new).getEmpMatricule());
		mDto.setEmployeCivilite(missEmploye.orElseThrow(EntityNotFoundException::new).getEmpCivilite());
		mDto.setEmployeNom(String.join(", ", missEmploye.orElseThrow(EntityNotFoundException::new).getEmpNom(),
				missEmploye.orElseThrow(EntityNotFoundException::new).getEmpPren()));
		mDto.setEmployeFonction(missEmploye.orElseThrow(EntityNotFoundException::new).getEmpFonction().getFonction());
		mDto.setInfoSupplementaires(mission.getInfoSupplementaires());
		mDto.setMotifMission(mission.getMotifMission());
		mDto.setNatureMission(mission.getNatureMission());
		mDto.setPaysMission(mission.getPaysMission());
		mDto.setVilleMission(mission.getVilleMission());
		mDto.setNumOrderMission(String.valueOf(mission.getId())); ///Formule à determiner

		return mDto;
	}

	private Mission missionDtoMapper(MissionDto missionDto){
		var optEmploye = employeRepository.findByEmpMatricule(missionDto.getEmployeMatricule());
		
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
