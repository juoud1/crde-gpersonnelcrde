package gpersonnelcrde.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.AffectationDto;
import gpersonnelcrde.domain.entities.Affectation;
import gpersonnelcrde.repository.AffectationRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class AffectationService {
	private final AffectationRepository affectationRepository;
	private final EmployeService employeService;
	private final LieuAffectationService lieuAffectationService;
	private final FonctionService fonctionService;

	public AffectationService(AffectationRepository affectationRepository, EmployeService employeService,
			LieuAffectationService lieuAffectationService, FonctionService fonctionService) {
		this.affectationRepository = affectationRepository;
		this.employeService = employeService;
		this.lieuAffectationService = lieuAffectationService;
		this.fonctionService = fonctionService;
	}

	public List<AffectationDto> getAllAffectation(){
		return affectationRepository.findAll().stream()
				.map(affect -> {
					var affectEmploye = employeService.getEmployeByMatricule(affect.getEmploye().getEmpMatricule());
					var affectLieuAffectation = lieuAffectationService.getLieuAffectByCode(affect.getLieuAffectation().getLieuAffectCode());
					var affectFonction = fonctionService.getFonctionByCode(affect.getFonction().getFonctionCode());
					var aDto = new AffectationDto();
					aDto.setDateDebutAffect(affect.getDateDebutAffect());
					
					if (!affect.getDateDebutAffect().equals(affect.getDateFinAffect())) {
						aDto.setDateFinAffect(affect.getDateFinAffect());			
					}
		
					aDto.setDatePriseService(affect.getDatePriseService());
					aDto.setEmplacementAffect(affect.getEmplacementAffect());
					aDto.setEmploye(affectEmploye.orElseThrow(EntityNotFoundException::new));
					aDto.setFonction(affectFonction.orElseThrow(EntityNotFoundException::new));
					aDto.setInfoSupplementaires(affect.getInfoSupplementaires());
					aDto.setLieuAffectation(affectLieuAffectation.orElseThrow(EntityNotFoundException::new));
					aDto.setReferenceAffect(affect.getReferenceAffect());
					aDto.setNumNoteService(String.valueOf(affect.getId())); ///Formule à détermier

					return aDto;
				})
				.toList();
	}

	public List<AffectationDto> getAffectationByEmployeMatricule(final String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return Collections.emptyList();
		}

		return this.getAllAffectation().stream()
				.filter(a -> empMatricule.equalsIgnoreCase(a.getEmploye().getEmpMatricule()))
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
		var affect = optAffect.get();
		var aDto = new AffectationDto();
		var affectEmp = employeService.getEmployeByMatricule(affect.getEmploye().getEmpMatricule());
		var affectLieuAffect = lieuAffectationService.getLieuAffectByCode(affect.getLieuAffectation().getLieuAffectCode());
		var affectFonction = fonctionService.getFonctionByCode(affect.getFonction().getFonctionCode());

		aDto.setDateDebutAffect(affect.getDateDebutAffect());
		aDto.setDateFinAffect(affect.getDateFinAffect());
		aDto.setDatePriseService(affect.getDatePriseService());
		aDto.setEmplacementAffect(affect.getEmplacementAffect());
		aDto.setEmploye(affectEmp.orElseThrow(EntityNotFoundException::new));
		aDto.setFonction(affectFonction.orElseThrow(EntityNotFoundException::new));
		aDto.setInfoSupplementaires(affect.getInfoSupplementaires());
		aDto.setLieuAffectation(affectLieuAffect.orElseThrow(EntityNotFoundException::new));
		aDto.setReferenceAffect(affect.getReferenceAffect());

		return Optional.of(aDto);
	}
}
