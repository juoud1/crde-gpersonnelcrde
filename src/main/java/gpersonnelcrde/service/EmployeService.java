package gpersonnelcrde.service;

import static gpersonnelcrde.utilitaires.CrdeConstants.*;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.entities.AdresseEmploye;
import gpersonnelcrde.domain.entities.Affectation;
import gpersonnelcrde.domain.entities.Conge;
import gpersonnelcrde.domain.entities.Employe;
import gpersonnelcrde.domain.entities.Fonction;
import gpersonnelcrde.domain.entities.LieuAffectation;
import gpersonnelcrde.domain.entities.Mission;
import gpersonnelcrde.domain.entities.MissionEmploye;
import gpersonnelcrde.domain.entities.Status;
import gpersonnelcrde.domain.entities.TypeEmploye;
import gpersonnelcrde.exception.EmployeServiceException;
import gpersonnelcrde.exception.StockageFichiersImagesException;
import gpersonnelcrde.repository.AdresseEmployeRepository;
import gpersonnelcrde.repository.AffectationRepository;
import gpersonnelcrde.repository.CongeRepository;
import gpersonnelcrde.repository.EmployeRepository;
import gpersonnelcrde.repository.FonctionRepository;
import gpersonnelcrde.repository.LieuAffectationRepository;
import gpersonnelcrde.repository.MissionEmployeRepository;
import gpersonnelcrde.repository.MissionRepository;
import gpersonnelcrde.repository.StatusRepository;
import gpersonnelcrde.repository.TypeEmployeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class EmployeService {
	public static final Logger logger = LoggerFactory.getLogger(EmployeService.class);

	private final EmployeRepository employeRepository;
	private final AffectationRepository affectationRepository;
	private final MissionRepository missionRepository;
	private final MissionEmployeRepository missionEmployeRepository;
	private final CongeRepository congeRepository;

	private final FonctionRepository fonctionRepository;
	private final TypeEmployeRepository typeEmployeRepository;
	private final StatusRepository statusRepository;
	private final LieuAffectationRepository lieuAffectationRepository;
	private final AdresseEmployeRepository adresseEmployeRepository;
	private final StockageFichiersImagesService stockagePhotoEmployeService;
	private final ResourceLoader resourceLoader;

	public EmployeService(EmployeRepository employeRepository, AffectationRepository affectationRepository, 
	    MissionRepository missionRepository, CongeRepository congeRepository, MissionEmployeRepository missionEmployeRepository,
		FonctionRepository fonctionRepository, TypeEmployeRepository typeEmployeRepository, StatusRepository statusRepository, 
		StockageFichiersImagesService stockagePhotoEmployeService, LieuAffectationRepository lieuAffectationRepository,
		AdresseEmployeRepository adresseEmployeRepository, ResourceLoader resourceLoader) {
		this.employeRepository = employeRepository;
		this.fonctionRepository = fonctionRepository;
		this.typeEmployeRepository=typeEmployeRepository;
		this.statusRepository = statusRepository;
		this.lieuAffectationRepository = lieuAffectationRepository;
		this.missionEmployeRepository = missionEmployeRepository;
		this.affectationRepository = affectationRepository;
		this.missionRepository =missionRepository;
		this.congeRepository = congeRepository;
		this.stockagePhotoEmployeService = stockagePhotoEmployeService;
		this.adresseEmployeRepository = adresseEmployeRepository;
		this.resourceLoader = resourceLoader;

		logger.info("composant employé service initialisé avec succès".toUpperCase());
	}

	@Transactional
	public List<EmployeDto> getAllEmploye() throws StockageFichiersImagesException, EmployeServiceException{
		var employes = employeRepository.findAll();
		logger.info("{} employé(s) récupérés avec succès!".toUpperCase(), employes.size());

		return employes!=null && !CollectionUtils.isEmpty(employes) ? employeRepository.findAll().stream()
				.distinct()
				.map((Employe emp) -> {
					EmployeDto eDto = null;
					try {
						eDto = employeToDtoMapper(emp);
					} catch (InterruptedException | ExecutionException | EmployeServiceException e) {
						logger.warn("La récupération d'un employé a échoué. \n{}", e.getMessage());
						throw new RuntimeException("Impossible de récupérer l'employé " + e.getMessage());
					}
					return eDto;
					/*try {
						eDto = employeToDtoMapper(emp);
						return eDto;
					} catch (InterruptedException | ExecutionException e) {
						//throw new RuntimeException(null);
						logger.warn("Le chargement de la photo a échoué. \n{}", e.getMessage());
						throw new RuntimeException("Impossible de recharger la photo " + e.getMessage());
					} catch (EmployeServiceException e) {
						logger.warn("La récupération d'un employé a échoué. \n{}", e.getMessage());
						throw new RuntimeException("Impossible de récupérer l'employé " + e.getMessage());
					}*/
					
				})
				.toList()
				: Collections.emptyList();
	}

	@Transactional
	public List<EmployeDto> getEmployesByLieuAffectation (String lAffect) throws StockageFichiersImagesException, EmployeServiceException{
		if (lAffect.isBlank()) {
			throw new IllegalArgumentException("Le lieu d'affectation ne peut pas être vide ou null.");
		}
		var lAff = this.lieuAffectationRepository.findByLieuAffectCode(lAffect)
						.get(0);

		return this.getAllEmploye().stream()
						.distinct()
						.filter(e -> e.getLieuAffectation().equalsIgnoreCase(lAff.getLieuAffect()))
						.toList();
	}

	@Transactional
	protected List<Employe> getEmployesFromEmpMatricules(final List<EmployeDto> empMatricules) {
		if (null==empMatricules ||  empMatricules.isEmpty()) {
			throw new IllegalArgumentException("La liste des employés autorisés à effectuer la mission ne doit être null ou vide.");
		}

		var matricules = empMatricules.stream()
			.distinct()
			.map(EmployeDto::getEmpMatricule)
			.sorted()
			.toList();
		
		return getEmployesFromMatricules(matricules);
	}

	private List<Employe> getEmployesFromMatricules(final List<String> empMatricules) {
		if (null==empMatricules ||  empMatricules.isEmpty()) {
			throw new IllegalArgumentException("La liste de matricules des employés autorisés à effectuer la mission ne doit être null ou vide.");
		}
		
		return employeRepository.findAll().stream()
			.distinct()
			.sorted(Comparator.comparing(Employe::getEmpMatricule))
			.filter(eDto -> empMatricules.contains(eDto.getEmpMatricule()))
			.toList();
	}

	public Optional<EmployeDto> addSignatureEmploye (String empMatricule, MultipartFile empSignature) throws IllegalAccessException{
		return null;
	}

	public Optional<EmployeDto> createEmploye (final String empCivilite, final String empNom, final String empPren, 
					final LocalDate empDateNsce, final String empLieuNsce, final String empNumActeNsce, final String typeEmploye, 
					final String empMatricule, final String empEmail, final String empTelephone, final String status, 
					final String empFonction, final String empAdrQtierResidce, final String empAdrVilleResidce, 
					final String empAdrPrefResidce, final String empAdrRegResidce,
					final String refDecretouArreteEntree, final String lieuAffectation, final LocalDate empDateDebutStatus,
					final LocalDate empDateFinStatus, final LocalDate dateDecretouArreteEntree, final MultipartFile empPhoto) throws IllegalAccessException, InterruptedException, ExecutionException, StockageFichiersImagesException, IOException, EmployeServiceException{
		
		Future<Path> futureEmplacementPhoto = null;
		//Future<Optional<EmployeDto>> futureOptEmploye = null;
		//Optional<EmployeDto> newEmp = Optional.empty();

		//if (Objects.nonNull(empphoto)){
		/*try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			if (Objects.nonNull(empPhoto)){
				futureEmplacementPhoto = executor.submit(() -> {
					return stockagePhotoEmployeService.stockerFichierCrde(empPhoto, List.of(empNom, empPren, empMatricule), false);
				});
			}

		}

		Path pathEmplacementPhoto = futureEmplacementPhoto!=null ? futureEmplacementPhoto.get() : null;
		*/
		Path pathEmplacementPhoto = null;
		Resource resourcePhoto = null;
		if (Objects.nonNull(empPhoto)){
			pathEmplacementPhoto = stockagePhotoEmployeService.stockerFichierCrde(empPhoto, List.of(empNom, empPren, empMatricule), false);
			logger.info("PATH PHOTO EMPLOYÉ STOCKÉE DANS {}\n NOMBRE DE BITS = {}\n URI = {}\n ", pathEmplacementPhoto, 0, pathEmplacementPhoto.toUri());
			//var resourcePhoto = resourceLoader.getResource(pathEmplacementPhoto.toString());//getClass().getResourceAsStream(pathEmplacementPhoto.toString());
			//logger.info("resourcePhoto PHOTO EMPLOYÉ = {}\n getContentAsByteArray {}\n getContentAsString {}\n", resourcePhoto, resourcePhoto.getContentAsByteArray(), resourcePhoto.getContentAsString(StandardCharsets.UTF_8));

			resourcePhoto = resourceLoader.getResource(pathEmplacementPhoto.toUri().getPath());//getClass().getResourceAsStream(pathEmplacementPhoto.toString());
			logger.info("resourcePhoto1 PHOTO EMPLOYÉ = {}\n toString() {}\n", resourcePhoto, resourcePhoto.toString()); //.getContentAsByteArray(), resourcePhoto.getContentAsString(StandardCharsets.UTF_8));
		}

		EmployeDto eDto = new EmployeDto();
		eDto.setDateDecretouArreteEntree(dateDecretouArreteEntree);
		eDto.setEmpCivilite(empCivilite);
		eDto.setEmpDateDebutStatus(dateDecretouArreteEntree);
		eDto.setEmpDateFinStatus(dateDecretouArreteEntree);
		eDto.setEmpEmail(empEmail);
		eDto.setEmpMatricule(empMatricule);
		eDto.setEmpNom(empNom);
		eDto.setEmpPren(empPren);
		eDto.setEmpTelephone(empTelephone);
		eDto.setFonction(empFonction);
		eDto.setLieuAffectation(lieuAffectation);
		eDto.setRefDecretouArreteEntree(refDecretouArreteEntree);
		eDto.setStatus("SVCE");
		eDto.setTypeEmploye(typeEmploye);
		eDto.setEmpEmplacementPhoto(Objects.nonNull(empPhoto) ? pathEmplacementPhoto.toString() : null);
		eDto.setEmpPhoto(resourcePhoto);//resourceLoader.getResource(pathEmplacementPhoto.toUri().getPath()));
		
		eDto.setEmpDateNsce(empDateNsce);
		eDto.setEmpLieuNsce(empLieuNsce);
		eDto.setEmpNumActeNsce(empNumActeNsce);

		eDto.setEmpAdrQuartierResidce(empAdrQtierResidce);
		eDto.setEmpAdrVilleResidce(empAdrVilleResidce);
		eDto.setEmpAdrPrefResidce(empAdrPrefResidce);
		eDto.setEmpAdrRegionResidce(lieuAffectation);

		var newEmp = saveEmployeUsingDto(eDto);
		logger.info("DONNÉES EMPLOYÉ À CRÉER :\n {}", newEmp);

		return newEmp;
	}

	private boolean checkExistanceEmploye(EmployeDto employeDto) throws StockageFichiersImagesException, EmployeServiceException{
		if (Objects.isNull(employeDto)){
			throw new IllegalArgumentException("Impossible de vérifier l'existance de l'employé car les données sont vides");
		}
		var optEmp = this.getAllEmploye().stream()
				.filter(emp -> emp.getEmpNom().equalsIgnoreCase(employeDto.getEmpNom()) && emp.getEmpPren().equalsIgnoreCase(employeDto.getEmpPren()))
				.findFirst();
		
		return optEmp.isPresent();
	}

	public Optional<EmployeDto> saveEmployeUsingDto (final EmployeDto employeDto) throws IllegalAccessException, InterruptedException, ExecutionException, EmployeServiceException, StockageFichiersImagesException {
		if (Objects.isNull(employeDto)) {
			logger.info("Impossible de créer l'employé car les données sont vides.");
			return Optional.empty();
		}

		/*var empExistant = checkExistanceEmploye(employeDto);
		if (empExistant){
			logger.info("Impossible de créer l'employé car, il existe déjà dans la base de données.");
			throw new IllegalAccessException("Cet employé existe déjà dans la base de données.");
		}*/
		
		Future<Optional<Fonction>> futureOptEmpFonct = null;
		Future<Optional<Status>> futureOptEmpStatus = null;
		Future<Optional<TypeEmploye>> futureOptTypeEmp = null;
		Future<Optional<LieuAffectation>> futureOptLieuAffect = null;
		Future<Path> futurePathPhoto = null;

		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try {//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
				futureOptEmpFonct = executor.submit(() -> {
					var result = fonctionRepository.findAll();
					return result.stream().distinct().filter(f -> f.getFonctionCode().equalsIgnoreCase(employeDto.getFonction().trim())).sorted().findFirst();
				});
				futureOptEmpStatus = executor.submit(() -> {
					var result = statusRepository.findAll();
					return result.stream().distinct().filter(s -> s.getStatusCode().equalsIgnoreCase(employeDto.getStatus().trim())).sorted().findFirst();
				});
				futureOptTypeEmp = executor.submit(() -> {
					var result = typeEmployeRepository.findAll();
					return result.stream().distinct().filter(t -> t.getTypeEmpCode().equalsIgnoreCase(employeDto.getTypeEmploye().trim())).sorted().findFirst();
				});
				futureOptLieuAffect = executor.submit(() -> {
					var result = lieuAffectationRepository.findAll();
					return result.stream().distinct().filter(l -> l.getLieuAffectCode().equalsIgnoreCase(employeDto.getLieuAffectation().trim())).sorted().findFirst();
				});
			//futurePathPhoto = executor.submit(() -> this.stockagePhotoEmployeService.chargerFichierCrde(employe.getEmpUrlphoto()));
		} catch (Exception e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base " +e.getMessage(), e.getCause());
		}
		executor.close();

		//try {
			logger.info("SAUVEGARDE DES DONNÉES EMPLOYÉ : {}, {} encours ...", employeDto.getEmpNom(), employeDto.getEmpPren());
			
			var empFonct = futureOptEmpFonct.get(); //fonctionRepository.findByFonctionCode(employeDto.getFonction().trim()); 
			var fonct = empFonct.orElseThrow(() -> new EntityNotFoundException("La fonction de l'employé est inconnue".toUpperCase()));

			var empStatus = futureOptEmpStatus.get(); //statusRepository.findByStatusCode(employeDto.getStatus().trim());
			var sttus = empStatus.orElseThrow(() -> new EntityNotFoundException("Le status de l'employé est inconnu".toUpperCase()));
			
			var empTypeEmp = futureOptTypeEmp.get(); //typeEmployeRepository.findByTypeEmpCode(employeDto.getTypeEmploye().trim());
			var typeEmp = empTypeEmp.orElseThrow(() -> new EntityNotFoundException("Le type d'employé de l'employé est inconnu".toUpperCase()));
			
			var empLieuAffect = futureOptLieuAffect.get(); //lieuAffectationRepository.findByLieuAffectCode(employeDto.getLieuAffectation().trim());
			var lAffect = empLieuAffect.orElseThrow(() -> new EntityNotFoundException("Le lieu d'affectation de l'employé est inconnu".toUpperCase()));
			
			var matEmp = employeDto.getEmpMatricule().replaceAll(CARACTERE_NON_AUTORISE, CARACTERE_REMPLACEMENT); 
			var refDecret = employeDto.getRefDecretouArreteEntree().replaceAll(CARACTERE_NON_AUTORISE, CARACTERE_REMPLACEMENT);
			
			Employe employe = new Employe();
			employe.setEmpCivilite(StringUtils.capitalize(employeDto.getEmpCivilite()));
			employe.setEmpCreeLe(LocalDateTime.now());
			employe.setEmpCreePar("admin");
			employe.setDateDecretEntree(employeDto.getDateDecretouArreteEntree());
			//employe.setDateDecretSortie(employeDto.getDateDecretouArreteDepart());
			employe.setEmpEmail(employeDto.getEmpEmail());
			employe.setEmpFonction(fonct);
			employe.setEmpLieuAffectation(lAffect);
			employe.setEmpMatricule(matEmp.toUpperCase());
			employe.setEmpModifieLe(LocalDateTime.now());
			employe.setEmpModifiePar("admin");
			employe.setEmpNom(StringUtils.capitalize(employeDto.getEmpNom()));
			employe.setEmpPren(StringUtils.capitalize(employeDto.getEmpPren()));
			employe.setEmpStatus(sttus);
			employe.setEmpTelephone(employeDto.getEmpTelephone());
			employe.setNumNoteService(null);
			employe.setReferenceDecretEntree(refDecret); //employeDto.getRefDecretouArreteEntree());
			employe.setTypeEmploye(typeEmp);
			employe.setEmpUrlphoto(employeDto.getEmpEmplacementPhoto());
			employe.setEmpDateNsce(employeDto.getEmpDateNsce());
			employe.setEmpLieuNsce(StringUtils.capitalize(employeDto.getEmpLieuNsce()));
			employe.setEmpNumActeNsce(employeDto.getEmpNumActeNsce());

			// Persistance de données
			var savedEmploye = saveEmploye(employe);
			logger.info("Employé enregistré avec succès sous le n° : {}\n  la photo stockée à l'emplacement : {}".toUpperCase(), savedEmploye.getId(), savedEmploye.getEmpUrlphoto());
			//employe.setNumNoteService(String.valueOf(employe.getId()));
			//logger.info("Employé enregistré avec succès.".toUpperCase());
			
			createInitialaffectation(savedEmploye);
			createAdresseActive(employeDto, savedEmploye);

			/*executor = Executors.newVirtualThreadPerTaskExecutor();
			try {
				executor.submit(() -> createInitialaffectation(savedEmploye));
				executor.submit(() -> createAdresseActive(employeDto, savedEmploye));
			} catch (Exception  e) {
				throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
			}
			executor.close();*/
			
			return employeMapper(Optional.ofNullable(savedEmploye));	
		/* } catch (EmployeServiceException e) {
			logger.info("La création de l'employé {} a echoué \n{}\n {}", employeDto.getEmpMatricule(), e.getMessage(), e.getCause());
			throw new EmployeServiceException("Impossile de cré. l'employé " + e.getMessage());
		}*/
		
	}

	@Transactional
	protected Employe saveEmploye(final Employe employe) throws IllegalAccessException{
		if (Objects.isNull(employe)) {
			logger.warn("Impossible de persister l'objet car l'employé est null.");
			throw new EntityNotFoundException("L'entité employé ne doit être null");
		}
		//logger.info("Affectation initiale de {}, {} enregistrée sous le n° : {}".toUpperCase(), employe.getEmpNom(), employe.getEmpPren(), );
		if (checkExistanceEmployeBeforSaving(employe, 1)){
			logger.info("Impossible de créer l'employé car il existe déjà dans la base de données.");
			throw new IllegalAccessException("Cet employé existe déjà dans la base de données.");
		}
		
		//Employe savedEmploye = null;
		//if (!checkExistanceEmployeBeforSaving(employe)){
		Employe savedEmploye = employeRepository.saveAndFlush(employe); 
		//}
		logger.debug("Employé {} {} est crée sous le numéro {}".toUpperCase(), employe.getEmpNom(), savedEmploye.getEmpPren(), savedEmploye.getId());

		return savedEmploye; //employeRepository.saveAndFlush(employe);
	}

	/**
	 * Double vérification d'abord sur l'existence du matricule puis celle du nom et prénoms

	 * @param employe
	 * @param verifParam
	 * @return true si existe sinon false
	 */
	private boolean checkExistanceEmployeBeforSaving(final Employe employe, int verifParam) {
		
		if (verifParam==1){
			var result = employeRepository.findByEmpMatricule(employe.getEmpMatricule());
			logger.info("le matricule {} de l'Employé existe déjà {}".toUpperCase(), employe.getEmpMatricule(), result.isPresent());
			
			return checkExistanceEmployeBeforSaving(employe, 2);
		} else {
			var result = employeRepository.findByEmpNomAndEmpPren(employe.getEmpNom(), employe.getEmpPren());
			logger.info("le nom {} et le prénom {} de l'Employé existent aussi déjà {}".toUpperCase(), employe.getEmpNom(), employe.getEmpPren(), result.isPresent());

			return result.isPresent();
		}
	}

	private boolean checkExistanceEmployeNameBeforSaving(final Employe employe) {
		var result = employeRepository.findByEmpMatricule(employe.getEmpMatricule());
		logger.info("Employé existe déjà {}".toUpperCase(), result.isPresent());

		return result.isPresent();
	}

	private void createInitialaffectation(final Employe employe){
		if (Objects.isNull(employe)) {
			throw new EntityNotFoundException("L'entité employé dont on veut créer l'affectation initiale ne doit être null");
		}

		Affectation initAffectation = new Affectation();
		//initAffectation.setCategorieAffect("Affectation initiale");
		initAffectation.setAffectCreeeLe(LocalDateTime.now());
		initAffectation.setAffectCreeePar("admin");
		initAffectation.setAffectModifieeLe(LocalDateTime.now());
		initAffectation.setAffectModifieePar("admin");
		initAffectation.setDateDebutAffect(employe.getDateDecretEntree()); //date decret ou arreté d'entrée
		initAffectation.setDateFinAffect(employe.getDateDecretEntree()); //par défaut, date decret ou arreté d'entrée
		initAffectation.setDatePriseService(employe.getDateDecretEntree()); //date decret ou arreté d'entrée
		initAffectation.setDateStatusAffect(employe.getDateDecretEntree()); //date decret ou arreté d'entrée
		initAffectation.setEmplacementAffect(employe.getEmpLieuAffectation().getLieuAffect());
		initAffectation.setEmploye(employe);
		initAffectation.setFonction(employe.getEmpFonction());
		//affectat.setId(null);
		initAffectation.setInfoSupplementaires("Affectation initiale");
		initAffectation.setLieuAffectation(employe.getEmpLieuAffectation());
		initAffectation.setNumNoteService(employe.getNumNoteService());
		initAffectation.setReferenceAffect(employe.getReferenceDecretEntree()); // référence decret ou arreté d'entrée
		initAffectation.setStatusAffect("Approuvée"); // Approuvée d'office
		initAffectation.setCategorieAffect("Affectation intérieur RCA"); //par défaut, inte
		initAffectation.setVilleResidence("Bangui"); //par défaut, Bangui
		initAffectation.setPaysResidence("RCA"); //par défaut, RCA

		var affectat = affectationRepository.saveAndFlush(initAffectation);
		logger.info("Affectation initiale de {} {} est créée avec succès sous le n° : {}".toUpperCase(), employe.getEmpNom(), employe.getEmpPren(), affectat.getId());
	}

	private void createAdresseActive(final EmployeDto eDto, final Employe employe){
		if (Objects.isNull(employe)) {
			throw new EntityNotFoundException("L'entité employé dont on veut créer l'adresse initiale ne doit être null");
		}

		AdresseEmploye adresseResidceEmp = new AdresseEmploye();
		adresseResidceEmp.setAdresseCreeLe(LocalDateTime.now());
		adresseResidceEmp.setAdresseCreePar("admin");
		adresseResidceEmp.setAdresseModifieLe(LocalDateTime.now());
		adresseResidceEmp.setAdresseModifiePar("admin");
		adresseResidceEmp.setEmploye(employe);
		adresseResidceEmp.setEstAdresseActive(Boolean.TRUE);
		adresseResidceEmp.setPrefectureResidence(eDto.getEmpAdrPrefResidce());
		adresseResidceEmp.setRegionResidence(eDto.getEmpAdrRegionResidce());
		adresseResidceEmp.setQuartierResidence(eDto.getEmpAdrQuartierResidce());
		adresseResidceEmp.setVilleResidence(eDto.getEmpAdrVilleResidce());

		var adrResidEmp = adresseEmployeRepository.saveAndFlush(adresseResidceEmp);
		logger.info("Adresse de résidence deùl'employé {} {} est créée avec succès sous le n° : {}".toUpperCase(), employe.getEmpNom(), employe.getEmpPren(), adrResidEmp.getId());
	}

	private final void computeStatusEncoursEmploye(final Optional<Affectation> optAffectation, Optional<Conge> optConge, Optional<MissionEmploye> optMissionEmploye, EmployeDto employeDto) throws EmployeServiceException {
		Mission mission = null;
		if (Objects.nonNull(optMissionEmploye) && optMissionEmploye.isPresent()){
			mission = optMissionEmploye.get().getId().getMission();
		}

		try {
			computeStatusEncoursEmploye(optAffectation, optConge, mission, employeDto);
		} catch (EmployeServiceException e) {
			logger.warn("Un problème est surgit lors de détermination du status de l'employé {}\n {}\n {}", String.join(" ", employeDto.getEmpNom(), employeDto.getEmpPren()), e.getMessage(), e.getCause());
			throw new EmployeServiceException("Un probléme est surgit lors de détermination du status de l'employé dont la cause est {}\n");
		}
		
	}

	private final void computeStatusEncoursEmploye(final Optional<Affectation> optAffectation, Optional<Conge> optConge, Mission missionEmploye, EmployeDto employeDto) throws EmployeServiceException {
			
		optAffectation.ifPresent(affectationEmploye -> {
			Conge congeEmploye = null;
			//Mission missionEmploye = null;
			
			if (Objects.nonNull(optConge) && optConge.isPresent()){
				congeEmploye = optConge.get();
			}

			/*if (Objects.nonNull(optMission) && optMission.isPresent()){
				missionEmploye = optMission.get();
			}*/

			if (Objects.nonNull(congeEmploye) && Objects.nonNull(missionEmploye)){ 
				// employé a dejà pris de congés et a aussi effectué des missions
				if (congeEmploye.getDateDebutConge().isAfter(missionEmploye.getDateRetour()) && congeEmploye.getDateDebutConge().isAfter(affectationEmploye.getDateDebutAffect())){
						// Employé en congé
						employeDto.setEmpDateDebutStatus(congeEmploye.getDateDebutConge());
						employeDto.setEmpDateFinStatus(congeEmploye.getDateFinConge());
						employeDto.setStatus(statusRepository.findByStatusCode(String.valueOf("CGE")).stream().distinct().sorted().findFirst().orElseThrow(EntityNotFoundException::new).getStatus());	
				} else {
					if (missionEmploye.getDateDepart().isAfter(affectationEmploye.getDateDebutAffect())){
						// Employé en mission
						employeDto.setEmpDateDebutStatus(missionEmploye.getDateDepart());
						employeDto.setEmpDateFinStatus(missionEmploye.getDateRetour());
						employeDto.setStatus(statusRepository.findByStatusCode(String.valueOf("MSN")).stream().distinct().sorted().findFirst().orElseThrow(EntityNotFoundException::new).getStatus());
					}
				}		
			} else {
				//logger.info("computeStatusEncoursEmploye 2 MISSION: {}", missionEmploye.getCadreMission());
				// employé a soit dejà pris de congés ou a aussi effectué des missions
				if (Objects.nonNull(missionEmploye) && missionEmploye.getDateDepart().isAfter(affectationEmploye.getDateDebutAffect())){
						// Employé en mission
						employeDto.setEmpDateDebutStatus(missionEmploye.getDateDepart());
						employeDto.setEmpDateFinStatus(missionEmploye.getDateRetour());
						employeDto.setStatus(statusRepository.findByStatusCode(String.valueOf("MSN")).stream().distinct().sorted().findFirst().orElseThrow(EntityNotFoundException::new).getStatus());
					
				} else {
					if (Objects.nonNull(congeEmploye) && congeEmploye.getDateDebutConge().isAfter(affectationEmploye.getDateDebutAffect())){
							// Employé en congé
							employeDto.setEmpDateDebutStatus(congeEmploye.getDateDebutConge());
							employeDto.setEmpDateFinStatus(congeEmploye.getDateFinConge());
							employeDto.setStatus(statusRepository.findByStatusCode(String.valueOf("CGE")).stream().distinct().sorted().findFirst().orElseThrow(EntityNotFoundException::new).getStatus());
					} else {
						// Employé n'a ni congé ni mission
						employeDto.setEmpDateDebutStatus(affectationEmploye.getDateDebutAffect());
						employeDto.setStatus(statusRepository.findByStatusCode(String.valueOf("SVCE")).stream().distinct().sorted().findFirst().orElseThrow(EntityNotFoundException::new).getStatus());

						if (!affectationEmploye.getDateFinAffect().isAfter(affectationEmploye.getDateDebutAffect())){
							employeDto.setEmpDateFinStatus(affectationEmploye.getDateFinAffect());
						}
					}
				}
			}

			// Fonction et lieu d'affectation encours
			employeDto.setFonction(affectationEmploye.getFonction().getFonction());
			employeDto.setLieuAffectation(affectationEmploye.getLieuAffectation().getLieuAffect());

		}); 
	}

	public Optional<EmployeDto> getEmployeByMatricule(String empMatricule) throws InterruptedException, ExecutionException, EmployeServiceException{
		if (StringUtils.isBlank(empMatricule)){
			return Optional.empty();
		}
		var optionalEmp = employeRepository.findByEmpMatricule(empMatricule);
	
		return employeMapper(optionalEmp);
	}

	private Optional<EmployeDto> employeMapper(Optional<Employe> optEmploye) throws InterruptedException, ExecutionException, EmployeServiceException{
		if (!optEmploye.isPresent()){
			return Optional.empty();	
		}
		
		var eDto = employeToDtoMapper(optEmploye.get());

		return Optional.of(eDto);
	}
    
    private EmployeDto employeToDtoMapper(Employe employe) throws InterruptedException, ExecutionException, EmployeServiceException{
	    if (Objects.isNull(employe)){
			logger.warn("Impossible de faire le mappage car aucune donnée de l'employé n'est fournie");
            throw new EntityNotFoundException("L'entité employé ne doit être null");
		}
		
		/*Future<Optional<Fonction>> futureOptEmpFonct = null;
		Future<Optional<Status>> futureOptEmpStatus = null;
		Future<Optional<TypeEmploye>> futureOptTypeEmp = null;
		Future<Optional<LieuAffectation>> futureOptLieuAffect = null;
		//Future<Path> futurePathPhoto = null;

		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try {//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futureOptEmpFonct = executor.submit(() -> fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode()));
			futureOptEmpStatus = executor.submit(() -> statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode()));
			futureOptTypeEmp = executor.submit(() -> typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode()));
			futureOptLieuAffect = executor.submit(() -> lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode()));
			//futurePathPhoto = executor.submit(() -> this.stockagePhotoEmployeService.chargerFichierCrde(employe.getEmpUrlphoto()));
		} catch (Exception e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto");
		}
		executor.close(); //waits until all tasks have completed execution and the executor has terminated
		
		 var empFonct = futureOptEmpFonct.get(); //fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode());
		var empStatus = futureOptEmpStatus.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		var empTypeEmp = futureOptTypeEmp.get(); //typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode());
		var empLieuAffect = futureOptLieuAffect.get(); //lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode());
		
		*/
		var empFonct = fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode()).stream().distinct().sorted().findFirst();
		var empStatus = statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode()).stream().distinct().sorted().findFirst();
		var empTypeEmp = typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode()).stream().distinct().sorted().findFirst();
		var empLieuAffect = lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode()).stream().distinct().sorted().findFirst();
		var empAdresseResdceActive = adresseEmployeRepository.findByEmployeAndEstAdresseActive(employe, Boolean.TRUE).stream().distinct().sorted().findFirst();
		var adrResidce = empAdresseResdceActive.orElseGet(AdresseEmploye::new);
		
		//var pathPhoto = futurePathPhoto.get();
		//logger.warn("futurePathPhoto.get() {}\n employe.getEmpUrlphoto() {}\n", pathPhoto.toString(), employe.getEmpUrlphoto());

		var eDto = new EmployeDto();
		eDto.setEmpCivilite(employe.getEmpCivilite());
		eDto.setEmpMatricule(employe.getEmpMatricule());
		eDto.setEmpNom(employe.getEmpNom());
		eDto.setEmpPren(employe.getEmpPren());
		eDto.setEmpTelephone(employe.getEmpTelephone());
		eDto.setEmpEmail(employe.getEmpEmail());
		eDto.setFonction(empFonct.orElseThrow(EntityNotFoundException::new).getFonction()); //fonction initiale
		eDto.setStatus(empStatus.orElseThrow(EntityNotFoundException::new).getStatus());
		eDto.setTypeEmploye(empTypeEmp.orElseThrow(EntityNotFoundException::new).getTypeEmp());
		eDto.setLieuAffectation(empLieuAffect.orElseThrow(EntityNotFoundException::new).getLieuAffect()); //lieu affectation initial
		eDto.setEmpNumInterne(String.valueOf(employe.getId()));
		eDto.setRefDecretouArreteEntree(employe.getReferenceDecretEntree());
		eDto.setDateDecretouArreteEntree(employe.getDateDecretEntree());
		eDto.setRefDecretouArreteDepart(employe.getReferenceDecretSortie());
		eDto.setDateDecretouArreteDepart(employe.getDateDecretSortie());
		eDto.setEmpEmplacementPhoto(employe.getEmpUrlphoto());

		eDto.setEmpAdrPrefResidce(adrResidce.getPrefectureResidence());
		eDto.setEmpAdrRegionResidce(adrResidce.getRegionResidence());
		eDto.setEmpAdrQuartierResidce(adrResidce.getQuartierResidence());
		eDto.setEmpAdrVilleResidce(adrResidce.getVilleResidence());

		eDto.setEmpDateNsce(employe.getEmpDateNsce());
		eDto.setEmpLieuNsce(employe.getEmpLieuNsce());
		eDto.setEmpNumActeNsce(employe.getEmpNumActeNsce());

		var optAffectationEmploye = gettatusEncoursEmploye(employe);
		var optCongeEmploye = getCongeEncoursEmploye(employe);
		var optMissionEmploye = getMissionEncoursEmploye(employe);
		
		computeStatusEncoursEmploye(optAffectationEmploye, optCongeEmploye, optMissionEmploye, eDto);
		return eDto;
	}

	private final Optional<Affectation> gettatusEncoursEmploye(final Employe employe) {

		return affectationRepository.findByEmploye(employe).stream()
								.distinct()
								.sorted(Comparator.comparing(Affectation::getId).reversed())
								//.sorted((a1, a2) -> a2.getId().compareTo(a1.getId()))
								//.filter(a -> a.getDateFinAffect().equals(a.getDateDebutAffect()))
								.findFirst();
	}

	private final Optional<Conge> getCongeEncoursEmploye(final Employe employe) {

		return congeRepository.findByEmploye(employe).stream()
								.distinct()
								.sorted(Comparator.comparing(Conge::getId).reversed())
								//.sorted((c1, c2) -> c2.getId().compareTo(c1.getId()))
								//.filter(a -> a.getDateFinAffect().equals(a.getDateDebutAffect()))
								.findFirst();
	}

	private final Optional<MissionEmploye> getMissionEncoursEmploye(final Employe employe) {

		return missionEmployeRepository.findAll().stream()
								.distinct()
								.filter(me -> me.getId().getEmploye().getEmpMatricule().equalsIgnoreCase(employe.getEmpMatricule()) && me.getId().getMission().getStatusMission().startsWith("Approuvé"))
								.sorted((m1, m2) -> m2.getId().getMission().getId().compareTo(m1.getId().getMission().getId()))
								.findFirst();
				
		//.findByEmploye(employe).stream()
		//						.sorted(Comparator.comparing(Mission::getId).reversed())
								//.sorted((m1, m2) -> m2.getId().compareTo(m1.getId()))
								//.filter(a -> a.getDateFinAffect().equals(a.getDateDebutAffect()))
		//						.findFirst();
	}

}