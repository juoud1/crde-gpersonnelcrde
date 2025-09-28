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

import gpersonnelcrde.domain.dto.VisiteDto;
import gpersonnelcrde.domain.entities.Visite;
import gpersonnelcrde.repository.VisiteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class PlanifVisiteService {
	private VisiteRepository visiteRepository;

	public PlanifVisiteService(VisiteRepository visiteRepository) {
		this.visiteRepository = visiteRepository;
	}

	public List<VisiteDto> getAllVisites(){
		return visiteRepository.findAll().stream()
				.map(visite -> {
					var aDto = visiteToDtoMapper(visite);
					
					return aDto;
				})
				.toList();
	}

	public List<VisiteDto> getVisiteByEmployeMatricule(final String empMatricule) {
		if (StringUtils.isBlank(empMatricule)){
			return Collections.emptyList();
		}

		return this.getAllVisites().stream()
				//.filter(v -> empMatricule.equalsIgnoreCase(v.))
				.sorted((v1, v2) -> v2.getDateDebutVisite().compareTo(v1.getDateDebutVisite()))
				.toList();
	}

	public Optional<VisiteDto> getVisiteByNumVisite(String numVisite){
		if (StringUtils.isBlank(numVisite) || !NumberUtils.isDigits(numVisite )){
			return Optional.empty();
		}
		var optionalVisite = visiteRepository.findById(Long.parseLong(numVisite));
	
		return visiteMapper(optionalVisite);
	}

	public Optional<VisiteDto> visiteMapper(Optional<Visite> optVisite){
		if (!optVisite.isPresent()){
			return Optional.empty();
		}

		var vDto = visiteToDtoMapper(optVisite.get());

		return Optional.of(vDto);		
	}

	private VisiteDto visiteToDtoMapper(final Visite visite){
		if (Objects.isNull(visite)){
            throw new EntityNotFoundException("L'entité visite ne doit être null");
		}
		var vDto = new VisiteDto();
		vDto.setNumVisite(String.valueOf(visite.getId()));
		vDto.setCiviliteVisiteur(visite.getCiviliteVisiteur());
		vDto.setButVisite(visite.getButVisite());
		vDto.setDateDebutVisite(visite.getDateDebutVisite());
		vDto.setDateFinVisite(visite.getDateFinVisite());
		vDto.setDureeVisite(visite.getDureeVisite());
		vDto.setEmployeSolliteCivilite(visite.getEmploye().getEmpCivilite());
		vDto.setEmployeSolliteFonction(visite.getEmploye().getEmpFonction().getFonction());
		vDto.setEmployeSolliteMatricule(visite.getEmploye().getEmpMatricule());
		vDto.setEmployeSolliteNom(visite.getEmploye().getEmpNom() + " " + visite.getEmploye().getEmpPren());
		vDto.setFonctionVisiteur(visite.getFonctionVisiteur());
		vDto.setNomVisiteur(visite.getNomVisiteur());
		vDto.setPaysVisiteur(visite.getPaysVisiteur());
		vDto.setPrenomVisiteur(visite.getPrenomVisiteur());
		vDto.setStatusVisite(visite.getStatusVisite());
		vDto.setDateStatusVisite(visite.getDateStatusVisite());
		
		return vDto;
	}
}
