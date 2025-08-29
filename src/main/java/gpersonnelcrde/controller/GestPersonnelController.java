package gpersonnelcrde.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import gpersonnelcrde.service.AffectationService;
import gpersonnelcrde.service.CongeService;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.FonctionService;
import gpersonnelcrde.service.LieuAffectationService;
import gpersonnelcrde.service.MissionService;
import gpersonnelcrde.service.StatusService;
import gpersonnelcrde.service.TypeEmployeService;
import gpersonnelcrde.service.UtilisateurService;


@Controller
//@RequestMapping("/crde/gpsnel" )
public class GestPersonnelController {
	private final StatusService statusService;
	private final TypeEmployeService typeEmployeService;
	private final FonctionService fonctionRepository;
	private final LieuAffectationService lieuAffectationService;
	private final UtilisateurService utilisateurService;
	private final EmployeService employeService; 
	private final MissionService missionService;
	private final AffectationService affectationService;
	private final CongeService congeService;

	public GestPersonnelController(StatusService statusService, TypeEmployeService typeEmployeService,
			FonctionService fonctionRepository, LieuAffectationService lieuAffectationService, 
			UtilisateurService utilisateurService, EmployeService employeService, MissionService missionService, 
			AffectationService affectationService, CongeService congeService) {
		this.statusService = statusService;
		this.typeEmployeService = typeEmployeService;
		this.fonctionRepository = fonctionRepository;
		this.lieuAffectationService = lieuAffectationService;
		this.utilisateurService = utilisateurService;
		this.employeService = employeService;
		this.missionService = missionService;
		this.affectationService = affectationService;
		this.congeService = congeService;
	}



	@GetMapping("/accueil.html")
	public String displayGestPersonnel(Model model) {
		model.addAttribute("allStatus", statusService.getAllStatus());
		model.addAttribute("allTypeEmp", typeEmployeService.getAllTypeEmp());
		model.addAttribute("allFonction", fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
		model.addAttribute("allUtilisateurs", utilisateurService.getAllUtilisateur());
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> "SVCE".equalsIgnoreCase(emp.getStatus().getStatusCode()))
			.toList()
		);
		model.addAttribute("allMissions", missionService.getAllMissions());
		model.addAttribute("allAffectations", affectationService.getAllAffectation());
		model.addAttribute("allConges", congeService.getAllConge());

		return "index";
	}	
}