package gpersonnelcrde.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import gpersonnelcrde.domain.dto.AffectationDto;
import gpersonnelcrde.domain.entities.Affectation;
import gpersonnelcrde.repository.AffectationRepository;
import gpersonnelcrde.repository.EmployeRepository;
import gpersonnelcrde.repository.FonctionRepository;
import gpersonnelcrde.repository.LieuAffectationRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class AffectationService {
	public static final Logger logger = LoggerFactory.getLogger(AffectationService.class);

	private final AffectationRepository affectationRepository;
	private final EmployeRepository employeRepository;
	private final LieuAffectationRepository lieuAffectationRepository;
	private final FonctionRepository fonctionRepository;

	public AffectationService(AffectationRepository affectationRepository, EmployeRepository employeRepository,
			 LieuAffectationRepository lieuAffectationRepository, FonctionRepository fonctionRepository) {
		this.affectationRepository = affectationRepository;
		this.employeRepository = employeRepository;
		this.lieuAffectationRepository = lieuAffectationRepository;
		this.fonctionRepository = fonctionRepository;
	}

	public List<AffectationDto> getAllAffectation(){
		return affectationRepository.findAll().stream()
				.map(affect -> {
					var aDto = affectationToDtoMapper(affect);
					
					return aDto;
				})
				.toList();
	}

	public Optional<AffectationDto> createAffectation(String categorieAffect, String affectEmpMatricule, LocalDate dateDebAffect, LocalDate dateFinAffect, LocalDate datePriseService,
					String numNoteServiceAffect, String lieuAffect, String emplacementAffect, String fonction, String commenataireAffect, String villeResidence, String paysResidence) throws IllegalAccessException{
	
		AffectationDto aDto = new AffectationDto();
		aDto.setCategorieAffect(categorieAffect);
		aDto.setDateDebutAffect(dateDebAffect);
		aDto.setDateFinAffect(dateFinAffect);
		aDto.setDatePriseService(datePriseService);
		aDto.setDateStatusAffect(datePriseService);
		aDto.setEmplacementAffect(emplacementAffect);
		aDto.setEmployeCivilite(null);
		aDto.setEmployeMatricule(affectEmpMatricule);
		aDto.setEmployeNom(null);
		aDto.setFonction(fonction);
		aDto.setInfoSupplementaires(commenataireAffect);
		aDto.setLieuAffectation(lieuAffect);
		aDto.setNumNoteService(numNoteServiceAffect);
		aDto.setReferenceAffect(null);
		aDto.setVilleResidence(villeResidence);
		aDto.setPaysResidence(paysResidence);
		aDto.setStatusAffect("En attente");

		return createAffectation(aDto);
	}

	private boolean checkAffectationExistance(AffectationDto affectationDto){
		if (Objects.isNull(affectationDto)){
			throw new IllegalArgumentException("Impossible de vérifier l'existance de l'affectation employé car les données sont vides");
		}
		var optAffect = this.getAllAffectation().stream()
				.filter(aff -> aff.getEmployeMatricule().equalsIgnoreCase(affectationDto.getEmployeMatricule()) && aff.getDateDebutAffect().equals(affectationDto.getDateDebutAffect()) 
						&& aff.getFonction().equalsIgnoreCase(affectationDto.getFonction()) && aff.getLieuAffectation().equalsIgnoreCase(affectationDto.getLieuAffectation()))
				.findFirst();
		
		return optAffect.isPresent();
	}

	private Affectation getCurrentAffectationForUpdate(final String employeMatricule){
		var optEmp = employeRepository.findByEmpMatricule(employeMatricule);
		var emp =  optEmp.orElseThrow(() -> new EntityNotFoundException("L'employé affecté est inconnu".toUpperCase()));

		var currentAffectation = affectationRepository.findByEmploye(emp).stream()
									.sorted(Comparator.comparingLong(Affectation::getId).reversed())
									.findFirst().orElseThrow(() -> new EntityNotFoundException("Aucunne affectation pour cet employé"));
		logger.info("Affectation encours de {} est le n° {}", employeMatricule, currentAffectation.getId());
		
		return currentAffectation;
	}

	private void updateCurrentAffectation(final String employeMatricule, final LocalDate dateFinCurrentAffectation){
		Affectation currentAff = getCurrentAffectationForUpdate(employeMatricule);
		currentAff.setDateFinAffect(dateFinCurrentAffectation);
		//Affectation avec dateFin affectation renseignée
		var cAff = saveAffectation(currentAff);
		logger.info("Mise à jour de l'affectation encours est effectuée avec succès! {}", cAff.getDateFinAffect());
	}

	public Optional<AffectationDto> createAffectation(AffectationDto affectationDto) throws IllegalAccessException{
		if (Objects.isNull(affectationDto)) {
			logger.info("Impossible de créer l'affectation employé car les données sont vides.");
			return Optional.empty();
		}

		var affectExistant = checkAffectationExistance(affectationDto);
		if (affectExistant){ //Objects.nonNull(empExistant)){
			logger.info("Impossible de créer l'affectation employé car, elle existe déjà dans la base de données.");
			throw new IllegalAccessException("Cette affectation employé existe déjà dans la base de données.");
		}



		logger.info("SAUVEGARDE DES DONNÉES AFFECTATION {} DE L'EMPLOYÉ {}", affectationDto.getNumNoteService(), affectationDto.getEmployeMatricule());
		var optEmpFonct = fonctionRepository.findByFonctionCode(affectationDto.getFonction().trim()).stream().sorted().findFirst(); 
		var fonct = optEmpFonct.orElseThrow(() -> new EntityNotFoundException("La fonction de l'employé affecté est inconnue".toUpperCase()));

		var optEmpLieuAffect = lieuAffectationRepository.findByLieuAffectCode(affectationDto.getLieuAffectation().trim()).stream().sorted().findFirst();
		var lAffect = optEmpLieuAffect.orElseThrow(() -> new EntityNotFoundException("Le lieu d'affectation de l'employé affecté est inconnu".toUpperCase()));

		var optEmp = employeRepository.findByEmpMatricule(affectationDto.getEmployeMatricule());
		var emp =  optEmp.orElseThrow(() -> new EntityNotFoundException("L'employé affecté est inconnu".toUpperCase()));

		Affectation affectat = new Affectation();
		affectat.setAffectCreeeLe(LocalDateTime.now());
		affectat.setAffectCreeePar("admin");
		affectat.setAffectModifieeLe(LocalDateTime.now());
		affectat.setAffectModifieePar("admin");
		affectat.setDateDebutAffect(affectationDto.getDateDebutAffect());
		affectat.setDateFinAffect(affectationDto.getDateDebutAffect());
		affectat.setDatePriseService(affectationDto.getDatePriseService());
		affectat.setDateStatusAffect(affectationDto.getDateStatusAffect());
		affectat.setEmplacementAffect(affectationDto.getEmplacementAffect());
		affectat.setEmploye(emp);
		affectat.setFonction(fonct);
		//affectat.setId(null);
		affectat.setInfoSupplementaires(affectationDto.getInfoSupplementaires());
		affectat.setLieuAffectation(lAffect);
		affectat.setNumNoteService(affectationDto.getNumNoteService());
		affectat.setReferenceAffect(affectationDto.getReferenceAffect());
		affectat.setCategorieAffect(affectationDto.getCategorieAffect());
		affectat.setStatusAffect(affectationDto.getStatusAffect());
		affectat.setVilleResidence(affectationDto.getVilleResidence());
		affectat.setPaysResidence(affectationDto.getPaysResidence());
		
		//Mise à jour des données de l'affecation encours
		updateCurrentAffectation(affectationDto.getEmployeMatricule(), affectationDto.getDateDebutAffect());
		
		// Persistance de données de la nouvelle affectation
		affectat = saveAffectation(affectat);
		affectat.setNumNoteService(String.valueOf(affectat.getId()));

		return affectationMapper(Optional.ofNullable(affectat));
	}

	private Affectation saveAffectation(final Affectation affectation){

		return affectationRepository.saveAndFlush(affectation);
	}

	public List<AffectationDto> getAffectationByEmployeMatricule(final String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return Collections.emptyList();
		}

		return this.getAllAffectation().stream()
				.filter(a -> empMatricule.equalsIgnoreCase(a.getEmployeMatricule()))
				.sorted((a1, a2) -> a2.getDateDebutAffect().compareTo(a1.getDateDebutAffect()))
				.toList();
	}

	public Optional<AffectationDto> getAffectByByNumNoteService(String numNoteServiceAffectation){
		if (StringUtils.isBlank(numNoteServiceAffectation) || !NumberUtils.isDigits(numNoteServiceAffectation )){
			return Optional.empty();
		}
		var optionalAffect = affectationRepository.findById(Long.parseLong(numNoteServiceAffectation));
	
		return affectationMapper(optionalAffect);
	}
	
	private Optional<AffectationDto> affectationMapper(Optional<Affectation> optAffect){
		if (!optAffect.isPresent()){
			return Optional.empty();
		}

		var aDto = affectationToDtoMapper(optAffect.get());

		return Optional.of(aDto);
	}

	private AffectationDto affectationToDtoMapper(final Affectation affectation) {
	    if (Objects.isNull(affectation)){
            throw new EntityNotFoundException("L'entité affectation ne doit être null");
		}

		var affectEmploye = employeRepository.findByEmpMatricule(affectation.getEmploye().getEmpMatricule());
		var affectLieuAffectation = lieuAffectationRepository.findByLieuAffectCode(affectation.getLieuAffectation().getLieuAffectCode()).stream().sorted().findFirst();
		var affectFonction = fonctionRepository.findByFonctionCode(affectation.getFonction().getFonctionCode()).stream().sorted().findFirst();
					
		var aDto = new AffectationDto();
		aDto.setDateDebutAffect(affectation.getDateDebutAffect());
					
		if (!affectation.getDateDebutAffect().equals(affectation.getDateFinAffect())) {
			aDto.setDateFinAffect(affectation.getDateFinAffect());			
		}
		
		aDto.setDatePriseService(affectation.getDatePriseService());
		aDto.setEmplacementAffect(affectation.getEmplacementAffect());
		aDto.setEmployeMatricule(affectEmploye.orElseThrow(EntityNotFoundException::new).getEmpMatricule());
		aDto.setEmployeCivilite(affectEmploye.orElseThrow(EntityNotFoundException::new).getEmpCivilite());
		aDto.setEmployeNom(String.join(", ", affectEmploye.orElseThrow(EntityNotFoundException::new).getEmpNom(),
				affectEmploye.orElseThrow(EntityNotFoundException::new).getEmpPren()));
		aDto.setFonction(affectFonction.orElseThrow(EntityNotFoundException::new).getFonction());
		aDto.setInfoSupplementaires(affectation.getInfoSupplementaires());
		aDto.setLieuAffectation(affectLieuAffectation.orElseThrow(EntityNotFoundException::new).getLieuAffect());
		aDto.setReferenceAffect(affectation.getReferenceAffect());
		aDto.setNumNoteService(String.valueOf(affectation.getId())); ///Formule à détermier
		aDto.setCategorieAffect(affectation.getCategorieAffect());
		aDto.setVilleResidence(affectation.getVilleResidence());
		aDto.setPaysResidence(affectation.getPaysResidence());
		aDto.setStatusAffect(affectation.getStatusAffect());
		aDto.setDateStatusAffect(affectation.getDateStatusAffect());

		return aDto;
	}
}
