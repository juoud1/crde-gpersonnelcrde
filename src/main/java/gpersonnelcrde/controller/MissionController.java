package gpersonnelcrde.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;
import gpersonnelcrde.service.MissionService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MissionController {
	private final MissionService missionService;

	public MissionController(MissionService missionService) {
		this.missionService = missionService;
	}

	@PostMapping ("/mission-emp-crde.html")
	public String addMission(@RequestParam("missempmatricule") String MissEmpMatricule, @RequestParam("naturedeplacement") String natureDeplacement, 
							@RequestParam("cadremission") String cadreMission, @RequestParam("precisioncadremiss") String precisionCadreMiss,
							@RequestParam("datedepartmiss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepartMiss, 
							@RequestParam("dateretourmiss") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetourMiss, 
							@RequestParam("destville") String destVille, @RequestParam("destpays") String destPays,
							@RequestParam("motifmission") String motifMission, @RequestParam("infosupplmission") String infoSupplmission, HttpServletRequest request, Model model) {
		var savedMission = missionService.saveMissionEmploye(MissEmpMatricule, natureDeplacement, cadreMission, precisionCadreMiss, dateDepartMiss, dateRetourMiss, destVille, destPays, motifMission, infoSupplmission);
		model.addAttribute("savedMission", savedMission);
		model.addAttribute("allMissions", missionService.getAllMissions());

		return "redirect:accueil.html";
	}
}
