package gpersonnelcrde.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import gpersonnelcrde.domain.dto.EmployeDto;
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
	private final StockageFichiersImagesService stockagePhotoEmployeService;
	private final ResourceLoader resourceLoader;

	public EmployeService(EmployeRepository employeRepository, AffectationRepository affectationRepository, 
	    MissionRepository missionRepository, CongeRepository congeRepository, MissionEmployeRepository missionEmployeRepository,
		FonctionRepository fonctionRepository, TypeEmployeRepository typeEmployeRepository, StatusRepository statusRepository, 
		StockageFichiersImagesService stockagePhotoEmployeService, LieuAffectationRepository lieuAffectationRepository, ResourceLoader resourceLoader) {
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
		this.resourceLoader = resourceLoader;

		logger.info("composant employé service initialisé avec succès".toUpperCase());
	}

	public List<EmployeDto> getAllEmploye() throws StockageFichiersImagesException, EmployeServiceException{
		var employes = employeRepository.findAll();
		logger.info("{} employé(s) récupérés avec succès!".toUpperCase(), employes.size());

		return employes!=null && !CollectionUtils.isEmpty(employes) ? employeRepository.findAll().stream()
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
	protected List<Employe> getEmployesFromEmpMatricules(final List<EmployeDto> empMatricules) {
		if (null==empMatricules ||  empMatricules.isEmpty()) {
			throw new IllegalArgumentException("La liste des employés autorisés à effectuer la mission ne doit être null ou vide.");
		}

		var matricules = empMatricules.stream()
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
			.sorted(Comparator.comparing(Employe::getEmpMatricule))
			.filter(eDto -> empMatricules.contains(eDto.getEmpMatricule()))
			.toList();
	}

	public Optional<EmployeDto> addSignatureEmploye (String empMatricule, MultipartFile empSignature) throws IllegalAccessException{
		return null;
	}

	public Optional<EmployeDto> createEmploye (String empCivilite, String empNom, String empPren, String typeEmploye, 
					String empMatricule, String empEmail, String empTelephone, String status, String empFonction,
					String refDecretouArreteEntree, String lieuAffectation, LocalDate empDateDebutStatus,
					LocalDate empDateFinStatus, LocalDate dateDecretouArreteEntree, MultipartFile empPhoto) throws IllegalAccessException, InterruptedException, ExecutionException, StockageFichiersImagesException, IOException, EmployeServiceException{
		
		//Future<Path> futureEmplacementPhoto = null;
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
		
		var newEmp = createEmploye (eDto);
		logger.info("DONNÉES EMPLOYÉ À CRÉER :\n {}", newEmp);

		return newEmp;
	}

	private boolean checkEmployeExistance(EmployeDto employeDto) throws StockageFichiersImagesException, EmployeServiceException{
		if (Objects.isNull(employeDto)){
			throw new IllegalArgumentException("Impossible de vérifier l'existance de l'employé car les données sont vides");
		}
		var optEmp = this.getAllEmploye().stream()
				.filter(emp -> emp.getEmpNom().equalsIgnoreCase(employeDto.getEmpNom()) && emp.getEmpPren().equalsIgnoreCase(employeDto.getEmpPren()))
				.findFirst();
		
		return optEmp.isPresent();
	}

	public Optional<EmployeDto> createEmploye (final EmployeDto employeDto) throws IllegalAccessException, InterruptedException, ExecutionException, EmployeServiceException {
		if (Objects.isNull(employeDto)) {
			logger.info("Impossible de créer l'employé car les données sont vides.");
			return Optional.empty();
		}

		/*****var empExistant = checkEmployeExistance(employeDto);
		if (empExistant){
			logger.info("Impossible de créer l'employé car, il existe déjà dans la base de données.");
			throw new IllegalAccessException("Cet employé existe déjà dans la base de données.");
		}******À REVOIR**/
		
		Future<Optional<Fonction>> futureOptEmpFonct = null;
		Future<Optional<Status>> futureOptEmpStatus = null;
		Future<Optional<TypeEmploye>> futureOptTypeEmp = null;
		Future<Optional<LieuAffectation>> futureOptLieuAffect = null;
		Future<Path> futurePathPhoto = null;

		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try {//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futureOptEmpFonct = executor.submit(() -> fonctionRepository.findByFonctionCode(employeDto.getFonction().trim()));
			futureOptEmpStatus = executor.submit(() -> statusRepository.findByStatusCode(employeDto.getStatus().trim()));
			futureOptTypeEmp = executor.submit(() -> typeEmployeRepository.findByTypeEmpCode(employeDto.getTypeEmploye().trim()));
			futureOptLieuAffect = executor.submit(() -> lieuAffectationRepository.findByLieuAffectCode(employeDto.getLieuAffectation().trim()));
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
			
			Employe employe = new Employe();
			employe.setEmpCivilite(employeDto.getEmpCivilite());
			employe.setEmpCreeLe(LocalDateTime.now());
			employe.setEmpCreePar("admin");
			employe.setDateDecretEntree(employeDto.getDateDecretouArreteEntree());
			//employe.setDateDecretSortie(employeDto.getDateDecretouArreteDepart());
			employe.setEmpEmail(employeDto.getEmpEmail());
			employe.setEmpFonction(fonct);
			employe.setEmpLieuAffectation(lAffect);
			employe.setEmpMatricule(employeDto.getEmpMatricule());
			employe.setEmpModifieLe(LocalDateTime.now());
			employe.setEmpModifiePar("admin");
			employe.setEmpNom(employeDto.getEmpNom());
			employe.setEmpPren(employeDto.getEmpPren());
			employe.setEmpStatus(sttus);
			employe.setEmpTelephone(employeDto.getEmpTelephone());
			employe.setNumNoteService(null);
			employe.setReferenceDecretEntree(employeDto.getRefDecretouArreteEntree());
			employe.setTypeEmploye(typeEmp);
			employe.setEmpUrlphoto(employeDto.getEmpEmplacementPhoto());

			// Persistance de données
			employe = saveEmploye(employe);
			logger.info("Employé enregistré avec succès sous le n° : {}\n  la photo stockée à l'emplacement : {}".toUpperCase(), employe.getId(), employe.getEmpUrlphoto());
			//employe.setNumNoteService(String.valueOf(employe.getId()));
			//logger.info("Employé enregistré avec succès.".toUpperCase());

			createInitialaffectation(employe);

			return employeMapper(Optional.ofNullable(employe));	
		/* } catch (EmployeServiceException e) {
			logger.info("La création de l'employé {} a echoué \n{}\n {}", employeDto.getEmpMatricule(), e.getMessage(), e.getCause());
			throw new EmployeServiceException("Impossile de cré. l'employé " + e.getMessage());
		}*/
		
	}

	@Transactional
	protected Employe saveEmploye(final Employe employe){
		if (Objects.isNull(employe)) {
			throw new EntityNotFoundException("L'entité employé ne doit être null");
		}
		//logger.info("Affectation initiale de {}, {} enregistrée sous le n° : {}".toUpperCase(), employe.getEmpNom(), employe.getEmpPren(), );
		
		return employeRepository.saveAndFlush(employe);
	}

	private void createInitialaffectation(final Employe employe){
		if (Objects.isNull(employe)) {
			throw new EntityNotFoundException("L'entité employé dont on veut créer son affectation initiale ne doit être null");
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
		logger.info("Affectation initiale de {}, {} est créée avec succès sous le n° : {}".toUpperCase(), employe.getEmpNom(), employe.getEmpPren(), affectat.getId());
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
						employeDto.setStatus(statusRepository.findByStatusCode(String.valueOf("CGE")).orElseThrow(EntityNotFoundException::new).getStatus());	
				} else {
					if (missionEmploye.getDateDepart().isAfter(affectationEmploye.getDateDebutAffect())){
						// Employé en mission
						employeDto.setEmpDateDebutStatus(missionEmploye.getDateDepart());
						employeDto.setEmpDateFinStatus(missionEmploye.getDateRetour());
						employeDto.setStatus(statusRepository.findByStatusCode(String.valueOf("MSN")).orElseThrow(EntityNotFoundException::new).getStatus());
					}
				}		
			} else {
				//logger.info("computeStatusEncoursEmploye 2 MISSION: {}", missionEmploye.getCadreMission());
				// employé a soit dejà pris de congés ou a aussi effectué des missions
				if (Objects.nonNull(missionEmploye) && missionEmploye.getDateDepart().isAfter(affectationEmploye.getDateDebutAffect())){
						// Employé en mission
						employeDto.setEmpDateDebutStatus(missionEmploye.getDateDepart());
						employeDto.setEmpDateFinStatus(missionEmploye.getDateRetour());
						employeDto.setStatus(statusRepository.findByStatusCode(String.valueOf("MSN")).orElseThrow(EntityNotFoundException::new).getStatus());
					
				} else {
					if (Objects.nonNull(congeEmploye) && congeEmploye.getDateDebutConge().isAfter(affectationEmploye.getDateDebutAffect())){
							// Employé en congé
							employeDto.setEmpDateDebutStatus(congeEmploye.getDateDebutConge());
							employeDto.setEmpDateFinStatus(congeEmploye.getDateFinConge());
							employeDto.setStatus(statusRepository.findByStatusCode(String.valueOf("CGE")).orElseThrow(EntityNotFoundException::new).getStatus());
					} else {
						// Employé n'a ni congé ni mission
						employeDto.setEmpDateDebutStatus(affectationEmploye.getDateDebutAffect());
						employeDto.setStatus(statusRepository.findByStatusCode(String.valueOf("SVCE")).orElseThrow(EntityNotFoundException::new).getStatus());

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
		var empFonct = fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode());
		var empStatus = statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		var empTypeEmp = typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode());
		var empLieuAffect = lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode());
		
		
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

		var optAffectationEmploye = gettatusEncoursEmploye(employe);
		var optCongeEmploye = getCongeEncoursEmploye(employe);
		var optMissionEmploye = getMissionEncoursEmploye(employe);
		
		computeStatusEncoursEmploye(optAffectationEmploye, optCongeEmploye, optMissionEmploye, eDto);
		return eDto;
	}

	private final Optional<Affectation> gettatusEncoursEmploye(final Employe employe) {

		return affectationRepository.findByEmploye(employe).stream()
								.sorted(Comparator.comparing(Affectation::getId).reversed())
								//.sorted((a1, a2) -> a2.getId().compareTo(a1.getId()))
								//.filter(a -> a.getDateFinAffect().equals(a.getDateDebutAffect()))
								.findFirst();
	}

	private final Optional<Conge> getCongeEncoursEmploye(final Employe employe) {

		return congeRepository.findByEmploye(employe).stream()
								.sorted(Comparator.comparing(Conge::getId).reversed())
								//.sorted((c1, c2) -> c2.getId().compareTo(c1.getId()))
								//.filter(a -> a.getDateFinAffect().equals(a.getDateDebutAffect()))
								.findFirst();
	}

	private final Optional<MissionEmploye> getMissionEncoursEmploye(final Employe employe) {

		return missionEmployeRepository.findAll().stream()
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