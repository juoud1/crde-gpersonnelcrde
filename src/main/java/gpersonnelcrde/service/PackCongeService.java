package gpersonnelcrde.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import gpersonnelcrde.domain.dto.AutorisationSortieDto;
import gpersonnelcrde.domain.dto.PackCongeDto;
import gpersonnelcrde.domain.entities.AutorisationSortie;
import gpersonnelcrde.domain.entities.Conge;
import gpersonnelcrde.exception.CongeServiceException;
import gpersonnelcrde.repository.AutorisationSortieRepository;
import gpersonnelcrde.repository.CongeRepository;
import gpersonnelcrde.repository.EmployeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class PackCongeService {
	private final static Logger logger = LoggerFactory.getLogger(PackCongeService.class);
	private final CongeRepository congeRepository;
	private final EmployeRepository employeRepository;
	private final AutorisationSortieRepository autorisationSortieRepository;

	public PackCongeService(CongeRepository congeRepository, EmployeRepository employeRepository, AutorisationSortieRepository autorisationSortieRepository) {
		this.congeRepository = congeRepository;
		this.employeRepository = employeRepository;
		this.autorisationSortieRepository = autorisationSortieRepository;
		logger.info("composant congé service initialisé avec succès".toUpperCase());
	}

	public List<PackCongeDto> getAllPackConges() {
		return congeRepository.findAll().stream()
				.map(conge -> {
					var cDto = congeToDtoMapper(conge);

					return cDto;	
				})
				.toList();
	}

	public List<PackCongeDto> getPackCongeByEmployeMatricule(final String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return Collections.emptyList();
		}

		return this.getAllPackConges().stream()
				.filter(c -> empMatricule.equalsIgnoreCase(c.getEmployeMatricule()))
				.sorted((c1, c2) -> c2.getDateFinConge().compareTo(c1.getDateDebutConge()))
				.toList();
	}

	public Optional<PackCongeDto> getPackCongeByNumNoteService(final String numConge){
		if (StringUtils.isBlank(numConge) || !NumberUtils.isDigits(numConge )){
			return Optional.empty();
		}
		var optionalConge = congeRepository.findById(Long.parseLong(numConge));
	
		return packCongeMapper(optionalConge);
	}

	public PackCongeDto savePackCongeEmploye(final String matriculeEmpConge, final String typeDemandeConge, 
										final LocalDate dateDebConge, final LocalDate dateFinConge, 
										final String infoSupplConge, final String numNoteServiceConge, final String numAutorisatSortie,
										final LocalDate dateDepartAutorisatSortie, final LocalDate dateRetourAutorisatSortie, 
										final String villeAutorisatSortie, final String paysAutorisatSortie, final String motifSortie) throws IllegalAccessException{
		
		var congeDto = getPackCongeDtoFromWebParm(matriculeEmpConge, typeDemandeConge, dateDebConge, dateFinConge, 
										infoSupplConge, numNoteServiceConge, numAutorisatSortie,
										dateDepartAutorisatSortie, dateRetourAutorisatSortie, 
										villeAutorisatSortie, paysAutorisatSortie, motifSortie);
	
		return savePackCongeEmploye(congeDto);			
	}
	
	private boolean checkExistanceCongeBeforSaving(final Conge congeToSave) {
		var result = congeRepository.findByDateDebutCongeAndDateFinCongeAndEmploye(congeToSave.getDateDebutConge(),
							congeToSave.getDateFinConge(), congeToSave.getEmploye());
		logger.info("Résultat de vérification de congé à persister: {}", result.isPresent());

		return result.isPresent();
	}

	private Conge saveConge(Conge congeToSave) {
		var result = congeRepository.saveAndFlush(congeToSave);
		logger.info("Congé enregistré avec succès sous le numéro; {}", result.getId());

		return result;
	}

	@Transactional
	public PackCongeDto savePackCongeEmploye(PackCongeDto packCongeDtoToSave) throws IllegalAccessException{
		if (Objects.isNull(packCongeDtoToSave)) {
			logger.warn("Impossible de persister l'objet car le Pack congé est null.");
			throw new IllegalArgumentException("L'objet Pack congé et/ou autorisation de sortie doit être non null.");
		}

		var conge = getCongeFromPack(packCongeDtoToSave);
		Conge savedConge  = null;
		//Check
		if (checkExistanceCongeBeforSaving(conge)){
			logger.info("Impossible de créer le congé car il existe déjà dans la base de données.");
			throw new IllegalAccessException("Ce congé existe déjà dans la base de données.");
		}
			//save
		savedConge = saveConge(conge);
		logger.debug("Un congé de l'Employé {} est crée sous le numéro {}".toUpperCase(), savedConge.getEmploye(), savedConge.getId());

		AutorisationSortie as, savedAs = null;
		if (!packCongeDtoToSave.getTypeDemandeConge().equalsIgnoreCase("Congé")) {
			as = getAutSortieFromPack(packCongeDtoToSave, savedConge);
				//Check
			if (checkExistanceAutSortieBeforSaving(as)){
				///// IL FAUT ANNULER LA CREATION DE CONGÉ
				//packCongeDtoToSave = updatePackCongeDtoAfterSaving(packCongeDtoToSave, savedConge, null);
				logger.info("Impossible de créer l'autorisation de sortie car elle existe déjà dans la base de données.");	
				throw new IllegalAccessException("Cette autorisation de sortie existe déjà dans la base de données.");
			}
					//save
			savedAs = saveAutSortie(as);
			logger.debug("Un autorisation de sortie relative au congé n° {} de l'Employé {} est créée sous le numéro {}".toUpperCase(), savedAs.getConge().getId(), savedAs.getConge().getEmploye().getEmpMatricule(), savedAs.getId());
				//} else {
				//	logger.info("Impossible de créer l'autorisation de sortie car elle existe déjà dans la base de données.");	
					//throw new IllegalAccessException("Cette autorisation de sortie existe déjà dans la base de données.");
				//}
				
				//packCongeDtoToSave = updatePackCongeDtoAfterSaving(packCongeDtoToSave, savedConge, savedAs);
		}
			
		packCongeDtoToSave = updatePackCongeDtoAfterSaving(packCongeDtoToSave, savedConge, savedAs);
		
		//} else {
		//	logger.info("Impossible de créer le congé car il existe déjà dans la base de données.");
		//	throw new IllegalAccessException("Ce congé existe déjà dans la base de données.");
		//}

		return packCongeDtoToSave;
	}

	private PackCongeDto updatePackCongeDtoAfterSaving(PackCongeDto packCongeDto, final Conge savedConge, final AutorisationSortie savedAs){
		packCongeDto.setNumConge(String.valueOf(savedConge.getId()));
		packCongeDto.setEmployeCivilite(savedConge.getEmploye().getEmpCivilite());
		packCongeDto.setEmployeFonction(savedConge.getEmploye().getEmpFonction().getFonctionCode());
		packCongeDto.setEmployeNom(String.join(" ", savedConge.getEmploye().getEmpNom(), savedConge.getEmploye().getEmpPren()));

		if (Objects.nonNull(savedAs)){
			//packCongeDto.setNumConge(String.valueOf(savedConge.getId()));
			packCongeDto.setNumAutSortie(String.valueOf(savedAs.getId()));
			//packCongeDto.setEmployeCivilite(savedConge.getEmploye().getEmpCivilite());
			//packCongeDto.setEmployeFonction(savedConge.getEmploye().getEmpFonction().getFonctionCode());
			//packCongeDto.setEmployeNom(String.join(", ", savedConge.getEmploye().getEmpNom(), savedConge.getEmploye().getEmpPren()));	
		}
		logger.info("Mise à jour du Pack de congé et autorisation de sortie".toUpperCase());

		return packCongeDto;
	}

	private AutorisationSortie saveAutSortie(AutorisationSortie asToSave){
		var result = autorisationSortieRepository.saveAndFlush(asToSave);
		logger.info("Autorisation de sortie enregistrée avec succès sous le numéro; {}", result.getId());

		return result;
	}

	private boolean checkExistanceAutSortieBeforSaving(final AutorisationSortie asToSave) {
		var result = autorisationSortieRepository.findByAsDateDepartAndAsDateRetourAndConge(asToSave.getAsDateDepart(), asToSave.getAsDateRetour(), asToSave.getConge());
		logger.info("Résultat de vérification de l'autorisation de sortie à persister: {}", result.isPresent());

		return result.isPresent();
	}

	private Conge getCongeFromPack(final PackCongeDto packCongeDtoToSave){
		
		var congeEmploye = employeRepository.findByEmpMatricule(packCongeDtoToSave.getEmployeMatricule())
								.orElseThrow(() -> new EntityNotFoundException(packCongeDtoToSave.getEmployeMatricule() + " est un matricule employé inexistant."));

		var conge = new Conge();
		conge.setCongeCreeLe(LocalDateTime.now());
		conge.setCongeCreePar("admin");
		conge.setCongeModifieLe(LocalDateTime.now());
		conge.setCongeModifiePar("admin");
		conge.setDateDebutConge(packCongeDtoToSave.getDateDebutConge());
		conge.setDateFinConge(packCongeDtoToSave.getDateFinConge());
		conge.setDateStatusConge(packCongeDtoToSave.getDateStatusConge());
		conge.setEmploye(congeEmploye);
		conge.setInfoSupplementaires(packCongeDtoToSave.getInfoSupplementaires());
		conge.setNumNoteServiceConge(packCongeDtoToSave.getNumNoteServiceConge());
		conge.setStatusConge(packCongeDtoToSave.getStatusConge());
		conge.setTypeDemandeConge(packCongeDtoToSave.getTypeDemandeConge());

		return conge;
	}

	private AutorisationSortie getAutSortieFromPack(final PackCongeDto packCongeDtoToSave, final Conge savedConge){
		var autorisationSortie = new AutorisationSortie();
		autorisationSortie.setAsCreeLe(savedConge.getCongeCreeLe());
		autorisationSortie.setAsCreePar(savedConge.getCongeCreePar());
		autorisationSortie.setAsDateDepart(packCongeDtoToSave.getDateDepartAutorisatSortie());
		autorisationSortie.setAsDateRetour(packCongeDtoToSave.getDateRetourAutorisatSortie());
		autorisationSortie.setAsModifieLe(savedConge.getCongeModifieLe());
		autorisationSortie.setAsModifiePar(savedConge.getCongeModifiePar());
		autorisationSortie.setMotifSortie(packCongeDtoToSave.getMotifSortie());
		autorisationSortie.setAsNum(packCongeDtoToSave.getNumAutorisatSortie());
		autorisationSortie.setAsPays(packCongeDtoToSave.getPaysAutorisatSortie());
		autorisationSortie.setAsVille(packCongeDtoToSave.getVilleAutorisatSortie());
		autorisationSortie.setStatusAs(savedConge.getStatusConge());
		autorisationSortie.setDateStatusAs(savedConge.getDateStatusConge());
		autorisationSortie.setConge(savedConge);

		return autorisationSortie;
	}

	private PackCongeDto getPackCongeDtoFromWebParm(final String matriculeEmpConge, final String typeDemandeConge,
												final LocalDate dateDebConge, final LocalDate dateFinConge, 
												final String infoSupplConge, final String numNoteServiceConge, final String numAutorisatSortie,
												final LocalDate dateDepartAutorisatSortie, final LocalDate dateRetourAutorisatSortie, 
												final String villeAutorisatSortie, final String paysAutorisatSortie, final String motifSortie){
		
		
		//var congeEmploye = employeRepository.findByEmpMatricule(matriculeEmpConge)
		//						.orElseThrow(() -> new EntityNotFoundException(matriculeEmpConge + " est un matricule employé inexistant."));

		var packCongeDto = new PackCongeDto();
		packCongeDto.setDateDebutConge(dateDebConge);
		packCongeDto.setDateDepartAutorisatSortie(dateDepartAutorisatSortie);
		packCongeDto.setDateFinConge(dateFinConge);
		packCongeDto.setDateRetourAutorisatSortie(dateRetourAutorisatSortie);
		packCongeDto.setDateStatusConge(LocalDate.now());
		packCongeDto.setDateStatusAutorisatSortie(LocalDate.now());
		//packCongeDto.setEmployeCivilite(congeEmploye.getEmpCivilite());
		//packCongeDto.setEmployeFonction(congeEmploye.getEmpFonction().getFonctionCode());
		packCongeDto.setEmployeMatricule(matriculeEmpConge);
		//packCongeDto.setEmployeNom(String.join(", ", congeEmploye.getEmpNom(), congeEmploye.getEmpPren()));
		packCongeDto.setInfoSupplementaires(infoSupplConge);
		packCongeDto.setNumAutorisatSortie(numAutorisatSortie);
		packCongeDto.setNumNoteServiceConge(numNoteServiceConge);
		packCongeDto.setMotifSortie(motifSortie);
		packCongeDto.setNumAutSortie(null);
		packCongeDto.setNumConge(null);
		packCongeDto.setPaysAutorisatSortie(paysAutorisatSortie);
		packCongeDto.setVilleAutorisatSortie(villeAutorisatSortie);
		packCongeDto.setStatusConge("En attente");
		packCongeDto.setStatusAutorisatSortie("En attente");
		packCongeDto.setTypeDemandeConge(typeDemandeConge);
		
		return packCongeDto;
	}

	private PackCongeDto getPackCongeDtoFromWebParm(String matriculeEmpConge, String typeDemandeConge, LocalDate dateDebConge, LocalDate dateFinConge, String infoSupplConge,
												LocalDate dateDepartAutorisatSortie, LocalDate dateRetourAutorisatSortie, 
												String villeAutorisatSortie, String paysAutorisatSortie){
		
		
		return new PackCongeDto();
	}

	private AutorisationSortieDto getAutorisatFromWebParm(String matriculeEmpConge, LocalDate dateDebConge, LocalDate dateFinConge, String infoSupplConge,
												LocalDate dateDepartAutorisatSortie, LocalDate dateRetourAutorisatSortie, 
												String villeAutorisatSortie, String paysAutorisatSortie){
		
		
		return new AutorisationSortieDto();
	}

	private Optional<PackCongeDto> packCongeMapper(Optional<Conge> optConge){
		if (!optConge.isPresent()){
			return Optional.empty();
		}
		
		var cDto = congeToDtoMapper(optConge.get());
		
		return Optional.of(cDto);
	}

	private PackCongeDto congeToDtoMapper(final Conge conge){
	    if (Objects.isNull(conge)){
            throw new EntityNotFoundException("L'entité congé ne doit être null");
		}

		AutorisationSortie asEmploye = autorisationSortieRepository.findByConge(conge).stream()
							.filter(as -> as.getStatusAs().equalsIgnoreCase(conge.getStatusConge()))
							.findFirst()
							.orElseGet(AutorisationSortie::new);					
		logger.info("Autorisation de sortie n° {} enregistré sous le n° {}", asEmploye.getAsNum(), asEmploye.getId());

		var congeEmploye = employeRepository.findByEmpMatricule(conge.getEmploye().getEmpMatricule());
		//logger.info("Congé de l'employé {}", congeEmploye.toString());

		var cDto = new PackCongeDto();
		cDto.setDateDebutConge(conge.getDateDebutConge());
		cDto.setDateFinConge(conge.getDateDebutConge());
		cDto.setDateStatusConge(conge.getDateStatusConge());
		cDto.setEmployeMatricule(congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpMatricule());
		cDto.setEmployeCivilite(congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpCivilite());
		cDto.setEmployeNom(String.join(", ", congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpNom(),
				congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpPren()));
		cDto.setEmployeFonction(congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpFonction().getFonction());
		cDto.setInfoSupplementaires(conge.getInfoSupplementaires());
		cDto.setNumNoteServiceConge(String.valueOf(conge.getNumNoteServiceConge()));
		cDto.setNumConge(String.valueOf(conge.getId()));
		cDto.setStatusConge(conge.getStatusConge());
		cDto.setTypeDemandeConge(conge.getTypeDemandeConge());

		cDto.setDateDepartAutorisatSortie(asEmploye.getAsDateDepart());
		cDto.setDateRetourAutorisatSortie(asEmploye.getAsDateRetour());
		cDto.setDateStatusAutorisatSortie(asEmploye.getDateStatusAs());
		cDto.setMotifSortie(asEmploye.getMotifSortie());
		cDto.setNumAutSortie(String.valueOf(asEmploye.getId()));
		cDto.setNumAutorisatSortie(asEmploye.getAsNum());
		cDto.setPaysAutorisatSortie(asEmploye.getAsPays());
		cDto.setStatusAutorisatSortie(asEmploye.getStatusAs());
		cDto.setVilleAutorisatSortie(asEmploye.getAsVille());
		logger.info("{} n° {} enregistré sous le n° {}", cDto.getTypeDemandeConge(), cDto.getNumAutorisatSortie(), cDto.getNumAutSortie());

		return cDto;
	}

	/**
	 * Traitement de maj des congés qui ont été déjà effectués/complétés
	 * 	Du coup les employés concernés dans ces congés auront automatiquement le status En Service
	 *  donc disponible
	 *
	 * @param dateDebTrtmt
	 * @param dateFinTrtmt
	 * @throws CongeServiceException
	 */
	public void updatePackCongesEffectuees(LocalDate dateDebTrtmt, LocalDate dateFinTrtmt) throws CongeServiceException {
		if (Objects.isNull(dateDebTrtmt) && Objects.isNull(dateFinTrtmt)){
			logger.warn("Impossible d'effectuer le traitement de reprise du travail par les employés suite aux missions effectuées car les dates sont vides ou null");
			throw new CongeServiceException("Les dates de traitements sont obligatoires pour permettre la mise à jour du status des employés et des missions déjà effectuées.");
		}

		if ( Objects.nonNull(dateDebTrtmt) && Objects.nonNull(dateFinTrtmt) && dateDebTrtmt.isAfter(dateFinTrtmt)){
			logger.warn("Impossible d'effectuer le traitement de reprise du travail par les employés suite aux missions effectuées car la date de début {} est plus grande que celle de fin {} de traitement", dateDebTrtmt, dateFinTrtmt);
			throw new CongeServiceException("Les dates de traitements sont incohérentes et ne permettent pas la mise à jour du status des employés et des missions déjà effectuées.");
		}

		var congesEffectues = this.congeRepository.findAll().parallelStream()
				.filter(c -> c.getStatusConge().equalsIgnoreCase("Approuvé") && c.getDateFinConge().isBefore(dateDebTrtmt))
				.map(cApp -> {
						cApp.setStatusConge("Effectué");
						cApp.setDateStatusConge(dateDebTrtmt);
						return cApp;
					})
				.toList();
		logger.info("{} congé(s) approuvé(s) qui sont déjà effectué(s).", congesEffectues.size());
		
		var resultMaj = this.congeRepository.saveAllAndFlush(congesEffectues);
		logger.info("{} mise à jour(s) de congés(s) effectué(s) dans la base de données avec succès.", resultMaj.size());
	}
}