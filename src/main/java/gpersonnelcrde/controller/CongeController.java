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

import gpersonnelcrde.domain.dto.CongeDto;
import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.dto.MissionEmployesDto;
import gpersonnelcrde.exception.EmployeServiceException;
import gpersonnelcrde.exception.StockageFichiersImagesException;
import gpersonnelcrde.service.CongeService;
import gpersonnelcrde.service.EmployeService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CongeController {
	private final CongeService congeService;
	private final EmployeService employeService;

	public CongeController(CongeService congeService, EmployeService employeService) {
		this.congeService = congeService;
		this.employeService = employeService;
	}

	@GetMapping ("/conges-emp-crde.html")
	public String getGestConges(HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException, InterruptedException, ExecutionException{
	    Future<List<CongeDto>> futureCongesEmployes = null;
		Future<List<EmployeDto>> futureEmployes = null;
		
		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try 
		{//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futureCongesEmployes = executor.submit(() -> congeService.getAllConges());
			futureEmployes = executor.submit(() -> employeService.getAllEmploye());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		
		executor.close();//awaitTermination(5, TimeUnit.SECONDS); //waits until all tasks have completed execution and the executor has terminated
		
		var allConges = futureCongesEmployes.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		var allEmployes = futureEmployes.get();
		
		model.addAttribute("allConges", allConges); //congeService.getAllConges());
		model.addAttribute("allEmployes", allEmployes); //employeService.getAllEmploye());
		//request.getSession().setAttribute("modelMission", model);

	    return "gcongecrdelist";
	}

	@GetMapping ("/conge-emp-crde.html/{typCnge}")
	public String getConge(@PathVariable(required = false) String typCnge, HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException, InterruptedException, ExecutionException{
	    Future<List<CongeDto>> futureCongesEmployes = null;
		Future<List<EmployeDto>> futureEmployes = null;
		
		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try 
		{//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futureCongesEmployes = executor.submit(() -> congeService.getAllConges());
			futureEmployes = executor.submit(() -> employeService.getAllEmploye());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		executor.close();//awaitTermination(5, TimeUnit.SECONDS); //waits until all tasks have completed execution and the executor has terminated
		
		if (StringUtils.isNotBlank(typCnge)){
			if ("cnge-et-as".equalsIgnoreCase(typCnge)) {
				model.addAttribute("typCngeValue", "Congé et autorisation sortie");
				model.addAttribute("typCngeText", "Congé et autorisation de sortie");
				//model.addAttribute("paysResidenceText", "");
			} else {
				model.addAttribute("typCngeValue", "Congé");
				model.addAttribute("typCngeText", "Congé");
				//model.addAttribute("paysResidenceText", "Rép. Centrafricaine");
			}
		}

		var allConges = futureCongesEmployes.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		var allEmployes = futureEmployes.get();

		/*model.addAttribute("allConges", congeService.getAllConges());
		model.addAttribute("allEmployes", employeService.getAllEmploye());*/

		model.addAttribute("allConges", allConges); //congeService.getAllConges());
		model.addAttribute("allEmployes", allEmployes); //employeService.getAllEmploye());

		//request.getSession().setAttribute("modelMission", model);
	    return "gcongecrde";
	}

	@PostMapping("/conge-emp-crde.html")
	public String addConge(@RequestParam("matriculeempconge") String matriculeEmpConge, @RequestParam("datedebconge") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebConge,
	                @RequestParam("datefinconge") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFinConge, @RequestParam("infosupplconge") String infoSupplConge, 
					 @RequestParam("datedepartautorisatsortie") String dateDepartAutorisatSortie,
					 @RequestParam("datefinautorisatSortie") LocalDate dateRetourAutorisatSortie,
					 @RequestParam("villeautorisatsortie") String villeAutorisatSortie, @RequestParam("paysautorisatsortie") String paysAutorisatSortie, HttpServletRequest request, Model model){
		
		var savedConge = new CongeDto(); // congeService.saveCongeEmploye(matriculeEmpConge, dateDebConge, dateFinConge, infoSupplConge, dateDepartAutorisatSortie, dateRetourAutorisatSortie, villeAutorisatSortie, paysAutorisatSortie);
		
		if (Objects.nonNull(savedConge)){
			model.addAttribute("resultTraitement", "Création de congé effectuée avec succès.");
		}

		model.addAttribute("savedConge", savedConge);

		return "gcongecrdeRecap";
	}

	@GetMapping ("/conge-emp-crde-m.html/{numNoteServiceConge}")
	public String getCongeByNumNoteSvceConge(@PathVariable String numNoteServiceConge, HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException{
		var savedConge = congeService.getCongeByNumNoteService(numNoteServiceConge)
							.orElseGet(CongeDto::new);
		var allEmployes = employeService.getAllEmploye();

		model.addAttribute("savedConge", savedConge);
		model.addAttribute("allEmployes", allEmployes); //employeService.getAllEmploye());
		model.addAttribute("employesEnSvce", allEmployes.stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);
							
		return "gcongecrdeMaj";
	}

	@GetMapping ("/conges-emp-crde.html/{employeMatricule}")
	public String getCongesByEmpMatricule(@PathVariable String employeMatricule, HttpServletRequest request, Model model){
	    var savedCongessEmploye = congeService.getCongeByEmployeMatricule(employeMatricule);								
		model.addAttribute("savedCongesEmploye", savedCongessEmploye);

		return "gcongecrdeMaj";
	}

	@GetMapping ("/conges-emp-crde.html/{employeMatricule}/{choixStr}")
	public String getCongesByEmpMatricule(@PathVariable String employeMatricule, @PathVariable String choixStr, HttpServletRequest request, Model model){
	    var savedCongesEmploye = congeService.getCongeByEmployeMatricule(employeMatricule);								
		
		model.addAttribute("savedCongesEmploye", savedCongesEmploye);
		model.addAttribute("congeEmpMatricule", employeMatricule);
		
		if (StringUtils.isNotBlank(choixStr) && !"hist".equalsIgnoreCase(choixStr)){
			model.addAttribute("savedChoixStr", choixStr);
		}

		return "gcongesemphistoriq";
	}

	@DeleteMapping ("/conge-emp-crde-m.html/{numNoteServiceConge}")
	public String deleteCongeByNumNoteSvceConge(@PathVariable String numNoteServiceConge, HttpServletRequest request, Model model){

		return "redirect:/conges-emp-crde.html";
	}

	@PutMapping ("/conge-emp-crde-m.html/{numNoteServiceConge}")
	public String putCongeByNumNoteSvceConge(@PathVariable String numNoteServiceConge, HttpServletRequest request, Model model){

		return "redirect:/conges-emp-crde.html";
	}
}
