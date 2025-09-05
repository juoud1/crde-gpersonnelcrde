package gpersonnelcrde.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
		var affectLieuAffectation = lieuAffectationRepository.findByLieuAffectCode(affectation.getLieuAffectation().getLieuAffectCode());
		var affectFonction = fonctionRepository.findByFonctionCode(affectation.getFonction().getFonctionCode());
					
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

		return aDto;
	}
}
