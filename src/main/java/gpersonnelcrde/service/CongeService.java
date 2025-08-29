package gpersonnelcrde.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.CongeDto;
import gpersonnelcrde.domain.entities.Conge;
import gpersonnelcrde.repository.CongeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class CongeService {
	private final CongeRepository congeRepository;
	private final EmployeService employeService;

	public CongeService(CongeRepository congeRepository, EmployeService employeService) {
		this.congeRepository = congeRepository;
		this.employeService = employeService;
	}

	public List<CongeDto> getAllConge() {
		return congeRepository.findAll().stream()
				.map(conge -> {
					var congeEmploye = employeService.getEmployeByMatricule(conge.getEmploye().getEmpMatricule());
					var cDto = new CongeDto();
					cDto.setDateDebutConge(conge.getDateDebutConge());
					cDto.setDateFinConge(conge.getDateDebutConge());
					cDto.setEmploye(congeEmploye.orElseThrow(EntityNotFoundException::new));
					cDto.setInfoSupplementaires(conge.getInfoSupplementaires());
					cDto.setNumNoteServiceConge(String.valueOf(conge.getId()));

					return cDto;	
				})
				.toList();
	}

	public List<CongeDto> getCongeByEmployeMatricule(final String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return Collections.emptyList();
		}

		return this.getAllConge().stream()
				.filter(c -> empMatricule.equalsIgnoreCase(c.getEmploye().getEmpMatricule()))
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

	private Optional<CongeDto> congeMapper(Optional<Conge> optConge){
		if (!optConge.isPresent()){
			return Optional.empty();
		}
		var conge = optConge.get();
		var cDto = new CongeDto();
		var congeEmp = employeService.getEmployeByMatricule(conge.getEmploye().getEmpMatricule());

		cDto.setDateDebutConge(conge.getDateDebutConge());
		cDto.setDateFinConge(conge.getDateFinConge());
		cDto.setEmploye(congeEmp.orElseThrow(EntityNotFoundException::new));
		cDto.setInfoSupplementaires(conge.getInfoSupplementaires());
		cDto.setNumNoteServiceConge(String.valueOf(conge.getId()));

		return Optional.of(cDto);
	}
}
