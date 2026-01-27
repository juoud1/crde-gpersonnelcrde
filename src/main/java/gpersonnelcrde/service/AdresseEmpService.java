package gpersonnelcrde.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.AdresseEmpDto;
import gpersonnelcrde.domain.entities.AdresseEmploye;
import gpersonnelcrde.repository.AdresseEmployeRepository;
import gpersonnelcrde.repository.EmployeRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class AdresseEmpService {
	private static final  Logger logger = LoggerFactory.getLogger(AdresseEmpService.class);

	private final AdresseEmployeRepository adresseEmployeRepository;
	private final EmployeRepository employeRepository;

	public AdresseEmpService(AdresseEmployeRepository adresseEmployeRepository, EmployeRepository employeRepository){
		this.adresseEmployeRepository = adresseEmployeRepository;
		this.employeRepository = employeRepository;

		logger.info("composant adresse-employé service initialisé avec succès".toUpperCase());
	}

	@Transactional
	public List<AdresseEmpDto> getAllAdressesEmp(){
		var adresses = adresseEmployeRepository.findAll();
		logger.info("{} adresse(s) récupérées avec succès!".toUpperCase(), adresses.size());

		return adresses != null && !CollectionUtils.isEmpty(adresses) ? adresses.stream()
				.distinct()
				.map(adr -> {
					AdresseEmpDto aDto = adrempToDtoMapper(adr);
					return aDto;
				})
				.toList()
				: Collections.emptyList();
	}

	@Transactional
	public List<AdresseEmpDto> getAdressesEmpByMatricule(final String matriculeEmp){
		if (matriculeEmp.isBlank()){
			logger.warn("La récupération de l'employé dont le matricule est null a échoué.");
			throw new RuntimeException("Impossible de récupérer les adresses d'employé dont le matricule est null");
		}

		return this.getAllAdressesEmp().stream()
					.distinct()
					.filter(a -> a.getEmpMatricule().equalsIgnoreCase(matriculeEmp))
					.toList();
	}

	public Optional<AdresseEmpDto> saveAdresseEmp(final String empAdrQtierResidce, final String empAdrVilleResidce, 
					final String empAdrPrefResidce, final String empAdrRegResidce, final boolean estActive){

		var aDto = new AdresseEmpDto();

		return saveAdresseEmpUsingDto(aDto);
	}

	public Optional<AdresseEmpDto> saveAdresseEmpUsingDto(final AdresseEmpDto aDto){

		return Optional.ofNullable(null);
	}

	private boolean checkExistanceAdresseEmp (final AdresseEmpDto aDto) {
		if (Objects.isNull(aDto)){
			throw new IllegalArgumentException("Impossible de vérifier l'existance de l'adresse car les données sont vides ou null");
		}

		return Boolean.FALSE;
	}

	private AdresseEmpDto adrempToDtoMapper(final AdresseEmploye adresseEmploye){
		if (Objects.isNull(adresseEmploye)){
			logger.warn("Impossible de faire le mappage car aucune donnée de l'adresse-employé n'est fournie");
            throw new EntityNotFoundException("L'entité adresse-employé ne doit être null");
		}

		//var employe = employeRepository.findByEmpMatricule(adresseEmploye.get)
		var aDto = new AdresseEmpDto();
		aDto.setAdrId(String.valueOf(adresseEmploye.getId()));
		aDto.setAdrPrefResidceEmp(adresseEmploye.getPrefectureResidence());
		aDto.setAdrQuartierResidceEmp(adresseEmploye.getQuartierResidence());
		aDto.setAdrRegionResidceEmp(adresseEmploye.getRegionResidence());
		aDto.setEmpMatricule(adresseEmploye.getEmploye().getEmpMatricule());
		aDto.setAdrVilleResidceEmp(adresseEmploye.getVilleResidence());
		aDto.setEstAdrResidceEmpActive(adresseEmploye.isEstAdresseActive());

		return aDto;
	}
}
