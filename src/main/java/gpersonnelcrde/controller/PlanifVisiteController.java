package gpersonnelcrde.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.PlanifVisiteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PlanifVisiteController {
	private final EmployeService employeService; 
	private final PlanifVisiteService planifVisiteService;
	
	public PlanifVisiteController(PlanifVisiteService planifVisiteService, EmployeService employeService){
		this.planifVisiteService = planifVisiteService;
		this.employeService = employeService;
	}

	@GetMapping ("/planif-visites-crde.html")
	public String getVisites(HttpServletRequest request, Model model){
		model.addAttribute("allVisites", planifVisiteService.getAllVisites());

		return "planifvisitecrdelist";
	}

	@GetMapping ("/planif-visite-crde.html")
	public String addVisite(HttpServletRequest request, Model model){
		/*model.addAttribute("allStatus", statusService.getAllStatus());
		model.addAttribute("allTypeEmp", typeEmployeService.getAllTypeEmp());
		model.addAttribute("allFonctions", fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());*/
		//model.addAttribute("allEmployes", employeService.getAllEmploye());
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);

		return "planifvisitecrde";
	}

	@GetMapping ("/planif-visite-crde-m.html/{numVisite}")
	public String getVisiteByNumVisite(@PathVariable String numVisite, HttpServletRequest request, Model model){
		var savedVisite = planifVisiteService.getVisiteByNumVisite(numVisite); 
		model.addAttribute("savedVisite", savedVisite.orElseThrow(EntityNotFoundException::new));
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);

		return "planifvisitecrdemaj";
	}
}
