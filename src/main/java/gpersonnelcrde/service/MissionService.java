package gpersonnelcrde.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.MissionDto;
import gpersonnelcrde.domain.entities.Mission;
import gpersonnelcrde.repository.MissionRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class MissionService {
private final static Logger logger = LoggerFactory.getLogger(MissionService.class);

	private final MissionRepository missionRepository;
	
	public MissionService(MissionRepository missionRepository) {
		this.missionRepository = missionRepository;
		
		logger.info("Service mission initialisé avec succès!");
	}

	public List<MissionDto> getAllMissions() {
		return missionRepository.findAll().stream()
				.map((Mission mission) -> {
					var mDto = missionToDtoMapper(mission);
					
					return mDto;
				})
				.toList();
	}

	public MissionDto saveMission(String natureDeplacement, String cadreMission,
												LocalDate dateDepartMiss, LocalDate dateRetourMiss, String destVille, String destPays, String motifMission, String infoSupplmission, final String numOrdreMiss, final String typeOrdreMission){
		var missionDto = getMissionDtoFromWebParm(numOrdreMiss, typeOrdreMission, natureDeplacement, cadreMission, dateDepartMiss, dateRetourMiss, destVille, destPays, motifMission, infoSupplmission);
		
		return saveMission(missionDto);
	}

	public MissionDto saveMission(final MissionDto missionDtoToSave){
		//var missionToSave = missionDtoMapper(missionDtoToSave);
		
		var savedMission = saveMissionFromDto(missionDtoToSave);
		missionDtoToSave.setNumMission(String.valueOf(savedMission.getId()));
		return missionDtoToSave;
	}

	@Transactional
	protected Mission saveMissionFromDto(final MissionDto missionDtoToSave){
		var missionToSave = missionDtoMapper(missionDtoToSave);
		missionToSave = missionRepository.save(missionToSave);
		logger.info("Mission sauvegardée avec succès sous le n° {} à {}", missionToSave.getId(), missionToSave.getMissionCreeeLe().toLocalTime());
		
		return missionToSave;
	}

	private MissionDto getMissionDtoFromWebParm(final String numOrdreMiss, final String typeOrdreMission, String natureDeplacement, String cadreMission,
												LocalDate dateDepartMiss, LocalDate dateRetourMiss, 
												String destVille, String destPays, String motifMission, String infoSupplmission){
		var mDto = new MissionDto();

		mDto.setTypeOrdreMission(typeOrdreMission);
		mDto.setNumOrdreMission(numOrdreMiss);
		mDto.setCadreMission(cadreMission);
		mDto.setDateDepart(dateDepartMiss);
		mDto.setDateRetour(dateRetourMiss);
		mDto.setInfoSupplementaires(infoSupplmission);
		mDto.setMotifMission(motifMission);
		mDto.setNatureMission(natureDeplacement);
		mDto.setPaysMission(destPays);
		mDto.setVilleMission(destVille);
		mDto.setStatusMission("En attente de validation");
		mDto.setDateStatusMission(LocalDate.now());

		return mDto;
	}

	public List<MissionDto> saveMissionEmployes(final String numOrdreMiss, final String typeOrdreMission, final String natureDeplacement, final String cadreMission,
												final LocalDate dateDepartMiss, final LocalDate dateRetourMiss, final String destVille, final String destPays, 
												final String motifMission, final String infoSupplmission, 
												final String missEmpChefMissMatricule, final String... missEmpMatricules){
		
		List<MissionDto> missionDtos = new ArrayList<>(); 
		for (String missEmpMatricule : missEmpMatricules) {
			var missionDto = saveMission(natureDeplacement, cadreMission, dateDepartMiss, dateRetourMiss, destVille, destPays, motifMission, infoSupplmission, numOrdreMiss, typeOrdreMission);
			missionDtos.add(missionDto);
		} 
		
		return missionDtos;
	}

	public List<MissionDto> getMissionByNumOrdreMission (String numOrdreMission){
		if (StringUtils.isBlank(numOrdreMission) || !NumberUtils.isDigits(numOrdreMission)){
			return Collections.emptyList();
		}
		final List<MissionDto> mDtos = new ArrayList<>();
		/*var missions = missionRepository.findByNumOrdreMission(numOrdreMission);
		missions.forEach(m -> mDtos.add(missionToDtoMapper(m)));*/

		return mDtos;//missionMapper(optionalMission);
	}

	@Transactional
	public Optional<MissionDto> getMissionByNum (final String numMission){
		/*if (StringUtils.isBlank(numOrdreMission) || !NumberUtils.isDigits(numOrdreMission)){
			return Optional.empty();
		}
		
		var optionalMission = missionRepository.findByNumOrdreMission(numOrdreMission);*/
		var optionalMission = getMissionByNumMiss(numMission);

		return missionMapper(optionalMission); 
	}

	@Transactional
	protected Optional<Mission> getMissionByNumMiss (String numMission){
		if (StringUtils.isBlank(numMission)){
			logger.error("{} n'est pas valide", numMission);
			throw new IllegalArgumentException("Le n° de mission ne doit être null ou vide");
		}
		
		if (!NumberUtils.isDigits(numMission)){
			logger.error("{} n'est pas un nombre entier", numMission);
			throw new IllegalArgumentException("Le n° de mission doit être un nombre");
		}
		
		return missionRepository.findById(Long.valueOf(numMission));
	}

	public Optional<MissionDto> getMissionByNumOrdreMissionAndMatriculeEmp (final String numOrdreMission, final String empMatricule){
		if (StringUtils.isBlank(empMatricule) || StringUtils.isBlank(numOrdreMission) || !NumberUtils.isDigits(numOrdreMission)){
			return Optional.empty();
		}
		var optMissionDto = getMissionByNumOrdreMission(numOrdreMission).stream()
								////.filter(mDto -> empMatricule.equalsIgnoreCase(mDto.getEmployeMatricule()))
								.findFirst();

		return optMissionDto; //missionMapper(optionalMission);
	}

	public List<MissionDto> getMissionByEmployeMatricule (final String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return Collections.emptyList();
		}
		
		return this.getAllMissions().stream()
									/////.filter(m -> empMatricule.equalsIgnoreCase(m.getEmployeMatricule()))
									.sorted((m1, m2) -> m2.getDateDepart().compareTo(m1.getDateDepart()))
									.toList();
	}
	
	private Optional<MissionDto> missionMapper(final Optional<Mission> optMission){
		if (!optMission.isPresent()){
			return Optional.empty();
		}

		var mDto = missionToDtoMapper(optMission.get());

		return Optional.of(mDto);
	}

	private MissionDto missionToDtoMapper(final Mission mission){
	    if (Objects.isNull(mission)){
            throw new EntityNotFoundException("L'entité mission ne doit être vide");
		}
	    
		////var missEmploye = employeRepository.findByEmpMatricule(mission.getEmploye().getEmpMatricule());
		var mDto = new MissionDto();
		mDto.setNumOrdreMission(mission.getNumOrdreMission());
		mDto.setTypeOrdreMission(mission.getTypeOrdreMission());
	    mDto.setCadreMission(mission.getCadreMission());
		mDto.setDateDepart(mission.getDateDepart());
		mDto.setDateRetour(mission.getDateRetour());
		mDto.setDureeEnLetMission(mission.getDureeEnLetMission());
		////mDto.setEmployeMatricule(missEmploye.orElseThrow(EntityNotFoundException::new).getEmpMatricule());
		////mDto.setEmployeCivilite(missEmploye.orElseThrow(EntityNotFoundException::new).getEmpCivilite());
		////mDto.setEmployeNom(String.join(", ", missEmploye.orElseThrow(EntityNotFoundException::new).getEmpNom(),
		////		missEmploye.orElseThrow(EntityNotFoundException::new).getEmpPren()));
		////mDto.setEmployeFonction(missEmploye.orElseThrow(EntityNotFoundException::new).getEmpFonction().getFonction());
		mDto.setInfoSupplementaires(mission.getInfoSupplementaires());
		mDto.setMotifMission(mission.getMotifMission());
		mDto.setNatureMission(mission.getNatureMission());
		mDto.setPaysMission(mission.getPaysMission());
		mDto.setVilleMission(mission.getVilleMission());
		mDto.setNumOrdreMission(mission.getNumOrdreMission()); ///Formule à determiner
		mDto.setNumMission(String.valueOf(mission.getId())); 
		mDto.setStatusMission(mission.getStatusMission());
		mDto.setDateStatusMission(mission.getDateStatusMission());

		return mDto;
	}

	private Mission missionDtoMapper(MissionDto missionDto){
		//var optEmploye = employeRepository.findByEmpMatricule(missionDto.getEmployeMatricule());
		
		var missionToSave = new Mission();
		missionToSave.setTypeOrdreMission(missionDto.getTypeOrdreMission());
		missionToSave.setNumOrdreMission(missionDto.getNumOrdreMission());
		missionToSave.setCadreMission(missionDto.getCadreMission());
		missionToSave.setDateDepart(missionDto.getDateDepart());
		missionToSave.setDateRetour(missionDto.getDateRetour());
		missionToSave.setDureeEnLetMission(missionDto.getDureeEnLetMission());
		//missionToSave.setEmploye(optEmploye.orElseThrow(EntityNotFoundException::new));
		missionToSave.setInfoSupplementaires(missionDto.getInfoSupplementaires());
		missionToSave.setMotifMission(missionDto.getMotifMission());
		missionToSave.setNatureMission(missionDto.getNatureMission());
		missionToSave.setVilleMission(missionDto.getVilleMission());
		missionToSave.setPaysMission(missionDto.getPaysMission());

		if (Objects.nonNull(missionDto.getStatusMission())){
			missionToSave.setStatusMission(missionDto.getStatusMission());
		} else {
			missionToSave.setStatusMission("En attente");
		}

		missionToSave.setDateStatusMission(LocalDate.now());
		missionToSave.setMissionCreeeLe(LocalDateTime.now());
		missionToSave.setMissionCreeePar("admin");
		missionToSave.setMissionModifieeLe(LocalDateTime.now());
		missionToSave.setMissionModifieePar("admin");

		return missionToSave;
	}
}
