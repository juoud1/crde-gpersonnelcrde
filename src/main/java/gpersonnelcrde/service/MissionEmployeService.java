package gpersonnelcrde.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public List<MissionEmployesDto> getAllMissionEmployes(){
		var missEmps = getMissionsEmployes();

		return meToMissionEmployesDtoMapper(missEmps);
	}

	private List<MissionEmployeDto> getMissionsEmployes(){
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
													final String dureeEnLettreMiss, final String destVille, final String destPays, 
													final String motifMission, String infoSupplmission, 
													final String empChefDeMissMatricule, final String... missEmpMatricules){
		
		var meDto = getMissionEmployeDtoFromWebParm(numOrdreMiss, typeOrdreMission, 
													empChefDeMissMatricule, natureDeplacement, 
													cadreMission, dateDepartMiss, dateRetourMiss, dureeEnLettreMiss,
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
		mDto.setDureeEnLetMission(missionEmployesDtoToSave.getDureeMiss());
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
																final LocalDate dateRetourMiss, final String dureeEnLettreMiss, 
																final String destVille, final String destPays,  
																final String motifMission, String infoSupplmission, 
																final String... missEmpMatricules){

		// Liste des employés (y compris le chef de mission) autorisés à effectuer la mission
		List<EmployeDto> eDtos = getEmployesDtoFromWebParm(typeOrdreMission, missEmpMatricule, missEmpMatricules);
		
		// Employé Chef de mission
		EmployeDto empChefDeMissionDto = this.employeService.getEmployeByMatricule(missEmpMatricule).orElseThrow(() -> new EntityNotFoundException("Employé inexistant pour le matricule " + missEmpMatricule));

		var meDto = new MissionEmployesDto();
		meDto.setCadreMission(cadreMission);
		meDto.setDateDepart(dateDepartMiss);
		meDto.setDateRetour(dateRetourMiss);
		meDto.setDureeMiss(dureeEnLettreMiss);
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

	private List<EmployeDto> getEmployesDtoFromWebParm(String typeOrdreMission, String missEmpChefDeMissMatricule, final String... missEmpMatricules) {
		// Liste des matricules des employés (y compris celui du chef de mission) autorisés à effectuer la mission
		List<String> empMatricules = new ArrayList<>();
		if (!"Mission".equalsIgnoreCase(typeOrdreMission)){
			empMatricules = new ArrayList<>(Arrays.asList(missEmpMatricules));
			empMatricules.add(missEmpChefDeMissMatricule);
			empMatricules.sort(Comparator.naturalOrder());
			//eDtos = getEmployesDtoFromWebParm(empMatricules);
		}else{
			empMatricules.add(missEmpChefDeMissMatricule);
		}

		return getEmployesDtoFromWebParm(empMatricules);
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
		
		var meResult = getMissionsEmployes().stream()
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
		
		var meResult = getMissionsEmployes().stream()
			.filter(meDto -> meDto.getEmployeMatricule().equalsIgnoreCase(employeMatricule))
			.toList();
		logger.info("{} mission(s)-employé(s) trouvée(s)", meResult.size());
		
		return null;
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

		EmployeDto empDto = employeService.getEmployeByMatricule(emp.getEmpMatricule())
								.orElseThrow(() -> new EntityNotFoundException(String.join("Aucun employé existe sous le matricule ", emp.getEmpMatricule())));
		
		MissionDto missDto = missionService.getMissionByNum(String.valueOf(miss.getId()))
								.orElseThrow(() -> new EntityNotFoundException(String.join("Aucune mission existe sous le n° ", miss.getId().toString())));

		MissionEmployeDto meDto = new MissionEmployeDto();
		meDto.setMission(missDto);
		meDto.setEmploye(empDto);


		meDto.setIsChefDeMission(missionEmploye.isEmployeChefMission());

		meDto.setCadreMission(miss.getCadreMission());
		meDto.setDateDepart(miss.getDateDepart());
		meDto.setDateRetour(miss.getDateRetour());
		meDto.setDureeEnLettre(miss.getDureeEnLetMission());
		meDto.setDateStatusMission(miss.getDateStatusMission());
		meDto.setEmployeCivilite(emp.getEmpCivilite());
		meDto.setEmployeFonction(emp.getEmpFonction().getFonction());
		meDto.setEmployeMatricule(emp.getEmpMatricule());
		meDto.setEmployeNom(String.join(" ", emp.getEmpNom(), emp.getEmpPren()));
		//meDto.setIsChefDeMission(missionEmploye.isEmployeChefMission());
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

	@Transactional
	public List<MissionEmployeDto> getMissionsEmployesByMatriculeEmp (final String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return List.of();
		}

		return getMissionsEmployes().stream()
						//.sorted(Comparator.comparing(MissionEmployeDto::getIsChefDeMission).reversed())
						.filter(meDto -> meDto.getEmployeMatricule().equalsIgnoreCase(empMatricule))
						.toList();
	}

	// VOIR getMissionByNumOm
	@Transactional
	public Optional<MissionEmployesDto> getMissionEmployesByNumMiss (final String numMission){
		if (StringUtils.isBlank(numMission) || !NumberUtils.isDigits(numMission)){
			logger.warn("n° mission incorrect");
			throw new IllegalArgumentException("Le numéro d'une mission ne doit pas être vide ni alpha-numérique");
		}
		
		//var mission = missionService.getMissionByNum(numMission).orElseThrow(()-> new EntityNotFoundException(String.join("La mission n° ", numMission, " n'existe pas")));
		/*var missEmps = getAllMissionsEmployes().stream()
						.sorted(Comparator.comparing(MissionEmployeDto::getIsChefDeMission).reversed())
						.filter(meDto -> meDto.getMission().getNumMission().equalsIgnoreCase(numMission))
						.toList();

		return meToMissEmpsDto(missEmps);*/
		
		return getAllMissionEmployes().stream()
						.filter(meDto -> meDto.getNumMission().equalsIgnoreCase(numMission))
						.findFirst();
	}

	private List<MissionEmployesDto> meToMissionEmployesDtoMapper (final List<MissionEmployeDto> missionEmployeDtos){
		Map<MissionDto, List<MissionEmployeDto>> missEmpMap = missionEmployeDtos.stream()
															.collect(Collectors.groupingBy(MissionEmployeDto::getMission));

		return meToMissionEmployesDtoMapper(missEmpMap);
	}

	private List<MissionEmployesDto> meToMissionEmployesDtoMapper (Map<MissionDto, List<MissionEmployeDto>> meMap){
		List<MissionEmployesDto> missionEmployesDtos  = new ArrayList<>();
		meMap.forEach((miss, missemps) -> {
						List<EmployeDto> employeDtos = missemps.stream()
										.map(MissionEmployeDto::getEmploye)
										.toList();
						//logger.error("EMPLOYEDTO DANS MAPPER = {}", employeDtos);
						//logger.error("MISSEMPS DANS MAPPER = {}", missemps);
						EmployeDto employeChefDeMiss = missemps.stream()
										.filter(MissionEmployeDto::getIsChefDeMission)
										.map(MissionEmployeDto::getEmploye)
										.findFirst().orElseThrow(() -> new EntityNotFoundException("L'employé, chef de mission n'existe pas"));

						MissionEmployesDto missionEmployesDto = new MissionEmployesDto();
						missionEmployesDto.setCadreMission(miss.getCadreMission());
						missionEmployesDto.setDateDepart(miss.getDateDepart());
						missionEmployesDto.setDateRetour(miss.getDateRetour());
						missionEmployesDto.setDateStatusMission(miss.getDateStatusMission());
						missionEmployesDto.setDureeMiss(miss.getDureeEnLetMission());
						missionEmployesDto.setEmployeChefDeMissCivilite(employeChefDeMiss.getEmpCivilite());
						missionEmployesDto.setEmployeChefDeMissFonction(employeChefDeMiss.getFonction());
						missionEmployesDto.setEmployeChefDeMissMatricule(employeChefDeMiss.getEmpMatricule());
						missionEmployesDto.setEmployeChefDeMissNom(employeChefDeMiss.getEmpNom());
						missionEmployesDto.setEmployesMission(employeDtos);
						missionEmployesDto.setInfoSupplementaires(miss.getInfoSupplementaires());
						missionEmployesDto.setMotifMission(miss.getMotifMission());
						missionEmployesDto.setNatureMission(miss.getNatureMission());
						missionEmployesDto.setNumMission(miss.getNumMission());
						missionEmployesDto.setNumOrdreMission(miss.getNumOrdreMission());
						missionEmployesDto.setPaysMission(miss.getPaysMission());
						missionEmployesDto.setStatusMission(miss.getStatusMission());
						missionEmployesDto.setTypeOrdreMission(miss.getTypeOrdreMission());
						missionEmployesDto.setVilleMission(miss.getVilleMission());
						
						missionEmployesDtos.add(missionEmployesDto);

						return;
					});	

		return missionEmployesDtos;
	}

	private Optional<MissionEmployesDto> meToMissEmpsDto (List<MissionEmployeDto> missionEmployeDtos){
		MissionDto missionDto = missionEmployeDtos.get(0).getMission();
		List<EmployeDto> employeDtos = missionEmployeDtos.stream()
										.map(MissionEmployeDto::getEmploye)
										.toList();

		EmployeDto employeChefDeMiss = missionEmployeDtos.stream()
										.filter(MissionEmployeDto::getIsChefDeMission)
										.map(MissionEmployeDto::getEmploye)
										.findFirst().orElseThrow(() -> new EntityNotFoundException("L'employé, chef de mission n'existe pas"));
		MissionEmployesDto missionEmployesDto = new MissionEmployesDto();

		missionEmployesDto.setCadreMission(missionDto.getCadreMission());
		missionEmployesDto.setDateDepart(missionDto.getDateDepart());
		missionEmployesDto.setDateRetour(missionDto.getDateRetour());
		missionEmployesDto.setDateStatusMission(missionDto.getDateStatusMission());
		missionEmployesDto.setDureeMiss(missionDto.getDureeEnLetMission());
		missionEmployesDto.setEmployeChefDeMissCivilite(employeChefDeMiss.getEmpCivilite());
		missionEmployesDto.setEmployeChefDeMissFonction(employeChefDeMiss.getFonction());
		missionEmployesDto.setEmployeChefDeMissMatricule(employeChefDeMiss.getEmpMatricule());
		missionEmployesDto.setEmployeChefDeMissNom(employeChefDeMiss.getEmpNom());
		missionEmployesDto.setEmployesMission(employeDtos);
		missionEmployesDto.setInfoSupplementaires(missionDto.getInfoSupplementaires());
		missionEmployesDto.setMotifMission(missionDto.getMotifMission());
		missionEmployesDto.setNatureMission(missionDto.getNatureMission());
		missionEmployesDto.setNumMission(missionDto.getNumMission());
		missionEmployesDto.setNumOrdreMission(missionDto.getNumOrdreMission());
		missionEmployesDto.setPaysMission(missionDto.getPaysMission());
		missionEmployesDto.setStatusMission(missionDto.getStatusMission());
		missionEmployesDto.setTypeOrdreMission(missionDto.getTypeOrdreMission());
		missionEmployesDto.setVilleMission(missionDto.getVilleMission());

		return Optional.of(missionEmployesDto);
	}
}
