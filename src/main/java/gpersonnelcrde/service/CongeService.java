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

import gpersonnelcrde.domain.dto.CongeDto;
import gpersonnelcrde.domain.entities.Conge;
import gpersonnelcrde.exception.CongeServiceException;
import gpersonnelcrde.repository.CongeRepository;
import gpersonnelcrde.repository.EmployeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class CongeService {
	private final static Logger logger = LoggerFactory.getLogger(CongeService.class);
	private final CongeRepository congeRepository;
	private final EmployeRepository employeRepository;

	public CongeService(CongeRepository congeRepository, EmployeRepository employeRepository) {
		this.congeRepository = congeRepository;
		this.employeRepository = employeRepository;
	}

	public List<CongeDto> getAllConges() {
		return congeRepository.findAll().stream()
				.map(conge -> {
					var cDto = congeToDtoMapper(conge);

					return cDto;	
				})
				.toList();
	}

	public List<CongeDto> getCongeByEmployeMatricule(final String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return Collections.emptyList();
		}

		return this.getAllConges().stream()
				.filter(c -> empMatricule.equalsIgnoreCase(c.getEmployeMatricule()))
				.sorted((c1, c2) -> c2.getDateFinConge().compareTo(c1.getDateDebutConge()))
				.toList();
	}

	public Optional<CongeDto> getCongeByNumNoteService(String numNoteServiceConge){
		if (StringUtils.isBlank(numNoteServiceConge) || !NumberUtils.isDigits(numNoteServiceConge )){
			return Optional.empty();
		}
		var optionalAffect = congeRepository.findById(Long.parseLong(numNoteServiceConge));
	
		return congeMapper(optionalAffect);
	}

	public CongeDto saveCongeEmploye(String matriculeEmpConge, LocalDate dateDebConge, LocalDate dateFinConge, String infoSupplConge,
										String dateDepartAutorisatSortie, String dateRetourAutorisatSortie, String villeAutorisatSortie, String paysAutorisatSortie){
		var congeDto = getMissionDtoFromWebParm(matriculeEmpConge, dateDebConge, dateFinConge, infoSupplConge, dateDepartAutorisatSortie, dateRetourAutorisatSortie, villeAutorisatSortie, paysAutorisatSortie);
		return saveCongeEmploye(congeDto);			
	}

	public CongeDto saveCongeEmploye(final CongeDto congeDtoToSave){

		return congeDtoToSave;
	}

	private CongeDto getMissionDtoFromWebParm(String matriculeEmpConge, LocalDate dateDebConge, LocalDate dateFinConge, String infoSupplConge,
												String dateDepartAutorisatSortie, String dateRetourAutorisatSortie, String villeAutorisatSortie, String paysAutorisatSortie){
		return new CongeDto();
	}

	private Optional<CongeDto> congeMapper(Optional<Conge> optConge){
		if (!optConge.isPresent()){
			return Optional.empty();
		}
		
		var cDto = congeToDtoMapper(optConge.get());
		
		return Optional.of(cDto);
	}

	private CongeDto congeToDtoMapper(final Conge conge){
	    if (Objects.isNull(conge)){
            throw new EntityNotFoundException("L'entité congé ne doit être null");
		}

	    var congeEmploye = employeRepository.findByEmpMatricule(conge.getEmploye().getEmpMatricule());
		var cDto = new CongeDto();
		cDto.setDateDebutConge(conge.getDateDebutConge());
		cDto.setDateFinConge(conge.getDateDebutConge());
		cDto.setEmployeMatricule(congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpMatricule());
		cDto.setEmployeCivilite(congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpCivilite());
		cDto.setEmployeNom(String.join(", ", congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpNom(),
				congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpPren()));
		cDto.setEmployeFonction(congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpFonction().getFonction());
		cDto.setInfoSupplementaires(conge.getInfoSupplementaires());
		cDto.setNumNoteServiceConge(String.valueOf(conge.getId()));

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
	public void updateCongesEffectuees(LocalDate dateDebTrtmt, LocalDate dateFinTrtmt) throws CongeServiceException {
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
