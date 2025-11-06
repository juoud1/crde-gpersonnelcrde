package gpersonnelcrde.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.entities.Affectation;
import gpersonnelcrde.domain.entities.Conge;
import gpersonnelcrde.domain.entities.Employe;
import gpersonnelcrde.domain.entities.Mission;
import gpersonnelcrde.repository.AffectationRepository;
import gpersonnelcrde.repository.CongeRepository;
import gpersonnelcrde.repository.EmployeRepository;
import gpersonnelcrde.repository.FonctionRepository;
import gpersonnelcrde.repository.LieuAffectationRepository;
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
	private final CongeRepository congeRepository;

	private final FonctionRepository fonctionRepository;
	private final TypeEmployeRepository typeEmployeRepository;
	private final StatusRepository statusRepository;
	private final LieuAffectationRepository lieuAffectationRepository;

	public EmployeService(EmployeRepository employeRepository, AffectationRepository affectationRepository, 
	    MissionRepository missionRepository, CongeRepository congeRepository,
		FonctionRepository fonctionRepository, TypeEmployeRepository typeEmployeRepository, 
		StatusRepository statusRepository, LieuAffectationRepository lieuAffectationRepository) {
		this.employeRepository = employeRepository;
		
		this.fonctionRepository = fonctionRepository;
		this.typeEmployeRepository=typeEmployeRepository;
		this.statusRepository = statusRepository;
		this.lieuAffectationRepository= lieuAffectationRepository;

		this.affectationRepository = affectationRepository;
		this.missionRepository =missionRepository;
		this.congeRepository = congeRepository;
		logger.info("composant employé service initialisé avec succès".toUpperCase());
	}

	public List<EmployeDto> getAllEmploye(){

		return employeRepository.findAll().stream()
				.map((Employe emp) -> {
					var eDto = employeToDtoMapper(emp);
					return eDto;
				})
				.toList();
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

	public Optional<EmployeDto> createEmploye (String empCivilite, String empNom, String empPren, String typeEmploye, 
					String empMatricule, String empEmail, String empTelephone, String status, String empFonction,
					String refDecretouArreteEntree, String lieuAffectation, LocalDate empDateDebutStatus,
					LocalDate empDateFinStatus, LocalDate dateDecretouArreteEntree) throws IllegalAccessException{
		
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
		eDto.setStatus(status);
		eDto.setTypeEmploye(typeEmploye);

		var newEmp = createEmploye (eDto);
		
		return newEmp;
	}

	private boolean checkEmployeExistance(EmployeDto employeDto){
		if (Objects.isNull(employeDto)){
			throw new IllegalArgumentException("Impossible de vérifier l'existance de l'employé car les données sont vides");
		}
		var optEmp = this.getAllEmploye().stream()
				.filter(emp -> emp.getEmpNom().equalsIgnoreCase(employeDto.getEmpNom()) && emp.getEmpPren().equalsIgnoreCase(employeDto.getEmpPren()))
				.findFirst();
		
		return optEmp.isPresent();
	}

	public Optional<EmployeDto> createEmploye (final EmployeDto employeDto) throws IllegalAccessException {
		if (Objects.isNull(employeDto)) {
			logger.info("Impossible de créer l'employé car les données sont vides.");
			return Optional.empty();
		}

		var empExistant = checkEmployeExistance(employeDto);
		if (empExistant){
			logger.info("Impossible de créer l'employé car, il existe déjà dans la base de données.");
			throw new IllegalAccessException("Cet employé existe déjà dans la base de données.");
		}

		logger.info("SAUVEGARDE DES DONNÉES EMPLOYÉ : {}, {}", employeDto.getEmpNom(), employeDto.getEmpPren());
		var empFonct = fonctionRepository.findByFonctionCode(employeDto.getFonction().trim()); 
		var fonct = empFonct.orElseThrow(() -> new EntityNotFoundException("La fonction de l'employé est inconnue".toUpperCase()));

		var empStatus = statusRepository.findByStatusCode(employeDto.getStatus().trim());
		var sttus = empStatus.orElseThrow(() -> new EntityNotFoundException("Le status de l'employé est inconnu".toUpperCase()));
		var empTypeEmp = typeEmployeRepository.findByTypeEmpCode(employeDto.getTypeEmploye().trim());
		var typeEmp = empTypeEmp.orElseThrow(() -> new EntityNotFoundException("Le type d'employé de l'employé est inconnu".toUpperCase()));
		
		var empLieuAffect = lieuAffectationRepository.findByLieuAffectCode(employeDto.getLieuAffectation().trim());
		var lAffect = empLieuAffect.orElseThrow(() -> new EntityNotFoundException("Le lieu d'affectation de l'employé est inconnu".toUpperCase()));
		
		var employe = new Employe();
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

		// Persistance de données
		employe = saveEmploye(employe);
		logger.info("Employé enregistré avec succès sous le n° : {}".toUpperCase(), employe.getId());
		//employe.setNumNoteService(String.valueOf(employe.getId()));
		//logger.info("Employé enregistré avec succès.".toUpperCase());

		createInitialaffectation(employe);

		return employeMapper(Optional.ofNullable(employe));
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

	private final void computeStatusEncoursEmploye(final Optional<Affectation> optAffectation, Optional<Conge> optConge, Optional<Mission> optMission, EmployeDto employeDto) {
			optAffectation.ifPresent(affectationEmploye -> {
			Conge congeEmploye = null;
			Mission missionEmploye = null;
			
			if (Objects.nonNull(optConge) && optConge.isPresent()){
				congeEmploye = optConge.get();
			}

			if (Objects.nonNull(optMission) && optMission.isPresent()){
				missionEmploye = optMission.get();
			}

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

	public Optional<EmployeDto> getEmployeByMatricule(String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return Optional.empty();
		}
		var optionalEmp = employeRepository.findByEmpMatricule(empMatricule);
	
		return employeMapper(optionalEmp);
	}

	private Optional<EmployeDto> employeMapper(Optional<Employe> optEmploye){
		if (!optEmploye.isPresent()){
			return Optional.empty();	
		}
		
		var eDto = employeToDtoMapper(optEmploye.get());

		return Optional.of(eDto);
	}
    
    private EmployeDto employeToDtoMapper(Employe employe){
	    if (Objects.isNull(employe)){
			logger.warn("Impossible de faire le mappage car aucune donnée de l'employé n'est fournie");
            throw new EntityNotFoundException("L'entité employé ne doit être null");
		}

	    var empFonct = fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode());
		var empStatus = statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		var empTypeEmp = typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode());
		var empLieuAffect = lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode());
		
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

		var optAffectationEmploye = gettatusEncoursEmploye(employe);
		var optCongeEmploye = getCongeEncoursEmploye(employe);
		////var optMissionEmploye = getMissionEncoursEmploye(employe);
		////computeStatusEncoursEmploye(optAffectationEmploye, optCongeEmploye, optMissionEmploye, eDto);
		computeStatusEncoursEmploye(optAffectationEmploye, optCongeEmploye, null, eDto);
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

	/*private final Optional<Mission> getMissionEncoursEmploye(final Employe employe) {

		return missionRepository.findByEmploye(employe).stream()
								.sorted(Comparator.comparing(Mission::getId).reversed())
								//.sorted((m1, m2) -> m2.getId().compareTo(m1.getId()))
								//.filter(a -> a.getDateFinAffect().equals(a.getDateDebutAffect()))
								.findFirst();
	}*/
}
