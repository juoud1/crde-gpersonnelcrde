package gpersonnelcrde.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ServerWebExchange;

import gpersonnelcrde.service.FonctionService;
import gpersonnelcrde.service.LieuAffectationService;
import gpersonnelcrde.service.StatusService;
import gpersonnelcrde.service.TypeEmployeService;
import gpersonnelcrde.service.UtilisateurService;


@Controller
//@RequestMapping("/crde/gpsnel" )
public class GestPersonnelController {
	private StatusService statusService;
	private TypeEmployeService typeEmployeService;
	private FonctionService fonctionRepository;
	private LieuAffectationService lieuAffectationService;
	private UtilisateurService utilisateurService; 

	public GestPersonnelController(StatusService statusService, TypeEmployeService typeEmployeService,
			FonctionService fonctionRepository, LieuAffectationService lieuAffectationService, UtilisateurService utilisateurService) {
		this.statusService = statusService;
		this.typeEmployeService = typeEmployeService;
		this.fonctionRepository = fonctionRepository;
		this.lieuAffectationService = lieuAffectationService;
		this.utilisateurService = utilisateurService;
	}



	@GetMapping("/accueil.html")
	public String displayGestPersonnel(Model model) {
		model.addAttribute("allStatus", statusService.getAllStatus());
		model.addAttribute("allTypeEmp", typeEmployeService.getAllTypeEmp());
		model.addAttribute("allFonction", fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
		model.addAttribute("allUtilisateurs", utilisateurService.getAllUtilisateur());
		
		return "index";
	}
	
}
