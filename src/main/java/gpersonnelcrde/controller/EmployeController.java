package gpersonnelcrde.controller;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.FonctionService;
import gpersonnelcrde.service.LieuAffectationService;
import gpersonnelcrde.service.StatusService;
import gpersonnelcrde.service.TypeEmployeService;
import jakarta.persistence.EntityNotFoundException;
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
		model.addAttribute("allFonctions", fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);

		return "gemployecrdelist";
	}

	@GetMapping ("/employe-crde.html")
	public String addEmploye(HttpServletRequest request, Model model){
		model.addAttribute("allStatus", statusService.getAllStatus());
		model.addAttribute("allTypeEmp", typeEmployeService.getAllTypeEmp());
		model.addAttribute("allFonctions", fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
		//model.addAttribute("allEmployes", employeService.getAllEmploye());
		return "gemployecrde";
	}

	@PostMapping ("/employe-crde.html")
	public String addEmploye(@RequestParam("empcivilite") String empCivilite, 
					@RequestParam("empnom") String empNom,
					@RequestParam("emppren") String empPren,
					@RequestParam("typeemp") String typeEmploye, 
					@RequestParam(value="empmatricule", required=false) String empMatricule, 
					@RequestParam("empemail") String empEmail,
					@RequestParam(value="emptelephone", required=false) String empTelephone, 
					@RequestParam("sttus") String status,
					@RequestParam("empfnction") String empFonction,
					@RequestParam(value="refdecretouarreteentree", required=false) String refDecretouArreteEntree,
					@RequestParam("lieuaffectation") String lieuAffectation,
					//@RequestParam("refdecretouarretedepart") String refDecretouArreteDepart,
					@RequestParam(value="empdatedebutstatus", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate empDateDebutStatus,
					@RequestParam(value="empdatefinstatus", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate empDateFinStatus,
					@RequestParam(value="datedecretouarreteentree", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDecretouArreteEntree,
					//@RequestParam("datedecretouarretedepart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDecretouArreteDepart,
					HttpServletRequest request, Model model) throws EntityNotFoundException, IllegalAccessException{

		var savedEmploye = employeService.createEmploye(empCivilite, empNom, empPren, typeEmploye, empMatricule, empEmail, empTelephone, 
										status, empFonction, refDecretouArreteEntree, lieuAffectation, empDateDebutStatus, 
										empDateDebutStatus, dateDecretouArreteEntree).orElseThrow(() -> new EntityNotFoundException("La création de l'employé a échouée."));
		
		if (Objects.nonNull(savedEmploye)) {
			model.addAttribute("traitement", "création du nouvel employé ou stagiaire");
			model.addAttribute("resultTraitement", "Création de l'employé effectuée avec succès.");
		}

		model.addAttribute("savedEmploye", savedEmploye);

		return "gemployecrderecap";
	}

	@GetMapping ("/employe-crde-m.html/{empMatricule}")
	public String getEmployeByNumInterne(@PathVariable String empMatricule, HttpServletRequest request, Model model){
		var savedEmploye = employeService.getEmployeByMatricule(empMatricule)
							.orElseGet(EmployeDto::new);
		model.addAttribute("savedEmploye", savedEmploye);
		model.addAttribute("allStatus", statusService.getAllStatus());
		model.addAttribute("allTypeEmp", typeEmployeService.getAllTypeEmp());
		model.addAttribute("allFonctions", fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
							
		return "gemployecrdemaj";
	}

	@DeleteMapping ("/employe-crde-m.html/{empMatricule}")
	public String deleteEmployeByNumInterne(@PathVariable String empMatricule, HttpServletRequest request, Model model){

		return "redirect:/employes-crde.html";
	}

	@PostMapping ("/employe-crde-m.html") //PUT de modification
	public String updateEmployeByNumInterne(
					@RequestParam("empcivilite") String empCivilite, 
					@RequestParam("empnom") String empNom,
					@RequestParam(value="emppren", required=false) String empPren,
					@RequestParam("typeemp") String typeEmploye, 
					@RequestParam(value="empmatricule", required=false) String empMatricule, 
					@RequestParam("empemail") String empEmail,
					@RequestParam(value="emptelephone", required=false) String empTelephone, 
					@RequestParam("sttus") String status,
					@RequestParam("empfnction") String empFonction,
					@RequestParam(value="refdecretouarreteentree", required=false) String refDecretouArreteEntree,
					@RequestParam("lieuaffectation") String lieuAffectation,
					@RequestParam(value="refdecretouarretedepart", required=false) String refDecretouArreteDepart,
					@RequestParam(value="empdatedebutstatus", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate empDateDebutStatus,
					@RequestParam(value="empdatefinstatus", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate empDateFinStatus,
					@RequestParam(value="datedecretouarreteentree", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDecretouArreteEntree,
					@RequestParam(value="datedecretouarretedepart", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDecretouArreteDepart,
	 				HttpServletRequest request, Model model){

		var savedEmploye = new EmployeDto();
		savedEmploye.setEmpMatricule(empMatricule);
		if (Objects.nonNull(savedEmploye)) {
			model.addAttribute("traitement", "modification de l'employé/stagiaire " + empMatricule);
			model.addAttribute("resultTraitement", "Modification de l'employé effectuée avec succès.");
		}

		model.addAttribute("savedEmploye", savedEmploye);

		return "gemployecrderecap"; //"redirect:/employes-crde.html";
	}

}