package gpersonnelcrde.controller;

import java.time.LocalDate;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gpersonnelcrde.domain.dto.AffectationDto;
import gpersonnelcrde.service.AffectationService;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.FonctionService;
import gpersonnelcrde.service.LieuAffectationService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AffectationController {
	private final AffectationService affectationService;
	private final EmployeService employeService;
	private final FonctionService fonctionRepository;
	private final LieuAffectationService lieuAffectationService;

	public AffectationController(AffectationService affectationService, EmployeService employeService,
			FonctionService fonctionRepository, LieuAffectationService lieuAffectationService) {
		this.affectationService = affectationService;
		this.employeService = employeService;
		this.fonctionRepository = fonctionRepository;
		this.lieuAffectationService = lieuAffectationService;
	}

	@GetMapping ("/affectations-emp-crde.html")
	public String getGestAffectations(HttpServletRequest request, Model model){
		model.addAttribute("allFonction", fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
	    model.addAttribute("allAffectations", affectationService.getAllAffectation());
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		
	    return "gaffectationcrdelist";
	}

	@GetMapping ("/affectation-emp-crde.html")
	public String getAffectation(HttpServletRequest request, Model model){
		model.addAttribute("allFonction", fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
	    model.addAttribute("allAffectations", affectationService.getAllAffectation());
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		
	    return "gaffectationcrde";
	}

	@PostMapping("/affectation-emp-crde.html")
	public String addAffectation(@RequestParam("affectempmatricule") String affectEmpMatricule, @RequestParam("datedebaffect") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebAffect,
	                @RequestParam("datefinaffect") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFinAffect, @RequestParam("datepriseservice") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePriseService,
					@RequestParam("numnoteserviceaffect") String numNoteServiceAffect, @RequestParam("lieuaffect") String lieuAffect, @RequestParam("emplacementaffect") String emplacementAffect, 
					 @RequestParam("fonctioncode") String fonction, @RequestParam("commenataireaffect") String commenataireAffect, HttpServletRequest request, Model model){
		
		var savedAffectation = new AffectationDto(); // congeService.saveCongeEmploye(matriculeEmpConge, dateDebConge, dateFinConge, infoSupplConge, dateDepartAutorisatSortie, dateRetourAutorisatSortie, villeAutorisatSortie, paysAutorisatSortie);
		savedAffectation.setEmployeNom(affectEmpMatricule);
		if (Objects.nonNull(savedAffectation)){
			model.addAttribute("resultTraitement", "Création d'Affectation effectuée avec succès.");
		}

		model.addAttribute("savedAffectation", savedAffectation);

		return "gaffectationcrdeRecap";
	}

	@GetMapping ("/affectation-emp-crde-m.html/{numNoteServiceAffect}")
	public String getAffectationByNumNoteSvceAffect(@PathVariable String numNoteServiceAffect, HttpServletRequest request, Model model){
		var savedAffectation = affectationService.getAffectByByNumNoteService(numNoteServiceAffect)
							.orElseGet(AffectationDto::new);
		model.addAttribute("savedAffectation", savedAffectation);
		model.addAttribute("allFonction", fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
	    model.addAttribute("allAffectations", affectationService.getAllAffectation());
						
		return "gaffectationcrdeMaj";
	}

	@GetMapping ("/affectations-emp-crde.html/{employeMatricule}")
	public String getAffectationsByEmpMatricule(@PathVariable String employeMatricule, HttpServletRequest request, Model model){
	    var savedAffectationsEmploye = affectationService.getAffectationByEmployeMatricule(employeMatricule);								
		model.addAttribute("savedAffectationsEmploye", savedAffectationsEmploye);

		return "gaffectationcrdeMaj";
	}

	@GetMapping ("/affectations-emp-crde.html/{employeMatricule}/{choixStr}")
	public String getMissionsByEmpMatricule(@PathVariable String employeMatricule, @PathVariable String choixStr, HttpServletRequest request, Model model){
	    var savedAffectationsEmploye = affectationService.getAffectationByEmployeMatricule(employeMatricule);								
		model.addAttribute("savedAffectationsEmploye", savedAffectationsEmploye);
		model.addAttribute("affectEmpMatricule", employeMatricule);
		if (StringUtils.isNotBlank(choixStr) && !"hist".equalsIgnoreCase(choixStr)){
			model.addAttribute("savedChoixStr", choixStr);
		}

		return "gaffectationsemphistoriq";
	}

	@DeleteMapping ("/affectation-emp-crde-m.html/{numNoteServiceAffect}")
	public String deleteAffectationByNumNoteSvceAffect(@PathVariable String numNoteServiceAffect, HttpServletRequest request, Model model){

		return "redirect:/affecatations-emp-crde.html";
	}

	@PutMapping ("/affectation-emp-crde-m.html/{numNoteServiceAffect}")
	public String putAffectationByNumNoteSvceAffect(@PathVariable String numNoteServiceAffect, HttpServletRequest request, Model model){

		return "redirect:/affecatations-emp-crde.html";
	}

}