package gpersonnelcrde.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.dto.FonctionDto;
import gpersonnelcrde.domain.dto.LieuAffectationDto;
import gpersonnelcrde.exception.EmployeServiceException;
import gpersonnelcrde.exception.StockageFichiersImagesException;
import gpersonnelcrde.service.AffectationService;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.FonctionService;
import gpersonnelcrde.service.LieuAffectationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AffectationController {
	private final AffectationService affectationService;
	private final EmployeService employeService;
	private final FonctionService fonctionService;
	private final LieuAffectationService lieuAffectationService;

	public AffectationController(AffectationService affectationService, EmployeService employeService,
			FonctionService fonctionService, LieuAffectationService lieuAffectationService) {
		this.affectationService = affectationService;
		this.employeService = employeService;
		this.fonctionService = fonctionService;
		this.lieuAffectationService = lieuAffectationService;
	}

	@GetMapping ("/affectations-emp-crde.html")
	public String getGestAffectations(HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException, InterruptedException, ExecutionException{
		Future<List<FonctionDto>> futureEmpFonctions = null;
		Future<List<AffectationDto>> futureEmpAffectations = null;
		//Future<List<TypeEmployeDto>> futureTypeEmployes = null;
		Future<List<LieuAffectationDto>> futureLieuAffectations = null;
		Future<List<EmployeDto>> futureEmployes = null;
		
		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try {//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futureEmpFonctions = executor.submit(() -> fonctionService.getAllFonction());
			futureEmpAffectations = executor.submit(() -> affectationService.getAllAffectation());
			//futureTypeEmployes = executor.submit(() -> typeEmployeService.getAllTypeEmp());
			futureLieuAffectations = executor.submit(() -> lieuAffectationService.getAllLieuAffect());
			futureEmployes = executor.submit(() -> employeService.getAllEmploye());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		
		executor.close();//awaitTermination(5, TimeUnit.SECONDS); //waits until all tasks have completed execution and the executor has terminated
		
		var allFonctions = futureEmpFonctions.get(); //fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode());
		var allAffectations = futureEmpAffectations.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		//var allTypeEmp = futureTypeEmployes.get(); //typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode());
		var allLieuAffect = futureLieuAffectations.get(); //lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode());
		var allEmployes = futureEmployes.get();
		
		model.addAttribute("allFonctions", allFonctions); //fonctionRepository.getAllFonction());
		model.addAttribute("allLieuxAffect", allLieuAffect); //lieuAffectationService.getAllLieuAffect());
	    model.addAttribute("allAffectations", allAffectations); //affectationService.getAllAffectation());
		model.addAttribute("allEmployes", allEmployes); //employeService.getAllEmploye());
		
	    return "gaffectationcrdelist";
	}

	@GetMapping ("/affectation-emp-crde.html/{categAffect}")
	public String getAffectation(@PathVariable(required = false) String categAffect, HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException, InterruptedException, ExecutionException{
		Future<List<FonctionDto>> futureEmpFonctions = null;
		Future<List<AffectationDto>> futureEmpAffectations = null;
		//Future<List<TypeEmployeDto>> futureTypeEmployes = null;
		Future<List<LieuAffectationDto>> futureLieuAffectations = null;
		Future<List<EmployeDto>> futureEmployes = null;
		
		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try {//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futureEmpFonctions = executor.submit(() -> fonctionService.getAllFonction());
			futureEmpAffectations = executor.submit(() -> affectationService.getAllAffectation());
			//futureTypeEmployes = executor.submit(() -> typeEmployeService.getAllTypeEmp());
			futureLieuAffectations = executor.submit(() -> lieuAffectationService.getAllLieuAffect());
			futureEmployes = executor.submit(() -> employeService.getAllEmploye());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		
		executor.close();//awaitTermination(5, TimeUnit.SECONDS); //waits until all tasks have completed execution and the executor has terminated
		
		var allFonctions = futureEmpFonctions.get(); //fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode());
		var allAffectations = futureEmpAffectations.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		//var allTypeEmp = futureTypeEmployes.get(); //typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode());
		var allLieuAffect = futureLieuAffectations.get(); //lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode());
		var allEmployes = futureEmployes.get();
		
		model.addAttribute("allFonction", allFonctions); //fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", allLieuAffect); //lieuAffectationService.getAllLieuAffect());
	    model.addAttribute("allAffectations", allAffectations); //affectationService.getAllAffectation());
		model.addAttribute("allEmployes", allEmployes); //employeService.getAllEmploye());
		/*
		model.addAttribute("allFonction", fonctionService.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
	    model.addAttribute("allAffectations", affectationService.getAllAffectation());
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		*/
		
		if (StringUtils.isNotBlank(categAffect)){
			if ("aff-ext".equalsIgnoreCase(categAffect)) {
				model.addAttribute("categAffectValue", "Affectation internationale");
				model.addAttribute("categAffectText", "Affectation à l'international");
				model.addAttribute("paysResidenceText", "");
			} else {
				model.addAttribute("categAffectValue", "Affectation intérieure RCA");
				model.addAttribute("categAffectText", "Affectation à l'intérieur de la RCA");
				model.addAttribute("paysResidenceText", "Rép. Centrafricaine");
			}
		}

	    return "gaffectationcrde";
	}

	@PostMapping("/affectation-emp-crde.html")
	public String addAffectation(@RequestParam("affectempmatricule") String affectEmpMatricule, @RequestParam(name="datedebaffect", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebAffect,
	                @RequestParam(name = "datefinaffect", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFinAffect, @RequestParam("datepriseservice") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePriseService,
					@RequestParam("numnoteserviceaffect") String numNoteServiceAffect, @RequestParam("lieuaffect") String lieuAffect, @RequestParam("emplacementaffect") String emplacementAffect, 
					 @RequestParam("fonctioncode") String fonction, @RequestParam("porteeaffect") String categorieAffect, @RequestParam(name="commenataireaffect", required = false) String commenataireAffect, @RequestParam(name="villeresidence", required = false) String villeResidence, @RequestParam(name="paysresidence", required = false) String paysResidence, HttpServletRequest request, Model model) throws IllegalAccessException{
		
		var savedAffectation = affectationService.createAffectation(categorieAffect, affectEmpMatricule, dateDebAffect, dateFinAffect, datePriseService, numNoteServiceAffect, lieuAffect, emplacementAffect, fonction, commenataireAffect, villeResidence, paysResidence)
										.orElseThrow(() -> new EntityNotFoundException("La création de l'affectation de l'employé a échouée."));
		//savedAffectation.setEmployeNom(affectEmpMatricule);
		if (Objects.nonNull(savedAffectation)){
			model.addAttribute("traitement", "création de nouvelle affectation");
			model.addAttribute("resultTraitement", "Création de l'Affectation employé effectuée avec succès.");
		}

		model.addAttribute("savedAffectation", savedAffectation);

		return "gaffectationcrdeRecap";
	}

	@GetMapping ("/affectation-emp-crde-m.html/{numNoteServiceAffect}")
	public String getAffectationByNumNoteSvceAffect(@PathVariable String numNoteServiceAffect, HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException, InterruptedException, ExecutionException{
		var savedAffectation = affectationService.getAffectByByNumNoteService(numNoteServiceAffect)
							.orElseGet(AffectationDto::new);
		model.addAttribute("savedAffectation", savedAffectation);
		
		Future<List<FonctionDto>> futureEmpFonctions = null;
		Future<List<AffectationDto>> futureEmpAffectations = null;
		//Future<List<TypeEmployeDto>> futureTypeEmployes = null;
		Future<List<LieuAffectationDto>> futureLieuAffectations = null;
		Future<List<EmployeDto>> futureEmployes = null;
		
		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try {//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futureEmpFonctions = executor.submit(() -> fonctionService.getAllFonction());
			futureEmpAffectations = executor.submit(() -> affectationService.getAllAffectation());
			//futureTypeEmployes = executor.submit(() -> typeEmployeService.getAllTypeEmp());
			futureLieuAffectations = executor.submit(() -> lieuAffectationService.getAllLieuAffect());
			futureEmployes = executor.submit(() -> employeService.getAllEmploye());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		
		executor.close();//awaitTermination(5, TimeUnit.SECONDS); //waits until all tasks have completed execution and the executor has terminated
		
		var allFonctions = futureEmpFonctions.get(); //fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode());
		var allAffectations = futureEmpAffectations.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		//var allTypeEmp = futureTypeEmployes.get(); //typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode());
		var allLieuAffect = futureLieuAffectations.get(); //lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode());
		var allEmployes = futureEmployes.get();
		
		model.addAttribute("allFonction", allFonctions); //fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", allLieuAffect); //lieuAffectationService.getAllLieuAffect());
	    model.addAttribute("allAffectations", allAffectations); //affectationService.getAllAffectation());
		model.addAttribute("allEmployes", allEmployes); //employeService.getAllEmploye());
		
		/*
		model.addAttribute("allFonction", fonctionService.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
	    model.addAttribute("allAffectations", affectationService.getAllAffectation());
		model.addAttribute("allEmployes", employeService.getAllEmploye());
		*/

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