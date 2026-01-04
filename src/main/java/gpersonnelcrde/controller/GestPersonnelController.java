package gpersonnelcrde.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gpersonnelcrde.domain.dto.AffectationDto;
import gpersonnelcrde.domain.dto.CongeDto;
import gpersonnelcrde.domain.dto.EmployeDto;
import gpersonnelcrde.domain.dto.FonctionDto;
import gpersonnelcrde.domain.dto.LieuAffectationDto;
import gpersonnelcrde.domain.dto.MissionDto;
import gpersonnelcrde.domain.dto.StatusDto;
import gpersonnelcrde.domain.dto.TypeEmployeDto;
import gpersonnelcrde.domain.dto.UtilisateurDto;
import gpersonnelcrde.exception.EmployeServiceException;
import gpersonnelcrde.exception.StockageFichiersImagesException;
import gpersonnelcrde.service.FonctionService;
import gpersonnelcrde.service.LieuAffectationService;
import gpersonnelcrde.service.StatusService;
import gpersonnelcrde.service.TypeEmployeService;
import gpersonnelcrde.service.UtilisateurService;


@Controller
//@RequestMapping("/crde/gpsnel" )
public class GestPersonnelController {
	private static final Logger logger = LoggerFactory.getLogger(GestPersonnelController.class);
	
	private final StatusService statusService;
	private final TypeEmployeService typeEmployeService;
	private final FonctionService fonctionRepository;
	private final LieuAffectationService lieuAffectationService;
	private final UtilisateurService utilisateurService;
	//private final EmployeService employeService; 
	//private final MissionService missionService;
	//private final AffectationService affectationService;
	//private final CongeService congeService;

	public GestPersonnelController(StatusService statusService, TypeEmployeService typeEmployeService,
			FonctionService fonctionRepository, LieuAffectationService lieuAffectationService, 
			UtilisateurService utilisateurService) {//, EmployeService employeService, MissionService missionService, 
			//AffectationService affectationService, CongeService congeService) {
		this.statusService = statusService;
		this.typeEmployeService = typeEmployeService;
		this.fonctionRepository = fonctionRepository;
		this.lieuAffectationService = lieuAffectationService;
		this.utilisateurService = utilisateurService;
		/*this.employeService = employeService;
		this.missionService = missionService;
		this.affectationService = affectationService;
		this.congeService = congeService;*/
		logger.info("composant-de-présentaion d'accès à la page d'accueil de CRDR initialisé avec succès!".toUpperCase());
	}

	@GetMapping("/accueil.html")
	public String displayGestPersonnel(Model model) throws StockageFichiersImagesException, EmployeServiceException, InterruptedException, ExecutionException {
		
		Future<List<FonctionDto>> futureEmpFonctions = null;
		Future<List<StatusDto>> futureEmpStatus = null;
		Future<List<TypeEmployeDto>> futureTypeEmployes = null;
		Future<List<LieuAffectationDto>> futureLieuAffectations = null;
		Future<List<UtilisateurDto>> futureUtilisateurs = null;
		//Future<Path> futurePathPhoto = null;

		var executor = Executors.newVirtualThreadPerTaskExecutor();
		try {//(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
			futureEmpFonctions = executor.submit(() -> fonctionRepository.getAllFonction());
			futureEmpStatus = executor.submit(() -> statusService.getAllStatus());
			futureTypeEmployes = executor.submit(() -> typeEmployeService.getAllTypeEmp());
			futureLieuAffectations = executor.submit(() -> lieuAffectationService.getAllLieuAffect());
			futureUtilisateurs = executor.submit(() -> utilisateurService.getAllUtilisateur());
		} catch (Exception  e) {
			throw new EmployeServiceException("Un ou plusieurs problèmes surgissent durant la récupération des données de base pour le mappage employé/Dto; " + e.getMessage());
		}
		
		executor.close();//awaitTermination(5, TimeUnit.SECONDS); //waits until all tasks have completed execution and the executor has terminated
		
		var allFonctions = futureEmpFonctions.get(); //fonctionRepository.findByFonctionCode(employe.getEmpFonction().getFonctionCode());
		var allStatus = futureEmpStatus.get(); //statusRepository.findByStatusCode(employe.getEmpStatus().getStatusCode());
		var allTypeEmp = futureTypeEmployes.get(); //typeEmployeRepository.findByTypeEmpCode(employe.getTypeEmploye().getTypeEmpCode());
		var allLieuAffect = futureLieuAffectations.get(); //lieuAffectationRepository.findByLieuAffectCode(employe.getEmpLieuAffectation().getLieuAffectCode());
		var allUtilisateurs = futureUtilisateurs.get();

		model.addAttribute("allStatus", allStatus); //statusService.getAllStatus());
		model.addAttribute("allTypeEmp", allTypeEmp); // typeEmployeService.getAllTypeEmp());
		model.addAttribute("allFonction", allFonctions); //fonctionRepository.getAllFonction());
		model.addAttribute("allLieuAffect", allLieuAffect);  //lieuAffectationService.getAllLieuAffect());
		model.addAttribute("allUtilisateurs", allUtilisateurs); //utilisateurService.getAllUtilisateur());
		//model.addAttribute("allEmployes", allEmployes); //employeService.getAllEmploye());
		/*model.addAttribute("employesEnSvce", allEmployes.stream() //employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);*/
		//model.addAttribute("allMissions", allMissions); //missionService.getAllMissions());
		//model.addAttribute("allAffectations", allAffectations); //affectationService.getAllAffectation());
		//model.addAttribute("allConges", allConges); //congeService.getAllConges());
		logger.info(allStatus.toString());
		logger.info(allTypeEmp.toString());
		logger.info(allFonctions.toString());
		logger.info(allLieuAffect.toString());
		logger.info(allUtilisateurs.toString());

		return "index";
	}	
}