package gpersonnelcrde.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.entities.Affectation;
import gpersonnelcrde.domain.entities.Conge;
import gpersonnelcrde.domain.entities.Employe;
import gpersonnelcrde.domain.entities.Mission;
import gpersonnelcrde.repository.AffectationRepository;
import gpersonnelcrde.repository.CongeRepository;
import gpersonnelcrde.repository.EmployeRepository;
import gpersonnelcrde.repository.MissionRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class EmployeService {
	private final EmployeRepository employeRepository;
	private final AffectationRepository affectationRepository;
	private final MissionRepository missionRepository;
	private final CongeRepository congeRepository;
	private final FonctionService fonctionService;
	private final TypeEmployeService typeEmployeService;
	private final StatusService statusService;
	private final LieuAffectationService lieuAffectationService;

	public EmployeService(EmployeRepository employeRepository, FonctionService fonctionService,
		TypeEmployeService typeEmployeService, StatusService statusService, LieuAffectationService lieuAffectationService,
		AffectationRepository affectationRepository, MissionRepository missionRepository, CongeRepository congeRepository) {
		this.employeRepository = employeRepository;
		this.fonctionService = fonctionService;
		this.typeEmployeService = typeEmployeService;
		this.statusService = statusService;
		this.lieuAffectationService = lieuAffectationService;
		this.affectationRepository = affectationRepository;
		this.missionRepository =missionRepository;
		this.congeRepository = congeRepository;
	}

	public List<EmployeDto> getAllEmploye(){

		return employeRepository.findAll().stream()
				.map((Employe emp) -> {
					/*var empFonct = fonctionService.getFonctionByCode(emp.getEmpFonction().getFonctionCode());
					var empStatus = statusService.getStatusByCode(emp.getEmpStatus().getStatusCode());
					var empTypeEmp = typeEmployeService.getTypeEmpByCode(emp.getTypeEmploye().getTypeEmpCode());
					var empLieuAffect = lieuAffectationService.getLieuAffectByCode(emp.getEmpLieuAffectation().getLieuAffectCode());
					var eDto = new EmployeDto();
					eDto.setEmpCivilite(emp.getEmpCivilite());
					eDto.setEmpMatricule(emp.getEmpMatricule());
					eDto.setEmpNom(emp.getEmpNom());
					eDto.setEmpPren(emp.getEmpPren());
					eDto.setEmpTelephone(emp.getEmpTelephone());
					eDto.setEmpEmail(emp.getEmpEmail());
					eDto.setFonction(empFonct.orElseThrow(EntityNotFoundException::new).getFonction());
					eDto.setStatus(empStatus.orElseThrow(EntityNotFoundException::new).getStatus());
					eDto.setTypeEmploye(empTypeEmp.orElseThrow(EntityNotFoundException::new).getTypeEmp());
					eDto.setLieuAffectation(empLieuAffect.orElseThrow(EntityNotFoundException::new).getLieuAffect());

					var optAffectationEmploye = checktatusEncoursEmploye(emp);
					var optCongeEmploye = checkCongeEncoursEmploye(emp);
					var optMissionEmploye = checkMissionEncoursEmploye(emp);
					computeStatusEncoursEmploye(optAffectationEmploye, optCongeEmploye, optMissionEmploye, eDto);*/
                    
					var eDto = employeToDtoMapper(emp);
					return eDto;
				})
				.toList();
	}

	private final void computeStatusEncoursEmploye(final Optional<Affectation> optAffectation, Optional<Conge> optConge, Optional<Mission> optMission, EmployeDto employeDto) {
		/*Conge congeEmploye = null;
		Mission missionEmploye = null;
		Affectation affectationEmploye = null;*/

		optAffectation.ifPresent(affectationEmploye -> {
			Conge congeEmploye = null;
			Mission missionEmploye = null;
			//Affectation affectationEmploye = null;

			//affectationEmploye = optAffectation.get();
		
			if (optConge.isPresent()){
				congeEmploye = optConge.get();
			}

			if (optMission.isPresent()){
				missionEmploye = optMission.get();
			}

			if (Objects.nonNull(congeEmploye) && Objects.nonNull(missionEmploye)){ 
				// employé a dejà pris de congés et a aussi effectué des missions
				if (congeEmploye.getDateDebutConge().isAfter(missionEmploye.getDateRetour()) && congeEmploye.getDateDebutConge().isAfter(affectationEmploye.getDateDebutAffect())){
						// Employé en congé
						employeDto.setEmpDateDebutStatus(congeEmploye.getDateDebutConge());
						employeDto.setEmpDateFinStatus(congeEmploye.getDateFinConge());
						employeDto.setStatus(statusService.getStatusByCode(String.valueOf("CGE")).orElseThrow(EntityNotFoundException::new).getStatus());	
				} else {
					if (missionEmploye.getDateDepart().isAfter(affectationEmploye.getDateDebutAffect())){
						// Employé en mission
						employeDto.setEmpDateDebutStatus(missionEmploye.getDateDepart());
						employeDto.setEmpDateFinStatus(missionEmploye.getDateRetour());
						employeDto.setStatus(statusService.getStatusByCode(String.valueOf("MSN")).orElseThrow(EntityNotFoundException::new).getStatus());
					}
				}		
			} else {
				// employé a soit dejà pris de congés ou a aussi effectué des missions
				if (Objects.nonNull(missionEmploye) && missionEmploye.getDateDepart().isAfter(affectationEmploye.getDateDebutAffect())){
						// Employé en mission
						employeDto.setEmpDateDebutStatus(missionEmploye.getDateDepart());
						employeDto.setEmpDateFinStatus(missionEmploye.getDateRetour());
						employeDto.setStatus(statusService.getStatusByCode(String.valueOf("MSN")).orElseThrow(EntityNotFoundException::new).getStatus());
					
				} else {
					if (Objects.nonNull(congeEmploye) && congeEmploye.getDateDebutConge().isAfter(affectationEmploye.getDateDebutAffect())){
							// Employé en congé
							employeDto.setEmpDateDebutStatus(congeEmploye.getDateDebutConge());
							employeDto.setEmpDateFinStatus(congeEmploye.getDateFinConge());
							employeDto.setStatus(statusService.getStatusByCode(String.valueOf("CGE")).orElseThrow(EntityNotFoundException::new).getStatus());
					} else {
						// Employé n'a ni congé ni mission
						employeDto.setEmpDateDebutStatus(affectationEmploye.getDateDebutAffect());
						employeDto.setStatus(statusService.getStatusByCode(String.valueOf("SVCE")).orElseThrow(EntityNotFoundException::new).getStatus());

						if (!affectationEmploye.getDateFinAffect().isAfter(affectationEmploye.getDateDebutAffect())){
							employeDto.setEmpDateFinStatus(affectationEmploye.getDateFinAffect());
						}
					}
				}
			}
		});
	}

	public Optional<EmployeDto> getEmployeByMatricule(String empMatricule){
		if (StringUtils.isBlank(empMatricule)){
			return Optional.empty();
		}
		var optionalEmp = employeRepository.findByEmpMatricule(empMatricule);
	
		return employeMapper(optionalEmp);
	}

	private Optional<EmployeDto> employeMapper(Optional<Employe> optEmploye){
		if (!optEmploye.isPresent()){
			return Optional.empty();	
		}
				
		/*var emp = optEmploye.get();
		var eDto = new EmployeDto();
		
		var empFonct = fonctionService.getFonctionByCode(emp.getEmpFonction().getFonctionCode());
		var empStatus = statusService.getStatusByCode(emp.getEmpStatus().getStatusCode());
		var empTypeEmp = typeEmployeService.getTypeEmpByCode(emp.getTypeEmploye().getTypeEmpCode());
		var empLieuAffect = lieuAffectationService.getLieuAffectByCode(emp.getEmpLieuAffectation().getLieuAffectCode());

		eDto.setEmpCivilite(emp.getEmpCivilite());
		eDto.setEmpMatricule(emp.getEmpMatricule());
		eDto.setEmpNom(emp.getEmpNom());
		eDto.setEmpPren(emp.getEmpPren());
		eDto.setEmpTelephone(emp.getEmpTelephone());
		eDto.setEmpEmail(emp.getEmpEmail());

		eDto.setFonction(empFonct.orElseThrow(EntityNotFoundException::new).getFonction());
		//eDto.setStatus(empStatus.orElseThrow(EntityNotFoundException::new)); // voir computeStatusEncoursEmploye
		eDto.setTypeEmploye(empTypeEmp.orElseThrow(EntityNotFoundException::new).getTypeEmp());
		eDto.setLieuAffectation(empLieuAffect.orElseThrow(EntityNotFoundException::new).getLieuAffect());

		var optAffectationEmploye = checktatusEncoursEmploye(emp);
		var optCongeEmploye = checkCongeEncoursEmploye(emp);
		var optMissionEmploye = checkMissionEncoursEmploye(emp);
		computeStatusEncoursEmploye(optAffectationEmploye, optCongeEmploye, optMissionEmploye, eDto);*/

		var eDto = employeToDtoMapper(optEmploye.get());

		return Optional.of(eDto);
	}
    
    private EmployeDto employeToDtoMapper(Employe employe){
	    if (Objects.isNull(employe)){
            throw new EntityNotFoundException("L'entité employé ne doit être null");
		}

	    var empFonct = fonctionService.getFonctionByCode(employe.getEmpFonction().getFonctionCode());
		var empStatus = statusService.getStatusByCode(employe.getEmpStatus().getStatusCode());
		var empTypeEmp = typeEmployeService.getTypeEmpByCode(employe.getTypeEmploye().getTypeEmpCode());
		var empLieuAffect = lieuAffectationService.getLieuAffectByCode(employe.getEmpLieuAffectation().getLieuAffectCode());
		
		var eDto = new EmployeDto();
		eDto.setEmpCivilite(employe.getEmpCivilite());
		eDto.setEmpMatricule(employe.getEmpMatricule());
		eDto.setEmpNom(employe.getEmpNom());
		eDto.setEmpPren(employe.getEmpPren());
		eDto.setEmpTelephone(employe.getEmpTelephone());
		eDto.setEmpEmail(employe.getEmpEmail());
		eDto.setFonction(empFonct.orElseThrow(EntityNotFoundException::new).getFonction());
		eDto.setStatus(empStatus.orElseThrow(EntityNotFoundException::new).getStatus());
		eDto.setTypeEmploye(empTypeEmp.orElseThrow(EntityNotFoundException::new).getTypeEmp());
		eDto.setLieuAffectation(empLieuAffect.orElseThrow(EntityNotFoundException::new).getLieuAffect());

		var optAffectationEmploye = checktatusEncoursEmploye(employe);
		var optCongeEmploye = checkCongeEncoursEmploye(employe);
		var optMissionEmploye = checkMissionEncoursEmploye(employe);
		computeStatusEncoursEmploye(optAffectationEmploye, optCongeEmploye, optMissionEmploye, eDto);

		return eDto;
	}

	private final Optional<Affectation> checktatusEncoursEmploye(final Employe employe) {

		return affectationRepository.findByEmploye(employe).stream()
								.sorted((a1, a2) -> a2.getId().compareTo(a1.getId()))
								//.filter(a -> a.getDateFinAffect().equals(a.getDateDebutAffect()))
								.findFirst();
	}

	private final Optional<Conge> checkCongeEncoursEmploye(final Employe employe) {

		return congeRepository.findByEmploye(employe).stream()
								.sorted((c1, c2) -> c2.getId().compareTo(c1.getId()))
								//.filter(a -> a.getDateFinAffect().equals(a.getDateDebutAffect()))
								.findFirst();
	}

	private final Optional<Mission> checkMissionEncoursEmploye(final Employe employe) {

		return missionRepository.findByEmploye(employe).stream()
								.sorted((m1, m2) -> m2.getId().compareTo(m1.getId()))
								//.filter(a -> a.getDateFinAffect().equals(a.getDateDebutAffect()))
								.findFirst();
	}
}
