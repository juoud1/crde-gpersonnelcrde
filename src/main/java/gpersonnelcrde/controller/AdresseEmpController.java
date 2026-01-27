package gpersonnelcrde.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import gpersonnelcrde.service.AdresseEmpService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdresseEmpController {
	private static final Logger logger = LoggerFactory.getLogger(AdresseEmpController.class);

	private final AdresseEmpService adresseEmpService;

	public AdresseEmpController(AdresseEmpService adresseEmpService){
		this.adresseEmpService = adresseEmpService;
		logger.info("composant-de-présentaion de mappage des ressources d'adresses employés/stagiaires initialisé avec succès!".toUpperCase());
	}

	@GetMapping ("/adr-employe-crde-m.html/{empMatricule}/{typOp}")
	public String getAdresseEmpByMatriculeForUpdate(@PathVariable String empMatricule, @PathVariable String typOp, 
										HttpServletRequest request, Model model) {
		
		var adressesEmploye = this.adresseEmpService.getAdressesEmpByMatricule(empMatricule);
		var adresseActuelleEmploye = this.adresseEmpService.getAdressesEmpByMatricule(empMatricule).stream()
																.filter(a->a.isEstAdrResidceEmpActive())
																.findFirst()
																.orElse(null);
		
		//model.addAttribute("traitement", "Adresse actuelle de résidence de l'employé " + empMatricule);
		model.addAttribute("adresseActuelle", adresseActuelleEmploye);
		if ("cnsultadr".equalsIgnoreCase(typOp)){
			model.addAttribute("traitement", "Adresse actuelle de résidence de l'employé " + empMatricule + " -- (PAS ENCORE OPERATIONNEL, SVP)");
			//model.addAttribute("adresseActuelle", adresseActuelleEmploye);
			
			return "gadressempcrderecap";
		} else {
			model.addAttribute("allAdressesEmploye", adressesEmploye);

			return "gadressempcrdelist";
		}
		
		//return "cnsultadr".equalsIgnoreCase(typOp) ? "gadressempcrderecap" : "gadressempcrdelist";
	}

	@GetMapping ("/adr-employe-crde.html/{empMatricule}")
	public String getAdresseEmpByMatricule(@PathVariable String empMatricule, HttpServletRequest request, Model model) {
		var adresseActuelleEmploye = this.adresseEmpService.getAdressesEmpByMatricule(empMatricule).stream()
																.filter(a->a.isEstAdrResidceEmpActive())
																.findFirst()
																.orElse(null);
		model.addAttribute("traitement", "Ajouter une nouvelle dresse de résidence de l'employé " + empMatricule + " -- (PAS ENCORE OPERATIONNEL, SVP)");		
		model.addAttribute("adresseActuelle", adresseActuelleEmploye);

		return "gadressempcrde";
	}
}
