package gpersonnelcrde.controller;

import java.time.LocalDate;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import gpersonnelcrde.domain.dto.MissionDto;
import gpersonnelcrde.exception.EmployeServiceException;
import gpersonnelcrde.exception.StockageFichiersImagesException;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.MissionService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MissionController {
	private final static Logger logger = LoggerFactory.getLogger(MissionController.class);

	private final MissionService missionService;
	private final EmployeService employeService;

	public MissionController(MissionService missionService, EmployeService employeService) {
		this.missionService = missionService;
		this.employeService = employeService;
		logger.info("composant-de-présenataion de mission initialisé avec succès!");
	}
    
	////// VOIR miss-emp @GetMapping ("/missions-emp-crde.html")
	public String getGestMissions(HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException{
	    model.addAttribute("allMissions", missionService.getAllMissions()); 
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);
		//request.getSession().setAttribute("modelMission", model);
	    return "gmissioncrdelist";
	}

	/*@GetMapping ("/missions-emp-crde.html")
	public String getissions(HttpServletRequest request, Model model){
	    model.addAttribute("allMissions", missionService.getAllMissions()); 
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);
		request.getSession().setAttribute("modelMission", model);
	    return "gmissioncrde";
	}*/

	/*@GetMapping ("/mission-grpe-emp-crde.html")
	public String getMissionGroupe(HttpServletRequest request, Model model){
	    model.addAttribute("allMissions", missionService.getAllMissions()); 
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);
		//request.getSession().setAttribute("modelMission", model);
	    return "gmissioncrde";
	}*/

	////// VOIR miss-emp @GetMapping ("/mission-emp-crde.html/{typOrdMiss}")
	public String getMissionIndividuelle(@PathVariable(required = false) String typOrdMiss, HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException{
	    model.addAttribute("allMissions", missionService.getAllMissions()); 
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
				model.addAttribute("typMissText", "Mission d'un employé");
				model.addAttribute("paysResidenceText", "Rép. Centrafricaine");
			}
		}

		//request.getSession().setAttribute("modelMission", model);
	    return "gmissioncrde";
	}

	//@PostMapping ("/mission-grpe-emp-crde.html")
	public String addMissionGroupe(HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException{
	    model.addAttribute("allMissions", missionService.getAllMissions()); 
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);
		request.getSession().setAttribute("modelMission", model);
	    return "gmissioncrde";
	}

	//@PostMapping ("/mission-indiv-emp-crde.html")
	public String addMissionIndividuelle(HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException{
	    model.addAttribute("allMissions", missionService.getAllMissions()); 
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);
		request.getSession().setAttribute("modelMission", model);
	    return "gmissioncrde";
	}

	//@PostMapping ("/mission-emp-crde.html")
	public String addMission(@RequestParam("naturedeplacement") String natureDeplacement, 
							@RequestParam("cadremission") String cadreMission, @RequestParam("datedepartmiss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepartMiss, 
							@RequestParam("dateretourmiss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetourMiss, 
							@RequestParam("destville") String destVille, @RequestParam("destpays") String destPays,
							@RequestParam("motifmission") String motifMission, @RequestParam("infosupplmission") String infoSupplmission, @RequestParam("numordremiss") String numOrdreMiss,  @RequestParam("typeordremission") String typeOrdreMission,  HttpServletRequest request, Model model) {
		
		var savedMission = missionService.saveMission(natureDeplacement, cadreMission, dateDepartMiss, dateRetourMiss, destVille, destPays, motifMission, infoSupplmission, numOrdreMiss, typeOrdreMission);
		
		if (Objects.nonNull(savedMission)){
			model.addAttribute("resultTraitement", "Création de mission effectuée avec succès.");
		}
		model.addAttribute("savedMission", savedMission);
		//request.getSession().setAttribute("modelMission", model);

		return "gmissioncrdeRecap";
	}

	////// VOIR miss-emp @PostMapping ("/mission-emp-crde.html")
	public String addMission(@RequestParam("naturedeplacement") String natureDeplacement, 
							@RequestParam("cadremission") String cadreMission, 
							@RequestParam("datedepartmiss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepartMiss, 
							@RequestParam("dateretourmiss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetourMiss, 
							@RequestParam("typeordremission") String typeOrdreMission,
							@RequestParam("destville") String destVille, @RequestParam("destpays") String destPays, 
							@RequestParam("numordremiss") String numOrdreMiss, 
							@RequestParam("motifmission") String motifMission, 
							@RequestParam("infosupplmission") String infoSupplmission,
							@RequestParam("empchefmissionmatricule") String missEmpChefMissMatricule, 
							HttpServletRequest request, Model model,
							@RequestParam("missempmatricule") String... missEmpMatricules) {
		
		var savedMissions = missionService.saveMissionEmployes(numOrdreMiss, typeOrdreMission, natureDeplacement, cadreMission, dateDepartMiss, dateRetourMiss, destVille, destPays, motifMission, infoSupplmission, missEmpChefMissMatricule, missEmpMatricules);
		//model = (Model) request.getSession().getAttribute("modelMission");
		if (!savedMissions.isEmpty()){
			model.addAttribute("resultTraitement", "Création de mission effectuée avec succès.");
		}
		model.addAttribute("savedMissions", savedMissions);
		//request.getSession().setAttribute("modelMission", model);

		return "gmissioncrdeRecap";
	}

	////// VOIR miss-emp @GetMapping ("/mission-emp-crde-m.html/{numOrderMission}/{missEmpMatricule}")
	public String getMissionByNumOrdreAndEmpMatricule(@PathVariable String numOrderMission, @PathVariable String missEmpMatricule, HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException{
	    var savedMission = missionService.getMissionByNumOrdreMissionAndMatriculeEmp(numOrderMission, missEmpMatricule)
								.orElseGet(MissionDto::new);
		model.addAttribute("savedMission", savedMission);
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		
		return "gmissioncrdeMaj";
	}

	////// VOIR miss-emp @GetMapping ("/missions-emp-crde.html/{missEmpMatricule}/{choixStr}")
	public String getMissionsByEmpMatricule(@PathVariable String missEmpMatricule, @PathVariable String choixStr, HttpServletRequest request, Model model){
	    var savedMissionsEmploye = missionService.getMissionByEmployeMatricule(missEmpMatricule);								
		model.addAttribute("savedMissionsEmploye", savedMissionsEmploye);
		model.addAttribute("missEmpMatricule", missEmpMatricule);
		if (StringUtils.isNotBlank(choixStr) && !"hist".equalsIgnoreCase(choixStr)){
			model.addAttribute("savedChoixStr", choixStr);
		}

		return "gmissionsemphistoriq";
	}

	////// VOIR miss-emp @GetMapping ("/mission-emp-crde-m.html/{numOrderMission}")
	public String getMissionByNumOrdre(@PathVariable String numOrderMission, HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException{
	    var savedMission = missionService.getMissionByNum(numOrderMission)
								.orElseGet(MissionDto::new);
								
		model.addAttribute("savedMission", savedMission);
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		
		return "gmissioncrdeMaj";
	}

	////// VOIR miss-emp @DeleteMapping("/mission-emp-crde-m.html/{numOrderMission}")
	public String deleteMissionByNumOrdr(@PathVariable String numOrdreMission, HttpServletRequest request, Model model){
	    
		return "redirect:/missions-emp-crde.html";
	}
}
