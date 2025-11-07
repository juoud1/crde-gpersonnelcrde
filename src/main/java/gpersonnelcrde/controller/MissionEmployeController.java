package gpersonnelcrde.controller;

import java.time.LocalDate;
import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gpersonnelcrde.domain.dto.MissionEmployeDto;
import gpersonnelcrde.domain.dto.MissionEmployesDto;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.MissionEmployeService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MissionEmployeController {
	private final static Logger logger = LoggerFactory.getLogger(MissionEmployeController.class);

	private final MissionEmployeService missionEmployeService;
	//private final MissionService missionService;
	private final EmployeService employeService;

	public MissionEmployeController (MissionEmployeService missionEmployeService, EmployeService employeService) {
		this.missionEmployeService = missionEmployeService;
		this.employeService = employeService;
		logger.info("composant-de-présenataion de mission-employé initialisé avec succès!");
	}

	@GetMapping ("/missions-emp-crde.html")
	public String getGestMissionsEmployes(HttpServletRequest request, Model model){
	    model.addAttribute("allMissionsEmployes", missionEmployeService.getAllMissionEmployes().stream()
			.sorted((m, n) -> Long.valueOf(m.getNumMission()).compareTo(Long.valueOf(n.getNumMission())))
			.toList()); 
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);
		//request.getSession().setAttribute("modelMission", model);
	    return "gmissioncrdelist";
	}

	@GetMapping ("/mission-emp-crde.html/{typOrdMiss}")
	public String getMissionEmployeForCreation(@PathVariable(required = false) String typOrdMiss, HttpServletRequest request, Model model){
	    model.addAttribute("allMissionsEmployes", missionEmployeService.getAllMissionEmployes()); 
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);

		if (StringUtils.isNotBlank(typOrdMiss)){
			if ("miss-grpe".equalsIgnoreCase(typOrdMiss)) {
				model.addAttribute("typMissValue", "Mission de groupe");
				model.addAttribute("typMissText", "Mission de groupe");
				model.addAttribute("paysResidenceText", "");
			} else {
				model.addAttribute("typMissValue", "Mission");
				model.addAttribute("typMissText", "Mission");
				model.addAttribute("paysResidenceText", "Rép. Centrafricaine");
			}
		}

		updateUI(typOrdMiss, model);

		//request.getSession().setAttribute("modelMission", model);
	    return "gmissioncrde";
	}

	@PostMapping ("/mission-emp-crde.html")
	public String addMissionEmpl(@RequestParam("naturedeplacement") String natureDeplacement, 
							@RequestParam("cadremission") String cadreMission, 
							@RequestParam("datedepartmiss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepartMiss, 
							@RequestParam("dateretourmiss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetourMiss, 
							@RequestParam("typeordremission") String typeOrdreMission,
							@RequestParam("destville") String destVille, @RequestParam("destpays") String destPays, 
							@RequestParam("numordremiss") String numOrdreMiss, @RequestParam(required = false) String dureeMiss,
							@RequestParam("motifmission") String motifMission, 
							@RequestParam(name="infosupplmission", required = false) String infoSupplmission,
							@RequestParam("empchefmissionmatricule") String missEmpChefMissMatricule, 
							HttpServletRequest request, Model model,
							@RequestParam(name="missempmatricule", required = false) String... missEmpMatricules) {
		
		MissionEmployesDto savedMissionEmployes = missionEmployeService.saveMissionEmployes(numOrdreMiss, typeOrdreMission, natureDeplacement, 
																cadreMission, dateDepartMiss, dateRetourMiss, dureeMiss, 
																destVille, destPays, motifMission, infoSupplmission, 
																missEmpChefMissMatricule, missEmpMatricules);
		//model = (Model) request.getSession().getAttribute("modelMission");
		if (StringUtils.isNotBlank(savedMissionEmployes.getNumMission())){
			model.addAttribute("resultTraitement", "Création de mission - employé(s) effectuée avec succès.");
			model.addAttribute("traitement", "création de nouvelle mission");
		}
		model.addAttribute("savedMissionEmployes", savedMissionEmployes);
		model.addAttribute("allMissionsEmployes", missionEmployeService.getAllMissionEmployes());
		
		updateUI(typeOrdreMission, model);

		return "gmissioncrdeRecap";
	}

	@GetMapping ("/mission-emp-crde-m.html/{numMission}/{empMatricule}") /// Il manque le cas d'appel èa partir de la formRécap
	public String getMissionByNumAndEmpMatricule(@PathVariable String numMission, @PathVariable String empMatricule, HttpServletRequest request, Model model){
	    var savedMissionEmploye = missionEmployeService.getMissionEmployeByNumMissAndMatriculeEmp(numMission, empMatricule)
								.orElseGet(MissionEmployeDto::new);
		model.addAttribute("savedMissionEmploye", savedMissionEmploye);
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		
		updateUI(savedMissionEmploye.getTypeOrdreMission(), model);

		return "gmissioncrdeMaj";
	}

	@GetMapping ("/missions-emp-crde.html/{missEmpMatricule}/{choixStr}")
	public String getMissionsByEmpMatricule(@PathVariable String missEmpMatricule, @PathVariable String choixStr, HttpServletRequest request, Model model){
	    var savedMissionsEmployes = missionEmployeService.getMissionsEmployesByMatriculeEmp(missEmpMatricule);								
		model.addAttribute("savedMissionsEmployes", savedMissionsEmployes);
		model.addAttribute("missEmpMatricule", missEmpMatricule);
		if (StringUtils.isNotBlank(choixStr) && !"hist".equalsIgnoreCase(choixStr)){
			model.addAttribute("savedChoixStr", choixStr);
		}

		return "gmissionsemphistoriq";
	}

	@GetMapping ("/mission-emp-crde-m.html/{numMission}")
	public String getMissionByNum(@PathVariable String numMission, HttpServletRequest request, Model model){
	    var savedMissionEmployes = missionEmployeService.getMissionEmployesByNumMiss(numMission)
								.orElseGet(MissionEmployesDto::new);
		
		model.addAttribute("savedMissionEmployes", savedMissionEmployes);
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		model.addAttribute("allMissionsEmployes", missionEmployeService.getAllMissionEmployes()); 
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);
		
		updateUI(savedMissionEmployes.getTypeOrdreMission(), model);

		return "gmissioncrdeMaj";
	}

	private void updateUI(String typeOrdreMiss, Model model){
		if (StringUtils.isNotBlank(typeOrdreMiss)){
			if (typeOrdreMiss.equalsIgnoreCase("miss-grpe") || typeOrdreMiss.equalsIgnoreCase("Mission de groupe")) {
				model.addAttribute("typMissValue", "Mission de groupe");
				model.addAttribute("typMissText", "Mission de groupe");
				model.addAttribute("paysResidenceText", "");
			} else {
				model.addAttribute("typMissValue", "Mission");
				model.addAttribute("typMissText", "Mission");
				model.addAttribute("paysResidenceText", "Rép. Centrafricaine");
			}
		}
	}
}
