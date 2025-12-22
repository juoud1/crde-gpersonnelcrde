package gpersonnelcrde.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.dto.PackCongeDto;
import gpersonnelcrde.exception.EmployeServiceException;
import gpersonnelcrde.exception.StockageFichiersImagesException;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.PackCongeService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PackCongeController {
	private static final Logger logger = LoggerFactory.getLogger(PackCongeController.class);

	private final PackCongeService packCongeService;
	private final EmployeService employeService;

	public PackCongeController(PackCongeService packCongeService, EmployeService employeService) {
		this.packCongeService = packCongeService;
		this.employeService = employeService;
		logger.info("composant-de-présentaion de mappage des ressources de pack/paquettage de congé des employés initialisé avec succès!".toUpperCase());
	}

	@GetMapping ("/conges-emp-crde.html")
	public String getGestConges(HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException, InterruptedException, ExecutionException{
	    Future<List<PackCongeDto>> futurePackCongesEmployes = null;
		Future<List<EmployeDto>> futureEmployes = null;
		
		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try 
		{//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futurePackCongesEmployes = executor.submit(() -> packCongeService.getAllPackConges());
			futureEmployes = executor.submit(() -> employeService.getAllEmploye());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		
		executor.close();//awaitTermination(5, TimeUnit.SECONDS); //waits until all tasks have completed execution and the executor has terminated
		
		var allConges = futurePackCongesEmployes.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		var allEmployes = futureEmployes.get();
		
		model.addAttribute("allConges", allConges); //congeService.getAllConges());
		model.addAttribute("allEmployes", allEmployes); //employeService.getAllEmploye());
		//request.getSession().setAttribute("modelMission", model);

	    return "gcongecrdelist";
	}

	@GetMapping ("/conge-emp-crde.html/{typCnge}")
	public String getConge(@PathVariable(required = false) String typCnge, HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException, InterruptedException, ExecutionException{
	    Future<List<PackCongeDto>> futurePackCongesEmployes = null;
		Future<List<EmployeDto>> futureEmployes = null;
		
		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try 
		{//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futurePackCongesEmployes = executor.submit(() -> packCongeService.getAllPackConges());
			futureEmployes = executor.submit(() -> employeService.getAllEmploye());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		
		executor.close();//awaitTermination(5, TimeUnit.SECONDS); //waits until all tasks have completed execution and the executor has terminated
		
		var allConges = futurePackCongesEmployes.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		var allEmployes = futureEmployes.get();

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

		/*model.addAttribute("allConges", congeService.getAllConges());
		model.addAttribute("allEmployes", employeService.getAllEmploye());*/

		model.addAttribute("allConges", allConges); //congeService.getAllConges());
		model.addAttribute("allEmployes", allEmployes); //employeService.getAllEmploye());

		//request.getSession().setAttribute("modelMission", model);
	    return "gcongecrde";
	}

	@PostMapping("/conge-emp-crde.html")
	public String addConge(@RequestParam(value="matriculeempconge", required = false) String matriculeEmpConge,  
					@RequestParam("typedemande") String typeDmdeConge,
					@RequestParam("datedebconge") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebConge,
	                @RequestParam("datefinconge") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFinConge, 
					@RequestParam("infosupplconge") String infoSupplConge,
					@RequestParam("numnoteserviceconge") String numNoteDeSvceConge,
					@RequestParam(value="numautorisatsortie", required = false) String numAutDeSortie, 
					@RequestParam(value="datedepartautorisatsortie", required = false) LocalDate dateDepartAutorisatSortie,
					@RequestParam(value="dateretourautorisatsortie", required = false) LocalDate dateRetourAutorisatSortie,
					@RequestParam(value="villeautorisatsortie", required = false) String villeAutorisatSortie,
					@RequestParam(value="paysautorisatsortie", required = false) String paysAutorisatSortie, 
					@RequestParam(value="motifsortie", required = false) String motifSortie, HttpServletRequest request, Model model){
		
		var savedConge = packCongeService.savePackCongeEmploye(matriculeEmpConge, typeDmdeConge, dateDebConge, dateFinConge, infoSupplConge,
								numNoteDeSvceConge, numAutDeSortie, dateDepartAutorisatSortie, dateRetourAutorisatSortie,
								villeAutorisatSortie, paysAutorisatSortie, motifSortie);
		
		if (Objects.nonNull(savedConge)){
			model.addAttribute("resultTraitement", "Création de congé effectuée avec succès.");
		}

		if (Objects.nonNull(savedConge) && StringUtils.isNotBlank(savedConge.getNumConge())){
			model.addAttribute("resultTraitement", "Création du pack congé et aut. de sortie de l'employé effectuée avec succès.");
			model.addAttribute("traitement", "création de nouveau congé");
		}

		model.addAttribute("savedConge", savedConge);

		return "gcongecrdeRecap";
	}

	@GetMapping ("/conge-emp-crde-m.html/{numConge}")
	public String getPackCongeByNumNoteSvceConge(@PathVariable String numConge, HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException, InterruptedException, ExecutionException{
		Future<PackCongeDto> futurePackCongeEmploye = null;
		Future<List<EmployeDto>> futureEmployes = null;
		
		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try 
		{//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futurePackCongeEmploye = executor.submit(() -> packCongeService.getPackCongeByNumNoteService(numConge)
																.orElseGet(PackCongeDto::new));
			futureEmployes = executor.submit(() -> employeService.getAllEmploye());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		executor.close();

		PackCongeDto savedConge = futurePackCongeEmploye.get(); //packCongeService.getPackCongeByNumNoteService(numNoteServiceConge) 
							//.orElseThrow(IllegalArgumentException::new);
							//.orElseGet(PackCongeDto::new);

		var allEmployes = employeService.getAllEmploye(); //futureEmployes.get(); //
		logger.info("Congé n° {} enregistré sous le n° {}", savedConge.getNumAutorisatSortie(), savedConge.getNumAutSortie());

		if (StringUtils.isNotBlank(savedConge.getNumConge())){
			model.addAttribute("resultTraitement", "Création du pack congé et aut. de sortie de l'employé effectuée avec succès.");
			model.addAttribute("traitement", "création de nouveau congé");
		}

		model.addAttribute("savedConge", savedConge);
		model.addAttribute("allEmployes", allEmployes); //employeService.getAllEmploye());
		model.addAttribute("employesEnSvce", allEmployes.stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);
							
		return "gcongecrdeMaj";
	}

	@GetMapping ("/conges-emp-crde.html/{employeMatricule}")
	public String getPackCongesByEmpMatricule(@PathVariable String employeMatricule, HttpServletRequest request, Model model){
	    var savedCongesEmploye = packCongeService.getPackCongeByEmployeMatricule(employeMatricule);								
		model.addAttribute("savedCongesEmploye", savedCongesEmploye);

		return "gcongecrdeMaj";
	}

	@GetMapping ("/conges-emp-crde.html/{employeMatricule}/{choixStr}")
	public String getCongesByEmpMatricule(@PathVariable String employeMatricule, @PathVariable String choixStr, HttpServletRequest request, Model model){
	    var savedCongesEmploye = packCongeService.getPackCongeByEmployeMatricule(employeMatricule);								
		
		model.addAttribute("savedCongesEmploye", savedCongesEmploye);
		model.addAttribute("congeEmpMatricule", employeMatricule);
		
		if (StringUtils.isNotBlank(choixStr) && !"hist".equalsIgnoreCase(choixStr)){
			model.addAttribute("savedChoixStr", choixStr);
		}

		return "gcongesemphistoriq";
	}
}
