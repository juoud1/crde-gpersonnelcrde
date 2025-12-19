package gpersonnelcrde.controller;

import java.time.LocalDate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gpersonnelcrde.exception.CongeServiceException;
import gpersonnelcrde.exception.EmployeServiceException;
import gpersonnelcrde.exception.MissionServiceException;
import gpersonnelcrde.service.CongeService;
import gpersonnelcrde.service.MissionService;

@Controller
public class RepriseTravailSuiteMissionCongeController {
	private static final Logger logger = LoggerFactory.getLogger(RepriseTravailSuiteMissionCongeController.class);

	private final MissionService missionService;
	private final CongeService congeService;

	public RepriseTravailSuiteMissionCongeController(MissionService missionService, CongeService congeService){
		this.missionService = missionService;
		this.congeService = congeService;

		logger.info("composant-de-présentaion de traitement de reprise de travail des employés à la suite mission ou congé initialisé avec succès!".toUpperCase());
	}

	@PostMapping ("/reprise-trav-employe-crde.html")
	public String processReprise(@RequestParam("typereprise") String typeReprise,
					@RequestParam(value="datedebtrtmtreprise", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebTrtmtReprise,
					@RequestParam(value="datefintrtmtreprise", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFinTrtmtReprise) throws MissionServiceException, CongeServiceException{

		if (StringUtils.isNotBlank(typeReprise)){
			if (typeReprise.equalsIgnoreCase("mission")) {
				this.missionService.updateMissionsEffectuees(dateDebTrtmtReprise, dateFinTrtmtReprise);
			
			} else if (typeReprise.equalsIgnoreCase("conge")){
				this.congeService.updateCongesEffectuees(dateDebTrtmtReprise, dateFinTrtmtReprise);
			} else {
				throw new IllegalArgumentException("Type de reprise non reconnu : ".toUpperCase() + typeReprise);
			}
			logger.info("Traitement de reprise de travail des employés à la suite de {}, est effectué avec succès!".toUpperCase(), typeReprise);
		} else {
			throw new IllegalArgumentException("Type de reprise est obligatoire.".toUpperCase());
		}

		return "redirect:/employes-crde.html";  //"gemployecrdelist";
	}
}
