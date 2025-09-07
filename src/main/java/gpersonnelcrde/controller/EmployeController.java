package gpersonnelcrde.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.FonctionService;
import gpersonnelcrde.service.LieuAffectationService;
import gpersonnelcrde.service.StatusService;
import gpersonnelcrde.service.TypeEmployeService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EmployeController {
	private final StatusService statusService;
	private final TypeEmployeService typeEmployeService;
	private final FonctionService fonctionRepository;
	private final LieuAffectationService lieuAffectationService;
	private final EmployeService employeService;

	public EmployeController(StatusService statusService, TypeEmployeService typeEmployeService,
			FonctionService fonctionRepository, LieuAffectationService lieuAffectationService,
			EmployeService employeService) {
		this.statusService = statusService;
		this.typeEmployeService = typeEmployeService;
		this.fonctionRepository = fonctionRepository;
		this.lieuAffectationService = lieuAffectationService;
		this.employeService = employeService;
	}

	@GetMapping ("/employes-crde.html")
	public String getEmployes(HttpServletRequest request, Model model){
		model.addAttribute("allStatus", statusService.getAllStatus());
		model.addAttribute("allTypeEmp", typeEmployeService.getAllTypeEmp());
		model.addAttribute("allFonction", fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);

		return "gemployecrde";
	}
}
