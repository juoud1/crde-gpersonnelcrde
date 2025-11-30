package gpersonnelcrde.controller;

import java.io.IOException;
import java.io.ObjectInputFilter.Status;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.dto.FonctionDto;
import gpersonnelcrde.domain.dto.LieuAffectationDto;
import gpersonnelcrde.domain.dto.StatusDto;
import gpersonnelcrde.domain.dto.TypeEmployeDto;
import gpersonnelcrde.domain.entities.Fonction;
import gpersonnelcrde.domain.entities.LieuAffectation;
import gpersonnelcrde.domain.entities.TypeEmploye;
import gpersonnelcrde.exception.EmployeServiceException;
import gpersonnelcrde.exception.StockageFichiersImagesException;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.FonctionService;
import gpersonnelcrde.service.LieuAffectationService;
import gpersonnelcrde.service.StatusService;
import gpersonnelcrde.service.StockageFichiersImagesService;
import gpersonnelcrde.service.TypeEmployeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EmployeController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeController.class);
	private final StatusService statusService;
	private final TypeEmployeService typeEmployeService;
	private final FonctionService fonctionService;
	private final LieuAffectationService lieuAffectationService;
	private final EmployeService employeService;
	private final StockageFichiersImagesService stockagePhotoEmployeService;

	public EmployeController(StatusService statusService, TypeEmployeService typeEmployeService,
			FonctionService fonctionService, LieuAffectationService lieuAffectationService,
			EmployeService employeService, StockageFichiersImagesService stockagePhotoEmployeService) {
		this.statusService = statusService;
		this.typeEmployeService = typeEmployeService;
		this.fonctionService = fonctionService;
		this.lieuAffectationService = lieuAffectationService;
		this.employeService = employeService;
		this.stockagePhotoEmployeService = stockagePhotoEmployeService;
		logger.info("composant-de-présenataion de traitement des données employé/stagiaire initialisé avec succès!");
	}

	@GetMapping ("/employes-crde.html")
	public String getEmployes(HttpServletRequest request, Model model) throws StockageFichiersImagesException, EmployeServiceException, InterruptedException, ExecutionException{
		Future<List<FonctionDto>> futureEmpFonctions = null;
		Future<List<StatusDto>> futureEmpStatus = null;
		Future<List<TypeEmployeDto>> futureTypeEmployes = null;
		Future<List<LieuAffectationDto>> futureLieuAffectations = null;
		Future<List<EmployeDto>> futureEmployes = null;
		//Future<Path> futurePathPhoto = null;

		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try {//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futureEmpFonctions = executor.submit(() -> fonctionService.getAllFonction());
			futureEmpStatus = executor.submit(() -> statusService.getAllStatus());
			futureTypeEmployes = executor.submit(() -> typeEmployeService.getAllTypeEmp());
			futureLieuAffectations = executor.submit(() -> lieuAffectationService.getAllLieuAffect());
			futureEmployes = executor.submit(() -> employeService.getAllEmploye());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		
		executor.close();//awaitTermination(5, TimeUnit.SECONDS); //waits until all tasks have completed execution and the executor has terminated
		
		var allFonctions = futureEmpFonctions.get(); //fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode());
		var allStatus = futureEmpStatus.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		var allTypeEmp = futureTypeEmployes.get(); //typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode());
		var allLieuAffect = futureLieuAffectations.get(); //lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode());
		var allEmployes = futureEmployes.get();
		
		model.addAttribute("allStatus", allStatus);
											/*.sorted(Comparator.comparing(LieuAffectation::getId))
											.sorted(Comparator.comparing(Fonction::getId))
											.toList());*/
		model.addAttribute("allTypeEmp", allTypeEmp);
		model.addAttribute("allFonctions", allFonctions);
		model.addAttribute("allLieuAffect", allLieuAffect);
		model.addAttribute("allEmployes", allEmployes);//employeService.getAllEmploye());
		model.addAttribute("employesEnSvce",allEmployes.stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);

		return "gemployecrdelist";
	}

	@GetMapping ("/employe-crde.html")
	public String addEmploye(HttpServletRequest request, Model model) throws EmployeServiceException, InterruptedException, ExecutionException{
		Future<List<FonctionDto>> futureEmpFonctions = null;
		Future<List<StatusDto>> futureEmpStatus = null;
		Future<List<TypeEmployeDto>> futureTypeEmployes = null;
		Future<List<LieuAffectationDto>> futureLieuAffectations = null;
		//Future<List<EmployeDto>> futureEmployes = null;
		//Future<Path> futurePathPhoto = null;

		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try {//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futureEmpFonctions = executor.submit(() -> fonctionService.getAllFonction());
			futureEmpStatus = executor.submit(() -> statusService.getAllStatus());
			futureTypeEmployes = executor.submit(() -> typeEmployeService.getAllTypeEmp());
			futureLieuAffectations = executor.submit(() -> lieuAffectationService.getAllLieuAffect());
			//futureEmployes = executor.submit(() -> employeService.getAllEmploye());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		
		executor.close();//awaitTermination(5, TimeUnit.SECONDS); //waits until all tasks have completed execution and the executor has terminated
		
		var allFonctions = futureEmpFonctions.get(); //fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode());
		var allStatus = futureEmpStatus.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		var allTypeEmp = futureTypeEmployes.get(); //typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode());
		var allLieuAffect = futureLieuAffectations.get(); //lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode());
		//var allEmployes = futureEmployes.get();
		
		model.addAttribute("allStatus", allStatus); //statusService.getAllStatus());
		model.addAttribute("allTypeEmp", allTypeEmp); //typeEmployeService.getAllTypeEmp());
		model.addAttribute("allFonctions", allFonctions); //fonctionService.getAllFonction());
		model.addAttribute("allLieuAffect", allLieuAffect); //lieuAffectationService.getAllLieuAffect());
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
					@RequestParam(value="sttus", required = false) String status,
					@RequestParam("empfnction") String empFonction,
					@RequestParam(value="refdecretouarreteentree", required=false) String refDecretouArreteEntree,
					@RequestParam("lieuaffectation") String lieuAffectation,
					//@RequestParam("refdecretouarretedepart") String refDecretouArreteDepart,
					@RequestParam(value="empdatedebutstatus", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate empDateDebutStatus,
					@RequestParam(value="empdatefinstatus", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate empDateFinStatus,
					@RequestParam(value="datedecretouarreteentree", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDecretouArreteEntree,
					//@RequestParam("datedecretouarretedepart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDecretouArreteDepart,
					@RequestParam(value="empphoto", required = false) MultipartFile empPhoto,
					HttpServletRequest request, Model model) throws EntityNotFoundException, IllegalAccessException, InterruptedException, ExecutionException, StockageFichiersImagesException, IOException, EmployeServiceException
					{

		final EmployeDto savedEmploye = employeService.createEmploye(empCivilite, empNom, empPren, typeEmploye, empMatricule, empEmail, empTelephone, 
										status, empFonction, refDecretouArreteEntree, lieuAffectation, empDateDebutStatus, 
										empDateDebutStatus, dateDecretouArreteEntree, empPhoto).orElseThrow(() -> new EntityNotFoundException("La création de l'employé a échouée."));
		
		/*Future<EmployeDto> futureEmploye = null;
		Future<Path> futureNbreByte = null;
		//if (Objects.nonNull(empphoto)){
		try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			if (Objects.nonNull(empphoto)){
				futureNbreByte = executor.submit(() -> {
					return stockagePhotoEmployeService.stockerFichierCrde(empPhoto, List.of(empNom, empPren, empMatricule), false);
				});
			}

			futureEmploye = executor.submit(() -> {	
				return employeService.createEmploye(empCivilite, empNom, empPren, typeEmploye, empMatricule, empEmail, empTelephone, 
									null, empFonction, refDecretouArreteEntree, lieuAffectation, empDateDebutStatus, 
									empDateDebutStatus, dateDecretouArreteEntree, empPhoto).orElseThrow(() -> new EntityNotFoundException("La création de l'employé a échouée."));
			});

		}

		logger.info("PHOTO EMPLOYÉ : {}\n; SIZE PHOTO : {}\n; CONTENT-TYPE PHOTO : {}", Objects.nonNull(empphoto)?empphoto.getOriginalFilename():null, Objects.nonNull(empphoto)?empphoto.getSize():-10, Objects.nonNull(empphoto)?empphoto.getContentType():null); //À utiliser pour la validation et la gestion d'exception	
		//}
		logger.info("PHOTO EMPLOYÉ, NOMBRE DE BITS STOCKÉS : {}", futureNbreByte!=null? futureNbreByte.get(): null);

		final EmployeDto savedEmploye = futureEmploye !=null ? futureEmploye.get() : null;*/
		
		/** LE BON - DEBUT
		logger.info("CONTROLLER EMPLACEMENT PHOTO EMPLOYÉ 1 : {}", savedEmploye.getEmpEmplacementPhoto());
		if (Objects.nonNull(savedEmploye)) {
			model.addAttribute("traitement", "Récapitulatif de la création du nouvel employé ou stagiaire");
			model.addAttribute("resultTraitement", "Création de l'employé effectuée avec succès.");
		
			if (savedEmploye.getEmpEmplacementPhoto()!=null){
				var path = stockagePhotoEmployeService.chargerFichierCrde(savedEmploye.getEmpEmplacementPhoto()); //chargerFichierCrdeAsResource(savedEmploye.getEmpEmplacementPhoto());
				var emplacementPhoto = MvcUriComponentsBuilder.fromMethodName(EmployeController.class,
							"serveFile", path.getFileName().toString()).build().toUri().toString();
				logger.info("CONTROLLER EMPLACEMENT PHOTO EMPLOYÉ INIT = {}\n  et EMPLACEMENT PHOTO EMPLOYÉ MVC-URI= {}\n DANS IF : ", savedEmploye.getEmpEmplacementPhoto(), emplacementPhoto);

				savedEmploye.setEmpEmplacementPhoto(emplacementPhoto);
			}
		}
		LE BON - FIN */
		
		//logger.info("PHOTO/SIGNATURE empPhoto.getOriginalFilename() {}\n empPhoto.getOriginalFilename.getBytes() {}\n", empPhoto.getOriginalFilename(), empPhoto.getOriginalFilename().getBytes());
		model.addAttribute("savedEmploye", savedEmploye);

		/*if (!empPhoto.isEmpty()){
			var bytes = empPhoto.getBytes();
			var inputStream = empPhoto.getInputStream();
			var inputStreamString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
			logger.info("PHOTO/SIGNATURE DANS CONTROLLER inputStreamString {}\n empPhoto.getInputStream() {}\n", inputStreamString, inputStream.toString());
		
		}
		model.addAttribute("empPhoto", emplacementPhoto);
		*/

		return "gemployecrderecap";
	}

	@GetMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws MalformedURLException, StockageFichiersImagesException {

		Resource file = stockagePhotoEmployeService.chargerFichierCrdeAsResource(filename);

		if (file == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

	@GetMapping ("/employe-crde-m.html/{empMatricule}/{typOp}")
	public String getEmployeByNumInterne(@PathVariable String empMatricule, @PathVariable String typOp, HttpServletRequest request, Model model) throws InterruptedException, ExecutionException, EmployeServiceException{
		
		var savedEmploye = employeService.getEmployeByMatricule(empMatricule)
							.orElseGet(EmployeDto::new);
		
		if (savedEmploye.getEmpEmplacementPhoto()!=null){
			var path = stockagePhotoEmployeService.chargerFichierCrde(savedEmploye.getEmpEmplacementPhoto()); //chargerFichierCrdeAsResource(savedEmploye.getEmpEmplacementPhoto());
			//var responseEntityResource = ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
			//		"attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
			
			//var emplacementPhoto = MvcUriComponentsBuilder.fromMethodCall(UriComponentsBuilder.newInstance(), responseEntityResource).build().toUri().toString();
			var emplacementPhoto = MvcUriComponentsBuilder.fromMethodName(EmployeController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString();
			logger.info("CONTROLLER EMPLACEMENT PHOTO EMPLOYÉ 2 : {}", savedEmploye.getEmpEmplacementPhoto());

			savedEmploye.setEmpEmplacementPhoto(emplacementPhoto);
		}

		model.addAttribute("savedEmploye", savedEmploye);
		model.addAttribute("allStatus", statusService.getAllStatus());
		model.addAttribute("allTypeEmp", typeEmployeService.getAllTypeEmp());
		model.addAttribute("allFonctions", fonctionService.getAllFonction());
		model.addAttribute("allLieuAffect", lieuAffectationService.getAllLieuAffect());
							
		if ("mdific".equalsIgnoreCase(typOp)){
			return "gemployecrdemaj";
		} else if ("cnsult".equalsIgnoreCase(typOp)) {
			model.addAttribute("traitement", "Fiche de l'employé ou stagiaire");
			return "gemployecrderecap";
		} else {
			return "redirect:/employes-crde.html";
		}
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