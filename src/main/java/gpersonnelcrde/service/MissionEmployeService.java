package gpersonnelcrde.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.dto.MissionDto;
import gpersonnelcrde.domain.dto.MissionEmployeDto;
import gpersonnelcrde.domain.dto.MissionEmployesDto;
import gpersonnelcrde.domain.entities.Employe;
import gpersonnelcrde.domain.entities.Mission;
import gpersonnelcrde.domain.entities.MissionEmploye;
import gpersonnelcrde.domain.entities.MissionEmployePk;
import gpersonnelcrde.repository.MissionEmployeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class MissionEmployeService {
	private final static Logger logger = LoggerFactory.getLogger(MissionEmployeService.class);

	private final MissionEmployeRepository missionEmployeRepository;

	private final MissionService missionService;
	private final EmployeService employeService;
	
	public MissionEmployeService(MissionEmployeRepository missionEmployeRepository, MissionService missionService, EmployeService employeService){
		this.missionEmployeRepository=missionEmployeRepository;
		this.missionService = missionService;
		this.employeService = employeService;
		logger.info("Service mission-employé initialisé avec succès!");
	}

	public List<MissionEmployeDto> getAllMissionsEmployes(){
		return missionEmployeRepository.findAll().stream()
				.map((MissionEmploye missEmp) -> {
					MissionEmployeDto meDto = missionEmployeToDtoMapper(missEmp);

					return meDto;
				})
				.toList();
	}

	public MissionEmployesDto saveMissionEmployes(final String numOrdreMiss, final String typeOrdreMission, 
													final String natureDeplacement, final String cadreMission, 
													final LocalDate dateDepartMiss, final LocalDate dateRetourMiss, 
													final String destVille, final String destPays, 
													final String motifMission, String infoSupplmission, 
													final String empChefDeMissMatricule, final String... missEmpMatricules){
		
		var meDto = getMissionEmployeDtoFromWebParm(numOrdreMiss, typeOrdreMission, 
													empChefDeMissMatricule, natureDeplacement, 
													cadreMission, dateDepartMiss, dateRetourMiss, 
													destVille, destPays, motifMission, infoSupplmission, 
													missEmpMatricules);
		
		//logger.info("Mission-employé-dto pour la sauvegarde: {}", meDto);
		
		return saveMissionEmployes(meDto);
	}

	@Transactional
	protected MissionEmployesDto saveMissionEmployes(final MissionEmployesDto missionEmployesDtoToSave){
		if (Objects.isNull(missionEmployesDtoToSave)) {
			throw new IllegalArgumentException("L'entité mission-employé(s) ne doit être null");
		}
		
		Mission savedMission = getMissionFromDto(missionEmployesDtoToSave);
		logger.info("Mission sauvegardée sous le n° {}", savedMission.getId());

		List<Employe> savedEmployes = getEmployesFromDto(missionEmployesDtoToSave.getEmployesMission());
		logger.info("{} Employé(s) autorisé(s) à effectuer la mission {}", savedEmployes.size(), savedMission.getId());

		List<MissionEmploye> missionsEmployesToSave = getMissionsEmployesToSave(savedMission, savedEmployes, missionEmployesDtoToSave.getEmployeChefDeMissMatricule());
		logger.info("{} mission-employé(s) à sauvegarder", missionsEmployesToSave.size());

		List<MissionEmploye> savedMissionsEmployes = this.missionEmployeRepository.saveAllAndFlush(missionsEmployesToSave);
		
		if (missionsEmployesToSave.size() != savedMissionsEmployes.size()){
			logger.error("Problème lors de la sauvegarde des données, {} mission-employé(s) à sauvegarder mais seulement {} sauvegardé(s)", missionsEmployesToSave.size(), savedMissionsEmployes.size());
			throw new IllegalArgumentException("Un problème est survenu lors de la sauvegarde des données de mission-employé(s) dans la base de données");
		}
		
		missionEmployesDtoToSave.setNumMission(String.valueOf(savedMission.getId()));
		logger.info("{} mission-employé(s) sauvegardée(s) avec succès", savedMissionsEmployes.size());
		//logger.info("Missions-employés sauvegardée(s): {}", savedMissionsEmployes);
		//logger.info("Mission-employé-dto mise à jour: {}", missionEmployesDtoToSave);
		
		return missionEmployesDtoToSave;
	}

	private List<MissionEmploye> getMissionsEmployesToSave(final Mission mission, final List<Employe> employes, final String empChefDeMissMatricule){
		if (Objects.isNull(mission)) {
			throw new IllegalArgumentException("L'entité mission-employé : la mission ne doit être null");
		}

		if (null==employes || employes.isEmpty()) {
			throw new IllegalArgumentException("L'entité mission-employé : la liste des employés ne doit être null ou vide");
		}

		if (null==employes || employes.isEmpty()) {
			throw new IllegalArgumentException("L'entité mission-employé : la liste des employés ne doit être null ou vide");
		}
		
		List<MissionEmploye> missionsEmployes = new ArrayList<>();
		employes.forEach(emp -> {
			MissionEmployePk missionEmployeId = new MissionEmployePk();
			missionEmployeId.setMission(mission);
			missionEmployeId.setEmploye(emp);

			MissionEmploye missionEmploye = new MissionEmploye();
			missionEmploye.setId(missionEmployeId);
			
			if (emp.getEmpMatricule().equalsIgnoreCase(empChefDeMissMatricule)) {
				missionEmploye.setEmployeChefMission(true);
			}

			missionEmploye.setMissEmpCreeeLe(LocalDateTime.now());
			missionEmploye.setMissEmpCreeePar("Admin");
			missionEmploye.setMissEmpModifieeLe(LocalDateTime.now());
			missionEmploye.setMissEmpModifieePar("Admin");

			missionsEmployes.add(missionEmploye);
		});
		logger.info("{} mission-employé(s) prête(s) à sauvegarder", missionsEmployes.size());

		return missionsEmployes;
	}

	private List<Employe> getEmployesFromDto(final List<EmployeDto> employesDtoToSave){
		if (null==employesDtoToSave ||  employesDtoToSave.isEmpty()) {
			throw new IllegalArgumentException("La liste des employés autorisés à effectuer la mission ne doit être null ou vide.");
		}

		return employeService.getEmployesFromEmpMatricules(employesDtoToSave);
	}

	private Mission getMissionFromDto(final MissionEmployesDto missionEmployesDtoToSave){
		if (Objects.isNull(missionEmployesDtoToSave)) {
			throw new EntityNotFoundException("L'entité mission-employé(s) ne doit être null");
		}

		MissionDto mDto = new MissionDto();
		mDto.setCadreMission(missionEmployesDtoToSave.getCadreMission());
		mDto.setDateDepart(missionEmployesDtoToSave.getDateDepart());
		mDto.setDateRetour(missionEmployesDtoToSave.getDateRetour());
		mDto.setDateStatusMission(missionEmployesDtoToSave.getDateStatusMission());
		//mDto.setEmployeCivilite(null);
		mDto.setInfoSupplementaires(missionEmployesDtoToSave.getInfoSupplementaires());
		mDto.setMotifMission(missionEmployesDtoToSave.getMotifMission());
		mDto.setNatureMission(missionEmployesDtoToSave.getNatureMission());
		mDto.setNumOrdreMission(missionEmployesDtoToSave.getNumOrdreMission());
		mDto.setPaysMission(missionEmployesDtoToSave.getPaysMission());
		mDto.setStatusMission(missionEmployesDtoToSave.getStatusMission());
		mDto.setTypeOrdreMission(missionEmployesDtoToSave.getTypeOrdreMission());
		mDto.setVilleMission(missionEmployesDtoToSave.getVilleMission());

		var savedMission = this.missionService.saveMissionFromDto(mDto);
		logger.info("La mission est sauvegardée avec succès.");

		return savedMission;
	}

	private MissionEmployesDto getMissionEmployeDtoFromWebParm(final String numOrdreMiss, final String typeOrdreMission, 
																final String missEmpMatricule, final String natureDeplacement, 
																final String cadreMission, final LocalDate dateDepartMiss, 
																final LocalDate dateRetourMiss, final String destVille, 
																final String destPays, final String motifMission, 
																String infoSupplmission, 
																final String... missEmpMatricules){
		
		// Liste des matricules des employés (y compris celui du chef de mission) autorisés à effectuer la mission
		List<String> empMatricules = new ArrayList<>(Arrays.asList(missEmpMatricules));
		empMatricules.add(missEmpMatricule);
		empMatricules.sort(Comparator.naturalOrder());

		// Liste des employés (y compris le chef de mission) autorisés à effectuer la mission
		List<EmployeDto> eDtos = getEmployesDtoFromWebParm(empMatricules);
		
		// Employé Chef de mission
		EmployeDto empChefDeMissionDto = this.employeService.getEmployeByMatricule(missEmpMatricule).orElseThrow(() -> new EntityNotFoundException("Employé inexistant pour le matricule " + missEmpMatricule));

		var meDto = new MissionEmployesDto();
		meDto.setCadreMission(cadreMission);
		meDto.setDateDepart(dateDepartMiss);
		meDto.setDateRetour(dateRetourMiss);
		meDto.setDateStatusMission(LocalDate.now());
		meDto.setEmployeChefDeMissCivilite(empChefDeMissionDto.getEmpCivilite());
		meDto.setEmployeChefDeMissFonction(empChefDeMissionDto.getFonction());
		meDto.setEmployeChefDeMissMatricule(empChefDeMissionDto.getEmpMatricule());
		meDto.setEmployeChefDeMissNom(empChefDeMissionDto.getEmpNom());
		meDto.setEmployesMission(eDtos);
		meDto.setInfoSupplementaires(infoSupplmission);
		meDto.setMotifMission(motifMission);
		meDto.setNatureMission(natureDeplacement);
		meDto.setNumOrdreMission(numOrdreMiss);
		meDto.setPaysMission(destPays);
		meDto.setStatusMission("En attente");
		meDto.setTypeOrdreMission(typeOrdreMission);
		meDto.setVilleMission(destVille);
		
		return meDto;
	}
	
	protected List<EmployeDto> getEmployesDtoFromWebParm(final List<String> missEmpMatricules) {
		if (null== missEmpMatricules || missEmpMatricules.isEmpty()) {
			logger.info("liste de matricules d'employés vide!".toUpperCase());
			return List.of();
		}

		return this.employeService.getAllEmploye().stream()
					.sorted(Comparator.comparing(EmployeDto::getEmpMatricule))
					.filter(eDto -> missEmpMatricules.contains(eDto.getEmpMatricule()))
					.toList();
	}


	public List<MissionEmployeDto> getMissionsEmployesByNumOrdreMission (final String numOrdreMission) {
		if (StringUtils.isBlank(numOrdreMission) || !NumberUtils.isDigits(numOrdreMission)){
			logger.info("Aucune mission-employé pour un numéro vide ou non-numérique.");
			return Collections.emptyList();
		}
		
		var meResult = getAllMissionsEmployes().stream()
			.filter(meDto -> meDto.getNumOrdreMission().equalsIgnoreCase(numOrdreMission))
			//.sorted(Comparator.comparing(MissionEmployeDto::isChefDeMission).reversed())
			.toList();
		logger.info("{} mission(s)-employé(s) trouvée(s)", meResult.size());

		return meResult;
	}

	public List<MissionEmployeDto> getMissionsEmployesByEmpMatricule (String employeMatricule) {

		if (StringUtils.isBlank(employeMatricule)){
			logger.info("Aucune mission-employé pour un matricule vide.");
			return Collections.emptyList();
		}
		
		var meResult = getAllMissionsEmployes().stream()
			.filter(meDto -> meDto.getEmployeMatricule().equalsIgnoreCase(employeMatricule))
			//.sorted(Comparator.comparing(MissionEmployeDto::isChefDeMission).reversed())
			.toList();
		logger.info("{} mission(s)-employé(s) trouvée(s)", meResult.size());
		
		return meResult;
	}

	public List<MissionEmployeDto> getMissionsEmployesByTypeOrdreMission (String typeOrdreMission) {

		return List.of();
	}

	private Optional<MissionEmployeDto> missionEmployeMapper(final Optional<MissionEmploye> optMissEmp){
		var meDto = missionEmployeToDtoMapper(optMissEmp.orElseThrow(()-> new EntityNotFoundException("La mission-employé n'existe pas!")));
		
		return Optional.of(meDto);
	}

	private MissionEmployeDto missionEmployeToDtoMapper(MissionEmploye missionEmploye){
		if (Objects.isNull(missionEmploye)){
			throw new EntityNotFoundException("L'entité mission-employé ne doit être vide");
		}
		
		var emp = missionEmploye.getId().getEmploye();
		var miss = missionEmploye.getId().getMission();

		MissionEmployeDto meDto = new MissionEmployeDto();
		meDto.setCadreMission(miss.getCadreMission());
		meDto.setDateDepart(miss.getDateDepart());
		meDto.setDateRetour(miss.getDateRetour());
		meDto.setDateStatusMission(miss.getDateStatusMission());
		meDto.setEmployeCivilite(emp.getEmpCivilite());
		meDto.setEmployeFonction(emp.getEmpFonction().getFonction());
		meDto.setEmployeMatricule(emp.getEmpMatricule());
		meDto.setEmployeNom(String.join(", ", emp.getEmpNom(), emp.getEmpPren()));
		meDto.setIsChefDeMission(missionEmploye.isEmployeChefMission());
		meDto.setInfoSupplementaires(miss.getInfoSupplementaires());
		meDto.setMotifMission(miss.getMotifMission());
		meDto.setNatureMission(miss.getNatureMission());
		meDto.setNumOrdreMission(miss.getNumOrdreMission());
		meDto.setNumMission(String.valueOf(miss.getId()));
		meDto.setPaysMission(miss.getPaysMission());
		meDto.setStatusMission(miss.getStatusMission());
		meDto.setTypeOrdreMission(miss.getTypeOrdreMission());
		meDto.setVilleMission(miss.getVilleMission());

		return meDto;
	}

	// VOIR getMissionByNumOrdreMissionAndMatriculeEmp
	public Optional<MissionEmployeDto> getMissionEmployeByNumMissAndMatriculeEmp (final String numMission, final String empMatricule){
		if (StringUtils.isBlank(empMatricule) || StringUtils.isBlank(numMission) || !NumberUtils.isDigits(numMission)){
			return Optional.empty();
		}

		MissionEmployeDto meDto = new MissionEmployeDto();
		meDto.setEmployeMatricule(empMatricule);
		meDto.setNumMission(numMission);
		meDto.setNumOrdreMission(numMission);

		return Optional.of(meDto);
	}

	// VOIR getMissionByEmployeMatricule
	public List<MissionEmployeDto> getMissionsEmployesByMatriculeEmp (final String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return List.of();
		}

		MissionEmployeDto meDto = new MissionEmployeDto();
		meDto.setEmployeMatricule(empMatricule);

		return List.of(meDto);
	}

	// VOIR getMissionByNumOm
	public Optional<MissionEmployesDto> getMissionEmployesByNumMiss (String numMission){
		if (StringUtils.isBlank(numMission) || !NumberUtils.isDigits(numMission)){
			return Optional.empty();
		}
		var meDto = new MissionEmployesDto();
		meDto.setNumMission(numMission);
		meDto.setNumOrdreMission(numMission);
		meDto.setTypeOrdreMission("Mission");

		return Optional.of(meDto); //missionMapper(optionalMission);
	}
}
