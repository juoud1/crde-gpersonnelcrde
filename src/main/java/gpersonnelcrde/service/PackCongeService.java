package gpersonnelcrde.service;

import java.time.LocalDate;
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
import gpersonnelcrde.domain.dto.CongeDto;
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
										final String villeAutorisatSortie, final String paysAutorisatSortie, final String motifSortie){
		
		var congeDto = getPackCongeDtoFromWebParm(matriculeEmpConge, typeDemandeConge, dateDebConge, dateFinConge, 
										infoSupplConge, numNoteServiceConge, numAutorisatSortie,
										dateDepartAutorisatSortie, dateRetourAutorisatSortie, 
										villeAutorisatSortie, paysAutorisatSortie, motifSortie);
	
		return savePackCongeEmploye(congeDto);			
	}

	public PackCongeDto savePackCongeEmploye(final PackCongeDto congeDtoToSave){

		return congeDtoToSave;
	}

	private PackCongeDto getPackCongeDtoFromWebParm(final String matriculeEmpConge, final String typeDemandeConge,
												final LocalDate dateDebConge, final LocalDate dateFinConge, 
												final String infoSupplConge, final String numNoteServiceConge, final String numAutorisatSortie,
												final LocalDate dateDepartAutorisatSortie, final LocalDate dateRetourAutorisatSortie, 
												final String villeAutorisatSortie, final String paysAutorisatSortie, final String motifSortie){
		
		
		var congeEmploye = employeRepository.findByEmpMatricule(matriculeEmpConge)
								.orElseThrow(() -> new EntityNotFoundException(matriculeEmpConge + " est un matricule employé inexistant."));

		var packCongeDto = new PackCongeDto();
		packCongeDto.setDateDebutConge(dateDebConge);
		packCongeDto.setDateDepartAutorisatSortie(dateDepartAutorisatSortie);
		packCongeDto.setDateFinConge(dateFinConge);
		packCongeDto.setDateRetourAutorisatSortie(dateRetourAutorisatSortie);
		packCongeDto.setDateStatusConge(LocalDate.now());
		packCongeDto.setDateStatusAutorisatSortie(LocalDate.now());
		packCongeDto.setEmployeCivilite(congeEmploye.getEmpCivilite());
		packCongeDto.setEmployeFonction(congeEmploye.getEmpFonction().getFonctionCode());
		packCongeDto.setEmployeMatricule(matriculeEmpConge);
		packCongeDto.setEmployeNom(String.join(", ", congeEmploye.getEmpNom(), congeEmploye.getEmpPren()));
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
