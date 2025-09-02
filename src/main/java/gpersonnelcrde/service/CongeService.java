package gpersonnelcrde.service;

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

	public List<CongeDto> getAllConge() {
		return congeRepository.findAll().stream()
				.map(conge -> {
					/*var congeEmploye = employeRepository.findByEmpMatricule(conge.getEmploye().getEmpMatricule());
					var cDto = new CongeDto();
					cDto.setDateDebutConge(conge.getDateDebutConge());
					cDto.setDateFinConge(conge.getDateDebutConge());
					cDto.setEmployeMatricule(congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpMatricule());
					cDto.setEmployeCivilite(congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpCivilite());
					cDto.setEmployeNom(String.join(", ", congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpNom(),
							congeEmploye.orElseThrow(EntityNotFoundException::new).getEmpPren()));
					cDto.setInfoSupplementaires(conge.getInfoSupplementaires());
					cDto.setNumNoteServiceConge(String.valueOf(conge.getId()));*/

					var cDto = congeToDtoMapper(conge);

					return cDto;	
				})
				.toList();
	}

	public List<CongeDto> getCongeByEmployeMatricule(final String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return Collections.emptyList();
		}

		return this.getAllConge().stream()
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

	private Optional<CongeDto> congeMapper(Optional<Conge> optConge){
		if (!optConge.isPresent()){
			return Optional.empty();
		}
		
		var cDto = congeToDtoMapper(optConge.get());
		/*var cDto = new CongeDto();
		var congeEmp = employeService.getEmployeByMatricule(conge.getEmploye().getEmpMatricule());

		cDto.setDateDebutConge(conge.getDateDebutConge());
		cDto.setDateFinConge(conge.getDateFinConge());
		cDto.setEmploye(congeEmp.orElseThrow(EntityNotFoundException::new));
		cDto.setInfoSupplementaires(conge.getInfoSupplementaires());
		cDto.setNumNoteServiceConge(String.valueOf(conge.getId()));*/

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
		cDto.setInfoSupplementaires(conge.getInfoSupplementaires());
		cDto.setNumNoteServiceConge(String.valueOf(conge.getId()));

		return cDto;
	}
}
