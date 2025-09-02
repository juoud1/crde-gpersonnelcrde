package gpersonnelcrde.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.ui.Model;

import gpersonnelcrde.domain.dto.MissionDto;
import gpersonnelcrde.service.EmployeService;
import gpersonnelcrde.service.MissionService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MissionController {
	private final MissionService missionService;
	private final EmployeService employeService;

	public MissionController(MissionService missionService, EmployeService employeService) {
		this.missionService = missionService;
		this.employeService = employeService;
	}
    
	@GetMapping ("/missions-emp-crde.html")
	public String getGestMissions(HttpServletRequest request, Model model){
	    model.addAttribute("allMissions", missionService.getAllMissions());
		model.addAttribute("employesEnSvce", employeService.getAllEmploye().stream()
			.filter(emp -> !"AUT".equalsIgnoreCase(emp.getStatus()))
			.toList()
		);
		request.getSession().setAttribute("modelMission", model);
	    return "gmissioncrde";
	}

	@PostMapping ("/mission-emp-crde.html")
	public String addMission(@RequestParam("missempmatricule") String MissEmpMatricule, @RequestParam("naturedeplacement") String natureDeplacement, 
							@RequestParam("cadremission") String cadreMission, @RequestParam("datedepartmiss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepartMiss, 
							@RequestParam("dateretourmiss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetourMiss, 
							@RequestParam("destville") String destVille, @RequestParam("destpays") String destPays,
							@RequestParam("motifmission") String motifMission, @RequestParam("infosupplmission") String infoSupplmission, HttpServletRequest request, Model model) {
		
		var savedMission = missionService.saveMissionEmploye(MissEmpMatricule, natureDeplacement, cadreMission, dateDepartMiss, dateRetourMiss, destVille, destPays, motifMission, infoSupplmission);
		//model = (Model) request.getSession().getAttribute("modelMission");
		model.addAttribute("savedMission", savedMission);
		//request.getSession().setAttribute("modelMission", model);

		return "gmissioncrdeRecap";
	}

	@GetMapping ("/mission-emp-crde-m.html/{numOrderMission}")
	public String getMissionByNumOrdr(@PathVariable String numOrderMission, HttpServletRequest request, Model model){
	    var savedMission = missionService.getMissionByNumOrdreMission(numOrderMission)
								.orElseGet(MissionDto::new);
		model.addAttribute("savedMission", savedMission);

		return "gmissioncrdeMaj";
	}

	@DeleteMapping("/mission-emp-crde-m.html/{numOrderMission}")
	public String deleteMissionByNumOrdr(@PathVariable String numOrdreMission, HttpServletRequest request, Model model){
	    
		return "redirect:/missions-emp-crde.html";
	}

	@PutMapping("/mission-emp-crde-m.html/{numOrderMission}")
	public String updateMissionByNumOrdr(@PathVariable String numOrdreMission, HttpServletRequest request, Model model){
	    
		return "redirect:/missions-emp-crde.html";
	}
}
