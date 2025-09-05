package gpersonnelcrde.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.CongeDto;
import gpersonnelcrde.domain.entities.Conge;
import gpersonnelcrde.repository.CongeRepository;
import gpersonnelcrde.repository.EmployeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class CongeService {
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
}
